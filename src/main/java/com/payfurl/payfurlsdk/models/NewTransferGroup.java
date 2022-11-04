package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NewTransferGroup {
    private final String providerId;
    private final String groupReference;
    private final String chargeId;
    private final List<NewTransfer> transfers;

    @JsonCreator
    public NewTransferGroup(@JsonProperty("ProviderId") String providerId,
                            @JsonProperty("GroupReference") String groupReference,
                            @JsonProperty("ChargeId") String chargeId,
                            @JsonProperty("Transfers") List<NewTransfer> transfers) {
        this.providerId = providerId;
        this.groupReference = groupReference;
        this.chargeId = chargeId;
        this.transfers = transfers;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getGroupReference() {
        return groupReference;
    }

    public String getChargeId() {
        return chargeId;
    }

    public List<NewTransfer> getTransfers() {
        return transfers;
    }

    @Override
    public String toString() {
        return "NewTransferGroup{" +
                "providerId='" + providerId + '\'' +
                ", groupReference='" + groupReference + '\'' +
                ", chargeId='" + chargeId + '\'' +
                ", transfers=" + transfers +
                '}';
    }

    public static class Builder {
        private String providerId;
        private String groupReference;
        private String chargeId;
        private List<NewTransfer> transfers;

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withGroupReference(String groupReference) {
            this.groupReference = groupReference;
            return this;
        }

        public Builder withChargeId(String chargeId) {
            this.chargeId = chargeId;
            return this;
        }

        public Builder withTransfers(List<NewTransfer> transfers) {
            this.transfers = transfers;
            return this;
        }

        public NewTransferGroup build() {
            return new NewTransferGroup(providerId, groupReference, chargeId, transfers);
        }
    }
}
