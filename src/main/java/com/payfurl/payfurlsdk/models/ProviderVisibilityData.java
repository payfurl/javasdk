package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProviderVisibilityData {
    private final String currency;
    private final BigDecimal lessThanAmount;
    private final BigDecimal greaterThanAmount;
    private final Boolean applyCurrencyConversion;

    @JsonCreator
    public ProviderVisibilityData(@JsonProperty("Currency") String currency,
                                  @JsonProperty("LessThanAmount") BigDecimal lessThanAmount,
                                  @JsonProperty("GreaterThanAmount") BigDecimal greaterThanAmount,
                                  @JsonProperty("ApplyCurrencyConversion") Boolean applyCurrencyConversion) {

        this.currency = currency;
        this.lessThanAmount = lessThanAmount;
        this.greaterThanAmount = greaterThanAmount;
        this.applyCurrencyConversion = applyCurrencyConversion;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getLessThanAmount() {
        return lessThanAmount;
    }

    public BigDecimal getGreaterThanAmount() {
        return greaterThanAmount;
    }

    public Boolean getApplyCurrencyConversion() {
        return applyCurrencyConversion;
    }

    @Override
    public String toString() {
        return "ProviderVisibilityData{" +
                "currency=" + currency +
                " lessThanAmount=" + lessThanAmount +
                " greaterThanAmount=" + greaterThanAmount +
                " applyCurrencyConversion=" + applyCurrencyConversion +
                '}';
    }
}
