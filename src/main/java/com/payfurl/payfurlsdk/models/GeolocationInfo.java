package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Where an IP address is, as the API looked it up: {@link ChargeData#getIpInfo()}.
 */
public class GeolocationInfo {
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("city")
    private String city;
    @JsonProperty("region")
    private String region;
    @JsonProperty("country")
    private String country;
    @JsonProperty("loc")
    private String loc;
    @JsonProperty("org")
    private String org;
    @JsonProperty("postal")
    private String postal;
    @JsonProperty("timezone")
    private String timezone;

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

    /** Latitude and longitude, comma-separated. */
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

    @Override
    public String toString() {
        return "GeolocationInfo{" +
                "ip='" + ip + '\'' +
                ", hostname='" + hostname + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", loc='" + loc + '\'' +
                ", org='" + org + '\'' +
                ", postal='" + postal + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
