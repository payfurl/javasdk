package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPayToAgreement {
    private final String providerId;
    private final String payerName;
    private final PayIdDetails payerPayIdDetails;
    private final String description;
    private final Integer maximumAmount;
    private final String ip;
    private final boolean setDefault;

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

    public boolean getSetDefault() { return setDefault; }

    public static class Builder {
        private String providerId;
        private String payerName;
        private PayIdDetails payerPayIdDetails;
        private String description;
        private Integer maximumAmount;
        private String ip;
        private boolean setDefault;

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

        public NewPayToAgreement build() {
            return new NewPayToAgreement(providerId, payerName, payerPayIdDetails, description, maximumAmount, ip, setDefault);
        }
    }
}
