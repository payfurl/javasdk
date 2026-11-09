package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
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
     * {@code GET /info/providers}: the payment methods (card, PayPal, PayTo, BNPL, ...) the account can take for this
     * amount and currency.
     *
     * @param amount   the amount, or null
     * @param currency the ISO 4217 currency, or null
     * @return the providers and payment methods available
     * @throws ApiException
     */
    public InfoProviders getProvidersInfo(BigDecimal amount, String currency) throws ApiException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("amount", amount);
        queryParams.put("currency", currency);
        return executeGetRequestWith(infoApiBaseEndpoint + "/providers", queryParams, InfoProviders.class);
    }
}

