package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class SubscriptionUpdate {

    private BigDecimal amount;
    private String currency;
    private SubscriptionInterval interval;
    private Integer frequency;
    private SubscriptionEnd endAfter;
    private SubscriptionRetryPolicy retry;
    private WebhookConfig webhook;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> metadata;

    @JsonCreator
    public SubscriptionUpdate(
            @JsonProperty("Amount") BigDecimal amount,
            @JsonProperty("Currency") String currency,
            @JsonProperty("Interval") SubscriptionInterval interval,
            @JsonProperty("Frequency") Integer frequency,
            @JsonProperty("EndAfter") SubscriptionEnd endAfter,
            @JsonProperty("Retry") SubscriptionRetryPolicy retry,
            @JsonProperty("Webhook") WebhookConfig webhook) {
        this.amount = amount;
        this.currency = currency;
        this.interval = interval;
        this.frequency = frequency;
        this.endAfter = endAfter;
        this.retry = retry;
        this.webhook = webhook;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public SubscriptionInterval getInterval() {
        return interval;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public SubscriptionEnd getEndAfter() {
        return endAfter;
    }

    public SubscriptionRetryPolicy getRetry() {
        return retry;
    }

    public WebhookConfig getWebhook() {
        return webhook;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "SubscriptionUpdate{" +
                "amount=" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", interval=" + interval + '\'' +
                ", frequency=" + frequency + '\'' +
                ", endAfter=" + endAfter + '\'' +
                ", retry=" + retry + '\'' +
                ", webhook=" + webhook +
                ", metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private SubscriptionInterval interval;
        private Integer frequency;
        private SubscriptionEnd endAfter;
        private SubscriptionRetryPolicy retry;
        private WebhookConfig webhook;
        private Map<String, String> metadata;

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public SubscriptionUpdate build() {
            SubscriptionUpdate result = new SubscriptionUpdate(amount, currency, interval, frequency, endAfter, retry, webhook);
            result.metadata = metadata;
            return result;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withInterval(SubscriptionInterval interval) {
            this.interval = interval;
            return this;
        }

        public Builder withFrequency(Integer frequency) {
            this.frequency = frequency;
            return this;
        }

        public Builder withEndAfter(SubscriptionEnd endAfter) {
            this.endAfter = endAfter;
            return this;
        }

        public Builder withRetry(SubscriptionRetryPolicy retry) {
            this.retry = retry;
            return this;
        }

        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }
    }
}
