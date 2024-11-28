package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.Batch.*;
import com.payfurl.payfurlsdk.models.Subscription.*;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionApi extends BaseApi {
    private final String subscriptionApiBaseEndpoint;

    public SubscriptionApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        subscriptionApiBaseEndpoint = String.format("%s/%s", baseUri, "subscription");
    }

    public SubscriptionData createSubscription(NewSubscription data) throws ApiException {
        return executePostRequestWith(subscriptionApiBaseEndpoint + "/payment_method", data, SubscriptionData.class);
    }

    public SubscriptionData getSubscription(String subscriptionId) throws ApiException {
        return executeGetRequestWith(subscriptionApiBaseEndpoint + "/" + subscriptionId, null, SubscriptionData.class);
    }

    public SubscriptionList searchSubscription(SubscriptionSearch search) throws ApiException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("Skip", search.getSkip());
        queryParameters.put("Limit", search.getLimit());
        queryParameters.put("AddedAfter", search.getAddedAfter());
        queryParameters.put("AddedBefore", search.getAddedBefore());
        queryParameters.put("AmountLessThan", search.getAmountLessThan());
        queryParameters.put("AmountGreaterThan", search.getAmountGreaterThan());
        queryParameters.put("Currency", search.getCurrency());
        queryParameters.put("Status", search.getStatus());
        queryParameters.put("SortBy", search.getSortBy());

        return executeGetRequestWith(subscriptionApiBaseEndpoint, queryParameters, SubscriptionList.class);
    }

    public SubscriptionData deleteSubscription(String subscriptionId) throws ApiException {
        return executeDeleteRequestWith(subscriptionApiBaseEndpoint + "/" + subscriptionId, null, SubscriptionData.class);
    }
    
    public SubscriptionData updateSubscription(String subscriptionId, SubscriptionUpdate data) throws ApiException {
        return executePutRequestWith(subscriptionApiBaseEndpoint + "/" + subscriptionId, data, SubscriptionData.class);
    }
}
