package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NewProviderToken {

    private final String providerId;
    private final String providerToken;
    private final String email;
    private final Map<String, String> providerTokenData;
    private final Map<String, String> metadata;
    private final boolean verify;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentType type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fallbackPaymentMethodId;

    @JsonCreator
    public NewProviderToken(@JsonProperty("ProviderId") String providerId,
                            @JsonProperty("ProviderToken") String providerToken,
                            @JsonProperty("Email") String email,
                            @JsonProperty("ProviderTokenData") Map<String, String> providerTokenData,
                            @JsonProperty("Metadata") Map<String, String> metadata,
                            @JsonProperty("Verify") boolean verify) {
        this.providerId = providerId;
        this.providerToken = providerToken;
        this.email = email;
        this.providerTokenData = providerTokenData;
        this.metadata = metadata;
        this.verify = verify;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, String> getProviderTokenData() {
        return providerTokenData;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public boolean isVerify() {
        return verify;
    }

    /** What the provider token pays with, e.g. BANKPAYMENT; null lets the gateway decide. */
    public PaymentType getType() {
        return type;
    }

    /** The payment method to charge when this one fails. */
    public String getFallbackPaymentMethodId() {
        return fallbackPaymentMethodId;
    }

    public static class Builder {
        private String providerId;
        private String providerToken;
        private String email;
        private Map<String, String> providerTokenData;
        private Map<String, String> metadata;
        private boolean verify;
        private PaymentType type;
        private String fallbackPaymentMethodId;

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withProviderToken(String providerToken) {
            this.providerToken = providerToken;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
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

        public Builder withVerify(boolean verify) {
            this.verify = verify;
            return this;
        }

        /** What the provider token pays with, e.g. BANKPAYMENT; null lets the gateway decide. */
        public Builder withType(PaymentType type) {
            this.type = type;
            return this;
        }

        /** The payment method to charge when this one fails. */
        public Builder withFallbackPaymentMethodId(String fallbackPaymentMethodId) {
            this.fallbackPaymentMethodId = fallbackPaymentMethodId;
            return this;
        }

        public NewProviderToken build() {
            NewProviderToken result = new NewProviderToken(providerId, providerToken, email, providerTokenData, metadata, verify);
            result.type = type;
            result.fallbackPaymentMethodId = fallbackPaymentMethodId;
            return result;
        }
    }
}
