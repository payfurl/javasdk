package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class NewPaymentMethodCard {
    private final String providerId;
    private final CardRequestInformation paymentInformation;
    private final boolean vaultCard;
    private final Date vaultExpireDate;
    private final Integer vaultExpireSeconds;
    private final boolean setDefault;
    private final Map<String, String> metadata;
    private final boolean skipExpiryDateValidation;

    @JsonCreator
    public NewPaymentMethodCard(@JsonProperty("ProviderId") String providerId,
                                @JsonProperty("PaymentInformation") CardRequestInformation paymentInformation,
                                @JsonProperty("VaultCard") boolean vaultCard,
                                @JsonProperty("VaultExpireDate") Date vaultExpireDate,
                                @JsonProperty("VaultExpireSeconds") Integer vaultExpireSeconds,
                                @JsonProperty("SetDefault") boolean setDefault,
                                @JsonProperty("Metadata") Map<String, String> metadata,
                                @JsonProperty("SkipExpiryDateValidation") boolean skipExpiryDateValidation) {
        this.providerId = providerId;
        this.paymentInformation = paymentInformation;
        this.vaultCard = vaultCard;
        this.vaultExpireDate = vaultExpireDate;
        this.vaultExpireSeconds = vaultExpireSeconds;
        this.setDefault = setDefault;
        this.metadata = metadata;
        this.skipExpiryDateValidation = skipExpiryDateValidation;
    }

    public String getProviderId() {
        return providerId;
    }

    public CardRequestInformation getPaymentInformation() {
        return paymentInformation;
    }

    public boolean isVaultCard() {
        return vaultCard;
    }

    public Date getVaultExpireDate() {
        return vaultExpireDate;
    }

    public Integer getVaultExpireSeconds() {
        return vaultExpireSeconds;
    }

    public boolean getSetDefault() { return setDefault; }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public boolean getSkipExpiryDateValidation() { return skipExpiryDateValidation; }

    @Override
    public String toString() {
        return "NewPaymentMethodCard{" +
                "providerId=" + providerId +
                ", paymentInformation=" + paymentInformation +
                ", vaultCard=" + vaultCard +
                ", vaultExpireDate='" + vaultExpireDate + '\'' +
                ", vaultExpireSeconds=" + vaultExpireSeconds +
                ", setDefault=" + setDefault +
                ", metadata=" + metadata +
                ", skipExpiryDateValidation=" + skipExpiryDateValidation +
                '}';
    }

    public static class Builder {
        private String providerId;
        private CardRequestInformation paymentInformation;
        private boolean vaultCard = false;
        private Date vaultExpireDate;
        private Integer vaultExpireSeconds;
        private boolean setDefault = false;
        private Map<String, String> metadata;
        private boolean skipExpiryDateValidation = false;

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withPaymentInformation(CardRequestInformation paymentInformation) {
            this.paymentInformation = paymentInformation;
            return this;
        }

        public Builder withVaultCard(boolean vaultCard) {
            this.vaultCard = vaultCard;
            return this;
        }

        public Builder withVaultExpireDate(Date vaultExpireDate) {
            this.vaultExpireDate = vaultExpireDate;
            return this;
        }

        public Builder withVaultExpireSeconds(Integer vaultExpireSeconds) {
            this.vaultExpireSeconds = vaultExpireSeconds;
            return this;
        }

        public Builder withSetDefault(boolean setDefault) {
            this.setDefault = setDefault;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder withSkipExpiryDateValidation(boolean skipExpiryDateValidation) {
            this.skipExpiryDateValidation = skipExpiryDateValidation;
            return this;
        }

        public NewPaymentMethodCard build() {
            return new NewPaymentMethodCard(providerId, paymentInformation, vaultCard, vaultExpireDate, vaultExpireSeconds, setDefault, metadata, skipExpiryDateValidation);
        }
    }
}
