package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.Provider;
import com.payfurl.payfurlsdk.models.NewProvider;
import com.payfurl.payfurlsdk.models.UpdateProvider;

import java.util.Map;

public class ProviderApi extends BaseApi {
    private final String providerApiBaseEndpoint;

    public ProviderApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        providerApiBaseEndpoint = String.format("%s/%s", baseUri, "provider");
    }

    /**
     * Create new provider
     *
     * @param newProvider parameter for creating provider
     * @return Provider provider details
     * @throws ApiException
     */
    public Provider create(NewProvider newProvider) throws ApiException {
        return executePostRequestWith(providerApiBaseEndpoint, newProvider, Provider.class);
    }

    /**
     * Update provider
     *
     * @param updateProvider parameter for updating provider
     * @return Provider provider details
     * @throws ApiException
     */
    public Provider update(String providerId, UpdateProvider updateProvider) throws ApiException {
        return executePutRequestWith(providerApiBaseEndpoint + "/" + providerId, updateProvider, Provider.class);
    }
}
