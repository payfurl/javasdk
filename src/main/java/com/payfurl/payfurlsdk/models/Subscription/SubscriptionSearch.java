package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class SubscriptionSearch {
    private final Integer limit;
    private final Integer skip;
    private final Date addedAfter;
    private final Date addedBefore;
    private final BigDecimal amountLessThan;
    private final BigDecimal amountGreaterThan;
    private final String currency;
    private final String status;
    private final String sortBy;

    @JsonCreator
    public SubscriptionSearch(@JsonProperty("Limit") Integer limit,
                              @JsonProperty("Skip") Integer skip,
                              @JsonProperty("AddedAfter") Date addedAfter,
                              @JsonProperty("AddedBefore") Date addedBefore,
                              @JsonProperty("AmountLessThan") BigDecimal amountLessThan,
                              @JsonProperty("AmountGreaterThan") BigDecimal amountGreaterThan,
                              @JsonProperty("Currency") String currency,
                              @JsonProperty("Status") String status,
                              @JsonProperty("SortBy") String sortBy) {

        this.limit = limit;
        this.skip = skip;
        this.addedAfter = addedAfter;
        this.addedBefore = addedBefore;
        this.amountLessThan = amountLessThan;
        this.amountGreaterThan = amountGreaterThan;
        this.currency = currency;
        this.status = status;
        this.sortBy = sortBy;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }

    public Date getAddedAfter() {
        return addedAfter;
    }

    public Date getAddedBefore() {
        return addedBefore;
    }

    public BigDecimal getAmountLessThan() { return amountLessThan; }

    public BigDecimal getAmountGreaterThan() { return amountGreaterThan; }

    public String getCurrency() { return currency; }

    public String getStatus() { return status; }

    public String getSortBy() { return sortBy; }

    @Override
    public String toString() {
        return "BatchSearch{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                ", amountLessThan=" + amountLessThan +
                ", amountGreaterThan=" + amountGreaterThan +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", sortBy='" + sortBy + '\'' +
                '}';
    }

    public static class Builder {
        private Integer limit;
        private Integer skip;
        private Date addedAfter;
        private Date addedBefore;
        private BigDecimal amountLessThan;
        private BigDecimal amountGreaterThan;
        private String currency;
        private String status;
        private String sortBy;

        public Builder withLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder withSkip(Integer skip) {
            this.skip = skip;
            return this;
        }

        public Builder withAddedAfter(Date addedAfter) {
            this.addedAfter = addedAfter;
            return this;
        }

        public Builder withAddedBefore(Date addedBefore) {
            this.addedBefore = addedBefore;
            return this;
        }

        public  Builder withAmountLessThan(BigDecimal amountLessThan) {
            this.amountLessThan = amountLessThan;
            return this;
        }

        public  Builder withAmountGreaterThan(BigDecimal amountGreaterThan) {
            this.amountGreaterThan = amountGreaterThan;
            return this;
        }

        public  Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public  Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public  Builder withSortBy(String sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public SubscriptionSearch build() {
            return new SubscriptionSearch(limit, skip, addedAfter, addedBefore, amountLessThan, amountGreaterThan, currency, status, sortBy);
        }
    }
}
