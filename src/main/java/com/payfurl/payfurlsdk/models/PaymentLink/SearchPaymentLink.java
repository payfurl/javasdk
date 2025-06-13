package com.payfurl.payfurlsdk.models.PaymentLink;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SearchPaymentLink {
    private final Integer limit;
    private final Integer skip;
    private final Date addedAfter;
    private final Date addedBefore;
    private final SortBy sort;

    public enum SortBy {
        None,
        Date,
        Title
    }

    @JsonCreator
    public SearchPaymentLink(
            @JsonProperty("Limit") Integer limit,
            @JsonProperty("Skip") Integer skip,
            @JsonProperty("AddedAfter") Date addedAfter,
            @JsonProperty("AddedBefore") Date addedBefore,
            @JsonProperty("Sort") SortBy sort) {
        this.limit = limit;
        this.skip = skip;
        this.addedAfter = addedAfter;
        this.addedBefore = addedBefore;
        this.sort = sort;
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

    public SortBy getSort() {
        return sort;
    }

    @Override
    public String toString() {
        return "SearchPaymentLink{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                ", sort=" + sort +
                '}';
    }

    public static class Builder {
        private Integer limit;
        private Integer skip;
        private Date addedAfter;
        private Date addedBefore;
        private SortBy sort;

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

        public Builder withSort(SortBy sort) {
            this.sort = sort;
            return this;
        }

        public SearchPaymentLink build() {
            return new SearchPaymentLink(limit, skip, addedAfter, addedBefore, sort);
        }
    }
}
