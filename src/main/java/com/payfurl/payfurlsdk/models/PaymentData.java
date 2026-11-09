package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentData {
    /** The API sends it as {@code card}. */
    @JsonAlias("card")
    public final CardData cardData;
    public final String type;
    public final String displayType;
    public final String email;

    @JsonProperty("paymentMethodId")
    private String paymentMethodId;
    @JsonProperty("bankPayment")
    private BankPaymentData bankPayment;

    @JsonCreator
    public PaymentData(@JsonProperty("CardData") CardData cardData,
                       @JsonProperty("Type") String type,
                       @JsonProperty("DisplayType") String displayType,
                       @JsonProperty("Email") String email) {
        this.cardData = cardData;
        this.type = type;
        this.displayType = displayType;
        this.email = email;
    }

    public CardData getCardData() {
        return cardData;
    }

    public String getType() {
        return type;
    }
    
    public String getDisplayType() {
        return displayType;
    }

    public String getEmail() {
        return email;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public BankPaymentData getBankPayment() {
        return bankPayment;
    }

    @Override
    public String toString() {
        return "PaymentData{" +
                "cardData=" + cardData +
                ", type='" + type + '\'' +
                ", displayType='" + displayType + '\'' +
                ", email='" + email + '\'' +
                ", paymentMethodId=" + paymentMethodId +
                ", bankPayment=" + bankPayment +
                '}';
    }
}
