package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCustomerToken {
    private final String reference;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String token;
    private final Address address;

    @JsonCreator
    public NewCustomerToken(@JsonProperty("Reference") String reference,
                            @JsonProperty("FirstName") String firstName,
                            @JsonProperty("LastName") String lastName,
                            @JsonProperty("Email") String email,
                            @JsonProperty("Phone") String phone,
                            @JsonProperty("Address") Address address,
                            @JsonProperty("Token") String token) {
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.token = token;
        this.address = address;
    }

    public String getReference() {
        return reference;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getToken() {
        return token;
    }

    public Address getAddress() {
        return address;
    }

    public static class Builder {
        private String reference;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String token;
        private Address address;

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public NewCustomerToken build() {
            return new NewCustomerToken(reference, firstName, lastName, email, phone, address, token);
        }
    }
}
