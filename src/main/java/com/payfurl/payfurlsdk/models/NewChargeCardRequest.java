package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeCardRequest {
    private final BigDecimal amount;
    private final String currency;
    private final String providerId;
    private final String reference;
    private final CardRequestInformation paymentInformation;
    private final Address address;
    private final Order order;
    private final boolean capture;

    @JsonCreator
    public NewChargeCardRequest(@JsonProperty("Amount") BigDecimal amount,
                                @JsonProperty("Currency") String currency,
                                @JsonProperty("ProviderId") String providerId,
                                @JsonProperty("Reference") String reference,
                                @JsonProperty("PaymentInformation") CardRequestInformation paymentInformation,
                                @JsonProperty("Address") Address address,
                                @JsonProperty("Order") Order order,
                                @JsonProperty("Capture") boolean capture) {
        this.amount = amount;
        this.currency = currency;
        this.providerId = providerId;
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

    public String getProviderId() {
        return providerId;
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

    public boolean isCapture() {
        return capture;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "NewChargeCardRequest{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", providerId='" + providerId + '\'' +
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
        private String providerId;
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

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
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

        public Builder withCapture(boolean capture) {
            this.capture = capture;
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

        public NewChargeCardRequest build() {
            return new NewChargeCardRequest(amount, currency, providerId, reference, paymentInformation, address, order, capture);
        }
    }
}
