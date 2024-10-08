package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePaymentMethod {
    public final UpdatePaymentMethodCardRequestInformation card;

    @JsonCreator
    public UpdatePaymentMethod(@JsonProperty("Card") UpdatePaymentMethodCardRequestInformation card) {
        this.card = card;
    }

    public UpdatePaymentMethodCardRequestInformation getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "UpdatePaymentMethod{" +
                "card='" + card + '\'' +
                '}';
    }
    
    public static class Builder {
        private UpdatePaymentMethodCardRequestInformation card;

        public Builder withCard(UpdatePaymentMethodCardRequestInformation card) {
            this.card = card;
            return this;
        }
        
        public UpdatePaymentMethod build() {
            return new UpdatePaymentMethod(card);
        }
    }
}
