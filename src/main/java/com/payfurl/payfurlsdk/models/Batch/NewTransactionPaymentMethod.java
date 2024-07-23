package com.payfurl.payfurlsdk.models.Batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.payfurl.payfurlsdk.models.WebhookConfig;

public class NewTransactionPaymentMethod {
    private final int count;
    private final String description;
    private final String batch;
    private final WebhookConfig webhook;

    @JsonCreator
    public NewTransactionPaymentMethod(@JsonProperty("Count") int count,
            @JsonProperty("Description") String description,
            @JsonProperty("Batch") String batch,
            @JsonProperty("Webhook") WebhookConfig webhook) {

        this.count = count;
        this.description = description;
        this.batch = batch;
        this.webhook = webhook;
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

    @Override
    public String toString() {
        return "NewTransactionPaymentMethod{" +
                "count=" + count +
                ", description='" + description + '\'' +
                ", batch='" + batch + '\'' +
                ", webhook='" + webhook + '\'' +
                '}';
    }

    public static class Builder {
        private int count;
        private String description;
        private String batch;
        private WebhookConfig webhook;

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

        public NewTransactionPaymentMethod build() {
            return new NewTransactionPaymentMethod(count, description, batch, webhook);
        }
    }
}
