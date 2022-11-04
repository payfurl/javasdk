package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CustomerDataSummary {
    public final String customerId;
    public final String reference;
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String phone;
    public final Date dateAdded;

    @JsonCreator
    public CustomerDataSummary(@JsonProperty("CustomerId") String customerId,
                               @JsonProperty("Reference") String reference,
                               @JsonProperty("FirstName") String firstName,
                               @JsonProperty("LastName") String lastName,
                               @JsonProperty("Email") String email,
                               @JsonProperty("Phone") String phone,
                               @JsonProperty("DateAdded") Date dateAdded) {
        this.customerId = customerId;
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateAdded = dateAdded;
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

    @Override
    public String toString() {
        return "CustomerDataSummary{" +
                "customerId='" + customerId + '\'' +
                ", reference='" + reference + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
