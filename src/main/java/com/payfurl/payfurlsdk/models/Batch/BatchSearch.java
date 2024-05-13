package com.payfurl.payfurlsdk.models.Batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BatchSearch {
    private final Integer limit;
    private final Integer skip;
    private final String description;
    private final Date addedAfter;
    private final Date addedBefore;

    @JsonCreator
    public BatchSearch(@JsonProperty("Limit") Integer limit,
                       @JsonProperty("Skip") Integer skip,
                       @JsonProperty("Description") String description,
                       @JsonProperty("AddedAfter") Date addedAfter,
                       @JsonProperty("AddedBefore") Date addedBefore) {

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

    public Date getAddedAfter() {
        return addedAfter;
    }

    public Date getAddedBefore() {
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
        private Date addedAfter;
        private Date addedBefore;

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

        public Builder withAddedAfter(Date addedAfter) {
            this.addedAfter = addedAfter;
            return this;
        }

        public Builder withAddedBefore(Date addedBefore) {
            this.addedBefore = addedBefore;
            return this;
        }

        public BatchSearch build() {
            return new BatchSearch(limit, skip, description, addedAfter, addedBefore);
        }
    }
}
