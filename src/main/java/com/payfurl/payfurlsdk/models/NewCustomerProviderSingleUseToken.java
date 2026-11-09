package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewCustomerProviderSingleUseToken {

    private final String providerId;
    private final String providerToken;
    private final Map<String, String> providerTokenData;
    private final Map<String, String> metadata;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reference;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ip;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Address address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fallbackPaymentMethodId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private WebhookConfig webhook;

    @JsonCreator
    public NewCustomerProviderSingleUseToken(
                                             @JsonProperty("ProviderId") String providerId,
                                             @JsonProperty("ProviderToken") String providerToken,
                                             @JsonProperty("ProviderTokenData") Map<String, String> providerTokenData,
                                             @JsonProperty("Metadata") Map<String, String> metadata) {
        this.providerId = providerId;
        this.providerToken = providerToken;
        this.providerTokenData = providerTokenData;
        this.metadata = metadata;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public Map<String, String> getProviderTokenData() {
        return providerTokenData;
    }

    public Map<String, String> getMetadata() {
        return metadata;
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

    /** The payment method to charge when this one fails. */
    public String getFallbackPaymentMethodId() {
        return fallbackPaymentMethodId;
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
                ", providerTokenData=" + providerTokenData +
                ", metadata=" + metadata +
                ", email=" + email +
                ", phone=" + phone +
                ", reference=" + reference +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", ip=" + ip +
                ", address=" + address +
                ", fallbackPaymentMethodId=" + fallbackPaymentMethodId +
                ", webhook=" + webhook +
                '}';
    }

    public static class Builder {
        private String providerId;
        private String providerToken;
        private Map<String, String> providerTokenData;
        private Map<String, String> metadata;
        private String email;
        private String phone;
        private String reference;
        private String firstName;
        private String lastName;
        private String ip;
        private Address address;
        private String fallbackPaymentMethodId;
        private WebhookConfig webhook;

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

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
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

        /** The payment method to charge when this one fails. */
        public Builder withFallbackPaymentMethodId(String fallbackPaymentMethodId) {
            this.fallbackPaymentMethodId = fallbackPaymentMethodId;
            return this;
        }

        /** Where to send this payment method's webhooks. */
        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }

        public NewCustomerProviderSingleUseToken build() {
            NewCustomerProviderSingleUseToken result = new NewCustomerProviderSingleUseToken(providerId, providerToken, providerTokenData, metadata);
            result.email = email;
            result.phone = phone;
            result.reference = reference;
            result.firstName = firstName;
            result.lastName = lastName;
            result.ip = ip;
            result.address = address;
            result.fallbackPaymentMethodId = fallbackPaymentMethodId;
            result.webhook = webhook;
            return result;
        }
    }
}
