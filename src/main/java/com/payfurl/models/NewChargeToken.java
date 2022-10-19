package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeToken {
    private final BigDecimal amount;
    private final String currency;
    private final String reference;
    private final String token;
    private final CheckoutTransfer checkoutTransfer;
    private final boolean capture;

    @JsonCreator
    public NewChargeToken(@JsonProperty("Amount") BigDecimal amount,
                          @JsonProperty("Currency") String currency,
                          @JsonProperty("Reference") String reference,
                          @JsonProperty("PaymentMethodId") String token,
                          @JsonProperty("CheckoutTransfer") CheckoutTransfer checkoutTransfer,
                          @JsonProperty("Capture") boolean capture) {
        this.amount = amount;
        this.currency = currency;
        this.reference = reference;
        this.token = token;
        this.checkoutTransfer = checkoutTransfer;
        this.capture = capture;
    }

    @JsonGetter("Amount")
    public BigDecimal getAmount() {
        return amount;
    }

    @JsonGetter("Currency")
    public String getCurrency() {
        return currency;
    }

    @JsonGetter("Reference")
    public String getReference() {
        return reference;
    }

    @JsonGetter("PaymentMethodId")
    public String getToken() {
        return token;
    }

    @JsonGetter("Capture")
    public boolean isCapture() {
        return capture;
    }

    @JsonGetter("CheckoutTransfer")
    public CheckoutTransfer getCheckoutTransfer() {
        return checkoutTransfer;
    }

    public static class Builder {

        private BigDecimal amount;
        private String currency;
        private String reference;
        private String token;
        private CheckoutTransfer checkoutTransfer;
        private boolean capture = true;

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withCheckoutTransfer(CheckoutTransfer checkoutTransfer) {
            this.checkoutTransfer = checkoutTransfer;
            return this;
        }

        public Builder withCapture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public NewChargeToken build() {
            return new NewChargeToken(amount, currency, reference, token, checkoutTransfer, capture);
        }
    }
}
