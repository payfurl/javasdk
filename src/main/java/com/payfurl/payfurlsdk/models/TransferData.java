package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class TransferData {
    private final String transferId;
    private final String status;
    private final String reference;
    private final String groupReference;
    private final Date dateAdded;
    private final BigDecimal totalAmount;
    private final String providerId;
    private final BigDecimal amount;
    private final String currency;
    private final String account;
    private final String chargeId;

    @JsonCreator
    public TransferData(@JsonProperty("TransferId") String transferId,
                        @JsonProperty("Status") String status,
                        @JsonProperty("Reference") String reference,
                        @JsonProperty("GroupReference") String groupReference,
                        @JsonProperty("DateAdded") Date dateAdded,
                        @JsonProperty("TotalAmount") BigDecimal totalAmount,
                        @JsonProperty("ProviderId") String providerId,
                        @JsonProperty("Amount") BigDecimal amount,
                        @JsonProperty("Currency") String currency,
                        @JsonProperty("Account") String account,
                        @JsonProperty("ChargeId") String chargeId) {
        this.transferId = transferId;
        this.status = status;
        this.reference = reference;
        this.groupReference = groupReference;
        this.dateAdded = dateAdded;
        this.totalAmount = totalAmount;
        this.providerId = providerId;
        this.amount = amount;
        this.currency = currency;
        this.account = account;
        this.chargeId = chargeId;
    }

    public String getTransferId() {
        return transferId;
    }

    public String getStatus() {
        return status;
    }

    public String getReference() {
        return reference;
    }

    public String getGroupReference() {
        return groupReference;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getProviderId() {
        return providerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAccount() {
        return account;
    }

    public String getChargeId() {
        return chargeId;
    }

    @Override
    public String toString() {
        return "TransferData{" +
                "transferId='" + transferId + '\'' +
                ", status='" + status + '\'' +
                ", reference='" + reference + '\'' +
                ", groupReference='" + groupReference + '\'' +
                ", dateAdded=" + dateAdded +
                ", totalAmount=" + totalAmount +
                ", providerId='" + providerId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", account='" + account + '\'' +
                ", chargeId='" + chargeId + '\'' +
                '}';
    }

    public static class Builder {
        private String transferId;
        private String status;
        private String reference;
        private String groupReference;
        private Date dateAdded;
        private BigDecimal totalAmount;
        private String providerId;
        private BigDecimal amount;
        private String currency;
        private String account;
        private String chargeId;

        public Builder withTransferId(String transferId) {
            this.transferId = transferId;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder withGroupReference(String groupReference) {
            this.groupReference = groupReference;
            return this;
        }

        public Builder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withAccount(String account) {
            this.account = account;
            return this;
        }

        public Builder withChargeId(String chargeId) {
            this.chargeId = chargeId;
            return this;
        }

        public TransferData build() {
            return new TransferData(transferId, status, reference, groupReference, dateAdded, totalAmount, providerId, amount, currency, account, chargeId);
        }
    }
}
