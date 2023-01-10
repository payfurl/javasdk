package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateCustomer {
    private final String email;
    private final String phone;
    private final Address address;

    @JsonCreator
    public UpdateCustomer(@JsonProperty("Email") String email,
                          @JsonProperty("Phone") String phone,
                          @JsonProperty("Address") Address address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
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

    public static class Builder {
        private String email;
        private String phone;
        private Address address;

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

        public UpdateCustomer build() {
            return new UpdateCustomer(email, phone, address);
        }
    }
}
