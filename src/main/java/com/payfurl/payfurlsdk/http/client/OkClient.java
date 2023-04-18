package com.payfurl.payfurlsdk.http.client;

import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.http.client.config.HttpClientConfiguration;
import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.http.client.support.Headers;
import com.payfurl.payfurlsdk.http.client.support.request.HttpBodyRequest;
import com.payfurl.payfurlsdk.http.client.support.request.HttpMethod;
import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;
import com.payfurl.payfurlsdk.http.client.support.response.HttpResponse;
import com.payfurl.payfurlsdk.http.client.support.response.HttpStringResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OkClient implements HttpClient {
    private static final Object SIMPLE_SYNC_OBJECT = new Object();
    private static volatile OkHttpClient defaultOkHttpClient;
    private final Environment environment;
    private OkHttpClient client;

    public OkClient(HttpClientConfiguration httpClientConfiguration) {
        this.environment = httpClientConfiguration.getEnvironment();

        OkHttpClient okClientInstance = getDefaultOkHttpClient();
        if (okClientInstance != null) {
            this.client = getConfiguredHttpClient(okClientInstance, httpClientConfiguration);
        }
    }

    private static TrustManager[] getTrustAllCertsManager() {
        return new TrustManager[]{
                new X509TrustManager() {

                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

    private static SSLSocketFactory getSslSocketFactory(TrustManager[] trustAllCertsManager) {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCertsManager, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    public static void shutdown() {
        if (defaultOkHttpClient != null) {
            defaultOkHttpClient.dispatcher().executorService().shutdown();
            defaultOkHttpClient.connectionPool().evictAll();
        }
    }

    protected static HttpResponse convertResponse(Response response) throws IOException {
        if (response == null) {
            return null;
        }

        ResponseBody responseBody = response.body();
        Headers headers = new Headers(response.headers().toMultimap());

        String responseString = Objects.requireNonNull(responseBody).string();

        HttpResponse httpResponse;
        try {
            InputStream responseStream = new ByteArrayInputStream(responseString.getBytes());
            httpResponse = new HttpStringResponse(response.code(), headers, responseStream, responseString);
        } finally {
            responseBody.close();
            response.close();
        }

        return httpResponse;
    }

    private static Request convertRequest(HttpRequest httpRequest) {
        okhttp3.Headers.Builder requestHeaders = new okhttp3.Headers.Builder();
        if (httpRequest.getHeaders() != null) {
            requestHeaders = createRequestHeaders(httpRequest.getHeaders());
        }

        StringBuilder urlBuilder = new StringBuilder(httpRequest.getQueryUrl());

        ApiUtils.appendUrlWithQueryParameters(urlBuilder, httpRequest.getQueryParameters());

        String url = ApiUtils.cleanUrl(urlBuilder);

        RequestBody requestBody = createRequestBodyFrom(httpRequest);

        return new Request.Builder()
                .method(httpRequest.getHttpMethod().toString(), requestBody)
                .headers(requestHeaders.build())
                .url(url)
                .build();
    }

    private static RequestBody createRequestBodyFrom(HttpRequest httpRequest) {
        HttpMethod httpMethod = httpRequest.getHttpMethod();

        if (httpMethod == HttpMethod.GET || !(httpRequest instanceof HttpBodyRequest)) {
            return null;
        }

        Object body = ((HttpBodyRequest) httpRequest).getBody();
        String contentType = httpRequest.getHeaders().value("content-type");

        return RequestBody.create(((String) body).getBytes(),
                okhttp3.MediaType.parse(contentType));
    }

    private static okhttp3.Headers.Builder createRequestHeaders(Headers headers) {
        okhttp3.Headers.Builder requestHeaders = new okhttp3.Headers.Builder();
        headers.asMultimap()
                .forEach((key, value) -> value
                        .forEach(innerListValue -> requestHeaders.add(key, innerListValue)));
        return requestHeaders;
    }

    private OkHttpClient getConfiguredHttpClient(OkHttpClient okHttpClient, HttpClientConfiguration httpClientConfiguration) {
        return okHttpClient.newBuilder()
                .readTimeout(httpClientConfiguration.getTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(httpClientConfiguration.getTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(httpClientConfiguration.getTimeout(), TimeUnit.MILLISECONDS)
                .callTimeout(httpClientConfiguration.getTimeout(), TimeUnit.MILLISECONDS)
                .build();
    }

    private OkHttpClient getDefaultOkHttpClient() {
        if (defaultOkHttpClient != null) {
            return defaultOkHttpClient;
        }

        synchronized (SIMPLE_SYNC_OBJECT) {
            if (defaultOkHttpClient == null) {
                defaultOkHttpClient = initializeOkClient();
            }
        }

        return defaultOkHttpClient;
    }

    private OkHttpClient initializeOkClient() {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .followSslRedirects(true);

        if (Environment.LOCAL == environment) {
            TrustManager[] trustAllCertsManager = getTrustAllCertsManager();
            SSLSocketFactory sslSocketFactory = getSslSocketFactory(trustAllCertsManager);

            okClientBuilder
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCertsManager[0])
                    .hostnameVerifier((hostname, session) -> true);
        }

        return okClientBuilder.build();
    }

    @Override
    public HttpResponse execute(HttpRequest httpRequest) throws IOException {
        Request okHttpRequest = convertRequest(httpRequest);

        try (Response okHttpResponse = client.newCall(okHttpRequest).execute()) {
            return convertResponse(okHttpResponse);
        }
    }

    @Override
    public HttpRequest prepareGetRequest(StringBuilder queryUrlBuilder,
                                         Headers headers,
                                         Map<String, Object> queryParams,
                                         List<SimpleEntry<String, Object>> parameters) {
        return new HttpRequest(HttpMethod.GET, queryUrlBuilder, headers, queryParams, parameters);
    }

    @Override
    public HttpRequest preparePostRequest(StringBuilder queryUrlBuilder,
                                          Headers headers,
                                          Map<String, Object> queryParams,
                                          List<SimpleEntry<String, Object>> parameters) {
        return new HttpRequest(HttpMethod.POST, queryUrlBuilder, headers, queryParams, parameters);
    }

    @Override
    public HttpBodyRequest preparePostBodyRequest(StringBuilder queryUrlBuilder,
                                                  Headers headers,
                                                  Map<String, Object> queryParams,
                                                  Object body) {
        return new HttpBodyRequest(HttpMethod.POST, queryUrlBuilder, headers, queryParams, body);
    }

    @Override
    public HttpBodyRequest preparePutBodyRequest(StringBuilder queryUrlBuilder,
                                                 Headers headers,
                                                 Map<String, Object> queryParams,
                                                 Object body) {
        return new HttpBodyRequest(HttpMethod.PUT, queryUrlBuilder, headers, queryParams, body);
    }

    @Override
    public HttpRequest prepareDeleteRequest(StringBuilder queryUrlBuilder,
                                            Headers headers,
                                            Map<String, Object> queryParams,
                                            List<SimpleEntry<String, Object>> parameters) {
        return new HttpRequest(HttpMethod.DELETE, queryUrlBuilder, headers, queryParams, parameters);
    }
}
