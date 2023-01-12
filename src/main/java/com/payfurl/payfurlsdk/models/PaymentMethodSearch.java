package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PaymentMethodSearch {
    private final String paymentType;
    private final String providerId;
    private final String customerId;
    private final String sortBy;
    private final Date addedAfter;
    private final Date addedBefore;
    private final Integer limit;
    private final Integer skip;
    private final String search;
    private final String token;


    @JsonCreator
    public PaymentMethodSearch(@JsonProperty("PaymentType") String paymentType,
                               @JsonProperty("ProviderId") String providerId,
                               @JsonProperty("CustomerId") String customerId,
                               @JsonProperty("SortBy") String sortBy,
                               @JsonProperty("AddedAfter") Date addedAfter,
                               @JsonProperty("AddedBefore") Date addedBefore,
                               @JsonProperty("Limit") Integer limit,
                               @JsonProperty("Skip") Integer skip,
                               @JsonProperty("Search") String search,
                               @JsonProperty("Token") String token) {
        this.paymentType = paymentType;
        this.providerId = providerId;
        this.customerId = customerId;
        this.sortBy = sortBy;
        this.addedAfter = addedAfter;
        this.addedBefore = addedBefore;
        this.limit = limit;
        this.skip = skip;
        this.search = search;
        this.token = token;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getSortBy() {
        return sortBy;
    }

    public Date getAddedAfter() {
        return addedAfter;
    }

    public Date getAddedBefore() {
        return addedBefore;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }

    public String getSearch() {
        return search;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "ChargeSearch{" +
                "paymentType='" + paymentType + '\'' +
                ", providerId='" + providerId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", sortBy='" + sortBy + '\'' +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                ", limit=" + limit +
                ", skip=" + skip +
                ", search='" + search + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public static class Builder {
        private String paymentType;
        private String providerId;
        private String customerId;
        private String sortBy;
        private Date addedAfter;
        private Date addedBefore;
        private Integer limit;
        private Integer skip;
        private String search;
        private String token;

        public Builder withPaymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withSortBy(String sortBy) {
            this.sortBy = sortBy;
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

        public Builder withSearch(String search) {
            this.search = search;
            return this;
        }

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public PaymentMethodSearch build() {
            return new PaymentMethodSearch(paymentType, providerId, customerId, sortBy, addedAfter, addedBefore, limit, skip, search, token);
        }
    }
}
