package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePaymentMethodCardRequestInformation {
    public final String expiryDate;
    public final String cardholder;

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

    @Override
    public String toString() {
        return "UpdatePaymentMethodCardRequestInformation{" +
                ", expiryDate='" + expiryDate + '\'' +
                ", cardholder='" + cardholder + '\'' +
                '}';
    }
    
    public static class Builder {
        private String expiryDate;
        private String cardholder;

        public Builder withExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }
                
        public Builder withCardholder(String cardholder) {
            this.cardholder = cardholder;
            return this;
        }
        
        public UpdatePaymentMethodCardRequestInformation build() {
            return new UpdatePaymentMethodCardRequestInformation(expiryDate, cardholder);
        }
    }
}
