package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.auth.SecretKeyAuthHandler;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.http.client.support.Headers;
import com.payfurl.payfurlsdk.http.client.support.request.HttpBodyRequest;
import com.payfurl.payfurlsdk.http.client.support.request.HttpMethod;
import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;
import com.payfurl.payfurlsdk.http.client.support.response.HttpResponse;
import com.payfurl.payfurlsdk.http.client.support.response.HttpStringResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class ApiTestSupport {
    static final String BASE_URI = "https://api.test.payfurl.com";

    private ApiTestSupport() {
    }

    static Map<AuthType, AuthHandler> authHandlers() {
        return Collections.singletonMap(AuthType.SECRET_KEY, new SecretKeyAuthHandler("secret_key_test"));
    }

    static Configuration testConfiguration() {
        return new TestConfiguration();
    }

    static String resolvedUrl(HttpRequest request) {
        StringBuilder urlBuilder = new StringBuilder(request.getQueryUrl());
        ApiUtils.appendUrlWithQueryParameters(urlBuilder, request.getQueryParameters());
        return ApiUtils.cleanUrl(urlBuilder);
    }

    static String bodyOf(HttpRequest request) {
        return ((HttpBodyRequest) request).getBody().toString();
    }

    static final class FakeHttpClient implements HttpClient {
        private final String responseBody;
        private HttpRequest lastRequest;

        FakeHttpClient(String responseBody) {
            this.responseBody = responseBody;
        }

        HttpRequest getLastRequest() {
            return lastRequest;
        }

        @Override
        public HttpResponse execute(HttpRequest httpRequest) throws IOException {
            this.lastRequest = httpRequest;
            byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
            return new HttpStringResponse(200, new Headers(), new ByteArrayInputStream(bytes), responseBody);
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

    private static final class TestConfiguration implements Configuration {
        @Override
        public Environment getEnvironment() {
            return Environment.SANDBOX;
        }

        @Override
        public String getPayFurlVersion() {
            return "test-version";
        }

        @Override
        public Headers getAdditionalHeaders() {
            return new Headers();
        }

        @Override
        public String getUserAgentDetail() {
            return "";
        }

        @Override
        public long getTimeout() {
            return 60_000;
        }

        @Override
        public String getSecretKey() {
            return "secret_key_test";
        }

        @Override
        public String getBaseUri() {
            return BASE_URI;
        }
    }
}
