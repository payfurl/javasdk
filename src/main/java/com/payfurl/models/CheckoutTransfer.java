package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CheckoutTransfer {
    private final String account;
    private final BigDecimal amount;

    @JsonCreator
    public CheckoutTransfer(@JsonProperty("Account") String account,
                            @JsonProperty("Amount") BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    @JsonGetter("Account")
    public String getAccount() {
        return account;
    }

    @JsonGetter("Amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public static class Builder {
        private String account;
        private BigDecimal amount;

        public Builder withAccount(String account) {
            this.account = account;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public CheckoutTransfer build() {
            return new CheckoutTransfer(account, amount);
        }
    }
}
