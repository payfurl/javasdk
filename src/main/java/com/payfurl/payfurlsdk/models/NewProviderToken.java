package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewProviderToken {

    private final String providerId;
    private final String providerToken;
    private final String email;
    private final Map<String, String> providerTokenData;
    private final Map<String, String> metadata;
    private final boolean verify;

    @JsonCreator
    public NewProviderToken(@JsonProperty("ProviderId") String providerId,
                            @JsonProperty("ProviderToken") String providerToken,
                            @JsonProperty("Email") String email,
                            @JsonProperty("ProviderTokenData") Map<String, String> providerTokenData,
                            @JsonProperty("Metadata") Map<String, String> metadata,
                            @JsonProperty("Verify") boolean verify) {
        this.providerId = providerId;
        this.providerToken = providerToken;
        this.email = email;
        this.providerTokenData = providerTokenData;
        this.metadata = metadata;
        this.verify = verify;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, String> getProviderTokenData() {
        return providerTokenData;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public boolean isVerify() {
        return verify;
    }

    public static class Builder {
        private String providerId;
        private String providerToken;
        private String email;
        private Map<String, String> providerTokenData;
        private Map<String, String> metadata;
        private boolean verify;

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withProviderToken(String providerToken) {
            this.providerToken = providerToken;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
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

        public Builder withVerify(boolean verify) {
            this.verify = verify;
            return this;
        }

        public NewProviderToken build() {
            return new NewProviderToken(providerId, providerToken, email, providerTokenData, metadata, verify);
        }
    }
}
