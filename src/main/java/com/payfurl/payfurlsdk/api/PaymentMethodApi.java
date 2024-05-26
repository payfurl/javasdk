package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.*;

import java.util.HashMap;
import java.util.Map;

public class PaymentMethodApi extends BaseApi {
    private final String paymentMethodApiBaseEndpoint;

    public PaymentMethodApi(Configuration config,
                            HttpClient httpClient,
                            Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        paymentMethodApiBaseEndpoint = String.format("%s/%s", baseUri, "payment_method");
    }

    /**
     * Add a payment method using a vault
     *
     * @param newPaymentMethodVault
     * @return
     * @throws ApiException
     */
    public PaymentMethodData createPaymentMethodWithVault(NewPaymentMethodVault newPaymentMethodVault) throws ApiException {
        String urlPath = paymentMethodApiBaseEndpoint + "/vault";
        return executePostRequestWith(urlPath, newPaymentMethodVault, PaymentMethodData.class);
    }

    /**
     * Add a payment method using a card
     *
     * @param newPaymentMethodCard
     * @return
     * @throws ApiException
     */
    public PaymentMethodData createPaymentMethodWithCard(NewPaymentMethodCard newPaymentMethodCard) throws ApiException {
        String urlPath = paymentMethodApiBaseEndpoint + "/card";
        return executePostRequestWith(urlPath, newPaymentMethodCard, PaymentMethodData.class);
    }

    /**
     * Add a payment method using provider single use token
     *
     * @param newPaymentMethodProviderSingleUseToken
     * @return
     * @throws ApiException
     */
    public PaymentMethodData createPaymentMethodWithSingleUseToken(NewPaymentMethodProviderSingleUseToken newPaymentMethodProviderSingleUseToken) throws ApiException {
        String urlPath = paymentMethodApiBaseEndpoint + "/provider_single_use_token";
        return executePostRequestWith(urlPath, newPaymentMethodProviderSingleUseToken, PaymentMethodData.class);
    }

    /**
     * Add a payment method using provider multi use token
     *
     * @param newProviderToken
     * @return
     * @throws ApiException
     */
    public PaymentMethodData createPaymentMethodWithProviderToken(NewProviderToken newProviderToken) throws ApiException {
        String urlPath = paymentMethodApiBaseEndpoint + "/provider_token";
        return executePostRequestWith(urlPath, newProviderToken, PaymentMethodData.class);
    }

    /**
     * Retrieve a single payment method
     *
     * @param paymentMethodId
     * @return
     * @throws ApiException
     */
    public PaymentMethodData single(String paymentMethodId) throws ApiException {
        String urlPath = String.format("%s/%s", paymentMethodApiBaseEndpoint, paymentMethodId);
        return executeGetRequestWith(urlPath, null, PaymentMethodData.class);
    }

    /**
     * Delete payment method
     *
     * @param paymentMethodId
     * @return
     * @throws ApiException
     */
    public PaymentMethodData deletePaymentMethod(String paymentMethodId) throws ApiException {
        String urlPath = String.format("%s/%s", paymentMethodApiBaseEndpoint, paymentMethodId);
        return executeDeleteRequestWith(urlPath, null, PaymentMethodData.class);
    }

    /**
     * Search for payment methods
     *
     * @param searchData
     * @return
     * @throws ApiException
     */
    public PaymentMethodList search(PaymentMethodSearch searchData) throws ApiException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("Skip", searchData.getSkip());
        queryParams.put("Limit", searchData.getLimit());
        queryParams.put("ProviderId", searchData.getProviderId());
        queryParams.put("CustomerId", searchData.getCustomerId());
        queryParams.put("Search", searchData.getSearch());
        queryParams.put("AddedAfter", searchData.getAddedAfter());
        queryParams.put("AddedBefore", searchData.getAddedBefore());
        queryParams.put("PaymentType", searchData.getPaymentType());
        queryParams.put("SortBy", searchData.getSortBy());

        return executeGetRequestWith(paymentMethodApiBaseEndpoint, queryParams, PaymentMethodList.class);
    }
    
    /**
     * Add a payment method using bank account
     *
     * @param newPaymentMethodBankPayment
     * @return
     * @throws ApiException
     */
    public PaymentMethodData createPaymentMethodWithBankAccount(NewPaymentMethodBankPayment newPaymentMethodBankPayment) throws ApiException {
        String urlPath = paymentMethodApiBaseEndpoint + "/bank_account";
        return executePostRequestWith(urlPath, newPaymentMethodBankPayment, PaymentMethodData.class);
    }
}
