package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class SubscriptionData {

    private final String subscriptionId;
    private final String paymentMethodId;
    private final BigDecimal amount;
    private final String currency;
    private final SubscriptionInterval interval;
    private final int frequency;
    private final Date createdDate;
    private final Date startDate;
    private final SubscriptionEnd endAfter;
    private final SubscriptionRetryPolicy retry;
    private final WebhookConfig webhook;
    private final SubscriptionStatus status;
    private final Map<String, String> metadata;

    @JsonCreator
    public SubscriptionData(
            @JsonProperty("SubscriptionId") String subscriptionId,
            @JsonProperty("PaymentMethodId") String paymentMethodId,
            @JsonProperty("Amount") BigDecimal amount,
            @JsonProperty("Currency") String currency,
            @JsonProperty("Interval") SubscriptionInterval interval,
            @JsonProperty("Frequency") int frequency,
            @JsonProperty("CreatedDate") Date createdDate,
            @JsonProperty("StartDate") Date startDate,
            @JsonProperty("EndAfter") SubscriptionEnd endAfter,
            @JsonProperty("Retry") SubscriptionRetryPolicy retry,
            @JsonProperty("Webhook") WebhookConfig webhook,
            @JsonProperty("Status") SubscriptionStatus status,
            @JsonProperty("Metadata") Map<String, String> metadata) {
        this.subscriptionId = subscriptionId;
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.currency = currency;
        this.interval = interval;
        this.frequency = frequency;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.endAfter = endAfter;
        this.retry = retry;
        this.webhook = webhook;
        this.status = status;
        this.metadata = metadata;
    }

    public String getSubscriptionId() {
        return subscriptionId;
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

    public int getFrequency() {
        return frequency;
    }

    public Date getCreatedDate() {
        return createdDate;
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

    public SubscriptionStatus getStatus() {
        return status;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "SubscriptionData{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                ", amount=" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", interval=" + interval + '\'' +
                ", frequency=" + frequency + '\'' +
                ", createdDate=" + createdDate + '\'' +
                ", startDate=" + startDate + '\'' +
                ", endAfter=" + endAfter + '\'' +
                ", retry=" + retry + '\'' +
                ", webhook=" + webhook + '\'' +
                ", status=" + status +
                ", metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private String subscriptionId;
        private String paymentMethodId;
        private BigDecimal amount;
        private String currency;
        private SubscriptionInterval interval;
        private int frequency;
        private Date createdDate;
        private Date startDate;
        private SubscriptionEnd endAfter;
        private SubscriptionRetryPolicy retry;
        private WebhookConfig webhook;
        private SubscriptionStatus status;
        private Map<String, String> metadata;

        public SubscriptionData build() {
            return new SubscriptionData(subscriptionId, paymentMethodId, amount, currency, interval, frequency, createdDate, startDate, endAfter, retry, webhook, status, metadata);
        }

        public Builder withSubscriptionId(String subscriptionId) {
            this.subscriptionId = subscriptionId;
            return this;
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

        public Builder withFrequency(int frequency) {
            this.frequency = frequency;
            return this;
        }

        public Builder withCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
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

        public Builder withStatus(SubscriptionStatus status) {
            this.status = status;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }
    }
}
