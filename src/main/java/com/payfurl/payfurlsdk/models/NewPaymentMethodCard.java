package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NewPaymentMethodCard {
    private final String providerId;
    private final CardRequestInformation paymentInformation;
    private final boolean vaultCard;
    private final Date vaultExpireDate;
    private final Integer vaultExpireSeconds;
    private final boolean setDefault;

    @JsonCreator
    public NewPaymentMethodCard(@JsonProperty("ProviderId") String providerId,
                                @JsonProperty("PaymentInformation") CardRequestInformation paymentInformation,
                                @JsonProperty("VaultCard") boolean vaultCard,
                                @JsonProperty("VaultExpireDate") Date vaultExpireDate,
                                @JsonProperty("VaultExpireSeconds") Integer vaultExpireSeconds,
                                @JsonProperty("SetDefault") boolean setDefault) {
        this.providerId = providerId;
        this.paymentInformation = paymentInformation;
        this.vaultCard = vaultCard;
        this.vaultExpireDate = vaultExpireDate;
        this.vaultExpireSeconds = vaultExpireSeconds;
        this.setDefault = setDefault;
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

    public static class Builder {
        private String providerId;
        private CardRequestInformation paymentInformation;
        private boolean vaultCard = false;
        private Date vaultExpireDate;
        private Integer vaultExpireSeconds;
        private boolean setDefault = false;

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

        public NewPaymentMethodCard build() {
            return new NewPaymentMethodCard(providerId, paymentInformation, vaultCard, vaultExpireDate, vaultExpireSeconds, setDefault);
        }
    }
}
