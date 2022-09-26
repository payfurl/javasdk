package com.payfurl.client;

import com.payfurl.client.config.HttpClientConfiguration;
import com.payfurl.client.support.HeadersData;
import com.payfurl.client.support.request.HttpRequest;
import com.payfurl.client.support.response.HttpResponse;
import com.payfurl.client.support.response.HttpStringResponse;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
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
        configureHttpClient(okClientInstance, httpClientConfiguration);
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

    protected static HttpResponse convertResponse(Response response, boolean hasBinaryResponse) throws IOException {
        if (response == null) {
            return null;
        }

        ResponseBody responseBody = response.body();
        HeadersData headersData = new HeadersData(response.headers().toMultimap());

        if (hasBinaryResponse) {
            InputStream responseStream = Objects.requireNonNull(responseBody).byteStream();
            return new HttpResponse(response.code(), headersData, responseStream);
        }

        String responseString = Objects.requireNonNull(responseBody).string();

        HttpResponse httpResponse;
        try {
            InputStream responseStream = new ByteArrayInputStream(responseString.getBytes());
            httpResponse = new HttpStringResponse(response.code(), headersData, responseStream, responseString);
        } finally {
            responseBody.close();
            response.close();
        }

        return httpResponse;
    }

    @Override
    public HttpRequest get(StringBuilder queryUrlBuilder, HeadersData headersData, Map<String, Object> queryParams, List<AbstractMap.SimpleEntry<String, Object>> parameters) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HttpRequest post(StringBuilder queryUrlBuilder, HeadersData headersData, Map<String, Object> queryParams, List<AbstractMap.SimpleEntry<String, Object>> parameters) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HttpRequest put(StringBuilder queryUrlBuilder, HeadersData headersData, Map<String, Object> queryParams, List<AbstractMap.SimpleEntry<String, Object>> parameters) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HttpRequest delete(StringBuilder queryUrlBuilder, HeadersData headersData, Map<String, Object> queryParams, List<AbstractMap.SimpleEntry<String, Object>> parameters) {
        throw new UnsupportedOperationException();
    }
}
