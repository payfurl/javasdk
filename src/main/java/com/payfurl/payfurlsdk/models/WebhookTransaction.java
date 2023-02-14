package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookTransaction {
    private final WebhookMeta meta;
    private final WebhookTransactionData data;

    @JsonCreator
    public WebhookTransaction(@JsonProperty("Meta") WebhookMeta meta,
                              @JsonProperty("Data") WebhookTransactionData data) {
        this.meta = meta;
        this.data = data;
    }

    public WebhookMeta getMeta() {
        return meta;
    }

    public WebhookTransactionData getData() {
        return data;
    }

    @Override
    public String toString() {
        return "WebhookTransaction{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }

    public static class Builder {

        private WebhookMeta meta;
        private WebhookTransactionData data;

        public Builder withMeta(WebhookMeta meta) {
            this.meta = meta;
            return this;
        }

        public Builder withData(WebhookTransactionData data) {
            this.data = data;
            return this;
        }

        public WebhookTransaction build() {
            return new WebhookTransaction(meta, data);
        }
    }
}