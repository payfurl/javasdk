package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class WebhookSubscriptionSearch {
    private String id;
    private Integer limit;
    private Integer skip;
    private String type;
    private Date addedAfter;
    private Date addedBefore;
    private SortBy sort;

    public enum SortBy {
        NONE,
        DATE
    }

    @JsonCreator
    public WebhookSubscriptionSearch(@JsonProperty("Id") String id,
                                     @JsonProperty("Limit") Integer limit,
                                     @JsonProperty("Skip") Integer skip,
                                     @JsonProperty("Type") String type,
                                     @JsonProperty("AddedAfter") Date addedAfter,
                                     @JsonProperty("AddedBefore") Date addedBefore,
                                     @JsonProperty("Sort") SortBy sort) {
        this.id = id;
        this.limit = limit;
        this.skip = skip;
        this.type = type;
        this.addedAfter = addedAfter;
        this.addedBefore = addedBefore;
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getAddedAfter() {
        return addedAfter;
    }

    public void setAddedAfter(Date addedAfter) {
        this.addedAfter = addedAfter;
    }

    public Date getAddedBefore() {
        return addedBefore;
    }

    public void setAddedBefore(Date addedBefore) {
        this.addedBefore = addedBefore;
    }

    public SortBy getSort() {
        return sort;
    }

    public void setSort(SortBy sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "WebhookSubscriptionSearch{" +
                "id='" + id + '\'' +
                ", limit=" + limit +
                ", skip=" + skip +
                ", type='" + type + '\'' +
                ", addedAfter=" + addedAfter +
                ", addedBefore=" + addedBefore +
                ", sort=" + sort +
                '}';
    }

    public static class Builder {
        private String id;
        private Integer limit;
        private Integer skip;
        private String type;
        private Date addedAfter;
        private Date addedBefore;
        private SortBy sort;

        public Builder withId(String id) {
            this.id = id;
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

        public Builder withType(String type) {
            this.type = type;
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

        public Builder withSort(SortBy sort) {
            this.sort = sort;
            return this;
        }

        public WebhookSubscriptionSearch build() {
            return new WebhookSubscriptionSearch(
                    id,
                    limit,
                    skip,
                    type,
                    addedAfter,
                    addedBefore,
                    sort);
        }
    }
}
