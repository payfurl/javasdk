package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UriData {
    private final String uri;
    private final String uriType;

    @JsonCreator
    public UriData(
            @JsonProperty("Uri") String uri,
            @JsonProperty("UriType") String uriType) {
        this.uri = uri;
        this.uriType = uriType;
    }

    public String getUri() {
        return uri;
    }

    public String getUriType() {
        return uriType;
    }

    @Override
    public String toString() {
        return "UriData{" +
                "uri='" + uri + '\'' +
                ", uriType='" + uriType + '\'' +
                '}';
    }

    public static class Builder {
        private String uri;
        private String uriType;

        public Builder withUri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder withUriType(String uriType) {
            this.uriType = uriType;
            return this;
        }

        public UriData build() {
            return new UriData(uri, uriType);
        }
    }
}
