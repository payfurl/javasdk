package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionUpdateStatus {

    private SubscriptionStatus status;

    @JsonCreator
    public SubscriptionUpdateStatus(
            @JsonProperty("Status") SubscriptionStatus status){

        this.status = status;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SubscriptionUpdateStatus{" +
                "status='" + status + '\'' +
                '}';
    }

    public static class Builder {
        private SubscriptionStatus status;

        public SubscriptionUpdateStatus build() {
            return new SubscriptionUpdateStatus(status);
        }

        public Builder withStatus(SubscriptionStatus status) {
            this.status = status;
            return this;
        }
    }
}
