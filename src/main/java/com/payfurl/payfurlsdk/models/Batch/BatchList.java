package com.payfurl.payfurlsdk.models.Batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BatchList {
    private final int limit;
    private final int skip;
    private final long count;
    private final List<BatchSummary> batches;

    @JsonCreator
    public BatchList(@JsonProperty("Limit") int limit,
                     @JsonProperty("Skip") int skip,
                     @JsonProperty("Count") long count,
                     @JsonProperty("Batches") List<BatchSummary> batches) {

        this.limit = limit;
        this.skip = skip;
        this.count = count;
        this.batches = batches;
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

    public List<BatchSummary> getBatches() {
        return batches;
    }

    @Override
    public String toString() {
        return "BatchList{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", count=" + count +
                ", batches=" + batches +
                '}';
    }

    public static class Builder {
        private int limit;
        private int skip;
        private long count;
        private List<BatchSummary> batches;

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

        public Builder withBatches(List<BatchSummary> batches) {
            this.batches = batches;
            return this;
        }

        public BatchList build() {
            return new BatchList(limit, skip, count, batches);
        }
    }
}
