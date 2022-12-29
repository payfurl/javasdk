package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.*;

import java.io.IOException;
import java.util.HashMap;
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
     * Search for tokekns
     * @param searchData
     * @return
     * @throws IOException
     */
    public TokenList search(TokenSearch searchData) throws IOException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("Skip", searchData.getSkip());
        queryParams.put("Limit", searchData.getLimit());
        queryParams.put("Status", searchData.getStatus());
        queryParams.put("ProviderId", searchData.getProviderId());
        queryParams.put("AddedAfter", searchData.getAddedAfter());
        queryParams.put("AddedBefore", searchData.getAddedBefore());
        queryParams.put("SortBy", searchData.getSortBy());

        return executeGetRequestWith(tokenApiBaseEndpoint, queryParams, TokenList.class);
    }
}
