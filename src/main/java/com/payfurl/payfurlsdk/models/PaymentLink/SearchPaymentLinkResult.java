package com.payfurl.payfurlsdk.models.PaymentLink;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchPaymentLinkResult {
    private final List<PaymentLinkData> paymentLinks;
    private final long count;

    @JsonCreator
    public SearchPaymentLinkResult(
            @JsonProperty("PaymentLinks") List<PaymentLinkData> paymentLinks,
            @JsonProperty("Count") long count) {
        this.paymentLinks = paymentLinks;
        this.count = count;
    }

    public List<PaymentLinkData> getPaymentLinks() {
        return paymentLinks;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "SearchPaymentLinkResult{" +
                "paymentLinks=" + paymentLinks +
                ", count=" + count +
                '}';
    }

    public static class Builder {
        private List<PaymentLinkData> paymentLinks;
        private long count;

        public Builder withPaymentLinks(List<PaymentLinkData> paymentLinks) {
            this.paymentLinks = paymentLinks;
            return this;
        }

        public Builder withCount(long count) {
            this.count = count;
            return this;
        }

        public SearchPaymentLinkResult build() {
            return new SearchPaymentLinkResult(paymentLinks, count);
        }
    }
}
