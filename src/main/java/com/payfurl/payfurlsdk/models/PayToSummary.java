package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayToSummary {
    private final String payToId;
    private final String status;
    private final String payerName;
    private final PayerPayIdDetails payerPayIdDetails;
    private final DirectDebitDetails payerDirectDebitDetails;
    private final String providerId;
    private final String providerType;

    @JsonCreator
    public PayToSummary(
            @JsonProperty("PayToId") String payToId,
            @JsonProperty("Status") String status,
            @JsonProperty("PayerName") String payerName,
            @JsonProperty("PayerPayIdDetails") PayerPayIdDetails payerPayIdDetails,
            @JsonProperty("PayerDirectDebitDetails") DirectDebitDetails payerDirectDebitDetails,
            @JsonProperty("ProviderId") String providerId,
            @JsonProperty("ProviderType") String providerType) {
        this.payToId = payToId;
        this.status = status;
        this.payerName = payerName;
        this.payerPayIdDetails = payerPayIdDetails;
        this.payerDirectDebitDetails = payerDirectDebitDetails;
        this.providerId = providerId;
        this.providerType = providerType;
    }

    public String getPayToId() {
        return payToId;
    }

    public String getStatus() {
        return status;
    }

    public String getPayerName() {
        return payerName;
    }

    public PayerPayIdDetails getPayerPayIdDetails() {
        return payerPayIdDetails;
    }

    public DirectDebitDetails getPayerDirectDebitDetails() {
        return payerDirectDebitDetails;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderType() {
        return providerType;
    }

    @Override
    public String toString() {
        return "PayToSummary{" +
                "payToId='" + payToId + '\'' +
                ", status='" + status + '\'' +
                ", payerName='" + payerName + '\'' +
                ", payerPayIdDetails=" + payerPayIdDetails +
                ", payerDirectDebitDetails=" + payerDirectDebitDetails +
                ", providerId='" + providerId + '\'' +
                ", providerType='" + providerType + '\'' +
                '}';
    }

    public static class Builder {
        private String payToId;
        private String status;
        private String payerName;
        private PayerPayIdDetails payerPayIdDetails;
        private DirectDebitDetails payerDirectDebitDetails;
        private String providerId;
        private String providerType;

        public Builder withPayToId(String payToId) {
            this.payToId = payToId;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withPayerName(String payerName) {
            this.payerName = payerName;
            return this;
        }

        public Builder withPayerPayIdDetails(PayerPayIdDetails payerPayIdDetails) {
            this.payerPayIdDetails = payerPayIdDetails;
            return this;
        }

        public Builder withPayerDirectDebitDetails(DirectDebitDetails payerDirectDebitDetails) {
            this.payerDirectDebitDetails = payerDirectDebitDetails;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withProviderType(String providerType) {
            this.providerType = providerType;
            return this;
        }

        public PayToSummary build() {
            return new PayToSummary(
                    payToId, status, payerName, payerPayIdDetails, payerDirectDebitDetails, providerId, providerType
            );
        }
    }
}
