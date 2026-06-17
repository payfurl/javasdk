package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class SettlementData {
    private final String settlementId;
    private final Date settlementDate;
    private final Date createdDate;
    private final String providerId;
    private final String providerName;
    private final String chargeId;
    private final BigDecimal amount;

    @JsonCreator
    public SettlementData(@JsonProperty("SettlementId") String settlementId,
                          @JsonProperty("SettlementDate") Date settlementDate,
                          @JsonProperty("CreatedDate") Date createdDate,
                          @JsonProperty("ProviderId") String providerId,
                          @JsonProperty("ProviderName") String providerName,
                          @JsonProperty("ChargeId") String chargeId,
                          @JsonProperty("Amount") BigDecimal amount) {
        this.settlementId = settlementId;
        this.settlementDate = settlementDate;
        this.createdDate = createdDate;
        this.providerId = providerId;
        this.providerName = providerName;
        this.chargeId = chargeId;
        this.amount = amount;
    }

    public String getSettlementId() {
        return settlementId;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getChargeId() {
        return chargeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
