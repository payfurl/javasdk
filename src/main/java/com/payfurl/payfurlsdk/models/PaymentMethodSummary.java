package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PaymentMethodSummary {
    private final String paymentMethodId;
    private final String customerId;
    private final String type;
    private final CardData card;
    private final String providerId;
    private final String providerType;
    private final Date dateAdded;
    private final String email;
    private final String vaultId;

    @JsonCreator
    public PaymentMethodSummary(@JsonProperty("PaymentMethodId") String paymentMethodId,
                                @JsonProperty("CustomerId") String customerId,
                                @JsonProperty("Type") String type,
                                @JsonProperty("Card") CardData card,
                                @JsonProperty("ProviderId") String providerId,
                                @JsonProperty("ProviderType") String providerType,
                                @JsonProperty("DateAdded") Date dateAdded,
                                @JsonProperty("Email") String email,
                                @JsonProperty("VaultId") String vaultId) {
        this.paymentMethodId = paymentMethodId;
        this.customerId = customerId;
        this.type = type;
        this.card = card;
        this.providerId = providerId;
        this.providerType = providerType;
        this.dateAdded = dateAdded;
        this.email = email;
        this.vaultId = vaultId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getType() {
        return type;
    }

    public CardData getCard() {
        return card;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderType() {
        return providerType;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public String getEmail() {
        return email;
    }

    public String getVaultId() {
        return vaultId;
    }

    public static class Builder {
        private String paymentMethodId;
        private String customerId;
        private String type;
        private CardData card;
        private String providerId;
        private String providerType;
        private Date dateAdded;
        private String email;
        private String vaultId;

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withCard(CardData card) {
            this.card = card;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withProviderType(String providerType) {
            this.providerType = providerType;
            return this;
        }

        public Builder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withVaultId(String vaultId) {
            this.vaultId = vaultId;
            return this;
        }

        public PaymentMethodSummary build() {
            return new PaymentMethodSummary(paymentMethodId, customerId, type, card, providerId, providerType, dateAdded, email, vaultId);
        }
    }
}
