package com.payfurl.payfurlsdk.models;

import java.math.BigDecimal;

public class ProviderCostDataModel {
    private Boolean isInternationalCard;
    private String cardScheme;
    private BigDecimal transactionCost;
    private BigDecimal transactionPercentage;

    public Boolean getIsInternationalCard() {
        return this.isInternationalCard;
    }

    public void setIsInternationalCard(Boolean isInternationalCard) {
        this.isInternationalCard = isInternationalCard;
    }

    public String getCardScheme() {
        return this.cardScheme;
    }

    public void setCardScheme(String cardScheme) {
        this.cardScheme = cardScheme;
    }

    public BigDecimal getTransactionCost() {
        return this.transactionCost;
    }

    public void setTransactionCost(BigDecimal transactionCost) {
        this.transactionCost = transactionCost;
    }

    public BigDecimal getTransactionPercentage() {
        return this.transactionPercentage;
    }

    public void setTransactionPercentage(BigDecimal transactionPercentage) {
        this.transactionPercentage = transactionPercentage;
    }
}
