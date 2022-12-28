package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NewTokenCardRequest {

    private final CardRequestInformation paymentInformation;
    private final boolean vaultCard;
    private final Date vaultExpireDate;
    private final Integer vaultExpireSeconds;

    @JsonCreator
    public NewTokenCardRequest(@JsonProperty("PaymentInformation") CardRequestInformation paymentInformation,
                               @JsonProperty("VaultCard") boolean vaultCard,
                               @JsonProperty("VaultExpireDate") Date vaultExpireDate,
                               @JsonProperty("VaultExpireSeconds") Integer vaultExpireSeconds) {
        this.paymentInformation = paymentInformation;
        this.vaultCard = vaultCard;
        this.vaultExpireDate = vaultExpireDate;
        this.vaultExpireSeconds = vaultExpireSeconds == null ? 0 : vaultExpireSeconds;
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

    @Override
    public String toString() {
        return "NewTokenCardRequest{" +
                "paymentInformation=" + paymentInformation +
                ", vaultCard=" + vaultCard +
                ", vaultExpireDate=" + vaultExpireDate +
                ", vaultExpireSeconds=" + vaultExpireSeconds +
                '}';
    }

    public static class Builder {
        private CardRequestInformation paymentInformation;
        private boolean vaultCard;
        private Date vaultExpireDate;
        private Integer vaultExpireSeconds;

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

        public NewTokenCardRequest build() {
            return new NewTokenCardRequest(paymentInformation, vaultCard, vaultExpireDate, vaultExpireSeconds);
        }
    }
}
