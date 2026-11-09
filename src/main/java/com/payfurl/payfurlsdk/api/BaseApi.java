package com.payfurl.payfurlsdk.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.api.support.ErrorCode;
import com.payfurl.payfurlsdk.api.support.ErrorLink;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.http.client.support.Headers;
import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;
import com.payfurl.payfurlsdk.http.client.support.response.HttpResponse;
import com.payfurl.payfurlsdk.http.client.support.response.HttpStringResponse;
import com.payfurl.payfurlsdk.models.ApiError;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Map;
import java.util.function.Supplier;

public class BaseApi {
    private static final String SDK_VERSION_KEY = "{sdk-version}";
    private static final String ENGINE_KEY = "{engine}";
    private static final String ENGINE_VERSION_KEY = "{engine-version}";
    private static final String OS_INFO_KEY = "{os-info}";

    private static final ErrorCode UNKNOWN_ERROR = ErrorCode.UnknownError;
    private static final String USER_AGENT = String.format("PayFURL-Java-SDK-v.%s %s/%s (%s)",
            SDK_VERSION_KEY, ENGINE_KEY, ENGINE_VERSION_KEY, OS_INFO_KEY);
    protected final Configuration config;
    private final HttpClient httpClient;
    protected String internalUserAgent;
    protected Map<AuthType, AuthHandler> authHandlers;

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

    protected void validateResponse(HttpResponse response) throws ApiException {
        int responseCode = response.getStatusCode();

        if (responseCode == 408 || responseCode == 504) {
            throw new ApiException(ApiError.buildTimeoutError());
        }

        if (!Range.between(200, 208).contains(responseCode)) {
            String responseBody = ((HttpStringResponse) response).getBody();
            ApiError error;
            try {
                error = ApiUtils.deserialize(responseBody, ApiError.class);
                error.setHttpCode(responseCode);
            } catch (Exception e) {
                throw new ApiException(new ApiError.Builder()
                        .withMessage(responseBody)
                        // No API error body (a load balancer's 502, say): worth retrying only if the server failed.
                        .withIsRetryable(responseCode >= 500 || responseCode == 429)
                        .withCode(UNKNOWN_ERROR)
                        .withType(ErrorLink.buildWith(1))
                        .withHttpCode(responseCode)
                        .build());
            }
            throw new ApiException(error);
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

    protected <T, R> R executePostRequestWith(String urlPath, T chargeApiRequest, Class<R> returnType) throws ApiException {
        try {
            HttpRequest request = createApiPostRequest(urlPath, chargeApiRequest);

            HttpResponse response = getClientInstance().execute(request);

            return getDataFrom(response, returnType);
        } catch (InterruptedIOException interruptedIOException) {
            throw new ApiException(ApiError.buildTimeoutError());
        } catch (IOException exception) {
            throw new ApiException(exception);
        }
    }

    protected <T, R> R executePutRequestWith(String urlPath, T chargeApiRequest, Class<R> returnType) throws ApiException {
        try {
            HttpRequest request = createApiPutRequest(urlPath, chargeApiRequest);

            HttpResponse response = getClientInstance().execute(request);

            return getDataFrom(response, returnType);
        } catch (InterruptedIOException interruptedIOException) {
            throw new ApiException(ApiError.buildTimeoutError());
        } catch (IOException exception) {
            throw new ApiException(exception);
        }
    }

    protected <T> T executeGetRequestWith(String urlPath, Map<String, Object> queryParams, Class<T> returnType) throws ApiException {
        return executeGetWithRetries(() -> createApiGetRequest(urlPath, queryParams), returnType);
    }

    protected <T> T executeGetRequestWith(String urlPath, Map<String, Object> queryParams, Class<T> returnType, Headers headers) throws ApiException {
        return executeGetWithRetries(() -> createApiGetRequest(urlPath, queryParams, headers), returnType);
    }

    /**
     * A GET, retried with a doubling delay after a connection error, a timeout or an error the API marks retryable,
     * up to {@link Configuration#getMaxRetries()} times. Only GETs: the API has no idempotency keys, so a POST, PUT
     * or DELETE (a refund, a void) is never sent twice.
     */
    private <T> T executeGetWithRetries(Supplier<HttpRequest> request, Class<T> returnType) throws ApiException {
        int maxRetries = Math.max(0, config.getMaxRetries());
        for (int attempt = 0; ; attempt++) {
            try {
                HttpResponse response = getClientInstance().execute(request.get());
                return getDataFrom(response, returnType);
            } catch (ApiException apiException) {
                if (attempt >= maxRetries || !Boolean.TRUE.equals(apiException.isRetryable())) {
                    throw apiException;
                }
            } catch (InterruptedIOException interruptedIOException) {
                if (attempt >= maxRetries) {
                    throw new ApiException(ApiError.buildTimeoutError());
                }
            } catch (IOException exception) {
                if (attempt >= maxRetries) {
                    throw new ApiException(exception);
                }
            }
            sleepBeforeRetry(attempt);
        }
    }

    private void sleepBeforeRetry(int attempt) {
        long delay = config.getRetryDelay() << Math.min(attempt, 10);
        if (delay <= 0) {
            return;
        }
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(new InterruptedIOException("Interrupted while waiting to retry"));
        }
    }

    protected <T> T executeDeleteRequestWith(String urlPath, Map<String, Object> queryParams, Class<T> returnType) throws ApiException {
        try {
            HttpRequest request = createApiDeleteRequest(urlPath, queryParams);

            HttpResponse response = getClientInstance().execute(request);

            return getDataFrom(response, returnType);
        } catch (InterruptedIOException interruptedIOException) {
            throw new ApiException(ApiError.buildTimeoutError());
        } catch (IOException exception) {
            throw new ApiException(exception);
        }
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

    private HttpRequest createApiGetRequest(String urlPath, Map<String, Object> queryParams, Headers extraHeaders) {
        StringBuilder queryBuilder = new StringBuilder(urlPath);

        Headers headers = getPopulatedHeaders();
        headers.addAll(extraHeaders);

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
