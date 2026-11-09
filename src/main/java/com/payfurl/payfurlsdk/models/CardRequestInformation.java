package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CardRequestInformation {
    private final String cardNumber;
    private final String expiryDate;
    private final String ccv;
    private final String cardHolder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String threeDSServerTransID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> externalThreeDsData;

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

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCcv() {
        return ccv;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    /** The 3DS server transaction ID of a 3DS authentication done outside payFURL. */
    public String getThreeDSServerTransID() {
        return threeDSServerTransID;
    }

    /** 3DS authentication data from a 3DS server outside payFURL. */
    public Map<String, String> getExternalThreeDsData() {
        return externalThreeDsData;
    }

    @Override
    public String toString() {
        return "CardRequestInformation{" +
                "cardNumber='" + Mask.lastFour(cardNumber) + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", ccv='" + Mask.secret(ccv) + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", threeDSServerTransID=" + threeDSServerTransID +
                ", externalThreeDsData=" + externalThreeDsData +
                '}';
    }

    public static class Builder {
        private String cardNumber;
        private String expiryDate;
        private String ccv;
        private String cardHolder;
        private String threeDSServerTransID;
        private Map<String, String> externalThreeDsData;

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

        /** The 3DS server transaction ID of a 3DS authentication done outside payFURL. */
        public Builder withThreeDSServerTransID(String threeDSServerTransID) {
            this.threeDSServerTransID = threeDSServerTransID;
            return this;
        }

        /** 3DS authentication data from a 3DS server outside payFURL. */
        public Builder withExternalThreeDsData(Map<String, String> externalThreeDsData) {
            this.externalThreeDsData = externalThreeDsData;
            return this;
        }

        public CardRequestInformation build() {
            CardRequestInformation result = new CardRequestInformation(cardNumber, expiryDate, ccv, cardHolder);
            result.threeDSServerTransID = threeDSServerTransID;
            result.externalThreeDsData = externalThreeDsData;
            return result;
        }
    }
}
