package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewCustomerProviderSingleUseToken {

    private final String providerId;
    private final String providerToken;
    private final Map<String, String> providerTokenData;
    private final Map<String, String> metadata;

    @JsonCreator
    public NewCustomerProviderSingleUseToken(
                                             @JsonProperty("ProviderId") String providerId,
                                             @JsonProperty("ProviderToken") String providerToken,
                                             @JsonProperty("ProviderTokenData") Map<String, String> providerTokenData,
                                             @JsonProperty("Metadata") Map<String, String> metadata) {
        this.providerId = providerId;
        this.providerToken = providerToken;
        this.providerTokenData = providerTokenData;
        this.metadata = metadata;
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

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "NewCustomerProviderToken{" +
                "providerId='" + providerId + '\'' +
                ", providerToken='" + providerToken + '\'' +
                ", providerTokenData=" + providerTokenData +
                ", metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private String providerId;
        private String providerToken;
        private Map<String, String> providerTokenData;
        private Map<String, String> metadata;

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

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public NewCustomerProviderSingleUseToken build() {
            return new NewCustomerProviderSingleUseToken(providerId, providerToken, providerTokenData, metadata);
        }
    }
}
