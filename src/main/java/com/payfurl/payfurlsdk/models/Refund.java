package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class Refund {
    private final BigDecimal amount;
    private final Date dateAdded;

    @JsonCreator
    public Refund(@JsonProperty("Amount") BigDecimal amount,
                  @JsonProperty("DateAdded") Date dateAdded) {
        this.amount = amount;
        this.dateAdded = dateAdded;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    @Override
    public String toString() {
        return "Refund{" +
                "amount=" + amount +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
