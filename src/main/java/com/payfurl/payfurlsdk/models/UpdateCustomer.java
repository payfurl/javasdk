package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateCustomer {
    private final String email;
    private final String phone;
    private final Address address;
    private final String defaultPaymentMethodId;

    @JsonCreator
    public UpdateCustomer(@JsonProperty("Email") String email,
                          @JsonProperty("Phone") String phone,
                          @JsonProperty("Address") Address address,
                          @JsonProperty("DefaultPaymentMethodId") String defaultPaymentMethodId) {
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.defaultPaymentMethodId = defaultPaymentMethodId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }
    public String getDefaultPaymentMethodId() {
        return defaultPaymentMethodId;
    }

    public static class Builder {
        private String email;
        private String phone;
        private Address address;
        private String defaultPaymentMethodId;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withDefaultPaymentMethodId(String defaultPaymentMethodId) {
            this.defaultPaymentMethodId = defaultPaymentMethodId;
            return this;
        }

        public UpdateCustomer build() {
            return new UpdateCustomer(email, phone, address, defaultPaymentMethodId);
        }
    }
}
