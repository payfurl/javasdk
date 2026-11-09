package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A page of disputes: {@code GET /dispute}.
 */
public class DisputeList {
    @JsonProperty("limit")
    private int limit;
    @JsonProperty("skip")
    private int skip;
    @JsonProperty("count")
    private long count;
    @JsonProperty("disputes")
    private List<DisputeData> disputes;

    public int getLimit() {
        return limit;
    }

    public int getSkip() {
        return skip;
    }

    /** How many disputes match the search, across all pages. */
    public long getCount() {
        return count;
    }

    public List<DisputeData> getDisputes() {
        return disputes;
    }

    @Override
    public String toString() {
        return "DisputeList{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", count=" + count +
                ", disputes=" + disputes +
                '}';
    }
}
