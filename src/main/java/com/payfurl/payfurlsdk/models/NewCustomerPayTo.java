package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCustomerPayTo {
    private final NewPayToAgreement payToAgreement;
    private final WebhookConfig webhook;
    private final String reference;
    private final String firstName;
    private final String lastName;
    private final String ip;
    private final Address address;
    private final String email;
    private final String phone;

    @JsonCreator
    public NewCustomerPayTo(@JsonProperty("PayToAgreement") NewPayToAgreement payToAgreement,
                            @JsonProperty("Webhook") WebhookConfig webhook,
                            @JsonProperty("Reference") String reference,
                            @JsonProperty("FirstName") String firstName,
                            @JsonProperty("LastName") String lastName,
                            @JsonProperty("Ip") String ip,
                            @JsonProperty("Address") Address address,
                            @JsonProperty("Email") String email,
                            @JsonProperty("Phone") String phone) {
        this.payToAgreement = payToAgreement;
        this.webhook = webhook;
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ip = ip;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public NewPayToAgreement getPayToAgreement() {
        return payToAgreement;
    }

    public WebhookConfig getWebhook() {
        return webhook;
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

    public String getIp() {
        return ip;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public static class Builder {
        private NewPayToAgreement payToAgreement;
        private WebhookConfig webhook;
        private String reference;
        private String firstName;
        private String lastName;
        private String ip;
        private Address address;
        private String email;
        private String phone;

        public Builder withPayToAgreement(NewPayToAgreement payToAgreement) {
            this.payToAgreement = payToAgreement;
            return this;
        }

        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
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

        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
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

        public NewCustomerPayTo build() {
            return new NewCustomerPayTo(payToAgreement, webhook, reference, firstName, lastName, ip, address, email, phone);
        }
    }
}
