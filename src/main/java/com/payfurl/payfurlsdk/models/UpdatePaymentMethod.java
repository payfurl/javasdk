package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class UpdatePaymentMethod {
    public final UpdatePaymentMethodCardRequestInformation card;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> metadata;

    @JsonCreator
    public UpdatePaymentMethod(@JsonProperty("Card") UpdatePaymentMethodCardRequestInformation card) {
        this.card = card;
    }

    public UpdatePaymentMethodCardRequestInformation getCard() {
        return card;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "UpdatePaymentMethod{" +
                "card='" + card + '\'' +
                ", metadata=" + metadata +
                '}';
    }
    
    public static class Builder {
        private UpdatePaymentMethodCardRequestInformation card;
        private Map<String, String> metadata;

        public Builder withCard(UpdatePaymentMethodCardRequestInformation card) {
            this.card = card;
            return this;
        }
        
        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public UpdatePaymentMethod build() {
            UpdatePaymentMethod result = new UpdatePaymentMethod(card);
            result.metadata = metadata;
            return result;
        }
    }
}
