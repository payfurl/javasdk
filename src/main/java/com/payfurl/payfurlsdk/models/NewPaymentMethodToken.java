package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewPaymentMethodToken {
    private final String token;
    private final boolean setDefault;
    private final Map<String, String> metadata;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ip;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fallbackPaymentMethodId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private WebhookConfig webhook;

    @JsonCreator
    public NewPaymentMethodToken(@JsonProperty("Token") String token,
                                 @JsonProperty("SetDefault") boolean setDefault,
                                 @JsonProperty("Metadata") Map<String, String> metadata) {
        this.token = token;
        this.setDefault = setDefault;
        this.metadata = metadata;
    }

    public String getToken() {
        return token;
    }

    public boolean getSetDefault() {
        return setDefault;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
    public String getIp() {
        return ip;
    }

    /** The payment method to charge when this one fails. */
    public String getFallbackPaymentMethodId() {
        return fallbackPaymentMethodId;
    }

    /** Where to send this payment method's webhooks. */
    public WebhookConfig getWebhook() {
        return webhook;
    }

    @Override
    public String toString() {
        return "NewPaymentMethodToken{" +
                "token='" + token + '\'' +
                " setDefault=" + setDefault +
                " metadata=" + metadata +
                ", ip=" + ip +
                ", fallbackPaymentMethodId=" + fallbackPaymentMethodId +
                ", webhook=" + webhook +
                '}';
    }

    public static class Builder {
        private String token;
        private boolean setDefault;
        private Map<String, String> metadata;
        private String ip;
        private String fallbackPaymentMethodId;
        private WebhookConfig webhook;

        public Builder withToken(String token) {
            this.token = token;
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

        /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        /** The payment method to charge when this one fails. */
        public Builder withFallbackPaymentMethodId(String fallbackPaymentMethodId) {
            this.fallbackPaymentMethodId = fallbackPaymentMethodId;
            return this;
        }

        /** Where to send this payment method's webhooks. */
        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }

        public NewPaymentMethodToken build() {
            NewPaymentMethodToken result = new NewPaymentMethodToken(token, setDefault, metadata);
            result.ip = ip;
            result.fallbackPaymentMethodId = fallbackPaymentMethodId;
            result.webhook = webhook;
            return result;
        }
    }
}
