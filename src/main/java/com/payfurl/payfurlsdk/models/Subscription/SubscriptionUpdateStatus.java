package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionUpdateStatus {

    private String status;

    @JsonCreator
    public SubscriptionUpdateStatus(
            @JsonProperty("Status") String status){

        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SubscriptionUpdateStatus{" +
                "status='" + status + '\'' +
                '}';
    }

    public static class Builder {
        private String status;

        public SubscriptionUpdateStatus build() {
            return new SubscriptionUpdateStatus(status);
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }
    }
}
