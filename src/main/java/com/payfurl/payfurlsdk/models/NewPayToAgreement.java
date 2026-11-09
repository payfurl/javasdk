package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewPayToAgreement {
    private final String providerId;
    private final String payerName;
    private final PayIdDetails payerPayIdDetails;
    private final String description;
    private final Integer maximumAmount;
    private final String ip;
    private final boolean setDefault;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fallbackPaymentMethodId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private WebhookConfig webhook;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> metadata;

    @JsonCreator
    public NewPayToAgreement(@JsonProperty("ProviderId") String providerId,
                             @JsonProperty("PayerName") String payerName,
                             @JsonProperty("PayerPayIdDetails") PayIdDetails payerPayIdDetails,
                             @JsonProperty("Description") String description,
                             @JsonProperty("MaximumAmount") Integer maximumAmount,
                             @JsonProperty("Ip") String ip,
                             @JsonProperty("SetDefault") boolean setDefault) {
        this.providerId = providerId;
        this.payerName = payerName;
        this.payerPayIdDetails = payerPayIdDetails;
        this.description = description;
        this.maximumAmount = maximumAmount;
        this.ip = ip;
        this.setDefault = setDefault;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getPayerName() {
        return payerName;
    }

    public PayIdDetails getPayerPayIdDetails() {
        return payerPayIdDetails;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaximumAmount() {
        return maximumAmount;
    }

    public String getIp() {
        return ip;
    }

    public boolean getSetDefault() {
        return setDefault;
    }

    /** The payment method to charge when this one fails. */
    public String getFallbackPaymentMethodId() {
        return fallbackPaymentMethodId;
    }

    /** Where to send this payment method's webhooks. */
    public WebhookConfig getWebhook() {
        return webhook;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public static class Builder {
        private String providerId;
        private String payerName;
        private PayIdDetails payerPayIdDetails;
        private String description;
        private Integer maximumAmount;
        private String ip;
        private boolean setDefault;
        private String fallbackPaymentMethodId;
        private WebhookConfig webhook;
        private Map<String, String> metadata;

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withPayerName(String payerName) {
            this.payerName = payerName;
            return this;
        }

        public Builder withPayerPayIdDetails(PayIdDetails payerPayIdDetails) {
            this.payerPayIdDetails = payerPayIdDetails;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withMaximumAmount(Integer maximumAmount) {
            this.maximumAmount = maximumAmount;
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

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public NewPayToAgreement build() {
            NewPayToAgreement result = new NewPayToAgreement(providerId, payerName, payerPayIdDetails, description, maximumAmount, ip, setDefault);
            result.fallbackPaymentMethodId = fallbackPaymentMethodId;
            result.webhook = webhook;
            result.metadata = metadata;
            return result;
        }
    }
}
