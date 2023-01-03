package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TokenList {
    private final int limit;
    private final int skip;
    private final int count;
    private final List<TokenData> tokens;

    @JsonCreator
    public TokenList(@JsonProperty("Limit") int limit,
                     @JsonProperty("Skip") int skip,
                     @JsonProperty("Count") int count,
                     @JsonProperty("Tokens") List<TokenData> tokens) {
        this.limit = limit;
        this.skip = skip;
        this.count = count;
        this.tokens = tokens;
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

    public List<TokenData> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return "ChargeList{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", count=" + count +
                ", tokens=" + tokens +
                '}';
    }

    public static class Builder {
        private int limit;
        private int skip;
        private int count;
        private List<TokenData> tokens;

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

        public Builder withTokens(List<TokenData> tokens) {
            this.tokens = tokens;
            return this;
        }

        public TokenList createChargeList() {
            return new TokenList(limit, skip, count, tokens);
        }
    }
}
