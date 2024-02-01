package com.payfurl.payfurlsdk.models;

import java.util.Map;

public class InfoProvider {
    private String threeDsProviderId;
    private String threeDsProviderType;
    private Map<String, String> requiredFields;
    private GooglePayBaseInfo googlePay;

    public String getThreeDsProviderId() {
        return threeDsProviderId;
    }

    public void setThreeDsProviderId(String threeDsProviderId) {
        this.threeDsProviderId = threeDsProviderId;
    }

    public String getThreeDsProviderType() {
        return threeDsProviderType;
    }

    public void setThreeDsProviderType(String threeDsProviderType) {
        this.threeDsProviderType = threeDsProviderType;
    }

    public Map<String, String> getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(Map<String, String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public GooglePayBaseInfo getGooglePay() {
        return googlePay;
    }

    public void setGooglePay(GooglePayBaseInfo googlePay) {
        this.googlePay = googlePay;
    }
}
