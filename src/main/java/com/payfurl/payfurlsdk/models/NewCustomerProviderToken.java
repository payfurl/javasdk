package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ip;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentType type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private WebhookConfig webhook;

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

    /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
    public String getIp() {
        return ip;
    }

    /** What the provider token pays with, e.g. BANKPAYMENT; null lets the gateway decide. */
    public PaymentType getType() {
        return type;
    }

    /** Where to send this payment method's webhooks. */
    public WebhookConfig getWebhook() {
        return webhook;
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
                ", ip=" + ip +
                ", type=" + type +
                ", webhook=" + webhook +
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
        private String ip;
        private PaymentType type;
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

        /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        /** What the provider token pays with, e.g. BANKPAYMENT; null lets the gateway decide. */
        public Builder withType(PaymentType type) {
            this.type = type;
            return this;
        }

        /** Where to send this payment method's webhooks. */
        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }

        public NewCustomerProviderToken build() {
            NewCustomerProviderToken result = new NewCustomerProviderToken(reference, firstName, lastName, email, phone, address, providerId, providerToken, providerTokenData, verify, metaData);
            result.ip = ip;
            result.type = type;
            result.webhook = webhook;
            return result;
        }
    }
}
