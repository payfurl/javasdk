package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookPaymentData {
    private final String paymentMethodId;
    private final WebhookCardData card;
    private final String type;

    @JsonCreator
    public WebhookPaymentData(@JsonProperty("PaymentMethodId") String paymentMethodId,
                              @JsonProperty("Card") WebhookCardData card,
                              @JsonProperty("Type") String type) {
        this.paymentMethodId = paymentMethodId;
        this.card = card;
        this.type = type;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public WebhookCardData getCard() {
        return card;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "WebhookPaymentData{" +
                "paymentMethodId='" + paymentMethodId + '\'' +
                ", card=" + card +
                ", type='" + type + '\'' +
                '}';
    }

    public static class Builder {

        private String paymentMethodId;
        private WebhookCardData card;
        private String type;

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder withCard(WebhookCardData card) {
            this.card = card;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public WebhookPaymentData build() {
            return new WebhookPaymentData(paymentMethodId, card, type);
        }
    }
}

