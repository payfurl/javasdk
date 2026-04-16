package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GeolocationInfo {
    private final String ip;
    private final String hostname;
    private final String city;
    private final String region;
    private final String country;
    private final String loc;
    private final String org;
    private final String postal;
    private final String timezone;

    @JsonCreator
    public GeolocationInfo(@JsonProperty("Ip") String ip,
                           @JsonProperty("Hostname") String hostname,
                           @JsonProperty("City") String city,
                           @JsonProperty("Region") String region,
                           @JsonProperty("Country") String country,
                           @JsonProperty("Loc") String loc,
                           @JsonProperty("Org") String org,
                           @JsonProperty("Postal") String postal,
                           @JsonProperty("Timezone") String timezone) {
        this.ip = ip;
        this.hostname = hostname;
        this.city = city;
        this.region = region;
        this.country = country;
        this.loc = loc;
        this.org = org;
        this.postal = postal;
        this.timezone = timezone;
    }

    public String getIp() {
        return ip;
    }

    public String getHostname() {
        return hostname;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getLoc() {
        return loc;
    }

    public String getOrg() {
        return org;
    }

    public String getPostal() {
        return postal;
    }

    public String getTimezone() {
        return timezone;
    }
}
