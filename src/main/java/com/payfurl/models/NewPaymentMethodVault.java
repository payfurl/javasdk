package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPaymentMethodVault {
    private final String providerId;
    private final String vaultId;
    private final String paymentMethodId;
    private final String ccv;

    @JsonCreator
    public NewPaymentMethodVault(@JsonProperty("ProviderId") String providerId,
                                 @JsonProperty("VaultId") String vaultId,
                                 @JsonProperty("PaymentMethodId") String paymentMethodId,
                                 @JsonProperty("Ccv") String ccv) {
        this.providerId = providerId;
        this.vaultId = vaultId;
        this.paymentMethodId = paymentMethodId;
        this.ccv = ccv;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getVaultId() {
        return vaultId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public String getCcv() {
        return ccv;
    }

    @Override
    public String toString() {
        return "NewPaymentMethodVault{" +
                "providerId='" + providerId + '\'' +
                ", vaultId='" + vaultId + '\'' +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                ", ccv='" + ccv + '\'' +
                '}';
    }

    public static class Builder {
        private String providerId;
        private String vaultId;
        private String paymentMethodId;
        private String ccv;

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withVaultId(String vaultId) {
            this.vaultId = vaultId;
            return this;
        }

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Builder withCcv(String ccv) {
            this.ccv = ccv;
            return this;
        }

        public NewPaymentMethodVault build() {
            return new NewPaymentMethodVault(providerId, vaultId, paymentMethodId, ccv);
        }
    }
}
