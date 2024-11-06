package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Geolocation {
    private final float longitude;
    private final float latitude;

    @JsonCreator
    public Geolocation(@JsonProperty("Longitude") float longitude,
                       @JsonProperty("Latitude") float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Geolocation{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
