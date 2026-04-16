package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewCustomerToken {
    private final String reference;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String ip;
    private final String token;
    private final Address address;
    private final Map<String, String> metadata;
    private final WebhookConfig webhook;

    @JsonCreator
    public NewCustomerToken(@JsonProperty("Reference") String reference,
                            @JsonProperty("FirstName") String firstName,
                            @JsonProperty("LastName") String lastName,
                            @JsonProperty("Email") String email,
                            @JsonProperty("Phone") String phone,
                            @JsonProperty("Ip") String ip,
                            @JsonProperty("Address") Address address,
                            @JsonProperty("Token") String token,
                            @JsonProperty("Metadata") Map<String, String> metadata,
                            @JsonProperty("Webhook") WebhookConfig webhook) {
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.ip = ip;
        this.token = token;
        this.address = address;
        this.metadata = metadata;
        this.webhook = webhook;
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

    public String getIp() {
        return ip;
    }

    public String getToken() {
        return token;
    }

    public Address getAddress() {
        return address;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public WebhookConfig getWebhook() {
        return webhook;
    }

    @Override
    public String toString() {
        return "NewCustomerToken{" +
                "reference=" + reference +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", address=" + address +
                ", token='" + token + '\'' +
                ", metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private String reference;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String ip;
        private String token;
        private Address address;
        private Map<String, String> metadata;
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

        public Builder withIp(String ip) {
            this.ip = ip;
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

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }

        public NewCustomerToken build() {
            return new NewCustomerToken(reference, firstName, lastName, email, phone, ip, address, token, metadata, webhook);
        }
    }
}
