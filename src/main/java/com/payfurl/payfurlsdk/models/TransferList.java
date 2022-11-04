package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TransferList {
    private final int limit;
    private final int skip;
    private final int count;
    private final List<TransferData> transfers;

    @JsonCreator
    public TransferList(@JsonProperty("Limit") int limit,
                        @JsonProperty("Skip") int skip,
                        @JsonProperty("Count") int count,
                        @JsonProperty("Transfers") List<TransferData> transfers) {
        this.limit = limit;
        this.skip = skip;
        this.count = count;
        this.transfers = transfers;
    }

    public int getLimit() {
        return limit;
    }

    public int getSkip() {
        return skip;
    }

    public int getCount() {
        return count;
    }

    public List<TransferData> getTransfers() {
        return transfers;
    }

    @Override
    public String toString() {
        return "TransferList{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", count=" + count +
                ", transfers=" + transfers +
                '}';
    }

    public static class Builder {
        private int limit;
        private int skip;
        private int count;
        private List<TransferData> transfers;

        public Builder withLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder withSkip(int skip) {
            this.skip = skip;
            return this;
        }

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withTransfers(List<TransferData> transfers) {
            this.transfers = transfers;
            return this;
        }

        public TransferList build() {
            return new TransferList(limit, skip, count, transfers);
        }
    }
}
