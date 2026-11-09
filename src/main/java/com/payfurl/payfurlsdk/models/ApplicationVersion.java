package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * The API's version: {@code GET /version} (VersionController).
 */
public class ApplicationVersion {
    @JsonProperty("version")
    private String version;
    @JsonProperty("released")
    private Date released;
    @JsonProperty("region")
    private String region;

    public String getVersion() {
        return version;
    }

    public Date getReleased() {
        return released;
    }

    /** The region that answered. */
    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "ApplicationVersion{" +
                "version='" + version + '\'' +
                ", released=" + released +
                ", region='" + region + '\'' +
                '}';
    }
}
