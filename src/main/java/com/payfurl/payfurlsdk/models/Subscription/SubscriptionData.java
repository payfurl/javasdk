package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class SubscriptionData {

    private String subscriptionId;
    private String paymentMethodId;
    private BigDecimal amount;
    private String currency;
    private String interval;
    private int frequency;
    private Date createdDate;
    private Date startDate;
    private SubscriptionEnd endAfter;
    private SubscriptionRetryPolicy retry;
    private WebhookConfig webhook;
    private String status;

    @JsonCreator
    public SubscriptionData(
            @JsonProperty("SubscriptionId") String subscriptionId,
            @JsonProperty("PaymentMethodId") String paymentMethodId,
            @JsonProperty("Amount") BigDecimal amount,
            @JsonProperty("Currency") String currency,
            @JsonProperty("Interval") String interval,
            @JsonProperty("Frequency") int frequency,
            @JsonProperty("CreatedDate") Date createdDate,
            @JsonProperty("StartDate") Date startDate,
            @JsonProperty("EndAfter") SubscriptionEnd endAfter,
            @JsonProperty("Retry") SubscriptionRetryPolicy retry,
            @JsonProperty("Webhook") WebhookConfig webhook,
            @JsonProperty("Status") String status) {
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

    public String getInterval() {
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

    public String getStatus() {
        return status;
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
                '}';
    }

    public static class Builder {
        private String subscriptionId;
        private String paymentMethodId;
        private BigDecimal amount;
        private String currency;
        private String interval;
        private int frequency;
        private Date createdDate;
        private Date startDate;
        private SubscriptionEnd endAfter;
        private SubscriptionRetryPolicy retry;
        private WebhookConfig webhook;
        private String status;

        public SubscriptionData build() {
            return new SubscriptionData(subscriptionId, paymentMethodId, amount, currency, interval, frequency, createdDate, startDate, endAfter, retry, webhook, status);
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

        public Builder withInterval(String interval) {
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

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }
    }
}
