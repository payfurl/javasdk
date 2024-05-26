package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class NewPaymentMethodBankPayment {
    private final String firstName;
    private final String lastName;
    private final String providerId;
    private final BankPaymentRequestInformation bankPaymentInformation;
    private final boolean setDefault;
    private final Map<String, String> metadata;

    @JsonCreator
    public NewPaymentMethodBankPayment(@JsonProperty("FirstName") String firstName,
                           @JsonProperty("LastName") String lastName,
                           @JsonProperty("ProviderId") String providerId,
                           @JsonProperty("BankPaymentInformation") BankPaymentRequestInformation bankPaymentInformation,
                           @JsonProperty("SetDefault") boolean setDefault,
                           @JsonProperty("Metadata") Map<String, String> metadata) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.providerId = providerId;
        this.bankPaymentInformation = bankPaymentInformation;
        this.setDefault = setDefault;
        this.metadata = metadata;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProviderId() {
        return providerId;
    }

    public BankPaymentRequestInformation getBankPaymentInformation() {
        return bankPaymentInformation;
    }

    public boolean getSetDefault() {
        return setDefault;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "NewPaymentMethodBankPayment{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", providerId=" + providerId +
                ", bankPaymentInformation=" + bankPaymentInformation +
                ", setDefault=" + setDefault +
                ", metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String providerId;
        private BankPaymentRequestInformation bankPaymentInformation;
        private boolean setDefault;
        private Map<String, String> metadata;

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withBankPaymentInformation(BankPaymentRequestInformation bankPaymentInformation) {
            this.bankPaymentInformation = bankPaymentInformation;
            return this;
        }

        public Builder withSetDefaults(boolean setDefault) {
            this.setDefault = setDefault;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public NewPaymentMethodBankPayment build() {
            return new NewPaymentMethodBankPayment(firstName, lastName, providerId, bankPaymentInformation, setDefault, metadata);
        }
    }
}
