package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CustomerData {
    private final String customerId;
    private final String reference;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final Date dateAdded;
    private final PaymentMethodSummary defaultPaymentMethod;
    private final Address address;

    @JsonCreator
    public CustomerData(@JsonProperty("CustomerId") String customerId,
                        @JsonProperty("Reference") String reference,
                        @JsonProperty("FirstName") String firstName,
                        @JsonProperty("LastName") String lastName,
                        @JsonProperty("Email") String email,
                        @JsonProperty("Phone") String phone,
                        @JsonProperty("DateAdded") Date dateAdded,
                        @JsonProperty("DefaultPaymentMethod") PaymentMethodSummary defaultPaymentMethod,
                        @JsonProperty("Address") Address address) {
        this.customerId = customerId;
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateAdded = dateAdded;
        this.defaultPaymentMethod = defaultPaymentMethod;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public PaymentMethodSummary getDefaultPaymentMethod() {
        return defaultPaymentMethod;
    }

    public Address getAddress() {
        return address;
    }

    public static class Builder {
        private String customerId;
        private String reference;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private Date dateAdded;
        private Address address;
        private PaymentMethodSummary defaultPaymentMethod;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

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

        public Builder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withDefaultPaymentMethod(PaymentMethodSummary defaultPaymentMethod) {
            this.defaultPaymentMethod = defaultPaymentMethod;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public CustomerData build() {
            return new CustomerData(customerId, reference, firstName, lastName, email, phone, dateAdded, defaultPaymentMethod, address);
        }
    }
}
