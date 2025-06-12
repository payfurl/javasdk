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
    private final Date dateRemoved;
    private final String providerPaymentMethodId;
    private final Map<String, String> providerPaymentMethodData;
    private final String token;
    private final String fallbackPaymentMethodId;
    private final Map<String, String> metadata;
    private final String networkTokenId;

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
                                @JsonProperty("DateRemoved") Date dateRemoved,
                                @JsonProperty("ProviderPaymentMethodId") String providerPaymentMethodId,
                                @JsonProperty("ProviderPaymentMethodData") Map<String, String> providerPaymentMethodData,
                                @JsonProperty("Token") String token,
                                @JsonProperty("FallbackPaymentMethodId") String fallbackPaymentMethodId,
                                @JsonProperty("Metadata") Map<String, String> metadata,
                                @JsonProperty("NetworkTokenId") String networkTokenId) {
        this.paymentMethodId = paymentMethodId;
        this.customerId = customerId;
        this.type = type;
        this.card = card;
        this.providerId = providerId;
        this.providerType = providerType;
        this.dateAdded = dateAdded;
        this.email = email;
        this.vaultId = vaultId;
        this.dateRemoved = dateRemoved;
        this.providerPaymentMethodId = providerPaymentMethodId;
        this.providerPaymentMethodData = providerPaymentMethodData;
        this.token = token;
        this.fallbackPaymentMethodId = fallbackPaymentMethodId;
        this.metadata = metadata;
        this.networkTokenId = networkTokenId;
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

    public Date getDateRemoved() {
        return dateRemoved;
    }

    public String getProviderPaymentMethodId() {
        return providerPaymentMethodId;
    }

    public Map<String, String> getProviderPaymentMethodData() {
        return providerPaymentMethodData;
    }

    public String getToken() {
        return token;
    }

    public String getFallbackPaymentMethodId() {
        return fallbackPaymentMethodId;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public String getNetworkTokenId() {
        return networkTokenId;
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
        private Date dateRemoved;
        private String providerPaymentMethodId;
        private Map<String, String> providerPaymentMethodData;
        private String token;
        private String fallbackPaymentMethodId;
        private Map<String, String> metadata;
        private String networkTokenId;

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

        public Builder withDateRemoved(Date dateRemoved) {
            this.dateRemoved = dateRemoved;
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

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withFallbackPaymentMethodId(String fallbackPaymentMethodId) {
            this.fallbackPaymentMethodId = fallbackPaymentMethodId;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder withNetworkTokenId(String networkTokenId) {
            this.networkTokenId = networkTokenId;
            return this;
        }

        public PaymentMethodSummary build() {
            return new PaymentMethodSummary(paymentMethodId, customerId, type, card, providerId,
                    providerType, dateAdded, email, vaultId, dateRemoved, providerPaymentMethodId,
                    providerPaymentMethodData, token, fallbackPaymentMethodId, metadata, networkTokenId);
        }
    }
}
