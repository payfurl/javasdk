package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenApi extends BaseApi {
    private final String tokenApiBaseEndpoint;

    public TokenApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        tokenApiBaseEndpoint = String.format("%s/%s", baseUri, "token");
    }

    /**
     * Add a token
     * @param newTokenCardRequest
     * @return
     * @throws IOException
     */
    public PaymentTokenData create(NewTokenCardRequest newTokenCardRequest) throws IOException {
        return executePostRequestWith(tokenApiBaseEndpoint + "/card", newTokenCardRequest, PaymentTokenData.class);
    }

    /**
     * Add a token with least cost
     * @param newTokenCardLeastCostRequest
     * @return
     * @throws IOException
     */
    public PaymentTokenData createLeastCost(NewTokenCardLeastCostRequest newTokenCardLeastCostRequest) throws IOException {
        return executePostRequestWith(tokenApiBaseEndpoint + "/card/least_cost", newTokenCardLeastCostRequest, PaymentTokenData.class);
    }

    /**
     * Get Token by checkout referenceId
     * @param referenceId
     * @return
     * @throws IOException
     */
    public PaymentTokenData getByReferenceId(String referenceId) throws IOException {
        return executePostRequestWith(tokenApiBaseEndpoint + "/checkout?ReferenceId="+referenceId, null, PaymentTokenData.class);
    }

    /**
     * Get Token by id
     * @param tokenId
     * @return
     * @throws IOException
     */
    public TokenData single(String tokenId) throws IOException {
        String urlPath = String.format("%s/%s", tokenApiBaseEndpoint, tokenId);
        return executeGetRequestWith(urlPath, null, TokenData.class);
    }

    /**
     * Add a customer using a payment token
     * @param newCustomerToken
     * @return
     * @throws IOException
     */
    public CustomerData createWithToken(NewCustomerToken newCustomerToken) throws IOException {
        return executePostRequestWith(tokenApiBaseEndpoint + "/token", newCustomerToken, CustomerData.class);
    }

    /**
     * Add a payment method using a card
     * @param customerId
     * @param newPaymentMethodCard
     * @return
     * @throws IOException
     */
    public PaymentMethodData createPaymentMethodWithCard(String customerId, NewPaymentMethodCard newPaymentMethodCard) throws IOException {
        String urlPath = String.format("%s/%s/payment_method/card", tokenApiBaseEndpoint, customerId);
        return executePostRequestWith(urlPath, newPaymentMethodCard, PaymentMethodData.class);
    }

    /**
     * Add a payment method using a token
     * @param customerId
     * @param newPaymentMethodToken
     * @return
     * @throws IOException
     */
    public PaymentMethodData createWitPaymentMethodWithToken(String customerId, NewPaymentMethodToken newPaymentMethodToken) throws IOException {
        String urlPath = String.format("%s/%s/payment_method/token", tokenApiBaseEndpoint, customerId);
        return executePostRequestWith(urlPath, newPaymentMethodToken, PaymentMethodData.class);
    }



    /**
     * Search for customers
     * @param searchData
     * @return
     * @throws IOException
     */
    public CustomerList search(CustomerSearch searchData) throws IOException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("Skip", searchData.getSkip());
        queryParams.put("Limit", searchData.getLimit());
        queryParams.put("Reference", searchData.getReference());
        queryParams.put("PaymentMethodId", searchData.getPaymentMethodId());
        queryParams.put("CustomerId", searchData.getCustomerId());
        queryParams.put("Email", searchData.getEmail());
        queryParams.put("AddedAfter", searchData.getAddedAfter());
        queryParams.put("AddedBefore", searchData.getAddedBefore());
        queryParams.put("Search", searchData.getSearch());

        return executeGetRequestWith(tokenApiBaseEndpoint, queryParams, CustomerList.class);
    }

    /**
     * Retrieve payment methods for a customer
     * @param customerId
     * @return
     * @throws IOException
     */
    public List<PaymentMethodData> getPaymentMethods(String customerId) throws IOException {
        String urlPath = String.format("%s/%s/payment_method", tokenApiBaseEndpoint, customerId);
        PaymentMethodData[] paymentMethodData = executeGetRequestWith(urlPath, null, PaymentMethodData[].class);
        return Arrays.asList(paymentMethodData);
    }

    /**
     * Update customer information
     * @param customerId
     * @param updateCustomer
     * @return
     * @throws IOException
     */
    public CustomerData updateCustomer(String customerId, UpdateCustomer updateCustomer) throws IOException {
        return executePutRequestWith(tokenApiBaseEndpoint + "/"+customerId, updateCustomer, CustomerData.class);
    }

    /**
     * Delete customer
     * @param customerId
     * @return
     * @throws IOException
     */
    public CustomerData deleteCustomer(String customerId) throws IOException {
        return executeDeleteRequestWith(tokenApiBaseEndpoint + "/"+customerId, null, CustomerData.class);
    }
}
