package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewRefund {
    private final String chargeId;
    private final BigDecimal refundAmount;

    @JsonCreator
    public NewRefund(@JsonProperty("ChargeId") String chargeId,
                     @JsonProperty("RefundAmount") BigDecimal refundAmount) {
        this.chargeId = chargeId;
        this.refundAmount = refundAmount;
    }

    public String getChargeId() {
        return chargeId;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    @Override
    public String toString() {
        return "NewRefund{" +
                "chargeId='" + chargeId + '\'' +
                ", refundAmount=" + refundAmount +
                '}';
    }

    public static class Builder {
        private String chargeId;
        private BigDecimal refundAmount;

        public Builder withChargeId(String chargeId) {
            this.chargeId = chargeId;
            return this;
        }

        public Builder withRefundAmount(BigDecimal refundAmount) {
            this.refundAmount = refundAmount;
            return this;
        }

        public NewRefund build() {
            return new NewRefund(chargeId, refundAmount);
        }
    }
}
