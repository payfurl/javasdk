package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class SubscriptionRetryPolicy {

    private final int maximum;
    private final String interval;
    private final int frequency;

    @JsonCreator
    public SubscriptionRetryPolicy(@JsonProperty("Maximum") int maximum,
                           @JsonProperty("Interval") String interval,
                           @JsonProperty("Frequency") int frequency) {
        this.maximum = maximum;
        this.interval = interval;
        this.frequency = frequency;
    }

    public int getMaximum() {
        return maximum;
    }

    public String getInterval() {
        return interval;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "SubscriptionRetryPolicy{" +
                "maximum='" + maximum + '\'' +
                ", interval=" + interval + '\'' +
                ", frequency=" + frequency +
                '}';
    }

    public static class Builder {
        private int maximum;
        private String interval;
        private int frequency;

        public SubscriptionRetryPolicy build() {
            return new SubscriptionRetryPolicy(maximum, interval, frequency);
        }

        public Builder withMaximum(int maximum) {
            this.maximum = maximum;
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
    }
}
