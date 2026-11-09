package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Click to Pay card and billing address of a token: {@link TokenData#getClickToPay()}.
 */
public class ClickToPayData {
    @JsonProperty("billingAddress")
    private Address billingAddress;
    @JsonProperty("cardData")
    private ClickToPayCardData cardData;

    public Address getBillingAddress() {
        return billingAddress;
    }

    public ClickToPayCardData getCardData() {
        return cardData;
    }

    @Override
    public String toString() {
        return "ClickToPayData{" +
                "billingAddress=" + billingAddress +
                ", cardData=" + cardData +
                '}';
    }
}
