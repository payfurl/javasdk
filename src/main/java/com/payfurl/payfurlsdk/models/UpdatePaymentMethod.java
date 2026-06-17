package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class UpdatePaymentMethod {
    public final UpdatePaymentMethodCardRequestInformation card;
    public final Map<String, String> metadata;

    @JsonCreator
    public UpdatePaymentMethod(@JsonProperty("Card") UpdatePaymentMethodCardRequestInformation card,
                               @JsonProperty("Metadata") Map<String, String> metadata) {
        this.card = card;
        this.metadata = metadata;
    }

    public UpdatePaymentMethod(UpdatePaymentMethodCardRequestInformation card) {
        this(card, null);
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
            return new UpdatePaymentMethod(card, metadata);
        }
    }
}
