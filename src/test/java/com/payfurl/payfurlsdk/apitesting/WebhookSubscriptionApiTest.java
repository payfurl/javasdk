package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.WebhookSubscriptionApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebhookSubscriptionApiTest {
    private WebhookSubscriptionApi webhookSubscriptionApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        webhookSubscriptionApi = payFurlClient.getWebhookSubscriptionApi();
    }

    private WebhookSubscriptionData createWebhookSubscription() throws ApiException {
        List<WebhookType> types = new ArrayList<>();
        types.add(WebhookType.Transaction);
        NewWebhookSubscription newWebhookSubscription = new NewWebhookSubscription.Builder()
                .withUrl("https://webhook.site")
                .withTypes(types)
                .build();

        return webhookSubscriptionApi.createWebhookSubscription(newWebhookSubscription);
    }

    @Test
    void testCreateWebhookSubscription() throws ApiException {
        WebhookSubscriptionData result = createWebhookSubscription();

        assertThat(result).isNotNull();
        assertThat(result.getWebhookSubscriptionId()).isNotNull();
    }

    @Test
    void testGetWebhookSubscription() throws ApiException {
        WebhookSubscriptionData webhook = createWebhookSubscription();

        WebhookSubscriptionData result = webhookSubscriptionApi.getWebhookSubscription(webhook.getWebhookSubscriptionId());

        assertThat(result).isNotNull();
        assertThat(result.getWebhookSubscriptionId()).isEqualTo(webhook.getWebhookSubscriptionId());
    }

    @Test
    void testDeleteWebhookSubscription() throws ApiException {
        WebhookSubscriptionData webhook = createWebhookSubscription();

        WebhookSubscriptionData result = webhookSubscriptionApi.deleteWebhookSubscription(webhook.getWebhookSubscriptionId());

        assertThat(result).isNotNull();
        assertThat(result.getWebhookSubscriptionId()).isEqualTo(webhook.getWebhookSubscriptionId());
    }

    @Test
    void testSearchWebhookSubscription() throws ApiException {
        WebhookSubscriptionData webhook = createWebhookSubscription();

        WebhookSubscriptionSearch search = new WebhookSubscriptionSearch.Builder()
                .withId(webhook.getWebhookSubscriptionId())
                .build();

        WebhookSubscriptionSearchResults result = webhookSubscriptionApi.searchWebhookSubscription(search);

        assertThat(result).isNotNull();
        assertThat(result.getCount()).isEqualTo(1);
        assertThat(result.getWebhookSubscriptions().get(0).getWebhookSubscriptionId()).isEqualTo(webhook.getWebhookSubscriptionId());
    }
}
