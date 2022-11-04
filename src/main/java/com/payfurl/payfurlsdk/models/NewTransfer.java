package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewTransfer {
    private final String account;
    private final BigDecimal amount;
    private final String currency;
    private final String message;
    private final String reference;

    @JsonCreator
    public NewTransfer(@JsonProperty("Account") String account,
                       @JsonProperty("Amount") BigDecimal amount,
                       @JsonProperty("Currency") String currency,
                       @JsonProperty("Message") String message,
                       @JsonProperty("Reference") String reference) {
        this.account = account;
        this.amount = amount;
        this.currency = currency;
        this.message = message;
        this.reference = reference;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMessage() {
        return message;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public String toString() {
        return "NewTransfer{" +
                "account='" + account + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", message='" + message + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }

    public static class Builder {
        private String account;
        private BigDecimal amount;
        private String currency;
        private String message;
        private String reference;

        public Builder withAccount(String account) {
            this.account = account;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public NewTransfer build() {
            return new NewTransfer(account, amount, currency, message, reference);
        }
    }
}
