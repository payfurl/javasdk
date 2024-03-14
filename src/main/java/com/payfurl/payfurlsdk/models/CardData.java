package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardData {
    public final String cardNumber;
    public final String expiryDate;
    public final String cardholder;
    public final String type;
    public final IinData iinData;

    @JsonCreator
    public CardData(@JsonProperty("CardNumber") String cardNumber,
                    @JsonProperty("ExpiryDate") String expiryDate,
                    @JsonProperty("CardHolder") String cardholder,
                    @JsonProperty("Type") String type,
                    @JsonProperty("IinData") IinData iinData) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cardholder = cardholder;
        this.type = type;
        this.iinData = iinData;
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

    public IinData getIinData() {
        return iinData;
    }

    @Override
    public String toString() {
        return "CardData{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cardholder='" + cardholder + '\'' +
                ", type='" + type + '\'' +
                ", iinData='" + iinData + '\'' +
                '}';
    }
}
