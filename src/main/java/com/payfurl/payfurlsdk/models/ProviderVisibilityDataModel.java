package com.payfurl.payfurlsdk.models;

import java.math.BigDecimal;

public class ProviderVisibilityDataModel {
    private String currency;
    private BigDecimal lessThanAmount;
    private BigDecimal greaterThanAmount;
    private boolean applyCurrencyConversion;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getLessThanAmount() {
        return this.lessThanAmount;
    }

    public void setLessThanAmount(BigDecimal lessThanAmount) {
        this.lessThanAmount = lessThanAmount;
    }

    public BigDecimal getGreaterThanAmount() {
        return this.greaterThanAmount;
    }

    public void setGreaterThanAmount(BigDecimal greaterThanAmount) {
        this.greaterThanAmount = greaterThanAmount;
    }

    public boolean isApplyCurrencyConversion() {
        return this.applyCurrencyConversion;
    }

    public void setApplyCurrencyConversion(boolean applyCurrencyConversion) {
        this.applyCurrencyConversion = applyCurrencyConversion;
    }
}
