package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class VaultDataWithPci extends VaultData {
    private final String cardNumber;
    private final String ccv;

    @JsonCreator
    public VaultDataWithPci(@JsonProperty("VaultId") String vaultId,
                            @JsonProperty("CreatedDate") Date createdDate,
                            @JsonProperty("ExpireDate") Date expireDate,
                            @JsonProperty("MaskedCardNumber") String maskedCardNumber,
                            @JsonProperty("HasCvv") boolean hasCvv,
                            @JsonProperty("CardNumber") String cardNumber,
                            @JsonProperty("Ccv") String ccv) {
        super(vaultId, createdDate, expireDate, maskedCardNumber, hasCvv);

        this.cardNumber = cardNumber;
        this.ccv = ccv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCcv() {
        return ccv;
    }

    @Override
    public String toString() {
        return "VaultDataWithPci{" +
                "cardNumber='" + cardNumber + '\'' +
                ", ccv='" + ccv + '\'' +
                '}';
    }

    public static class Builder {
        private String vaultId;
        private Date createdDate;
        private Date expireDate;
        private String maskedCardNumber;
        private boolean hasCvv;
        private String cardNumber;
        private String ccv;

        public Builder withVaultId(String vaultId) {
            this.vaultId = vaultId;
            return this;
        }

        public Builder withCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder withExpireDate(Date expireDate) {
            this.expireDate = expireDate;
            return this;
        }

        public Builder withMaskedCardNumber(String maskedCardNumber) {
            this.maskedCardNumber = maskedCardNumber;
            return this;
        }

        public Builder withHasCvv(boolean hasCvv) {
            this.hasCvv = hasCvv;
            return this;
        }

        public Builder withCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder withCcv(String ccv) {
            this.ccv = ccv;
            return this;
        }

        public VaultDataWithPci build() {
            return new VaultDataWithPci(vaultId, createdDate, expireDate, maskedCardNumber, hasCvv, cardNumber, ccv);
        }
    }
}
