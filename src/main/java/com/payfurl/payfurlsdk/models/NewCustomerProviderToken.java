package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewCustomerProviderToken {
    private final String providerId;
    private final String providerToken;
    private final Map<String, String> providerTokenData;

    @JsonCreator
    public NewCustomerProviderToken(@JsonProperty("ProviderId") String providerId,
                                    @JsonProperty("ProviderToken") String providerToken,
                                    @JsonProperty("ProviderTokenData") Map<String,String> providerTokenData)
    {
        this.providerId = providerId;
        this.providerToken = providerToken;
        this.providerTokenData = providerTokenData;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public Map<String, String> getProviderTokenData() {
        return providerTokenData;
    }

    @Override
    public String toString() {
        return "NewCustomerProviderToken{" +
                "providerId='" + providerId  + '\'' +
                ", providerToken='" + providerToken + '\'' +
                ", providerTokenData=" + providerTokenData +
                '}';
    }

    public static class Builder {
        private String providerId;
        private String providerToken;
        private Map<String, String> providerTokenData;

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withProviderToken(String providerToken) {
            this.providerToken = providerToken;
            return this;
        }

        public Builder withProviderTokenData(Map<String, String> providerTokenData) {
            this.providerTokenData = providerTokenData;
            return this;
        }

        public NewCustomerProviderToken build() {
            return new NewCustomerProviderToken(providerId, providerToken, providerTokenData);
        }
    }
}
