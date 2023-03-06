package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewPaymentMethodToken {
    private final String token;
    private final boolean setDefault;
    private final Map<String, String> metadata;

    @JsonCreator
    public NewPaymentMethodToken(@JsonProperty("Token") String token,
                                 @JsonProperty("SetDefault") boolean setDefault,
                                 @JsonProperty("Metadata") Map<String, String> metadata) {
        this.token = token;
        this.setDefault = setDefault;
        this.metadata = metadata;
    }

    public String getToken() {
        return token;
    }

    public boolean getSetDefault() {
        return setDefault;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "NewPaymentMethodToken{" +
                "token='" + token + '\'' +
                " setDefault=" + setDefault +
                " metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private String token;
        private boolean setDefault;
        private Map<String, String> metadata;

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withSetDefault(boolean setDefault) {
            this.setDefault = setDefault;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public NewPaymentMethodToken build() {
            return new NewPaymentMethodToken(token, setDefault, metadata);
        }
    }
}
