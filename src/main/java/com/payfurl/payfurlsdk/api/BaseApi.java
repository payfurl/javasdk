package com.payfurl.payfurlsdk.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.http.client.support.Headers;
import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;
import com.payfurl.payfurlsdk.http.client.support.response.HttpResponse;
import com.payfurl.payfurlsdk.http.client.support.response.HttpStringResponse;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

public class BaseApi {
    private static final String SDK_VERSION_KEY = "{sdk-version}";
    private static final String ENGINE_KEY = "{engine}";
    private static final String ENGINE_VERSION_KEY = "{engine-version}";
    private static final String OS_INFO_KEY = "{os-info}";
    private static final String USER_AGENT = String.format("PayFURL-Java-SDK-v.%s %s/%s (%s)",
            SDK_VERSION_KEY, ENGINE_KEY, ENGINE_VERSION_KEY, OS_INFO_KEY);
    protected String internalUserAgent;

    protected final Configuration config;

    protected Map<AuthType, AuthHandler> authHandlers;

    private final HttpClient httpClient;

    protected BaseApi(Configuration config, HttpClient httpClient,
                      Map<AuthType, AuthHandler> authHandlers) {
        this.config = config;
        this.httpClient = httpClient;
        this.authHandlers = authHandlers;

        this.updateUserAgent();
    }

    public HttpClient getClientInstance() {
        return httpClient;
    }

    protected void validateResponse(HttpResponse response) {
        int responseCode = response.getStatusCode();
        if (!Range.between(200, 208).contains(responseCode)) {
            throw new ApiException("Response status is not OK");
        }
    }

    protected Headers getPopulatedHeaders() {
        Headers headers = new Headers();
        headers.add("content-type", "application/json; charset=utf-8");
        headers.add("user-agent", internalUserAgent);
        headers.addAll(config.getAdditionalHeaders());
        return headers;
    }

    protected void addAuthDataTo(HttpRequest request) {
        AuthHandler secretKeyHandler = authHandlers.get(AuthType.SECRET_KEY);
        secretKeyHandler.apply(request);
    }

    private void updateUserAgent() {
        String engineVersion = System.getProperty("java.runtime.version");
        String osName = String.format("%s-%s", System.getProperty("os.name"), System.getProperty("os.version"));
        internalUserAgent = USER_AGENT
                .replace(ENGINE_KEY, "JRE")
                .replace(SDK_VERSION_KEY, config.getPayFurlVersion())
                .replace(ENGINE_VERSION_KEY, StringUtils.defaultString(engineVersion))
                .replace(OS_INFO_KEY, osName);
    }

    protected <T, R> R executePostRequestWith(String urlPath, T chargeApiRequest, Class<R> returnType) throws IOException {
        HttpRequest request = createApiPostRequest(urlPath, chargeApiRequest);

        HttpResponse response = getClientInstance().execute(request);

        return getDataFrom(response, returnType);
    }

    protected <T, R> R executePutRequestWith(String urlPath, T chargeApiRequest, Class<R> returnType) throws IOException {
        HttpRequest request = createApiPutRequest(urlPath, chargeApiRequest);

        HttpResponse response = getClientInstance().execute(request);

        return getDataFrom(response, returnType);
    }

    protected <T> T executeGetRequestWith(String urlPath, Map<String, Object> queryParams, Class<T> returnType) throws IOException {
        HttpRequest request = createApiGetRequest(urlPath, queryParams);

        HttpResponse response = getClientInstance().execute(request);

        return getDataFrom(response, returnType);
    }

    protected <T> T executeDeleteRequestWith(String urlPath, Map<String, Object> queryParams, Class<T> returnType) throws IOException {
        HttpRequest request = createApiDeleteRequest(urlPath, queryParams);

        HttpResponse response = getClientInstance().execute(request);

        return getDataFrom(response, returnType);
    }

    private <T> T getDataFrom(HttpResponse response, Class<T> returnType) throws JsonProcessingException {
        validateResponse(response);

        String responseBody = ((HttpStringResponse) response).getBody();
        return ApiUtils.deserialize(responseBody, returnType);
    }

    private <T> HttpRequest createApiPostRequest(String urlPath, T chargeApiRequest) throws JsonProcessingException {
        StringBuilder queryBuilder = new StringBuilder(urlPath);

        Headers headers = getPopulatedHeaders();

        String bodyJson = ApiUtils.serialize(chargeApiRequest);
        HttpRequest request = getClientInstance().preparePostBodyRequest(queryBuilder, headers, null, bodyJson);

        addAuthDataTo(request);
        return request;
    }

    private <T> HttpRequest createApiPutRequest(String urlPath, T apiRequest) throws JsonProcessingException {
        StringBuilder queryBuilder = new StringBuilder(urlPath);

        Headers headers = getPopulatedHeaders();

        String bodyJson = ApiUtils.serialize(apiRequest);
        HttpRequest request = getClientInstance().preparePutBodyRequest(queryBuilder, headers, null, bodyJson);

        addAuthDataTo(request);
        return request;
    }

    private HttpRequest createApiGetRequest(String urlPath, Map<String, Object> queryParams) {
        StringBuilder queryBuilder = new StringBuilder(urlPath);

        Headers headers = getPopulatedHeaders();

        HttpRequest request = getClientInstance().prepareGetRequest(queryBuilder, headers, queryParams, null);

        addAuthDataTo(request);
        return request;
    }

    private HttpRequest createApiDeleteRequest(String urlPath, Map<String, Object> queryParams) {
        StringBuilder queryBuilder = new StringBuilder(urlPath);

        Headers headers = getPopulatedHeaders();

        HttpRequest request = getClientInstance().prepareDeleteRequest(queryBuilder, headers, queryParams, null);

        addAuthDataTo(request);
        return request;
    }
}
