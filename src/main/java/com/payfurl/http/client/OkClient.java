package com.payfurl.http.client;

import com.payfurl.http.client.config.HttpClientConfiguration;
import com.payfurl.http.client.support.ApiUtils;
import com.payfurl.http.client.support.Headers;
import com.payfurl.http.client.support.request.HttpBodyRequest;
import com.payfurl.http.client.support.request.HttpMethod;
import com.payfurl.http.client.support.request.HttpRequest;
import com.payfurl.http.client.support.response.HttpResponse;
import com.payfurl.http.client.support.response.HttpStringResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OkClient implements HttpClient {
    private static final Object SIMPLE_SYNC_OBJECT = new Object();
    private static final int DEFAULT_CALL_TIMEOUT_SECONDS = 60;
    private static volatile OkHttpClient defaultOkHttpClient;
    private OkHttpClient client;

    public OkClient(HttpClientConfiguration httpClientConfiguration) {
        OkHttpClient okClientInstance = getDefaultOkHttpClient();
        if (okClientInstance != null) {
            this.client = okClientInstance;
            configureHttpClient(okClientInstance, httpClientConfiguration);
        }
    }

    private void configureHttpClient(OkHttpClient okHttpClient, HttpClientConfiguration httpClientConfiguration) {
        okHttpClient.newBuilder()
                .readTimeout(httpClientConfiguration.getTimeout(), TimeUnit.SECONDS)
                .writeTimeout(httpClientConfiguration.getTimeout(), TimeUnit.SECONDS)
                .connectTimeout(httpClientConfiguration.getTimeout(), TimeUnit.SECONDS)
                .callTimeout(httpClientConfiguration.getTimeout(), TimeUnit.SECONDS)
                .build();
    }

    private OkHttpClient getDefaultOkHttpClient() {
        if (defaultOkHttpClient != null) {
            return defaultOkHttpClient;
        }

        synchronized (SIMPLE_SYNC_OBJECT) {
            if (defaultOkHttpClient == null) {
                defaultOkHttpClient = new OkHttpClient.Builder()
                        .retryOnConnectionFailure(false)
                        .callTimeout(DEFAULT_CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                        .followSslRedirects(true)
                        .build();
            }
        }

        return defaultOkHttpClient;
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

    @Override
    public HttpResponse execute(HttpRequest httpRequest) throws IOException {
        Request okHttpRequest = convertRequest(httpRequest);

        try (Response okHttpResponse = client.newCall(okHttpRequest).execute()) {
            return convertResponse(okHttpResponse);
        }
    }

    private static Request convertRequest(HttpRequest httpRequest) {
        okhttp3.Headers.Builder requestHeaders = new okhttp3.Headers.Builder();
        if (httpRequest.getHeaders() != null) {
            requestHeaders = createRequestHeaders(httpRequest.getHeaders());
        }

        StringBuilder urlBuilder = new StringBuilder(httpRequest.getQueryUrl());
        String url = ApiUtils.cleanUrl(urlBuilder);

        String contentType = httpRequest.getHeaders().value("content-type");

        Object body = ((HttpBodyRequest) httpRequest).getBody();
        RequestBody requestBody = RequestBody.create(((String) body).getBytes(),
                okhttp3.MediaType.parse(contentType));

        return new Request.Builder()
                .method(httpRequest.getHttpMethod().toString(), requestBody)
                .headers(requestHeaders.build())
                .url(url)
                .build();

    }

    private static okhttp3.Headers.Builder createRequestHeaders(Headers headers) {
        okhttp3.Headers.Builder requestHeaders = new okhttp3.Headers.Builder();
        headers.asMultimap()
                .forEach((key, value) -> value
                        .forEach(innerListValue -> requestHeaders.add(key, innerListValue)));
        return requestHeaders;
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
    public HttpRequest prepareDeleteRequest(StringBuilder queryUrlBuilder,
                                            Headers headers,
                                            Map<String, Object> queryParams,
                                            List<SimpleEntry<String, Object>> parameters) {
        return new HttpRequest(HttpMethod.DELETE, queryUrlBuilder, headers, queryParams, parameters);
    }
}
