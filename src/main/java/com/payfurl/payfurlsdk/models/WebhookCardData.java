package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookCardData {
    private final String cardNumber;
    private final String expiryDate;
    private final String type;
    private final String cardType;
    private final String cardIin;

    @JsonCreator
    public WebhookCardData(@JsonProperty("CardNumber") String cardNumber,
                           @JsonProperty("ExpiryDate") String expiryDate,
                           @JsonProperty("Type") String type,
                           @JsonProperty("CardType") String cardType,
                           @JsonProperty("CardIin") String cardIin) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.type = type;
        this.cardType = cardType;
        this.cardIin = cardIin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getType() {
        return type;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardIin() {
        return cardIin;
    }

    @Override
    public String toString() {
        return "WebhookCardData{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", type='" + type + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardIin='" + cardIin + '\'' +
                '}';
    }

    public static class Builder {

        private String cardNumber;
        private String expiryDate;
        private String type;
        private String cardType;
        private String cardIin;

        public Builder setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setCardType(String cardType) {
            this.cardType = cardType;
            return this;
        }

        public Builder setCardIin(String cardIin) {
            this.cardIin = cardIin;
            return this;
        }

        public WebhookCardData build() {
            return new WebhookCardData(cardNumber, expiryDate, type, cardType, cardIin);
        }
    }
}

