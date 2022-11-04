package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Checkout {
    private final String checkoutId;
    private final String transactionId;

    @JsonCreator
    public Checkout(@JsonProperty("CheckoutId") String checkoutId,
                    @JsonProperty("TransactionId") String transactionId) {
        this.checkoutId = checkoutId;
        this.transactionId = transactionId;
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "CardData{" +
                "cardNumber='" + checkoutId + '\'' +
                ", expiryDate='" + transactionId + '\'' +
                '}';
    }
}
