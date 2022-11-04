package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardData {
    public final String cardNumber;
    public final String expiryDate;
    public final String cardholder;
    public final String type;

    @JsonCreator
    public CardData(@JsonProperty("CardNumber") String cardNumber,
                    @JsonProperty("ExpiryDate") String expiryDate,
                    @JsonProperty("CardHolder") String cardholder,
                    @JsonProperty("Type") String type) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cardholder = cardholder;
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCardholder() {
        return cardholder;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CardData{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cardholder='" + cardholder + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
