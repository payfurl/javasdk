package com.payfurl.payfurlsdk.models.Batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.payfurl.payfurlsdk.models.WebhookConfig;

import java.util.Map;

public class NewTransactionPaymentMethod {
    private final int count;
    private final String description;
    private final String batch;
    private final WebhookConfig webhook;
    private final Map<String, String> metadata;

    @JsonCreator
    public NewTransactionPaymentMethod(@JsonProperty("Count") int count,
            @JsonProperty("Description") String description,
            @JsonProperty("Batch") String batch,
            @JsonProperty("Webhook") WebhookConfig webhook,
            @JsonProperty("Metadata") Map<String, String> metadata) {

        this.count = count;
        this.description = description;
        this.batch = batch;
        this.webhook = webhook;
        this.metadata = metadata;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }

    public String getBatch() {
        return batch;
    }

    public WebhookConfig getWebhook() {
        return webhook;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "NewTransactionPaymentMethod{" +
                "count=" + count +
                ", description='" + description + '\'' +
                ", batch='" + batch + '\'' +
                ", webhook='" + webhook + '\'' +
                ", metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private int count;
        private String description;
        private String batch;
        private WebhookConfig webhook;
        private Map<String, String> metadata;

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withBatch(String batch) {
            this.batch = batch;
            return this;
        }

        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public NewTransactionPaymentMethod build() {
            return new NewTransactionPaymentMethod(count, description, batch, webhook, metadata);
        }
    }
}
