package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerSearch {
    private final String paymentMethodId;
    private final String reference;
    private final String customerId;
    private final String email;
    private final Date addedAfter;
    private final Date addedBefore;
    private final Integer limit;
    private final Integer skip;
    private final String search;

    @JsonCreator
    public CustomerSearch(@JsonProperty("PaymentMethodId") String paymentMethodId,
                          @JsonProperty("Reference") String reference,
                          @JsonProperty("CustomerId") String customerId,
                          @JsonProperty("Email") String email,
                          @JsonProperty("AddedAfter") Date addedAfter,
                          @JsonProperty("AddedBefore") Date addedBefore,
                          @JsonProperty("Limit") Integer limit,
                          @JsonProperty("Skip") Integer skip,
                          @JsonProperty("Search") String search) {
        this.paymentMethodId = paymentMethodId;
        this.reference = reference;
        this.customerId = customerId;
        this.email = email;
        this.addedAfter = addedAfter;
        this.addedBefore = addedBefore;
        this.limit = limit;
        this.skip = skip;
        this.search = search;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public String getReference() {
        return reference;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
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

    @Override
    public String toString() {
        return "ChargeSearch{" +
                "paymentMethodId='" + paymentMethodId + '\'' +
                ", reference='" + reference + '\'' +
                ", customerId='" + customerId + '\'' +
                ", email='" + email + '\'' +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                ", limit=" + limit +
                ", skip=" + skip +
                ", search='" + search + '\'' +
                '}';
    }

    public static class Builder {

        private String paymentMethodId;
        private String reference;
        private String customerId;
        private String email;
        private Date addedAfter;
        private Date addedBefore;
        private Integer limit;
        private Integer skip;
        private String search;

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
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

        public CustomerSearch build() {
            return new CustomerSearch(paymentMethodId, reference, customerId, email, addedAfter, addedBefore, limit, skip, search);
        }
    }
}
