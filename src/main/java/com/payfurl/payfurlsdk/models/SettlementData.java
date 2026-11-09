package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A charge a gateway settled: the items of {@code GET /settlements}, which answers with a JSON array of them
 * (the API's api.Models.Settlement).
 */
public class SettlementData {
    @JsonProperty("settlementId")
    private String settlementId;
    @JsonProperty("settlementDate")
    private Date settlementDate;
    @JsonProperty("createdDate")
    private Date createdDate;
    @JsonProperty("providerId")
    private String providerId;
    @JsonProperty("providerName")
    private String providerName;
    @JsonProperty("chargeId")
    private String chargeId;
    @JsonProperty("amount")
    private BigDecimal amount;

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

    @Override
    public String toString() {
        return "SettlementData{" +
                "settlementId='" + settlementId + '\'' +
                ", settlementDate=" + settlementDate +
                ", createdDate=" + createdDate +
                ", providerId='" + providerId + '\'' +
                ", providerName='" + providerName + '\'' +
                ", chargeId='" + chargeId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
