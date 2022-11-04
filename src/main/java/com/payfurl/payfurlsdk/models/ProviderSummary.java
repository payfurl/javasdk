package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProviderSummary {
    public final String providerId;
    public final String type;
    public final String name;

    @JsonCreator
    public ProviderSummary(@JsonProperty("ProvideId") String providerId,
                           @JsonProperty("Type") String type,
                           @JsonProperty("Name") String name) {
        this.providerId = providerId;
        this.type = type;
        this.name = name;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProviderSummary{" +
                "providerId='" + providerId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
