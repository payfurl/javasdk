package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class NewTokenCardLeastCostRequest {

    private final CardRequestInformation paymentInformation;
    private final boolean vaultCard;
    private final Date vaultExpireDate;
    private final Integer vaultExpireSeconds;
    private final BigDecimal amount;
    private final String currency;

    @JsonCreator
    public NewTokenCardLeastCostRequest(@JsonProperty("PaymentInformation") CardRequestInformation paymentInformation,
                                        @JsonProperty("VaultCard") boolean vaultCard,
                                        @JsonProperty("VaultExpireDate") Date vaultExpireDate,
                                        @JsonProperty("VaultExpireSeconds") Integer vaultExpireSeconds,
                                        @JsonProperty("Amount") BigDecimal amount,
                                        @JsonProperty("Currency") String currency) {
        this.paymentInformation = paymentInformation;
        this.vaultCard = vaultCard;
        this.vaultExpireDate = vaultExpireDate;
        this.vaultExpireSeconds = vaultExpireSeconds == null ? 0 : vaultExpireSeconds;
        this.amount = amount;
        this.currency = currency;
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
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public static class Builder {
        private CardRequestInformation paymentInformation;
        private boolean vaultCard;
        private Date vaultExpireDate;
        private Integer vaultExpireSeconds;
        private BigDecimal amount;
        private String currency;

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

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withVaultExpireSeconds(Integer vaultExpireSeconds) {
            this.vaultExpireSeconds = vaultExpireSeconds;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public NewTokenCardLeastCostRequest build() {
            return new NewTokenCardLeastCostRequest(paymentInformation, vaultCard, vaultExpireDate, vaultExpireSeconds, amount, currency);
        }
    }
}