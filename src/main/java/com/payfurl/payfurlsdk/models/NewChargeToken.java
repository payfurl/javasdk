package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeToken {
    private final BigDecimal amount;
    private final String currency;
    private final String reference;
    private final String token;
    private final CheckoutTransfer checkoutTransfer;
    private final Address address;
    private final boolean capture;

    @JsonCreator
    public NewChargeToken(@JsonProperty("Amount") BigDecimal amount,
                          @JsonProperty("Currency") String currency,
                          @JsonProperty("Reference") String reference,
                          @JsonProperty("PaymentMethodId") String token,
                          @JsonProperty("CheckoutTransfer") CheckoutTransfer checkoutTransfer,
                          @JsonProperty("Address") Address address,
                          @JsonProperty("Capture") boolean capture) {
        this.amount = amount;
        this.currency = currency;
        this.reference = reference;
        this.token = token;
        this.checkoutTransfer = checkoutTransfer;
        this.address = address;
        this.capture = capture;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getReference() {
        return reference;
    }

    public String getToken() {
        return token;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isCapture() {
        return capture;
    }

    public CheckoutTransfer getCheckoutTransfer() {
        return checkoutTransfer;
    }

    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String reference;
        private String token;
        private CheckoutTransfer checkoutTransfer;
        private Address address;
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

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withCapture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public NewChargeToken build() {
            return new NewChargeToken(amount, currency, reference, token, checkoutTransfer, address, capture);
        }
    }
}
