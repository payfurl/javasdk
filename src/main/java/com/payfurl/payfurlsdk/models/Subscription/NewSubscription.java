package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class NewSubscription {

    private String paymentMethodId;
    private BigDecimal amount;
    private String currency;
    private SubscriptionInterval interval;
    private Integer frequency;

    private Date startDate;
    private SubscriptionEnd endAfter;
    private SubscriptionRetryPolicy retry;
    private WebhookConfig webhook;
    private Map<String, String> metadata;

    @JsonCreator
    public NewSubscription(
            @JsonProperty("PaymentMethodId") String paymentMethodId,
            @JsonProperty("Amount") BigDecimal amount,
            @JsonProperty("Currency") String currency,
            @JsonProperty("Interval") SubscriptionInterval interval,
            @JsonProperty("Frequency") Integer frequency,
            @JsonProperty("StartDate") Date startDate,
            @JsonProperty("EndAfter") SubscriptionEnd endAfter,
            @JsonProperty("Retry") SubscriptionRetryPolicy retry,
            @JsonProperty("Webhook") WebhookConfig webhook,
            @JsonProperty("Metadata") Map<String, String> metadata){

        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.currency = currency;
        this.interval = interval;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endAfter = endAfter;
        this.retry = retry;
        this.webhook = webhook;
        this.metadata = metadata;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
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

    public Date getStartDate() {
        return startDate;
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
        return "SubscriptionCreate{" +
                "paymentMethodId='" + paymentMethodId + '\'' +
                ", amount=" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", interval=" + interval + '\'' +
                ", frequency=" + frequency + '\'' +
                ", startDate=" + startDate + '\'' +
                ", endAfter=" + endAfter + '\'' +
                ", retry=" + retry + '\'' +
                ", webhook=" + webhook +
                ", metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private String paymentMethodId;
        private BigDecimal amount;
        private String currency;
        private SubscriptionInterval interval;
        private Integer frequency;
        private Date startDate;
        private SubscriptionEnd endAfter;
        private SubscriptionRetryPolicy retry;
        private WebhookConfig webhook;
        private Map<String, String> metadata;

        public NewSubscription build() {
            return new NewSubscription(paymentMethodId, amount, currency, interval, frequency, startDate, endAfter, retry, webhook, metadata);
        }

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
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

        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
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

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }
    }
}
