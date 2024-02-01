package com.payfurl.payfurlsdk.models;

import java.util.List;

public class InfoProviders {
    private List<String> paypalProviders;
    private boolean hasCardProviders;
    private boolean hasPaypalProviders;
    private String required3dsProvider;
    private boolean hasPayToProviders;
    private List<String> payToProviders;
    private List<PayToProviderOptions> payToProvidersWithOptions;
    private boolean hasBnplProviders;
    private List<BnplOptions> bnplProviders;
    private boolean hasVisaInstallmentsProviders;
    private List<String> visaInstallmentsProviders;
    private boolean hasFraudProviders;
    private List<FraudOptions> fraudProviders;
    private boolean hasThreeDsProviders;
    private List<ThreeDsOptions> threeDsProviders;
    private boolean hasGooglePayProviders;
    private List<GooglePayProviderInfo> googlePayProviders;
    private boolean clickToPayEnabled;
    private ClickToPayOptions clickToPayOptions;

    public List<String> getPaypalProviders() {
        return this.paypalProviders;
    }

    public void setPaypalProviders(List<String> paypalProviders) {
        this.paypalProviders = paypalProviders;
    }

    public boolean isHasCardProviders() {
        return this.hasCardProviders;
    }

    public void setHasCardProviders(boolean hasCardProviders) {
        this.hasCardProviders = hasCardProviders;
    }

    public boolean isHasPaypalProviders() {
        return this.hasPaypalProviders;
    }

    public void setHasPaypalProviders(boolean hasPaypalProviders) {
        this.hasPaypalProviders = hasPaypalProviders;
    }

    public String getRequired3dsProvider() {
        return this.required3dsProvider;
    }

    public void setRequired3dsProvider(String required3dsProvider) {
        this.required3dsProvider = required3dsProvider;
    }

    public boolean isHasPayToProviders() {
        return this.hasPayToProviders;
    }

    public void setHasPayToProviders(boolean hasPayToProviders) {
        this.hasPayToProviders = hasPayToProviders;
    }

    public List<String> getPayToProviders() {
        return this.payToProviders;
    }

    public void setPayToProviders(List<String> payToProviders) {
        this.payToProviders = payToProviders;
    }

    public List<PayToProviderOptions> getPayToProvidersWithOptions() {
        return this.payToProvidersWithOptions;
    }

    public void setPayToProvidersWithOptions(List<PayToProviderOptions> payToProvidersWithOptions) {
        this.payToProvidersWithOptions = payToProvidersWithOptions;
    }

    public boolean isHasBnplProviders() {
        return this.hasBnplProviders;
    }

    public void setHasBnplProviders(boolean hasBnplProviders) {
        this.hasBnplProviders = hasBnplProviders;
    }

    public List<BnplOptions> getBnplProviders() {
        return this.bnplProviders;
    }

    public void setBnplProviders(List<BnplOptions> bnplProviders) {
        this.bnplProviders = bnplProviders;
    }

    public boolean isHasVisaInstallmentsProviders() {
        return this.hasVisaInstallmentsProviders;
    }

    public void setHasVisaInstallmentsProviders(boolean hasVisaInstallmentsProviders) {
        this.hasVisaInstallmentsProviders = hasVisaInstallmentsProviders;
    }

    public List<String> getVisaInstallmentsProviders() {
        return this.visaInstallmentsProviders;
    }

    public void setVisaInstallmentsProviders(List<String> visaInstallmentsProviders) {
        this.visaInstallmentsProviders = visaInstallmentsProviders;
    }

    public boolean isHasFraudProviders() {
        return this.hasFraudProviders;
    }

    public void setHasFraudProviders(boolean hasFraudProviders) {
        this.hasFraudProviders = hasFraudProviders;
    }

    public List<FraudOptions> getFraudProviders() {
        return this.fraudProviders;
    }

    public void setFraudProviders(List<FraudOptions> fraudProviders) {
        this.fraudProviders = fraudProviders;
    }

    public boolean isHasThreeDsProviders() {
        return this.hasThreeDsProviders;
    }

    public void setHasThreeDsProviders(boolean hasThreeDsProviders) {
        this.hasThreeDsProviders = hasThreeDsProviders;
    }

    public List<ThreeDsOptions> getThreeDsProviders() {
        return this.threeDsProviders;
    }

    public void setThreeDsProviders(List<ThreeDsOptions> threeDsProviders) {
        this.threeDsProviders = threeDsProviders;
    }

    public boolean isHasGooglePayProviders() {
        return this.hasGooglePayProviders;
    }

    public void setHasGooglePayProviders(boolean hasGooglePayProviders) {
        this.hasGooglePayProviders = hasGooglePayProviders;
    }

    public List<GooglePayProviderInfo> getGooglePayProviders() {
        return this.googlePayProviders;
    }

    public void setGooglePayProviders(List<GooglePayProviderInfo> googlePayProviders) {
        this.googlePayProviders = googlePayProviders;
    }

    public boolean isClickToPayEnabled() {
        return this.clickToPayEnabled;
    }

    public void setClickToPayEnabled(boolean clickToPayEnabled) {
        this.clickToPayEnabled = clickToPayEnabled;
    }

    public ClickToPayOptions getClickToPayOptions() {
        return this.clickToPayOptions;
    }

    public void setClickToPayOptions(ClickToPayOptions clickToPayOptions) {
        this.clickToPayOptions = clickToPayOptions;
    }
}
