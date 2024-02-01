package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.http.client.support.Headers;
import com.payfurl.payfurlsdk.models.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InfoApi extends BaseApi {
    private final String infoApiBaseEndpoint;

    public InfoApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        infoApiBaseEndpoint = String.format("%s/%s", baseUri, "info");
    }

    /**
     * Get base info
     *
     * @return Base info details
     * @throws ApiException
     */
    public Info getInfo() throws ApiException {
        return executeGetRequestWith(infoApiBaseEndpoint, null, Info.class);
    }

    /**
     * Get providers info
     *
     * @return Providers info details
     * @throws ApiException
     */
    public InfoProviders GetProvidersInfo(BigDecimal amount, String currency)
    {
        Map<String, Object> queryParams = new HashMap<>();
        if (amount != null)
            queryParams.put("amount", amount);
        if (currency != null)
            queryParams.put("currency", currency);

        return executeGetRequestWith(infoApiBaseEndpoint + "/providers", queryParams, InfoProviders.class);
    }

    /**
     * Get provider info
     *
     * @return Provider info details
     * @throws ApiException
     */
    public InfoProvider GetProviderInfo(String providerId)
    {
        Headers headers = new Headers();
        headers.add("sdk-version", "4.5.7");
        return executeGetRequestWith(infoApiBaseEndpoint + "/providers/" + providerId, null, InfoProvider.class, headers);
    }

    /**
     * Get provider info by given token
     *
     * @return Provider info details
     * @throws ApiException
     */
    public InfoProvider GetProviderTokenInfo(String token, BigDecimal amount, String currency)
    {
        Map<String, Object> queryParams = new HashMap<>();
        if (amount != null)
            queryParams.put("amount", amount);
        if (currency != null)
            queryParams.put("currency", currency);

        return executeGetRequestWith(infoApiBaseEndpoint + "/providers/token/" + token, queryParams, InfoProvider.class);
    }

    /**
     * Get default fallback provider for the current account
     *
     * @return Provider info details
     * @throws ApiException
     */
    public ProviderData GetDefaultFallbackProvider()
    {
        return executeGetRequestWith(infoApiBaseEndpoint + "/default_fallback_provider", null, ProviderData.class);
    }
}
