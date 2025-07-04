package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.PaymentLink.CreatePaymentLink;
import com.payfurl.payfurlsdk.models.PaymentLink.PaymentLinkData;
import com.payfurl.payfurlsdk.models.PaymentLink.SearchPaymentLink;
import com.payfurl.payfurlsdk.models.PaymentLink.SearchPaymentLinkResult;

import java.util.HashMap;
import java.util.Map;

public class PaymentLinkApi extends BaseApi {
    private final String paymentLinkApiBaseEndpoint;

    public PaymentLinkApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        paymentLinkApiBaseEndpoint = String.format("%s/%s", baseUri, "payment_link");
    }

    /**
     * Create a new payment link
     *
     * @param paymentLinkData
     * @return
     * @throws ApiException
     */
    public PaymentLinkData create(CreatePaymentLink paymentLinkData) throws ApiException {
        return executePostRequestWith(paymentLinkApiBaseEndpoint, paymentLinkData, PaymentLinkData.class);
    }

    /**
     * Get Payment Link by id
     *
     * @param paymentLinkId
     * @return
     * @throws ApiException
     */
    public PaymentLinkData single(String paymentLinkId) throws ApiException {
        String urlPath = String.format("%s/%s", paymentLinkApiBaseEndpoint, paymentLinkId);
        return executeGetRequestWith(urlPath, null, PaymentLinkData.class);
    }

    /**
     * Search for payment links
     *
     * @param searchData
     * @return
     * @throws ApiException
     */
    public SearchPaymentLinkResult search(SearchPaymentLink searchData) throws ApiException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("Skip", searchData.getSkip());
        queryParams.put("Limit", searchData.getLimit());
        queryParams.put("AddedAfter", searchData.getAddedAfter());
        queryParams.put("AddedBefore", searchData.getAddedBefore());

        return executeGetRequestWith(paymentLinkApiBaseEndpoint, queryParams, SearchPaymentLinkResult.class);
    }
}
