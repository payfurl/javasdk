package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChargeList {
    private final int limit;
    private final int skip;
    private final int count;
    private final List<ChargeData> charges;

    @JsonCreator
    public ChargeList(@JsonProperty("Limit") int limit,
                      @JsonProperty("Skip") int skip,
                      @JsonProperty("Count") int count,
                      @JsonProperty("Charges") List<ChargeData> charges) {
        this.limit = limit;
        this.skip = skip;
        this.count = count;
        this.charges = charges;
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

    public List<ChargeData> getCharges() {
        return charges;
    }

    @Override
    public String toString() {
        return "ChargeList{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", count=" + count +
                ", charges=" + charges +
                '}';
    }

    public static class Builder {
        private int limit;
        private int skip;
        private int count;
        private List<ChargeData> charges;

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

        public Builder withCharges(List<ChargeData> charges) {
            this.charges = charges;
            return this;
        }

        public ChargeList build() {
            return new ChargeList(limit, skip, count, charges);
        }
    }
}
