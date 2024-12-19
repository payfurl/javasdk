package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.NewWebhookSubscription;
import com.payfurl.payfurlsdk.models.WebhookSubscriptionData;
import com.payfurl.payfurlsdk.models.WebhookSubscriptionSearch;
import com.payfurl.payfurlsdk.models.WebhookSubscriptionSearchResults;

import java.util.HashMap;
import java.util.Map;

public class WebhookSubscriptionApi extends BaseApi {
    private final String webhookSubscriptionApiBaseEndpoint;

    public WebhookSubscriptionApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        webhookSubscriptionApiBaseEndpoint = String.format("%s/%s", baseUri, "webhook/subscription");
    }

    public WebhookSubscriptionData createWebhookSubscription(NewWebhookSubscription data) throws ApiException {
        return executePostRequestWith(webhookSubscriptionApiBaseEndpoint + "/", data, WebhookSubscriptionData.class);
    }

    public WebhookSubscriptionData getWebhookSubscription(String webhookSubscriptionId) throws ApiException {
        return executeGetRequestWith(webhookSubscriptionApiBaseEndpoint + "/" + webhookSubscriptionId, null, WebhookSubscriptionData.class);
    }

    public WebhookSubscriptionSearchResults searchWebhookSubscription(WebhookSubscriptionSearch search) throws ApiException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("Skip", search.getSkip());
        queryParameters.put("Limit", search.getLimit());
        queryParameters.put("AddedAfter", search.getAddedAfter());
        queryParameters.put("AddedBefore", search.getAddedBefore());
        queryParameters.put("Type", search.getType());
        queryParameters.put("Id", search.getId());
        queryParameters.put("Sort", search.getSort());

        return executeGetRequestWith(webhookSubscriptionApiBaseEndpoint, queryParameters, WebhookSubscriptionSearchResults.class);
    }

    public WebhookSubscriptionData deleteWebhookSubscription(String webhookSubscriptionId) throws ApiException {
        return executeDeleteRequestWith(webhookSubscriptionApiBaseEndpoint + "/" + webhookSubscriptionId, null, WebhookSubscriptionData.class);
    }
}
