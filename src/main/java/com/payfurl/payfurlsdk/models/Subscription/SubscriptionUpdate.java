package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class SubscriptionUpdate {

    private BigDecimal amount;
    private String currency;
    private SubscriptionInterval interval;
    private Integer frequency;
    private SubscriptionEnd endAfter;
    private SubscriptionRetryPolicy retry;
    private WebhookConfig webhook;

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

        public SubscriptionUpdate build() {
            return new SubscriptionUpdate(amount, currency, interval, frequency, endAfter, retry, webhook);
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
