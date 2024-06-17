package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class SubscriptionEnd {

    private final int count;
    private final BigDecimal amount;
    private final Date date;

    @JsonCreator
    public SubscriptionEnd(@JsonProperty("Count") int count,
                           @JsonProperty("Amount") BigDecimal amount,
                           @JsonProperty("Date") Date date) {


        this.count = count;
        this.amount = amount;
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "SubscriptionEnd{" +
                "count='" + count + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    public static class Builder {
        private int count;
        private BigDecimal amount;
        private Date date;

        public SubscriptionEnd build() {
            return new SubscriptionEnd(count, amount, date);
        }

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }
    }
}
