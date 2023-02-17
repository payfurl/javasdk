package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class WebhookRefund {
    private final BigDecimal amount;
    private final Date dateAdded;
    private final String comment;

    @JsonCreator
    public WebhookRefund(@JsonProperty("Amount") BigDecimal amount,
                         @JsonProperty("DateAdded") Date dateAdded,
                         @JsonProperty("Comment") String comment) {
        this.amount = amount;
        this.dateAdded = dateAdded;
        this.comment = comment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDateAdded() {
        return dateAdded;
    }


    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "WebhookRefund{" +
                "amount=" + amount +
                ", dateAdded=" + dateAdded +
                ", comment='" + comment + '\'' +
                '}';
    }

    public static class Builder {

        private BigDecimal amount;
        private Date dateAdded;
        private String comment;

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public WebhookRefund build() {
            return new WebhookRefund(amount, dateAdded, comment);
        }
    }
}

