package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewRefund {
    private final String chargeId;
    private final BigDecimal refundAmount;
    private final String comment;

    @JsonCreator
    public NewRefund(@JsonProperty("ChargeId") String chargeId,
                     @JsonProperty("RefundAmount") BigDecimal refundAmount,
                     @JsonProperty("Comment") String comment) {
        this.chargeId = chargeId;
        this.refundAmount = refundAmount;
        this.comment = comment;
    }

    public String getChargeId() {
        return chargeId;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "NewRefund{" +
                "chargeId='" + chargeId + '\'' +
                ", refundAmount=" + refundAmount +
                ", comment='" + comment + '\'' +
                '}';
    }

    public static class Builder {
        private String chargeId;
        private BigDecimal refundAmount;
        private String comment;

        public Builder withChargeId(String chargeId) {
            this.chargeId = chargeId;
            return this;
        }

        public Builder withRefundAmount(BigDecimal refundAmount) {
            this.refundAmount = refundAmount;
            return this;
        }

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public NewRefund build() {
            return new NewRefund(chargeId, refundAmount, comment);
        }
    }
}
