package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TokenSearch {

    private final String providerId;
    private final String status;
    private final String sortBy;
    private final Date addedAfter;
    private final Date addedBefore;
    private final Integer limit;
    private final Integer skip;

    @JsonCreator
    public TokenSearch(@JsonProperty("ProviderId") String providerId,
                       @JsonProperty("Status") String status,
                       @JsonProperty("SortBy") String sortBy,
                       @JsonProperty("AddedAfter") Date addedAfter,
                       @JsonProperty("AddedBefore") Date addedBefore,
                       @JsonProperty("Limit") Integer limit,
                       @JsonProperty("Skip") Integer skip)
    {
        this.providerId = providerId;
        this.status = status;
        this.sortBy = sortBy;
        this.addedAfter = addedAfter;
        this.addedBefore = addedBefore;
        this.limit = limit;
        this.skip = skip;
    }

    public String getStatus() {
        return status;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getProviderId() {
        return providerId;
    }

    public Date getAddedAfter() {
        return addedAfter;
    }

    public Date getAddedBefore() {
        return addedBefore;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }

    @Override
    public String toString() {
        return "TokenSearch{" +
                "status='" + status + '\'' +
                ", providerId='" + providerId + '\'' +
                ", sortBy='" + sortBy + '\'' +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                ", limit=" + limit +
                ", skip=" + skip +
                '}';
    }

    public static class Builder {
        private String providerId;
        private String status;
        private String sortBy;
        private Date addedAfter;
        private Date addedBefore;
        private Integer limit;
        private Integer skip;

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withSortBy(String sortBy) {
            this.sortBy = sortBy;
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

        public Builder withLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder withSkip(Integer skip) {
            this.skip = skip;
            return this;
        }

        public TokenSearch build() {
            return new TokenSearch(providerId, status, sortBy, addedAfter, addedBefore, limit, skip);
        }
    }
}
