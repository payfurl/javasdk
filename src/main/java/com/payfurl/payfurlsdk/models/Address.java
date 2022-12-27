package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Address {
    private final String line1;
    private final String line2;
    private final String city;
    private final String country;
    private final String postalCode;
    private final String state;

    @JsonCreator
    public Address(@JsonProperty("Line1") String line1,
                   @JsonProperty("Line2") String line2,
                   @JsonProperty("City") String city,
                   @JsonProperty("Country") String country,
                   @JsonProperty("PostalCode") String postalCode,
                   @JsonProperty("State") String state) {

        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.state = state;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public static class Builder {
        private String line1;
        private String line2;
        private String city;
        private String country;
        private String postalCode;
        private String state;

        public Builder withLine1(String line1) {
            this.line1 = line1;
            return this;
        }

        public Builder withLine2(String line2) {
            this.line2 = line2;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder withSate(String state) {
            this.state = state;
            return this;
        }

        public Address build() {
            return new Address(line1, line2, city, country, postalCode, state);
        }
    }
}
