package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewCustomerProviderToken {
    private final String reference;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final Address address;
    private final String providerId;
    private final String providerToken;
    private final Map<String, String> providerTokenData;
    private final Map<String, String> metaData;
    private final Boolean verify;

    @JsonCreator
    public NewCustomerProviderToken(@JsonProperty("Reference") String reference,
                                    @JsonProperty("FirstName") String firstName,
                                    @JsonProperty("LastName") String lastName,
                                    @JsonProperty("Email") String email,
                                    @JsonProperty("Phone") String phone,
                                    @JsonProperty("Address") Address address,
                                    @JsonProperty("ProviderId") String providerId,
                                    @JsonProperty("ProviderToken") String providerToken,
                                    @JsonProperty("ProviderTokenData") Map<String, String> providerTokenData,
                                    @JsonProperty("Verify") Boolean verify,
                                    @JsonProperty("MetaData") Map<String, String> metaData
                                    ) {
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.providerId = providerId;
        this.providerToken = providerToken;
        this.providerTokenData = providerTokenData;
        this.verify = verify;
        this.metaData = metaData;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public Boolean getVerify() { return verify; }

    public Map<String, String> getProviderTokenData() {
        return providerTokenData;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    @Override
    public String toString() {
        return "NewCustomerProviderToken{" +
                "providerId='" + providerId + '\'' +
                ", providerToken='" + providerToken + '\'' +
                ", reference='" + reference + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", providerTokenData=" + providerTokenData +
                ", address=" + address +
                ", verify=" + verify +
                ", metaData=" + metaData +
                '}';
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

    public Address getAddress() {
        return address;
    }

    public static class Builder {
        private String reference;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private Address address;
        private String providerId;
        private String providerToken;
        private Map<String, String> providerTokenData;
        private Map<String, String> metaData;
        private Boolean verify = false;

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

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withProviderToken(String providerToken) {
            this.providerToken = providerToken;
            return this;
        }

        public Builder withProviderTokenData(Map<String, String> providerTokenData) {
            this.providerTokenData = providerTokenData;
            return this;
        }

        public Builder withMetadata(Map<String, String> metaData) {
            this.metaData = metaData;
            return this;
        }

        public Builder withVerify(Boolean verify) {
            this.verify = verify;
            return this;
        }

        public NewCustomerProviderToken build() {
            return new NewCustomerProviderToken(reference, firstName, lastName, email, phone, address, providerId, providerToken, providerTokenData, verify, metaData);
        }
    }
}
