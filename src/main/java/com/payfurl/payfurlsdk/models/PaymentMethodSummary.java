package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

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
    private final String providerPaymentMethodId;
    private final Map<String, String> providerPaymentMethodData;
    private final Map<String, String> metadata;

    @JsonCreator
    public PaymentMethodSummary(@JsonProperty("PaymentMethodId") String paymentMethodId,
                                @JsonProperty("CustomerId") String customerId,
                                @JsonProperty("Type") String type,
                                @JsonProperty("Card") CardData card,
                                @JsonProperty("ProviderId") String providerId,
                                @JsonProperty("ProviderType") String providerType,
                                @JsonProperty("DateAdded") Date dateAdded,
                                @JsonProperty("Email") String email,
                                @JsonProperty("VaultId") String vaultId,
                                @JsonProperty("ProviderPaymentMethodId") String providerPaymentMethodId,
                                @JsonProperty("ProviderPaymentMethodData") Map<String, String> providerPaymentMethodData,
                                @JsonProperty("Metadata") Map<String, String> metadata) {
        this.paymentMethodId = paymentMethodId;
        this.customerId = customerId;
        this.type = type;
        this.card = card;
        this.providerId = providerId;
        this.providerType = providerType;
        this.dateAdded = dateAdded;
        this.email = email;
        this.vaultId = vaultId;
        this.providerPaymentMethodId = providerPaymentMethodId;
        this.providerPaymentMethodData = providerPaymentMethodData;
        this.metadata = metadata;
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

    public String getProviderPaymentMethodId() {
        return providerPaymentMethodId;
    }
    public Map<String, String> getProviderPaymentMethodData() {
        return providerPaymentMethodData;
    }

    public Map<String, String> getMetadata() {
        return metadata;
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
        private String providerPaymentMethodId;
        private Map<String, String> providerPaymentMethodData;
        private Map<String, String> metadata;

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

        public Builder withProviderPaymentMethodId(String providerPaymentMethodId) {
            this.providerPaymentMethodId = providerPaymentMethodId;
            return this;
        }

        public Builder withProviderPaymentMethodData(Map<String, String> providerPaymentMethodData) {
            this.providerPaymentMethodData = providerPaymentMethodData;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public PaymentMethodSummary build() {
            return new PaymentMethodSummary(paymentMethodId, customerId, type, card, providerId, providerType, dateAdded, email, vaultId, providerPaymentMethodId, providerPaymentMethodData, metadata);
        }
    }
}
