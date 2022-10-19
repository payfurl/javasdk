package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeCapture {
    private final BigDecimal amount;

    @JsonCreator
    public NewChargeCapture(@JsonProperty("Amount") BigDecimal amount) {
        this.amount = amount;
    }

    @JsonGetter("Amount")
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "NewChargeCapture{" +
                "amount=" + amount +
                '}';
    }

    public static class Builder {

        private BigDecimal amount;

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public NewChargeCapture build() {
            return new NewChargeCapture(amount);
        }
    }
}
