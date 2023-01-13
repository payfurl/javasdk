package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeSearch {
    private final String paymentMethodId;
    private final String reference;
    private final BigDecimal amountGreaterThan;
    private final BigDecimal amountLessThan;
    private final String customerId;
    private final String status;
    private final Date addedAfter;
    private final Date addedBefore;
    private final Integer limit;
    private final Integer skip;
    private final String sortBy;

    @JsonCreator
    public ChargeSearch(@JsonProperty("PaymentMethodId") String paymentMethodId,
                        @JsonProperty("Reference") String reference,
                        @JsonProperty("AmountGreaterThan") BigDecimal amountGreaterThan,
                        @JsonProperty("AmountLessThan") BigDecimal amountLessThan,
                        @JsonProperty("CustomerId") String customerId,
                        @JsonProperty("Status") String status,
                        @JsonProperty("AddedAfter") Date addedAfter,
                        @JsonProperty("AddedBefore") Date addedBefore,
                        @JsonProperty("Limit") Integer limit,
                        @JsonProperty("Skip") Integer skip,
                        @JsonProperty("SortBy") String sortBy) {
        this.paymentMethodId = paymentMethodId;
        this.reference = reference;
        this.amountGreaterThan = amountGreaterThan;
        this.amountLessThan = amountLessThan;
        this.customerId = customerId;
        this.status = status;
        this.addedAfter = addedAfter;
        this.addedBefore = addedBefore;
        this.limit = limit;
        this.skip = skip;
        this.sortBy = sortBy;
    }

    @JsonProperty("PaymentMethodId")
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    @JsonProperty("Reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("AmountGreaterThan")
    public BigDecimal getAmountGreaterThan() {
        return amountGreaterThan;
    }

    @JsonProperty("AmountLessThan")
    public BigDecimal getAmountLessThan() {
        return amountLessThan;
    }

    @JsonProperty("CustomerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("AddedAfter")
    public Date getAddedAfter() {
        return addedAfter;
    }

    @JsonProperty("AddedBefore")
    public Date getAddedBefore() {
        return addedBefore;
    }

    @JsonProperty("Limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("Skip")
    public Integer getSkip() {
        return skip;
    }

    @JsonProperty("SortBy")
    public String getSortBy() {
        return sortBy;
    }

    @Override
    public String toString() {
        return "ChargeSearch{" +
                "paymentMethodId='" + paymentMethodId + '\'' +
                ", reference='" + reference + '\'' +
                ", amountGreaterThan=" + amountGreaterThan +
                ", amountLessThan=" + amountLessThan +
                ", customerId='" + customerId + '\'' +
                ", status='" + status + '\'' +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                ", limit=" + limit +
                ", skip=" + skip +
                ", sortBy='" + sortBy + '\'' +
                '}';
    }

    public static class Builder {
        private String paymentMethodId;
        private String reference;
        private BigDecimal amountGreaterThan;
        private BigDecimal amountLessThan;
        private String customerId;
        private String status;
        private Date addedAfter;
        private Date addedBefore;
        private Integer limit;
        private Integer skip;
        private String sortBy;

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder withAmountGreaterThan(BigDecimal amountGreaterThan) {
            this.amountGreaterThan = amountGreaterThan;
            return this;
        }

        public Builder withAmountLessThan(BigDecimal amountLessThan) {
            this.amountLessThan = amountLessThan;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
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

        public Builder withLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder withSkip(Integer skip) {
            this.skip = skip;
            return this;
        }

        public Builder withSortBy(String sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public ChargeSearch build() {
            return new ChargeSearch(paymentMethodId, reference, amountGreaterThan, amountLessThan, customerId, status, addedAfter, addedBefore, limit, skip, sortBy);
        }
    }
}
