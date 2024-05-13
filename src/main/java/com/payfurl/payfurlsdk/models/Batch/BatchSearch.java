package com.payfurl.payfurlsdk.models.Batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class BatchSearch {
    private final Integer limit;
    private final Integer skip;
    private final String description;
    private final LocalDateTime addedAfter;
    private final LocalDateTime addedBefore;

    @JsonCreator
    public BatchSearch(@JsonProperty("Limit") Integer limit,
                       @JsonProperty("Skip") Integer skip,
                       @JsonProperty("Description") String description,
                       @JsonProperty("AddedAfter") LocalDateTime addedAfter,
                       @JsonProperty("AddedBefore") LocalDateTime addedBefore) {

        this.limit = limit;
        this.skip = skip;
        this.description = description;
        this.addedAfter = addedAfter;
        this.addedBefore = addedBefore;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getAddedAfter() {
        return addedAfter;
    }

    public LocalDateTime getAddedBefore() {
        return addedBefore;
    }

    @Override
    public String toString() {
        return "BatchSearch{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", description='" + description + '\'' +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                '}';
    }

    public static class Builder {
        private Integer limit;
        private Integer skip;
        private String description;
        private LocalDateTime addedAfter;
        private LocalDateTime addedBefore;

        public Builder withLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder withSkip(Integer skip) {
            this.skip = skip;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withAddedAfter(LocalDateTime addedAfter) {
            this.addedAfter = addedAfter;
            return this;
        }

        public Builder withAddedBefore(LocalDateTime addedBefore) {
            this.addedBefore = addedBefore;
            return this;
        }

        public BatchSearch build() {
            return new BatchSearch(limit, skip, description, addedAfter, addedBefore);
        }
    }
}
