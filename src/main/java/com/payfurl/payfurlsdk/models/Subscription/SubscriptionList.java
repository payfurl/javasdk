package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SubscriptionList {
    private final int limit;
    private final int skip;
    private final long count;
    private final List<SubscriptionData> subscriptions;

    @JsonCreator
    public SubscriptionList(@JsonProperty("Limit") int limit,
                            @JsonProperty("Skip") int skip,
                            @JsonProperty("Count") long count,
                            @JsonProperty("Batches") List<SubscriptionData> subscriptions) {

        this.limit = limit;
        this.skip = skip;
        this.count = count;
        this.subscriptions = subscriptions;
    }

    public int getLimit() {
        return limit;
    }

    public int getSkip() {
        return skip;
    }

    public long getCount() {
        return count;
    }

    public List<SubscriptionData> getSubscriptions() {
        return subscriptions;
    }

    @Override
    public String toString() {
        return "BatchList{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", count=" + count +
                ", subscriptions=" + subscriptions +
                '}';
    }

    public static class Builder {
        private int limit;
        private int skip;
        private long count;
        private List<SubscriptionData> subscriptions;

        public Builder withLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder withSkip(int skip) {
            this.skip = skip;
            return this;
        }

        public Builder withCount(long count) {
            this.count = count;
            return this;
        }

        public Builder withSubscriptions(List<SubscriptionData> subscriptions) {
            this.subscriptions = subscriptions;
            return this;
        }

        public SubscriptionList build() {
            return new SubscriptionList(limit, skip, count, subscriptions);
        }
    }
}
