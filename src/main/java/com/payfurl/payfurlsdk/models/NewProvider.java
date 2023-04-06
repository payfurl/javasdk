package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewProvider {

    private final String type;
    private final String name;
    private final Environment environment;
    private final Map<String, String> authenticationParameters;
    private final String providerCountry;
    private final String currency;
    private final String threeDsProviderId;

    @JsonCreator
    public NewProvider(@JsonProperty("Type") String type,
                       @JsonProperty("Name") String name,
                       @JsonProperty("Environment") Environment environment,
                       @JsonProperty("AuthenticationParameters") Map<String, String> authenticationParameters,
                       @JsonProperty("ProviderCountry") String providerCountry,
                       @JsonProperty("Currency") String currency,
                       @JsonProperty("ThreeDsProviderId") String threeDsProviderId) {
        this.type = type;
        this.name = name;
        this.environment = environment;
        this.authenticationParameters = authenticationParameters;
        this.providerCountry = providerCountry;
        this.currency = currency;
        this.threeDsProviderId = threeDsProviderId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Environment getEnvironment() {
        return environment;
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

    public String getThreeDsProviderId() {
        return threeDsProviderId;
    }

    @Override
    public String toString() {
        return "NewProvider{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", environment='" + environment + '\'' +
                ", authenticationParameters='" + authenticationParameters + '\'' +
                ", providerCountry=" + providerCountry +
                ", currency=" + currency +
                ", threeDsProviderId=" + threeDsProviderId +
                '}';
    }

    public static class Builder {
        private String type;
        private String name;
        private Environment environment;
        private Map<String, String> authenticationParameters;
        private String providerCountry;
        private String currency;
        private String threeDsProviderId;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEnvironment(Environment environment) {
            this.environment = environment;
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

        public Builder withThreeDsProviderId(String threeDsProviderId) {
            this.threeDsProviderId = threeDsProviderId;
            return this;
        }

        public NewProvider build() {
            return new NewProvider(type, name, environment, authenticationParameters, providerCountry, currency, threeDsProviderId);
        }
    }
}
