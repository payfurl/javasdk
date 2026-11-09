package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.ApplicationVersion;
import com.payfurl.payfurlsdk.models.RootStatus;

import java.util.Map;

/** The API's root: whether it is up, and which version it runs. */
public class RootApi extends BaseApi {
    private final String baseUri;

    public RootApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);
        baseUri = config.getBaseUri();
    }

    /** {@code GET /}: {@code status} is {@code "ok"} when the API is up. */
    public RootStatus getStatus() throws ApiException {
        return executeGetRequestWith(baseUri + "/", null, RootStatus.class);
    }

    /** {@code GET /version}: the API's version, release date and region. */
    public ApplicationVersion getVersion() throws ApiException {
        return executeGetRequestWith(baseUri + "/version", null, ApplicationVersion.class);
    }
}
