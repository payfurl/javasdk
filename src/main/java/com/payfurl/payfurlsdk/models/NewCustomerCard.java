package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class NewCustomerCard {
    private final String reference;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String providerId;
    private final CardRequestInformation paymentInformation;
    private final Address address;
    private final boolean vaultCard;
    private final Date vaultExpireDate;
    private final Integer vaultExpireSeconds;
    private final Map<String, String> metadata;
    private final boolean skipExpiryDateValidation;

    @JsonCreator
    public NewCustomerCard(@JsonProperty("Reference") String reference,
                           @JsonProperty("FirstName") String firstName,
                           @JsonProperty("LastName") String lastName,
                           @JsonProperty("Email") String email,
                           @JsonProperty("Phone") String phone,
                           @JsonProperty("ProviderId") String providerId,
                           @JsonProperty("PaymentInformation") CardRequestInformation paymentInformation,
                           @JsonProperty("Address") Address address,
                           @JsonProperty("VaultCard") boolean vaultCard,
                           @JsonProperty("VaultExpireDate") Date vaultExpireDate,
                           @JsonProperty("VaultExpireSeconds") Integer vaultExpireSeconds,
                           @JsonProperty("Metadata") Map<String, String> metadata,
                           @JsonProperty("SkipExpiryDateValidation") boolean skipExpiryDateValidation) {
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.providerId = providerId;
        this.paymentInformation = paymentInformation;
        this.address = address;
        this.vaultCard = vaultCard;
        this.vaultExpireDate = vaultExpireDate;
        this.vaultExpireSeconds = vaultExpireSeconds;
        this.metadata = metadata;
        this.skipExpiryDateValidation = skipExpiryDateValidation;
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

    public String getProviderId() {
        return providerId;
    }

    public CardRequestInformation getPaymentInformation() {
        return paymentInformation;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isVaultCard() {
        return vaultCard;
    }

    public Date getVaultExpireDate() {
        return vaultExpireDate;
    }

    public Integer getVaultExpireSeconds() {
        return vaultExpireSeconds;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public boolean getSkipExpiryDateValidation() { return skipExpiryDateValidation; }

    @Override
    public String toString() {
        return "NewCustomerCard{" +
                "reference=" + reference +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", providerId=" + providerId +
                ", paymentInformation=" + paymentInformation +
                ", address=" + address +
                ", vaultCard=" + vaultCard +
                ", vaultExpireDate=" + vaultExpireDate +
                ", vaultExpireSeconds=" + vaultExpireSeconds +
                ", metadata=" + metadata +
                ", skipExpiryDateValidation=" + skipExpiryDateValidation +
                '}';
    }

    public static class Builder {
        private String reference;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String providerId;
        private CardRequestInformation paymentInformation;
        private Address address;
        private boolean vaultCard = false;
        private Date vaultExpireDate;
        private Integer vaultExpireSeconds;
        private Map<String, String> metadata;
        private boolean skipExpiryDateValidation = false;

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

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withPaymentInformation(CardRequestInformation paymentInformation) {
            this.paymentInformation = paymentInformation;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withVaultCard(boolean vaultCard) {
            this.vaultCard = vaultCard;
            return this;
        }

        public Builder withVaultExpireDate(Date vaultExpireDate) {
            this.vaultExpireDate = vaultExpireDate;
            return this;
        }

        public Builder withVaultExpireSeconds(Integer vaultExpireSeconds) {
            this.vaultExpireSeconds = vaultExpireSeconds;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder withSkipExpiryDateValidation(boolean skipExpiryDateValidation) {
            this.skipExpiryDateValidation = skipExpiryDateValidation;
            return this;
        }

        public NewCustomerCard build() {
            return new NewCustomerCard(reference, firstName, lastName, email, phone, providerId, paymentInformation, address, vaultCard, vaultExpireDate, vaultExpireSeconds, metadata, skipExpiryDateValidation);
        }
    }
}
