package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPaymentMethodVault {
    private final String providerId;
    private final String paymentMethodId;
    private final String ccv;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fallbackPaymentMethodId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private WebhookConfig webhook;

    @JsonCreator
    public NewPaymentMethodVault(@JsonProperty("ProviderId") String providerId,
                                 @JsonProperty("PaymentMethodId") String paymentMethodId,
                                 @JsonProperty("Ccv") String ccv) {
        this.providerId = providerId;
        this.paymentMethodId = paymentMethodId;
        this.ccv = ccv;
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
        return "NewPaymentMethodVault{" +
                "providerId='" + providerId + '\'' +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                ", ccv='" + Mask.secret(ccv) + '\'' +
                ", fallbackPaymentMethodId=" + fallbackPaymentMethodId +
                ", webhook=" + webhook +
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

        public NewPaymentMethodVault build() {
            NewPaymentMethodVault result = new NewPaymentMethodVault(providerId, paymentMethodId, ccv);
            result.fallbackPaymentMethodId = fallbackPaymentMethodId;
            result.webhook = webhook;
            return result;
        }
    }
}
