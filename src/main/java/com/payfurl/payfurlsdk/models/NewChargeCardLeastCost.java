package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeCardLeastCost {
    private final BigDecimal amount;
    private final String currency;
    private final String reference;
    private final CardRequestInformation paymentInformation;
    private final Address address;
    private final Order order;
    private final boolean capture;

    @JsonCreator
    public NewChargeCardLeastCost(@JsonProperty("Amount") BigDecimal amount,
                                  @JsonProperty("Currency") String currency,
                                  @JsonProperty("Reference") String reference,
                                  @JsonProperty("PaymentInformation") CardRequestInformation paymentInformation,
                                  @JsonProperty("Address") Address address,
                                  @JsonProperty("Order") Order order,
                                  @JsonProperty("Capture") boolean capture) {
        this.amount = amount;
        this.currency = currency;
        this.reference = reference;
        this.paymentInformation = paymentInformation;
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

    public String getReference() {
        return reference;
    }

    public CardRequestInformation getPaymentInformation() {
        return paymentInformation;
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

    @Override
    public String toString() {
        return "NewChargeCardLeastCost{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", reference='" + reference + '\'' +
                ", paymentInformation=" + paymentInformation +
                ", address=" + address +
                ", order=" + order +
                ", capture=" + capture +
                '}';
    }

    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String reference;
        private CardRequestInformation paymentInformation;
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

        public Builder withPaymentInformation(CardRequestInformation paymentInformation) {
            this.paymentInformation = paymentInformation;
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

        public Builder withCapture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public NewChargeCardLeastCost build() {
            return new NewChargeCardLeastCost(amount, currency, reference, paymentInformation, address, order, capture);
        }
    }
}
