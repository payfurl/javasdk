package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkTokenAuthenticationMethod {
    private final String authenticationMethodType;
    private final String authenticationSubject;
    private final UriData uriData;

    @JsonCreator
    public NetworkTokenAuthenticationMethod(
            @JsonProperty("AuthenticationMethodType") String authenticationMethodType,
            @JsonProperty("AuthenticationSubject") String authenticationSubject,
            @JsonProperty("UriData") UriData uriData) {
        this.authenticationMethodType = authenticationMethodType;
        this.authenticationSubject = authenticationSubject;
        this.uriData = uriData;
    }

    public String getAuthenticationMethodType() {
        return authenticationMethodType;
    }

    public String getAuthenticationSubject() {
        return authenticationSubject;
    }

    public UriData getUriData() {
        return uriData;
    }

    @Override
    public String toString() {
        return "NetworkTokenAuthenticationMethod{" +
                "authenticationMethodType='" + authenticationMethodType + '\'' +
                ", authenticationSubject='" + authenticationSubject + '\'' +
                ", uriData=" + uriData +
                '}';
    }

    public static class Builder {
        private String authenticationMethodType;
        private String authenticationSubject;
        private UriData uriData;

        public Builder withAuthenticationMethodType(String authenticationMethodType) {
            this.authenticationMethodType = authenticationMethodType;
            return this;
        }

        public Builder withAuthenticationSubject(String authenticationSubject) {
            this.authenticationSubject = authenticationSubject;
            return this;
        }

        public Builder withUriData(UriData uriData) {
            this.uriData = uriData;
            return this;
        }

        public NetworkTokenAuthenticationMethod build() {
            return new NetworkTokenAuthenticationMethod(
                    authenticationMethodType, authenticationSubject, uriData
            );
        }
    }
}
