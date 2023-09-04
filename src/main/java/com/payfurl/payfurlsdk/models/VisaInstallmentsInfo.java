package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;


public class VisaInstallmentsInfo {
    private final String providerId;
    private final String visPlanId;
    private final String visPlanIdRef;
    private final Integer visAcceptedTAndCVersion;
    private final String visPlanName;
    private final Integer visNumberOfInstallments;
    private final BigDecimal visTotalFees;
    private final BigDecimal visTotalPlanCost;
    private final String visInstallmentFrequency;
    private final List<VisaInstallmentsPlanTermsAndConditionsType> visTermsAndConditions;

    @JsonCreator
    public VisaInstallmentsInfo(@JsonProperty("ProviderId") String providerId,
                                @JsonProperty("VisPlanId") String visPlanId,
                                @JsonProperty("VisPlanIdRef") String visPlanIdRef,
                                @JsonProperty("VisAcceptedTAndCVersion") Integer visAcceptedTAndCVersion,
                                @JsonProperty("VisPlanName") String visPlanName,
                                @JsonProperty("VisNumberOfInstallments") Integer visNumberOfInstallments,
                                @JsonProperty("VisTotalFees") BigDecimal visTotalFees,
                                @JsonProperty("VisTotalPlanCost") BigDecimal visTotalPlanCost,
                                @JsonProperty("VisInstallmentFrequency") String visInstallmentFrequency,
                                @JsonProperty("VisTermsAndConditions") List<VisaInstallmentsPlanTermsAndConditionsType> visTermsAndConditions
    ) {
        this.providerId = providerId;
        this.visPlanId = visPlanId;
        this.visPlanIdRef = visPlanIdRef;
        this.visAcceptedTAndCVersion = visAcceptedTAndCVersion;
        this.visPlanName = visPlanName;
        this.visNumberOfInstallments = visNumberOfInstallments;
        this.visTotalFees = visTotalFees;
        this.visTotalPlanCost = visTotalPlanCost;
        this.visInstallmentFrequency = visInstallmentFrequency;
        this.visTermsAndConditions = visTermsAndConditions;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getVisPlanId() {
        return visPlanId;
    }

    public String getVisPlanIdRef() {
        return visPlanIdRef;
    }

    public Integer getVisAcceptedTAndCVersion() {
        return visAcceptedTAndCVersion;
    }

    public String getVisPlanName() {
        return visPlanName;
    }

    public Integer getVisNumberOfInstallments() {
        return visNumberOfInstallments;
    }

    public BigDecimal getVisTotalFees() {
        return visTotalFees;
    }

    public BigDecimal getVisTotalPlanCost() {
        return visTotalPlanCost;
    }

    public String getVisInstallmentFrequency() {
        return visInstallmentFrequency;
    }

    public List<VisaInstallmentsPlanTermsAndConditionsType> getVisTermsAndConditions() {
        return visTermsAndConditions;
    }

    @Override
    public String toString() {
        return "VisaInstallmentsInfo{" +
                "providerId=" + providerId +
                ", visPlanId=" + visPlanId +
                ", visPlanIdRef=" + visPlanIdRef +
                ", visAcceptedTAndCVersion=" + visAcceptedTAndCVersion +
                ", visPlanName=" + visPlanName +
                ", visNumberOfInstallments=" + visNumberOfInstallments +
                ", visTotalFees=" + visTotalFees +
                ", visTotalPlanCost=" + visTotalPlanCost +
                ", visInstallmentFrequency=" + visInstallmentFrequency +
                ", visTermsAndConditions=" + visTermsAndConditions +
                '}';
    }
}
