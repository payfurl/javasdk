package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProviderInfo {
    private final String id;
    private final String name;
    private final String type;
    private final Boolean applyCurrencyConversion;

    @JsonCreator
    public ProviderInfo(@JsonProperty("Id") String id,
                        @JsonProperty("Name") String name,
                        @JsonProperty("Type") String type,
                        @JsonProperty("ApplyCurrencyConversion") Boolean applyCurrencyConversion) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.applyCurrencyConversion = applyCurrencyConversion;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Boolean getApplyCurrencyConversion() {
        return applyCurrencyConversion;
    }

    @Override
    public String toString() {
        return "ProviderInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", applyCurrencyConversion='" + applyCurrencyConversion + '\'' +
                '}';
    }
}
