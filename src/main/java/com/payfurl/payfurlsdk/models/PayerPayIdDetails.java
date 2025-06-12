package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayerPayIdDetails {
    private final String payId;
    private final String payIdType;

    @JsonCreator
    public PayerPayIdDetails(
            @JsonProperty("PayId") String payId,
            @JsonProperty("PayIdType") String payIdType) {
        this.payId = payId;
        this.payIdType = payIdType;
    }

    public String getPayId() {
        return payId;
    }

    public String getPayIdType() {
        return payIdType;
    }

    @Override
    public String toString() {
        return "PayerPayIdDetails{" +
                "payId='" + payId + '\'' +
                ", payIdType='" + payIdType + '\'' +
                '}';
    }

    public static class Builder {
        private String payId;
        private String payIdType;

        public Builder withPayId(String payId) {
            this.payId = payId;
            return this;
        }

        public Builder withPayIdType(String payIdType) {
            this.payIdType = payIdType;
            return this;
        }

        public PayerPayIdDetails build() {
            return new PayerPayIdDetails(payId, payIdType);
        }
    }
}
