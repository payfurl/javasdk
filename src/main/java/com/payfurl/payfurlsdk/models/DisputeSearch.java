package com.payfurl.payfurlsdk.models;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The query of {@code GET /dispute} (DisputeController.Search): every field is optional, and null leaves it out.
 */
public class DisputeSearch {
    private final String transactionId;
    private final String providerId;
    private final DisputeStatus status;
    private final DisputeStage stage;
    private final String reasonCategory;
    private final String currency;
    private final BigDecimal amountGreaterThan;
    private final BigDecimal amountLessThan;
    private final Date addedAfter;
    private final Date addedBefore;
    private final Date dueBefore;
    private final String sortBy;
    private final String sortOrder;
    private final Integer limit;
    private final Integer skip;

    private DisputeSearch(Builder builder) {
        this.transactionId = builder.transactionId;
        this.providerId = builder.providerId;
        this.status = builder.status;
        this.stage = builder.stage;
        this.reasonCategory = builder.reasonCategory;
        this.currency = builder.currency;
        this.amountGreaterThan = builder.amountGreaterThan;
        this.amountLessThan = builder.amountLessThan;
        this.addedAfter = builder.addedAfter;
        this.addedBefore = builder.addedBefore;
        this.dueBefore = builder.dueBefore;
        this.sortBy = builder.sortBy;
        this.sortOrder = builder.sortOrder;
        this.limit = builder.limit;
        this.skip = builder.skip;
    }

    /** The disputed charge's ID. */
    public String getTransactionId() {
        return transactionId;
    }

    public String getProviderId() {
        return providerId;
    }

    public DisputeStatus getStatus() {
        return status;
    }

    public DisputeStage getStage() {
        return stage;
    }

    public String getReasonCategory() {
        return reasonCategory;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmountGreaterThan() {
        return amountGreaterThan;
    }

    public BigDecimal getAmountLessThan() {
        return amountLessThan;
    }

    public Date getAddedAfter() {
        return addedAfter;
    }

    public Date getAddedBefore() {
        return addedBefore;
    }

    /** Disputes whose evidence is due before this time. */
    public Date getDueBefore() {
        return dueBefore;
    }

    /** {@code Date} (the default), {@code Status}, {@code Amount}, {@code EvidenceDueBy} or {@code ProviderId}. */
    public String getSortBy() {
        return sortBy;
    }

    /** {@code Asc} or {@code Desc}. */
    public String getSortOrder() {
        return sortOrder;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }

    @Override
    public String toString() {
        return "DisputeSearch{" +
                "transactionId=" + transactionId +
                ", providerId=" + providerId +
                ", status=" + status +
                ", stage=" + stage +
                ", reasonCategory=" + reasonCategory +
                ", currency=" + currency +
                ", amountGreaterThan=" + amountGreaterThan +
                ", amountLessThan=" + amountLessThan +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                ", dueBefore=" + dueBefore +
                ", sortBy=" + sortBy +
                ", sortOrder=" + sortOrder +
                ", limit=" + limit +
                ", skip=" + skip +
                '}';
    }

    public static class Builder {
        private String transactionId;
        private String providerId;
        private DisputeStatus status;
        private DisputeStage stage;
        private String reasonCategory;
        private String currency;
        private BigDecimal amountGreaterThan;
        private BigDecimal amountLessThan;
        private Date addedAfter;
        private Date addedBefore;
        private Date dueBefore;
        private String sortBy;
        private String sortOrder;
        private Integer limit;
        private Integer skip;

        /** The disputed charge's ID. */
        public Builder withTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withStatus(DisputeStatus status) {
            this.status = status;
            return this;
        }

        public Builder withStage(DisputeStage stage) {
            this.stage = stage;
            return this;
        }

        public Builder withReasonCategory(String reasonCategory) {
            this.reasonCategory = reasonCategory;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
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

        public Builder withAddedAfter(Date addedAfter) {
            this.addedAfter = addedAfter;
            return this;
        }

        public Builder withAddedBefore(Date addedBefore) {
            this.addedBefore = addedBefore;
            return this;
        }

        /** Disputes whose evidence is due before this time. */
        public Builder withDueBefore(Date dueBefore) {
            this.dueBefore = dueBefore;
            return this;
        }

        /** {@code Date} (the default), {@code Status}, {@code Amount}, {@code EvidenceDueBy} or {@code ProviderId}. */
        public Builder withSortBy(String sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        /** {@code Asc} or {@code Desc}. */
        public Builder withSortOrder(String sortOrder) {
            this.sortOrder = sortOrder;
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

        public DisputeSearch build() {
            return new DisputeSearch(this);
        }
    }
}
