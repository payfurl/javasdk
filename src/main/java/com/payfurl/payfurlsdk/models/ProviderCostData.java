package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProviderCostData {
    private final Boolean isInternationalCard;
    private final String cardScheme;
    private final BigDecimal transactionCost;
    private final BigDecimal transactionPercentage;

    @JsonCreator
    public ProviderCostData(@JsonProperty("IsInternationalCard") Boolean isInternationalCard,
                            @JsonProperty("CardScheme") String cardScheme,
                            @JsonProperty("TransactionCost") BigDecimal transactionCost,
                            @JsonProperty("TransactionPercentage") BigDecimal transactionPercentage) {

        this.isInternationalCard = isInternationalCard;
        this.cardScheme = cardScheme;
        this.transactionCost = transactionCost;
        this.transactionPercentage = transactionPercentage;
    }

    public Boolean getInternationalCard() {
        return isInternationalCard;
    }

    public String getCardScheme() {
        return cardScheme;
    }

    public BigDecimal getTransactionCost() {
        return transactionCost;
    }

    public BigDecimal getTransactionPercentage() {
        return transactionPercentage;
    }

    @Override
    public String toString() {
        return "ProviderCostData{" +
                "isInternationalCard=" + isInternationalCard +
                ", getCardScheme='" + cardScheme + '\'' +
                ", transactionCost='" + transactionCost + '\'' +
                ", transactionPercentage='" + transactionPercentage + '\'' +
                '}';
    }
}
