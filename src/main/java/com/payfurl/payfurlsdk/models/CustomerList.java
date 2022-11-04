package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomerList {
    private final int limit;
    private final int skip;
    private final int count;
    private final List<CustomerData> customers;

    @JsonCreator
    public CustomerList(@JsonProperty("Limit") int limit,
                        @JsonProperty("Skip") int skip,
                        @JsonProperty("Count") int count,
                        @JsonProperty("Customers") List<CustomerData> customers) {
        this.limit = limit;
        this.skip = skip;
        this.count = count;
        this.customers = customers;
    }

    public int getLimit() {
        return limit;
    }

    public int getSkip() {
        return skip;
    }

    public int getCount() {
        return count;
    }

    public List<CustomerData> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "ChargeList{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", count=" + count +
                ", customers=" + customers +
                '}';
    }

    public static class Builder {
        private int limit;
        private int skip;
        private int count;
        private List<CustomerData> customers;

        public Builder withLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder withSkip(int skip) {
            this.skip = skip;
            return this;
        }

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withCustomers(List<CustomerData> customers) {
            this.customers = customers;
            return this;
        }

        public CustomerList createChargeList() {
            return new CustomerList(limit, skip, count, customers);
        }
    }
}
