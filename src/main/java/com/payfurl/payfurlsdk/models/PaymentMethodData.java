package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class PaymentMethodData {
    private final String paymentMethodId;
    private final String userId;
    private final String customerId;
    private final String type;
    private final CardData card;
    private final BankPaymentData bankPayment;
    private final String providerId;
    private final String providerType;
    private final Date dateAdded;
    private final String email;
    private final String providerPaymentMethodId;
    private final Map<String, String> providerPaymentMethodData;
    private final CustomerDataSummary customer;
    private final ProviderSummary provider;
    private final PayToSummary payTo;
    private final NetworkTokenSummary networkToken;
    private final String vaultId;
    private final String payToStatus;
    private final String ip;
    private final Date dateRemoved;
    private final String token;
    private final String networkTokenId;
    private final String fallbackPaymentMethodId;
    private final Map<String, String> metadata;

    @JsonCreator
    public PaymentMethodData(@JsonProperty("PaymentMethodId") String paymentMethodId,
                             @JsonProperty("UserId") String userId,
                             @JsonProperty("CustomerId") String customerId,
                             @JsonProperty("Type") String type,
                             @JsonProperty("Card") CardData card,
                             @JsonProperty("BankPayment") BankPaymentData bankPayment,
                             @JsonProperty("ProviderId") String providerId,
                             @JsonProperty("ProviderType") String providerType,
                             @JsonProperty("DateAdded") Date dateAdded,
                             @JsonProperty("Email") String email,
                             @JsonProperty("ProviderPaymentMethodId") String providerPaymentMethodId,
                             @JsonProperty("ProviderPaymentMethodData") Map<String, String> providerPaymentMethodData,
                             @JsonProperty("Customer") CustomerDataSummary customer,
                             @JsonProperty("Provider") ProviderSummary provider,
                             @JsonProperty("PayTo") PayToSummary payTo,
                             @JsonProperty("NetworkToken") NetworkTokenSummary networkToken,
                             @JsonProperty("VaultId") String vaultId,
                             @JsonProperty("PayToStatus") String payToStatus,
                             @JsonProperty("Ip") String ip,
                             @JsonProperty("DateRemoved") Date dateRemoved,
                             @JsonProperty("Token") String token,
                             @JsonProperty("NetworkTokenId") String networkTokenId,
                             @JsonProperty("FallbackPaymentMethodId") String fallbackPaymentMethodId,
                             @JsonProperty("Metadata") Map<String, String> metadata) {
        this.paymentMethodId = paymentMethodId;
        this.userId = userId;
        this.customerId = customerId;
        this.type = type;
        this.card = card;
        this.bankPayment = bankPayment;
        this.providerId = providerId;
        this.providerType = providerType;
        this.dateAdded = dateAdded;
        this.email = email;
        this.providerPaymentMethodId = providerPaymentMethodId;
        this.providerPaymentMethodData = providerPaymentMethodData;
        this.customer = customer;
        this.provider = provider;
        this.payTo = payTo;
        this.networkToken = networkToken;
        this.vaultId = vaultId;
        this.payToStatus = payToStatus;
        this.ip = ip;
        this.dateRemoved = dateRemoved;
        this.token = token;
        this.networkTokenId = networkTokenId;
        this.fallbackPaymentMethodId = fallbackPaymentMethodId;
        this.metadata = metadata;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public String getUserId() {
        return userId;
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

    public BankPaymentData getBankPayment() {
        return bankPayment;
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

    public String getProviderPaymentMethodId() {
        return providerPaymentMethodId;
    }

    public Map<String, String> getProviderPaymentMethodData() {
        return providerPaymentMethodData;
    }

    public CustomerDataSummary getCustomer() {
        return customer;
    }

    public ProviderSummary getProvider() {
        return provider;
    }

    public PayToSummary getPayTo() {
        return payTo;
    }

    public NetworkTokenSummary getNetworkToken() {
        return networkToken;
    }

    public String getVaultId() {
        return vaultId;
    }

    public String getPayToStatus() {
        return payToStatus;
    }

    public String getIp() {
        return ip;
    }

    public Date getDateRemoved() {
        return dateRemoved;
    }

    public String getToken() {
        return token;
    }

    public String getNetworkTokenId() {
        return networkTokenId;
    }

    public String getFallbackPaymentMethodId() {
        return fallbackPaymentMethodId;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public static class Builder {
        private String paymentMethodId;
        private String userId;
        private String customerId;
        private String type;
        private CardData card;
        private BankPaymentData bankPayment;
        private String providerId;
        private String providerType;
        private Date dateAdded;
        private String email;
        private String providerPaymentMethodId;
        private Map<String, String> providerPaymentMethodData;
        private CustomerDataSummary customer;
        private ProviderSummary provider;
        private PayToSummary payTo;
        private NetworkTokenSummary networkToken;
        private String vaultId;
        private String payToStatus;
        private String ip;
        private Date dateRemoved;
        private String token;
        private String networkTokenId;
        private String fallbackPaymentMethodId;
        private Map<String, String> metadata;

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
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

        public Builder withBankPayment(BankPaymentData bankPayment) {
            this.bankPayment = bankPayment;
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

        public Builder withProviderPaymentMethodId(String providerPaymentMethodId) {
            this.providerPaymentMethodId = providerPaymentMethodId;
            return this;
        }

        public Builder withProviderPaymentMethodData(Map<String, String> providerPaymentMethodData) {
            this.providerPaymentMethodData = providerPaymentMethodData;
            return this;
        }

        public Builder withCustomer(CustomerDataSummary customer) {
            this.customer = customer;
            return this;
        }

        public Builder withProvider(ProviderSummary provider) {
            this.provider = provider;
            return this;
        }

        public Builder withPayTo(PayToSummary payTo) {
            this.payTo = payTo;
            return this;
        }

        public Builder withNetworkToken(NetworkTokenSummary networkToken) {
            this.networkToken = networkToken;
            return this;
        }

        public Builder withVaultId(String vaultId) {
            this.vaultId = vaultId;
            return this;
        }

        public Builder withPayToStatus(String payToStatus) {
            this.payToStatus = payToStatus;
            return this;
        }

        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder withDateRemoved(Date dateRemoved) {
            this.dateRemoved = dateRemoved;
            return this;
        }

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withNetworkTokenId(String networkTokenId) {
            this.networkTokenId = networkTokenId;
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

        public PaymentMethodData build() {
            return new PaymentMethodData(paymentMethodId, userId, customerId, type, card, bankPayment,
                    providerId, providerType, dateAdded, email, providerPaymentMethodId,
                    providerPaymentMethodData, customer, provider, payTo, networkToken,
                    vaultId, payToStatus, ip, dateRemoved, token, networkTokenId,
                    fallbackPaymentMethodId, metadata);
        }
    }
}
