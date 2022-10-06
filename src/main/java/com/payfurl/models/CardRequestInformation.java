package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardRequestInformation {
    private final String cardNumber;
    private final String expiryDate;
    private final String ccv;

    private final String cardHolder;

    @JsonCreator
    public CardRequestInformation(@JsonProperty("CardNumber") String cardNumber,
                                  @JsonProperty("ExpiryDate") String expiryDate,
                                  @JsonProperty("Ccv") String ccv,
                                  @JsonProperty("CardHolder") String cardHolder) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.ccv = ccv;
        this.cardHolder = cardHolder;
    }

    @JsonGetter("CardNumber")
    public String getCardNumber() {
        return cardNumber;
    }

    @JsonGetter("ExpiryDate")
    public String getExpiryDate() {
        return expiryDate;
    }

    @JsonGetter("Ccv")
    public String getCcv() {
        return ccv;
    }

    @JsonGetter("CardHolder")
    public String getCardHolder() {
        return cardHolder;
    }

    @Override
    public String toString() {
        return "CardRequestInformation{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", ccv='" + ccv + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                '}';
    }

    public static class Builder {
        private String cardNumber;
        private String expiryDate;
        private String ccv;
        private String cardHolder;

        public Builder withCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder withExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Builder withCcv(String ccv) {
            this.ccv = ccv;
            return this;
        }

        public Builder withCardHolder(String cardHolder) {
            this.cardHolder = cardHolder;
            return this;
        }

        public CardRequestInformation build() {
            return new CardRequestInformation(cardNumber, expiryDate, ccv, cardHolder);
        }
    }
}
