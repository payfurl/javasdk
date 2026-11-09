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
import okhttp3.ConnectionPool;
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
import java.nio.charset.StandardCharsets;
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
    /**
     * The one OkHttp client every non-{@link Environment#LOCAL} instance derives from (sharing its connection pool),
     * with the JDK's certificate and hostname checks. Nothing changes its TLS settings.
     */
    private static final OkHttpClient SHARED_CLIENT = new OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .followSslRedirects(true)
            .build();

    private final Environment environment;
    private final OkHttpClient client;
    /** True when this instance built its own connection pool (LOCAL), so {@link #close()} may release it. */
    private final boolean ownsClient;

    public OkClient(HttpClientConfiguration httpClientConfiguration) {
        this.environment = httpClientConfiguration.getEnvironment();

        OkHttpClient base = httpClientConfiguration.getOkHttpClient();
        OkHttpClient.Builder builder = (base != null ? base : SHARED_CLIENT).newBuilder();

        // Trust-all exists only on a LOCAL client, on a builder of its own: it never reaches SHARED_CLIENT or a
        // client the caller passed in.
        if (Environment.LOCAL == environment) {
            TrustManager[] trustAllCertsManager = getTrustAllCertsManager();
            builder.sslSocketFactory(getSslSocketFactory(trustAllCertsManager), (X509TrustManager) trustAllCertsManager[0])
                    .hostnameVerifier((hostname, session) -> true);
            if (base == null) {
                builder.connectionPool(new ConnectionPool());
            }
        }
        this.ownsClient = Environment.LOCAL == environment && base == null;
        this.client = getConfiguredHttpClient(builder, httpClientConfiguration);
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
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertsManager, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    /** The OkHttp client this instance sends requests with. */
    public OkHttpClient getOkHttpClient() {
        return client;
    }

    /**
     * Releases what this instance owns: the connection pool of a LOCAL client. A client derived from the shared one
     * or from a caller's OkHttpClient owns nothing, so other clients are never affected.
     */
    public void close() {
        if (ownsClient) {
            client.connectionPool().evictAll();
            client.dispatcher().executorService().shutdown();
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
            InputStream responseStream = new ByteArrayInputStream(responseString.getBytes(StandardCharsets.UTF_8));
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

        return RequestBody.create(((String) body).getBytes(StandardCharsets.UTF_8),
                okhttp3.MediaType.parse(contentType));
    }

    private static okhttp3.Headers.Builder createRequestHeaders(Headers headers) {
        okhttp3.Headers.Builder requestHeaders = new okhttp3.Headers.Builder();
        headers.asMultimap()
                .forEach((key, value) -> value
                        .forEach(innerListValue -> requestHeaders.add(key, innerListValue)));
        return requestHeaders;
    }

    private static OkHttpClient getConfiguredHttpClient(OkHttpClient.Builder builder, HttpClientConfiguration httpClientConfiguration) {
        return builder
                .readTimeout(httpClientConfiguration.getTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(httpClientConfiguration.getTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(httpClientConfiguration.getTimeout(), TimeUnit.MILLISECONDS)
                .callTimeout(httpClientConfiguration.getTimeout(), TimeUnit.MILLISECONDS)
                .build();
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
