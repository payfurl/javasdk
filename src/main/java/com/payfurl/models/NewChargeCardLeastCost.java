package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeCardLeastCost {
    private final BigDecimal amount;
    private final String currency;
    private final String reference;
    private final CardRequestInformation paymentInformation;
    private final boolean capture;

    @JsonCreator
    public NewChargeCardLeastCost(@JsonProperty("Amount") BigDecimal amount,
                                  @JsonProperty("Currency") String currency,
                                  @JsonProperty("Reference") String reference,
                                  @JsonProperty("PaymentInformation") CardRequestInformation paymentInformation,
                                  @JsonProperty("Capture") boolean capture) {
        this.amount = amount;
        this.currency = currency;
        this.reference = reference;
        this.paymentInformation = paymentInformation;
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

    @JsonGetter("PaymentInformation")
    public CardRequestInformation getPaymentInformation() {
        return paymentInformation;
    }

    @JsonGetter("Capture")
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
                ", capture=" + capture +
                '}';
    }

    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String reference;
        private CardRequestInformation paymentInformation;
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

        public Builder withCapture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public NewChargeCardLeastCost build() {
            return new NewChargeCardLeastCost(amount, currency, reference, paymentInformation, capture);
        }
    }
}
