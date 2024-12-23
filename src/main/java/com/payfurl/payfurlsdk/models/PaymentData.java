package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentData {
    public final CardData cardData;
    public final String type;
    public final String displayType;
    public final String providerType;
    public final String email;

    @JsonCreator
    public PaymentData(@JsonProperty("CardData") CardData cardData,
                       @JsonProperty("Type") String type,
                       @JsonProperty("DisplayType") String displayType,
                       @JsonProperty("ProviderType") String providerType,
                       @JsonProperty("Email") String email) {
        this.cardData = cardData;
        this.type = type;
        this.displayType = displayType;
        this.providerType = providerType;
        this.email = email;
    }

    public CardData getCardData() {
        return cardData;
    }

    public String getType() {
        return type;
    }
    
    public String getDisplayType() {
        return displayType;
    }

    public String getProviderType() {
        return providerType;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "PaymentData{" +
                "cardData=" + cardData +
                ", type='" + type + '\'' +
                ", displayType='" + displayType + '\'' +
                ", providerType='" + providerType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
