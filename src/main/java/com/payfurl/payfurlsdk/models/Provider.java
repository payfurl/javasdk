package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Provider {

    private final String providerId;
    private final String type;
    private final String name;
    private final Environment environment;
    private final Map<String, String> authenticationParameters;
    private final String providerCountry;
    private final List<ProviderCostData> costData;
    private final List<ProviderVisibilityData> hideConfiguration;
    private final List<ProviderVisibilityData> requireConfiguration;
    private final String fallbackProviderId;
    private final String currency;
    private final String maxCapability;
    private final Boolean hasPartialRefund;
    private final ProviderInfo fallbackProvider;


    @JsonCreator
    public Provider(@JsonProperty("ProvideId") String providerId,
                    @JsonProperty("Type") String type,
                    @JsonProperty("Name") String name,
                    @JsonProperty("Environment") Environment environment,
                    @JsonProperty("AuthenticationParameters") Map<String, String> authenticationParameters,
                    @JsonProperty("ProviderCountry") String providerCountry,
                    @JsonProperty("CostData") List<ProviderCostData> costData,
                    @JsonProperty("HideConfiguration") List<ProviderVisibilityData> hideConfiguration,
                    @JsonProperty("RequireConfiguration") List<ProviderVisibilityData> requireConfiguration,
                    @JsonProperty("FallbackProviderId") String fallbackProviderId,
                    @JsonProperty("Currency") String currency,
                    @JsonProperty("MaxCapability") String maxCapability,
                    @JsonProperty("HasPartialRefund") Boolean hasPartialRefund,
                    @JsonProperty("FallbackProvider") ProviderInfo fallbackProvider) {

        this.providerId = providerId;
        this.type = type;
        this.name = name;
        this.environment = environment;
        this.authenticationParameters = authenticationParameters;
        this.providerCountry = providerCountry;
        this.costData = costData;
        this.hideConfiguration = hideConfiguration;
        this.requireConfiguration = requireConfiguration;
        this.fallbackProviderId = fallbackProviderId;
        this.currency = currency;
        this.maxCapability = maxCapability;
        this.hasPartialRefund = hasPartialRefund;
        this.fallbackProvider = fallbackProvider;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Map<String, String> getAuthenticationParameters() {
        return authenticationParameters;
    }

    public String getProviderCountry() {
        return providerCountry;
    }

    public List<ProviderCostData> getCostData() {
        return costData;
    }

    public List<ProviderVisibilityData> getHideConfiguration() {
        return hideConfiguration;
    }

    public List<ProviderVisibilityData> getRequireConfiguration() {
        return requireConfiguration;
    }

    public String getFallbackProviderId() {
        return fallbackProviderId;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMaxCapability() {
        return maxCapability;
    }

    public Boolean getHasPartialRefund() {
        return hasPartialRefund;
    }

    public ProviderInfo getFallbackProvider() {
        return fallbackProvider;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providerId=" + providerId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", environment='" + environment + '\'' +
                ", authenticationParameters='" + authenticationParameters + '\'' +
                ", providerCountry='" + providerCountry + '\'' +
                ", costData='" + costData + '\'' +
                ", hideConfiguration='" + hideConfiguration + '\'' +
                ", requireConfiguration='" + requireConfiguration + '\'' +
                ", fallbackProviderId='" + fallbackProviderId + '\'' +
                ", currency='" + currency + '\'' +
                ", maxCapability='" + maxCapability + '\'' +
                ", hasPartialRefund='" + hasPartialRefund + '\'' +
                ", fallbackProvider='" + fallbackProvider + '\'' +
                '}';
    }
}
