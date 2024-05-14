package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IinData {
    public final String scheme;
    public final String country;
    public final String issuer;
    public final String countryCode;
    public final String cardType;

    @JsonCreator
    public IinData(@JsonProperty("Scheme") String scheme,
                    @JsonProperty("Country") String country,
                    @JsonProperty("Issuer") String issuer,
                    @JsonProperty("CountryCode") String countryCode,
                    @JsonProperty("CardType") String cardType) {
        this.scheme = scheme;
        this.country = country;
        this.issuer = issuer;
        this.countryCode = countryCode;
        this.cardType = cardType;
    }

    public String getScheme() {
        return scheme;
    }

    public String getCountry() {
        return country;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCardType() {
        return cardType;
    }


    @Override
    public String toString() {
        return "IinData{" +
                "scheme='" + scheme + '\'' +
                ", country='" + country + '\'' +
                ", issuer='" + issuer + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
