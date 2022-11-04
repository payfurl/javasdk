package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public class NewCheckout {
    private final BigDecimal amount;
    private final String currency;
    private final String providerId;
    private final String reference;
    private final CheckoutTransfer checkoutTransfer;
    private final Map<String, String> options;

    @JsonCreator
    public NewCheckout(@JsonProperty("Amount") BigDecimal amount,
                       @JsonProperty("Currency") String currency,
                       @JsonProperty("ProviderId") String providerId,
                       @JsonProperty("Reference") String reference,
                       @JsonProperty("Transfer") CheckoutTransfer checkoutTransfer,
                       @JsonProperty("Options") Map<String, String> options) {
        this.amount = amount;
        this.currency = currency;
        this.providerId = providerId;
        this.reference = reference;
        this.checkoutTransfer = checkoutTransfer;
        this.options = options;
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

    public CheckoutTransfer getCheckoutTransfer() {
        return checkoutTransfer;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return "NewCheckout{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", providerId='" + providerId + '\'' +
                ", reference='" + reference + '\'' +
                ", paymentInformation=" + checkoutTransfer +
                ", options=" + options +
                '}';
    }

    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String providerId;
        private String reference;
        private CheckoutTransfer transfer;
        private Map<String, String> options;

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

        public Builder withPaymentInformation(CheckoutTransfer transfer) {
            this.transfer = transfer;
            return this;
        }

        public Builder withOptions(Map<String, String> options) {
            this.options = options;
            return this;
        }

        public NewCheckout build() {
            return new NewCheckout(amount, currency, providerId, reference, transfer, options);
        }
    }
}
