package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WebhookSubscriptionSearchResults {
    private List<WebhookSubscriptionData> webhookSubscriptions;
    private long count;

    @JsonCreator
    public WebhookSubscriptionSearchResults(@JsonProperty("WebhookSubscriptions") List<WebhookSubscriptionData> webhookSubscriptions,
                                            @JsonProperty("Count") long count) {
        this.webhookSubscriptions = webhookSubscriptions;
        this.count = count;
    }

    public List<WebhookSubscriptionData> getWebhookSubscriptions() {
        return webhookSubscriptions;
    }

    public void setWebhookSubscriptions(List<WebhookSubscriptionData> webhookSubscriptions) {
        this.webhookSubscriptions = webhookSubscriptions;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "WebhookSubscriptionSearchResults{" +
                "webhookSubscriptions=" + webhookSubscriptions +
                ", count=" + count +
                '}';
    }

    public static class Builder {
        private List<WebhookSubscriptionData> webhookSubscriptions;
        private long count;

        public Builder withWebhookSubscriptions(List<WebhookSubscriptionData> webhookSubscriptions) {
            this.webhookSubscriptions = webhookSubscriptions;
            return this;
        }

        public Builder withCount(long count) {
            this.count = count;
            return this;
        }

        public WebhookSubscriptionSearchResults build() {
            return new WebhookSubscriptionSearchResults(webhookSubscriptions, count);
        }
    }
}
