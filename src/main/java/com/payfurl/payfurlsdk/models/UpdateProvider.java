package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;


public class UpdateProvider {

    private final String name;
    private final Map<String, String> authenticationParameters;
    private final String providerCountry;
    private final String currency;

    @JsonCreator
    public UpdateProvider(@JsonProperty("Name") String name,
                       @JsonProperty("AuthenticationParameters") Map<String, String> authenticationParameters,
                       @JsonProperty("ProviderCountry") String providerCountry,
                       @JsonProperty("Currency") String currency) {
        this.name = name;
        this.authenticationParameters = authenticationParameters;
        this.providerCountry = providerCountry;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAuthenticationParameters() {
        return authenticationParameters;
    }

    public String getProviderCountry() {
        return providerCountry;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "NewProvider{" +
                "name='" + name + '\'' +
                ", authenticationParameters='" + authenticationParameters + '\'' +
                ", providerCountry=" + providerCountry +
                ", currency=" + currency +
                '}';
    }

    public static class Builder {
        private String name;
        private Map<String, String> authenticationParameters;
        private String providerCountry;
        private String currency;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAuthenticationParameters(Map<String, String> authenticationParameters) {
            this.authenticationParameters = authenticationParameters;
            return this;
        }

        public Builder withProviderCountry(String providerCountry) {
            this.providerCountry = providerCountry;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public UpdateProvider build() {
            return new UpdateProvider(name, authenticationParameters, providerCountry, currency);
        }
    }
}
