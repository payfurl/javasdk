package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;

public class NetworkTokenSummary {
    private final String networkTokenId;
    private final String schemeTokenId;
    private final String providerId;
    private final String providerType;
    private final String status;
    private final OffsetDateTime dateAdded;
    private final Map<String, String> schemeTokenData;
    private final NetworkTokenAuthenticationMethod authenticationMethod;

    @JsonCreator
    public NetworkTokenSummary(
            @JsonProperty("NetworkTokenId") String networkTokenId,
            @JsonProperty("SchemeTokenId") String schemeTokenId,
            @JsonProperty("ProviderId") String providerId,
            @JsonProperty("ProviderType") String providerType,
            @JsonProperty("Status") String status,
            @JsonProperty("DateAdded") OffsetDateTime dateAdded,
            @JsonProperty("SchemeTokenData") Map<String, String> schemeTokenData,
            @JsonProperty("AuthenticationMethod") NetworkTokenAuthenticationMethod authenticationMethod) {
        this.networkTokenId = networkTokenId;
        this.schemeTokenId = schemeTokenId;
        this.providerId = providerId;
        this.providerType = providerType;
        this.status = status;
        this.dateAdded = dateAdded;
        this.schemeTokenData = schemeTokenData;
        this.authenticationMethod = authenticationMethod;
    }

    public String getNetworkTokenId() {
        return networkTokenId;
    }

    public String getSchemeTokenId() {
        return schemeTokenId;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderType() {
        return providerType;
    }

    public String getStatus() {
        return status;
    }

    public OffsetDateTime getDateAdded() {
        return dateAdded;
    }

    public Map<String, String> getSchemeTokenData() {
        return schemeTokenData;
    }

    public NetworkTokenAuthenticationMethod getAuthenticationMethod() {
        return authenticationMethod;
    }

    @Override
    public String toString() {
        return "NetworkTokenSummary{" +
                "networkTokenId='" + networkTokenId + '\'' +
                ", schemeTokenId='" + schemeTokenId + '\'' +
                ", providerId='" + providerId + '\'' +
                ", providerType='" + providerType + '\'' +
                ", status='" + status + '\'' +
                ", dateAdded=" + dateAdded +
                ", schemeTokenData=" + schemeTokenData +
                ", authenticationMethod=" + authenticationMethod +
                '}';
    }

    public static class Builder {
        private String networkTokenId;
        private String schemeTokenId;
        private String providerId;
        private String providerType;
        private String status;
        private OffsetDateTime dateAdded;
        private Map<String, String> schemeTokenData;
        private NetworkTokenAuthenticationMethod authenticationMethod;

        public Builder withNetworkTokenId(String networkTokenId) {
            this.networkTokenId = networkTokenId;
            return this;
        }

        public Builder withSchemeTokenId(String schemeTokenId) {
            this.schemeTokenId = schemeTokenId;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withProviderType(String providerType) {
            this.providerType = providerType;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withDateAdded(OffsetDateTime dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withSchemeTokenData(Map<String, String> schemeTokenData) {
            this.schemeTokenData = schemeTokenData;
            return this;
        }

        public Builder withAuthenticationMethod(NetworkTokenAuthenticationMethod authenticationMethod) {
            this.authenticationMethod = authenticationMethod;
            return this;
        }

        public NetworkTokenSummary build() {
            return new NetworkTokenSummary(
                    networkTokenId, schemeTokenId, providerId, providerType, status,
                    dateAdded, schemeTokenData, authenticationMethod
            );
        }
    }
}
