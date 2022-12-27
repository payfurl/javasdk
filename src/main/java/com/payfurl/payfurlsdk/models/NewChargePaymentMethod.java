package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargePaymentMethod {
    private final BigDecimal amount;
    private final String currency;
    private final String paymentMethodId;
    private final String reference;
    private final Address address;
    private final boolean capture;

    @JsonCreator
    public NewChargePaymentMethod(@JsonProperty("Amount") BigDecimal amount,
                                  @JsonProperty("Currency") String currency,
                                  @JsonProperty("Reference") String reference,
                                  @JsonProperty("PaymentMethodId") String paymentMethodId,
                                  @JsonProperty("Address") Address address,
                                  @JsonProperty("Order") Order order,
                                  @JsonProperty("Capture") boolean capture) {
        this.amount = amount;
        this.currency = currency;
        this.reference = reference;
        this.paymentMethodId = paymentMethodId;
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

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isCapture() {
        return capture;
    }


    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String reference;
        private String paymentMethodId;
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

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
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

        public NewChargePaymentMethod build() {
            return new NewChargePaymentMethod(amount, currency, reference, paymentMethodId, address, capture);
        }
    }
}
