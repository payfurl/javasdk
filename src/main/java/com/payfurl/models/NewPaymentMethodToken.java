package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPaymentMethodToken {
    private final String token;

    @JsonCreator
    public NewPaymentMethodToken(@JsonProperty("Token") String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "NewPaymentMethodToken{" +
                "token='" + token + '\'' +
                '}';
    }

    public static class Builder {
        private String token;

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public NewPaymentMethodToken build() {
            return new NewPaymentMethodToken(token);
        }
    }
}
