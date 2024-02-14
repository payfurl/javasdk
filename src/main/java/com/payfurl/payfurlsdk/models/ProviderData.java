package com.payfurl.payfurlsdk.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProviderData {
    private String providerId;
    private String type;
    private String name;
    private String environment;
    private Map<String, String> authenticationParameters;
    private Map<String, String> additionalParameters;
    private String providerCountry;
    private List<ProviderCostDataModel> costData;
    private List<ProviderVisibilityDataModel> hideConfiguration;
    private List<ProviderVisibilityDataModel> requireConfiguration;
    private String fallbackProviderId;
    private String currency;
    private String maxCapability;
    private boolean hasPartialRefund;
    private ProviderInfoModel fallbackProvider;
    private boolean hasThreeDsCapability;

    public ProviderData() {
        this.authenticationParameters = new HashMap<>();
    }

    // getters and setters
    public String getProviderId() {
        return this.providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Map<String, String> getAuthenticationParameters() {
        return this.authenticationParameters;
    }

    public void setAuthenticationParameters(Map<String, String> authenticationParameters) {
        this.authenticationParameters = authenticationParameters;
    }

    public Map<String, String> getAdditionalParameters() {
        return this.additionalParameters;
    }

    public void setAdditionalParameters(Map<String, String> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    public String getProviderCountry() {
        return this.providerCountry;
    }

    public void setProviderCountry(String providerCountry) {
        this.providerCountry = providerCountry;
    }

    public List<ProviderCostDataModel> getCostData() {
        return this.costData;
    }

    public void setCostData(List<ProviderCostDataModel> costData) {
        this.costData = costData;
    }

    public List<ProviderVisibilityDataModel> getHideConfiguration() {
        return this.hideConfiguration;
    }

    public void setHideConfiguration(List<ProviderVisibilityDataModel> hideConfiguration) {
        this.hideConfiguration = hideConfiguration;
    }

    public List<ProviderVisibilityDataModel> getRequireConfiguration() {
        return this.requireConfiguration;
    }

    public void setRequireConfiguration(List<ProviderVisibilityDataModel> requireConfiguration) {
        this.requireConfiguration = requireConfiguration;
    }

    public String getFallbackProviderId() {
        return this.fallbackProviderId;
    }

    public void setFallbackProviderId(String fallbackProviderId) {
        this.fallbackProviderId = fallbackProviderId;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMaxCapability() {
        return this.maxCapability;
    }

    public void setMaxCapability(String maxCapability) {
        this.maxCapability = maxCapability;
    }

    public boolean isHasPartialRefund() {
        return this.hasPartialRefund;
    }

    public void setHasPartialRefund(boolean hasPartialRefund) {
        this.hasPartialRefund = hasPartialRefund;
    }

    public ProviderInfoModel getFallbackProvider() {
        return this.fallbackProvider;
    }

    public void setFallbackProvider(ProviderInfoModel fallbackProvider) {
        this.fallbackProvider = fallbackProvider;
    }

    public boolean isHasThreeDsCapability() {
        return this.hasThreeDsCapability;
    }

    public void setHasThreeDsCapability(boolean hasThreeDsCapability) {
        this.hasThreeDsCapability = hasThreeDsCapability;
    }
}
