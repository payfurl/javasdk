package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPaymentMethodVault {
    private final String providerId;
    private final String paymentMethodId;
    private final String ccv;
    private final String fallbackPaymentMethodId;
    private final WebhookConfig webhook;

    @JsonCreator
    public NewPaymentMethodVault(@JsonProperty("ProviderId") String providerId,
                                 @JsonProperty("PaymentMethodId") String paymentMethodId,
                                 @JsonProperty("Ccv") String ccv,
                                 @JsonProperty("FallbackPaymentMethodId") String fallbackPaymentMethodId,
                                 @JsonProperty("Webhook") WebhookConfig webhook) {
        this.providerId = providerId;
        this.paymentMethodId = paymentMethodId;
        this.ccv = ccv;
        this.fallbackPaymentMethodId = fallbackPaymentMethodId;
        this.webhook = webhook;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public String getCcv() {
        return ccv;
    }

    public String getFallbackPaymentMethodId() {
        return fallbackPaymentMethodId;
    }

    public WebhookConfig getWebhook() {
        return webhook;
    }

    @Override
    public String toString() {
        return "NewPaymentMethodVault{" +
                "providerId='" + providerId + '\'' +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                ", ccv='" + ccv + '\'' +
                '}';
    }

    public static class Builder {
        private String providerId;
        private String paymentMethodId;
        private String ccv;
        private String fallbackPaymentMethodId;
        private WebhookConfig webhook;

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder withCcv(String ccv) {
            this.ccv = ccv;
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

        public NewPaymentMethodVault build() {
            return new NewPaymentMethodVault(providerId, paymentMethodId, ccv, fallbackPaymentMethodId, webhook);
        }
    }
}
