package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class UpdateProvider {

    private final String name;
    private final Map<String, String> authenticationParameters;
    private final String providerCountry;
    private final String currency;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> additionalParameters;

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

    /** The provider's additional (non-secret) parameters. */
    public Map<String, String> getAdditionalParameters() {
        return additionalParameters;
    }

    @Override
    public String toString() {
        return "UpdateProvider{" +
                "name='" + name + '\'' +
                ", authenticationParameters='" + Mask.secretValues(authenticationParameters) + '\'' +
                ", providerCountry=" + providerCountry +
                ", currency=" + currency +
                ", additionalParameters=" + additionalParameters +
                '}';
    }

    public static class Builder {
        private String name;
        private Map<String, String> authenticationParameters;
        private String providerCountry;
        private String currency;
        private Map<String, String> additionalParameters;

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

        /** The provider's additional (non-secret) parameters. */
        public Builder withAdditionalParameters(Map<String, String> additionalParameters) {
            this.additionalParameters = additionalParameters;
            return this;
        }

        public UpdateProvider build() {
            UpdateProvider result = new UpdateProvider(name, authenticationParameters, providerCountry, currency);
            result.additionalParameters = additionalParameters;
            return result;
        }
    }
}
