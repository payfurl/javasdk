package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TransferSearch {
    private final String reference;
    private final String providerId;
    private final String status;
    private final Date addedAfter;
    private final Date addedBefore;
    private final Integer limit;
    private final Integer skip;
    private final String sortBy;

    @JsonCreator
    public TransferSearch(@JsonProperty("Reference") String reference,
                          @JsonProperty("ProviderId") String providerId,
                          @JsonProperty("Status") String status,
                          @JsonProperty("AddedAfter") Date addedAfter,
                          @JsonProperty("AddedBefore") Date addedBefore,
                          @JsonProperty("Limit") Integer limit,
                          @JsonProperty("Skip") Integer skip,
                          @JsonProperty("SortBy") String sortBy) {
        this.reference = reference;
        this.providerId = providerId;
        this.status = status;
        this.addedAfter = addedAfter;
        this.addedBefore = addedBefore;
        this.limit = limit;
        this.skip = skip;
        this.sortBy = sortBy;
    }

    public String getReference() {
        return reference;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getStatus() {
        return status;
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

    public String getSortBy() {
        return sortBy;
    }

    @Override
    public String toString() {
        return "TransferSearch{" +
                "reference='" + reference + '\'' +
                ", providerId='" + providerId + '\'' +
                ", status='" + status + '\'' +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                ", limit=" + limit +
                ", skip=" + skip +
                ", sortBy='" + sortBy + '\'' +
                '}';
    }

    public static class Builder {
        private String reference;
        private String providerId;
        private String status;
        private Date addedAfter;
        private Date addedBefore;
        private Integer limit;
        private Integer skip;
        private String sortBy;

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
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

        public Builder withSortBy(String sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public TransferSearch build() {
            return new TransferSearch(reference, providerId, status, addedAfter, addedBefore, limit, skip, sortBy);
        }
    }
}
