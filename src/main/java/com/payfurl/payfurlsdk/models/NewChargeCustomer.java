package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeCustomer {
    private final BigDecimal amount;
    private final String currency;
    private final String customerId;
    private final String reference;
    private final Address address;

    private final Order order;
    private final boolean capture;

    @JsonCreator
    public NewChargeCustomer(@JsonProperty("Amount") BigDecimal amount,
                             @JsonProperty("Currency") String currency,
                             @JsonProperty("CustomerId") String customerId,
                             @JsonProperty("Reference") String reference,
                             @JsonProperty("Address") Address address,
                             @JsonProperty("Order") Order order,
                             @JsonProperty("Capture") boolean capture) {
        this.amount = amount;
        this.currency = currency;
        this.customerId = customerId;
        this.reference = reference;
        this.address = address;
        this.order = order;
        this.capture = capture;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getReference() {
        return reference;
    }

    public Address getAddress() {
        return address;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isCapture() {
        return capture;
    }



    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String customerId;
        private String reference;
        private Address address;
        private Order order;
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

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withCapture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public NewChargeCustomer build() {
            return new NewChargeCustomer(amount, currency, customerId, reference, address, order, capture);
        }
    }
}
