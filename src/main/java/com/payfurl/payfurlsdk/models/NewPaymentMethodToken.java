package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPaymentMethodToken {
    private final String token;
    private final boolean setDefault;

    @JsonCreator
    public NewPaymentMethodToken(@JsonProperty("Token") String token,
                                 @JsonProperty("SetDefault") boolean setDefault) {
        this.token = token;
        this.setDefault = setDefault;
    }

    public String getToken() {
        return token;
    }

    public boolean getSetDefault() {
        return setDefault;
    }

    @Override
    public String toString() {
        return "NewPaymentMethodToken{" +
                "token='" + token + '\'' +
                '}';
    }

    public static class Builder {
        private String token;
        private boolean setDefault;

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withSetDefault(boolean setDefault) {
            this.setDefault = setDefault;
            return this;
        }

        public NewPaymentMethodToken build() {
            return new NewPaymentMethodToken(token, setDefault);
        }
    }
}
