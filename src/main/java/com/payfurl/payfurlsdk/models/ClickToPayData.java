package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClickToPayData {
    private final Address billingAddress;
    private final ClickToPayCardData cardData;

    @JsonCreator
    public ClickToPayData(@JsonProperty("BillingAddress") Address billingAddress,
                          @JsonProperty("CardData") ClickToPayCardData cardData) {
        this.billingAddress = billingAddress;
        this.cardData = cardData;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public ClickToPayCardData getCardData() {
        return cardData;
    }
}
