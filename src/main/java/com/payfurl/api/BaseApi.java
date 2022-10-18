package com.payfurl.api;

import com.payfurl.Configuration;
import com.payfurl.api.support.ApiException;
import com.payfurl.auth.AuthHandler;
import com.payfurl.auth.AuthType;
import com.payfurl.http.client.HttpClient;
import com.payfurl.http.client.support.Headers;
import com.payfurl.http.client.support.request.HttpRequest;
import com.payfurl.http.client.support.response.HttpResponse;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

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
        AuthHandler authHandler = authHandlers.get(AuthType.SECRET_KEY);
        authHandler.apply(request);
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
}
