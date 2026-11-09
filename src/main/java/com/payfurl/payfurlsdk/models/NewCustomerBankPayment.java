package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class NewCustomerBankPayment {
    private final String reference;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String providerId;
    private final BankPaymentInformation bankPaymentInformation;
    private final Address address;
    private final Map<String, String> metadata;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ip;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private WebhookConfig webhook;

    @JsonCreator
    public NewCustomerBankPayment(@JsonProperty("Reference") String reference,
                           @JsonProperty("FirstName") String firstName,
                           @JsonProperty("LastName") String lastName,
                           @JsonProperty("Email") String email,
                           @JsonProperty("Phone") String phone,
                           @JsonProperty("ProviderId") String providerId,
                           @JsonProperty("PaymentInformation") BankPaymentInformation bankPaymentInformation,
                           @JsonProperty("Address") Address address,
                           @JsonProperty("Metadata") Map<String, String> metadata) {
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.providerId = providerId;
        this.bankPaymentInformation = bankPaymentInformation;
        this.address = address;
        this.metadata = metadata;
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

    public BankPaymentInformation getBankPaymentInformation() {
        return bankPaymentInformation;
    }

    public Address getAddress() {
        return address;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
    public String getIp() {
        return ip;
    }

    /** Where to send this payment method's webhooks. */
    public WebhookConfig getWebhook() {
        return webhook;
    }

    @Override
    public String toString() {
        return "NewCustomerBankPayment{" +
                "reference=" + reference +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", providerId=" + providerId +
                ", bankPaymentInformation=" + bankPaymentInformation +
                ", address=" + address +
                ", metadata=" + metadata +
                ", ip=" + ip +
                ", webhook=" + webhook +
                '}';
    }

    public static class Builder {
        private String reference;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String providerId;
        private BankPaymentInformation bankPaymentInformation;
        private Address address;
        private Map<String, String> metadata;
        private String ip;
        private WebhookConfig webhook;

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

        public Builder withBankPaymentInformation(BankPaymentInformation bankPaymentInformation) {
            this.bankPaymentInformation = bankPaymentInformation;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        /** Where to send this payment method's webhooks. */
        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }

        public NewCustomerBankPayment build() {
            NewCustomerBankPayment result = new NewCustomerBankPayment(reference, firstName, lastName, email, phone, providerId, bankPaymentInformation, address, metadata);
            result.ip = ip;
            result.webhook = webhook;
            return result;
        }
    }
}
