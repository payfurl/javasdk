package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class PaymentTokenData {
    private final String tokenId;
    private final String providerId;
    private final Date dateAdded;
    private final String vaultId;
    private final String type;
    private final String payToStatus;

    @JsonCreator
    public PaymentTokenData(@JsonProperty("TokenId") String tokenId,
                            @JsonProperty("ProviderId") String providerId,
                            @JsonProperty("DateAdded") Date dateAdded,
                            @JsonProperty("VaultId") String vaultId,
                            @JsonProperty("Type") String type,
                            @JsonProperty("PayToStatus") String payToStatus) {

        this.tokenId = tokenId;
        this.providerId = providerId;
        this.dateAdded = dateAdded;
        this.vaultId = vaultId;
        this.type = type;
        this.payToStatus = payToStatus;
    }

    public String getTokenId() {
        return tokenId;
    }

    public String getProviderId() {
        return providerId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public String getVaultId() {
        return vaultId;
    }

    public String getType() {
        return type;
    }

    public String getPayToStatus() {
        return payToStatus;
    }


    @Override
    public String toString() {
        return "PaymentTokenData{" +
                "tokenId=" + tokenId +
                ", providerId=" + providerId +
                ", dateAdded=" + dateAdded +
                ", vaultId=" + vaultId +
                ", type=" + type +
                ", payToStatus=" + payToStatus +
                '}';
    }

    public class Builder {
        private String tokenId;
        private String providerId;
        private Date dateAdded;
        private String vaultId;
        private String type;

        private String payToStatus;

        public Builder withTokenId(String tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withVaultId(String vaultId) {
            this.vaultId = vaultId;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withPayToStatus(String payToStatus) {
            this.payToStatus = payToStatus;
            return this;
        }

        public PaymentTokenData build() {
            return new PaymentTokenData(tokenId, providerId, dateAdded, vaultId, type, payToStatus);
        }
    }
}


