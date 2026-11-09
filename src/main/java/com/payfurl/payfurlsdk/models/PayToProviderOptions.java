package com.payfurl.payfurlsdk.models;

import java.math.BigDecimal;

public class PayToProviderOptions {
    private String providerId;
    private BigDecimal maximumAmount;
    private String description;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    /** The provider's PayTo maximum amount. */
    public BigDecimal getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(BigDecimal maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
