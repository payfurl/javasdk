package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class VaultData {
    private final String vaultId;
    private final Date createdDate;
    private final Date expireDate;
    private final String maskedCardNumber;
    private final boolean hasCvv;

    @JsonCreator
    public VaultData(@JsonProperty("VaultId") String vaultId,
                     @JsonProperty("CreatedDate") Date createdDate,
                     @JsonProperty("ExpireDate") Date expireDate,
                     @JsonProperty("MaskedCardNumber") String maskedCardNumber,
                     @JsonProperty("HasCvv") boolean hasCvv) {
        this.vaultId = vaultId;
        this.createdDate = createdDate;
        this.expireDate = expireDate;
        this.maskedCardNumber = maskedCardNumber;
        this.hasCvv = hasCvv;
    }

    public String getVaultId() {
        return vaultId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    public boolean isHasCvv() {
        return hasCvv;
    }

    @Override
    public String toString() {
        return "VaultData{" +
                "vaultId='" + vaultId + '\'' +
                ", createdDate=" + createdDate +
                ", expireDate=" + expireDate +
                ", maskedCardNumber='" + maskedCardNumber + '\'' +
                ", hasCvv=" + hasCvv +
                '}';
    }

    public static class Builder {
        private String vaultId;
        private Date createdDate;
        private Date expireDate;
        private String maskedCardNumber;
        private boolean hasCvv;

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

        public VaultData build() {
            return new VaultData(vaultId, createdDate, expireDate, maskedCardNumber, hasCvv);
        }
    }
}
