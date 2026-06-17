package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewPaymentMethodToken {
    private final String token;
    private final String ip;
    private final boolean setDefault;
    private final Map<String, String> metadata;
    private final String fallbackPaymentMethodId;
    private final WebhookConfig webhook;

    @JsonCreator
    public NewPaymentMethodToken(@JsonProperty("Token") String token,
                                 @JsonProperty("Ip") String ip,
                                 @JsonProperty("SetDefault") boolean setDefault,
                                 @JsonProperty("Metadata") Map<String, String> metadata,
                                 @JsonProperty("FallbackPaymentMethodId") String fallbackPaymentMethodId,
                                 @JsonProperty("Webhook") WebhookConfig webhook) {
        this.token = token;
        this.ip = ip;
        this.setDefault = setDefault;
        this.metadata = metadata;
        this.fallbackPaymentMethodId = fallbackPaymentMethodId;
        this.webhook = webhook;
    }

    public String getToken() {
        return token;
    }

    public String getIp() {
        return ip;
    }

    public boolean getSetDefault() {
        return setDefault;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public String getFallbackPaymentMethodId() {
        return fallbackPaymentMethodId;
    }

    public WebhookConfig getWebhook() {
        return webhook;
    }

    @Override
    public String toString() {
        return "NewPaymentMethodToken{" +
                "token='" + token + '\'' +
                " setDefault=" + setDefault +
                " metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private String token;
        private String ip;
        private boolean setDefault;
        private Map<String, String> metadata;
        private String fallbackPaymentMethodId;
        private WebhookConfig webhook;

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder withSetDefault(boolean setDefault) {
            this.setDefault = setDefault;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder withFallbackPaymentMethodId(String fallbackPaymentMethodId) {
            this.fallbackPaymentMethodId = fallbackPaymentMethodId;
            return this;
        }

        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }

        public NewPaymentMethodToken build() {
            return new NewPaymentMethodToken(token, ip, setDefault, metadata, fallbackPaymentMethodId, webhook);
        }
    }
}
