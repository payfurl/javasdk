package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A customer with a PayTo agreement: {@code POST /customer/payto} (the API's api.Models.NewCustomerPayTo).
 * Null fields are left out of the request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewCustomerPayTo {
    private final NewPayToAgreement payToAgreement;
    private final String email;
    private final String phone;
    private final String reference;
    private final String firstName;
    private final String lastName;
    private final String ip;
    private final Address address;
    private final WebhookConfig webhook;

    private NewCustomerPayTo(Builder builder) {
        this.payToAgreement = builder.payToAgreement;
        this.email = builder.email;
        this.phone = builder.phone;
        this.reference = builder.reference;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.ip = builder.ip;
        this.address = builder.address;
        this.webhook = builder.webhook;
    }

    /** Required. */
    public NewPayToAgreement getPayToAgreement() {
        return payToAgreement;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
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

    /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
    public String getIp() {
        return ip;
    }

    public Address getAddress() {
        return address;
    }

    public WebhookConfig getWebhook() {
        return webhook;
    }

    @Override
    public String toString() {
        return "NewCustomerPayTo{" +
                "payToAgreement=" + payToAgreement +
                ", email=" + email +
                ", phone=" + phone +
                ", reference=" + reference +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", ip=" + ip +
                ", address=" + address +
                ", webhook=" + webhook +
                '}';
    }

    public static class Builder {
        private NewPayToAgreement payToAgreement;
        private String email;
        private String phone;
        private String reference;
        private String firstName;
        private String lastName;
        private String ip;
        private Address address;
        private WebhookConfig webhook;

        /** Required. */
        public Builder withPayToAgreement(NewPayToAgreement payToAgreement) {
            this.payToAgreement = payToAgreement;
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

        /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }

        public NewCustomerPayTo build() {
            return new NewCustomerPayTo(this);
        }
    }
}
