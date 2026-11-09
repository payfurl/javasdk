package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePaymentMethodCardRequestInformation {
    public final String expiryDate;
    public final String cardholder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cardNumber;

    @JsonCreator
    public UpdatePaymentMethodCardRequestInformation(@JsonProperty("ExpiryDate") String expiryDate,
                    @JsonProperty("CardHolder") String cardholder) {
        this.expiryDate = expiryDate;
        this.cardholder = cardholder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCardholder() {
        return cardholder;
    }

    /** A new card number for the payment method. */
    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return "UpdatePaymentMethodCardRequestInformation{" +
                ", expiryDate='" + expiryDate + '\'' +
                ", cardholder='" + cardholder + '\'' +
                ", cardNumber=" + Mask.lastFour(cardNumber) +
                '}';
    }
    
    public static class Builder {
        private String expiryDate;
        private String cardholder;
        private String cardNumber;

        public Builder withExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }
                
        public Builder withCardholder(String cardholder) {
            this.cardholder = cardholder;
            return this;
        }
        
        /** A new card number for the payment method. */
        public Builder withCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public UpdatePaymentMethodCardRequestInformation build() {
            UpdatePaymentMethodCardRequestInformation result = new UpdatePaymentMethodCardRequestInformation(expiryDate, cardholder);
            result.cardNumber = cardNumber;
            return result;
        }
    }
}
