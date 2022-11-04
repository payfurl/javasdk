package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaymentMethodList {
    private final int limit;
    private final int skip;
    private final int count;
    private final List<PaymentMethodData> paymentMethods;

    @JsonCreator
    public PaymentMethodList(@JsonProperty("Limit") int limit,
                             @JsonProperty("Skip") int skip,
                             @JsonProperty("Count") int count,
                             @JsonProperty("PaymentMethods") List<PaymentMethodData> paymentMethods) {
        this.limit = limit;
        this.skip = skip;
        this.count = count;
        this.paymentMethods = paymentMethods;
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

    public List<PaymentMethodData> getPaymentMethods() {
        return paymentMethods;
    }

    @Override
    public String toString() {
        return "PaymentMethodList{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", count=" + count +
                ", paymentMethods=" + paymentMethods +
                '}';
    }

    public static class Builder {
        private int limit;
        private int skip;
        private int count;
        private List<PaymentMethodData> paymentMethods;

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

        public Builder withPaymentMethods(List<PaymentMethodData> paymentMethods) {
            this.paymentMethods = paymentMethods;
            return this;
        }

        public PaymentMethodList createChargeList() {
            return new PaymentMethodList(limit, skip, count, paymentMethods);
        }
    }
}
