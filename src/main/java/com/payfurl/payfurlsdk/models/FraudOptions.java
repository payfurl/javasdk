package com.payfurl.payfurlsdk.models;

public class FraudOptions {
    private String providerId;
    private String providerType;
    private boolean enabled;
    private boolean stopPaymentOnFailure;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isStopPaymentOnFailure() {
        return stopPaymentOnFailure;
    }

    public void setStopPaymentOnFailure(boolean stopPaymentOnFailure) {
        this.stopPaymentOnFailure = stopPaymentOnFailure;
    }
}

