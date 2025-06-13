package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ChargeData {
    public final String chargeId;
    public final String providerChargeId;
    public final BigDecimal amount;
    public final String providerId;
    public final String reference;
    public final PaymentData paymentInformation;
    public final String customerId;
    public final TransactionStatus status;
    public final Date dateAdded;
    public final Date successDate;
    public final Date voidDate;
    public final BigDecimal refundedAmount;
    public final BigDecimal estimatedCost;
    public final String estimatedCostCurrency;
    public final String currency;
    public final List<Refund> refunds;
    public final List<FailureData> failedAttempts;
    public final CustomerDataSummary customer;
    public final ProviderSummary provider;
    public final String threeDsServerTransId;
    public final boolean threeDsVerified;
    public final BigDecimal authorisationAmount;
    private final Initiator initiator;
    private final VisaInstallmentsInfo visaInstallments;
    private final String descriptor;
    private final String threeDsRedirectUrl;
    private final String paymentTokenId;
    private final String subscriptionId;
    private final Map<String, String> metadata;

    @JsonCreator
    public ChargeData(@JsonProperty("ChargeId") String chargeId,
                      @JsonProperty("ProviderChargeId") String providerChargeId,
                      @JsonProperty("Amount") BigDecimal amount,
                      @JsonProperty("ProviderId") String providerId,
                      @JsonProperty("Reference") String reference,
                      @JsonProperty("PaymentInformation") PaymentData paymentInformation,
                      @JsonProperty("CustomerId") String customerId,
                      @JsonProperty("Status") TransactionStatus status,
                      @JsonProperty("DateAdded") Date dateAdded,
                      @JsonProperty("SuccessDate") Date successDate,
                      @JsonProperty("VoidDate") Date voidDate,
                      @JsonProperty("RefundedAmount") BigDecimal refundedAmount,
                      @JsonProperty("EstimatedCost") BigDecimal estimatedCost,
                      @JsonProperty("EstimatedCostCurrency") String estimatedCostCurrency,
                      @JsonProperty("Currency") String currency,
                      @JsonProperty("Refunds") List<Refund> refunds,
                      @JsonProperty("FailedAttempts") List<FailureData> failedAttempts,
                      @JsonProperty("Customer") CustomerDataSummary customer,
                      @JsonProperty("Provider") ProviderSummary provider,
                      @JsonProperty("ThreeDsServerTransId") String threeDsServerTransId,
                      @JsonProperty("ThreeDsVerified") boolean threeDsVerified,
                      @JsonProperty("AuthorisationAmount") BigDecimal authorisationAmount,
                      @JsonProperty("Initiator") Initiator initiator,
                      @JsonProperty("VisaInstallments") VisaInstallmentsInfo visaInstallments,
                      @JsonProperty("Descriptor") String descriptor,
                      @JsonProperty("ThreeDsRedirectUrl") String threeDsRedirectUrl,
                      @JsonProperty("PaymentTokenId") String paymentTokenId,
                      @JsonProperty("SubscriptionId") String subscriptionId,
                      @JsonProperty("Metadata") Map<String, String> metadata) {
        this.chargeId = chargeId;
        this.providerChargeId = providerChargeId;
        this.amount = amount;
        this.providerId = providerId;
        this.reference = reference;
        this.paymentInformation = paymentInformation;
        this.customerId = customerId;
        this.status = status;
        this.dateAdded = dateAdded;
        this.successDate = successDate;
        this.voidDate = voidDate;
        this.refundedAmount = refundedAmount;
        this.estimatedCost = estimatedCost;
        this.estimatedCostCurrency = estimatedCostCurrency;
        this.currency = currency;
        this.refunds = refunds;
        this.failedAttempts = failedAttempts;
        this.customer = customer;
        this.provider = provider;
        this.threeDsServerTransId = threeDsServerTransId;
        this.threeDsVerified = threeDsVerified;
        this.authorisationAmount = authorisationAmount;
        this.initiator = initiator;
        this.visaInstallments = visaInstallments;
        this.descriptor = descriptor;
        this.threeDsRedirectUrl = threeDsRedirectUrl;
        this.paymentTokenId = paymentTokenId;
        this.subscriptionId = subscriptionId;
        this.metadata = metadata;
    }

    public String getChargeId() {
        return chargeId;
    }

    public String getProviderChargeId() {
        return providerChargeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getReference() {
        return reference;
    }

    public PaymentData getPaymentInformation() {
        return paymentInformation;
    }

    public String getCustomerId() {
        return customerId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Date getSuccessDate() {
        return successDate;
    }

    public Date getVoidDate() {
        return voidDate;
    }

    public BigDecimal getRefundedAmount() {
        return refundedAmount;
    }

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public String getEstimatedCostCurrency() {
        return estimatedCostCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public List<Refund> getRefunds() {
        return refunds;
    }

    public List<FailureData> getFailedAttempts() {
        return failedAttempts;
    }

    public CustomerDataSummary getCustomer() {
        return customer;
    }

    public ProviderSummary getProvider() {
        return provider;
    }

    public String getThreeDsServerTransId() {
        return threeDsServerTransId;
    }

    public boolean isThreeDsVerified() {
        return threeDsVerified;
    }

    public BigDecimal getAuthorisationAmount() {
        return authorisationAmount;
    }

    public Initiator getInitiator() {
        return initiator;
    }

    public String getDescriptor() {
        return descriptor;
        }

    public VisaInstallmentsInfo getVisaInstallments() {
        return visaInstallments;
    }

    public String getThreeDsRedirectUrl() {
        return threeDsRedirectUrl;
    }

    public String getPaymentTokenId() {
        return paymentTokenId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "ChargeData{" +
                "chargeId='" + chargeId + '\'' +
                ", providerChargeId='" + providerChargeId + '\'' +
                ", amount=" + amount +
                ", providerId='" + providerId + '\'' +
                ", reference='" + reference + '\'' +
                ", paymentInformation=" + paymentInformation +
                ", customerId='" + customerId + '\'' +
                ", status='" + status + '\'' +
                ", dateAdded=" + dateAdded +
                ", successDate=" + successDate +
                ", voidDate=" + voidDate +
                ", refundedAmount=" + refundedAmount +
                ", estimatedCost=" + estimatedCost +
                ", estimatedCostCurrency='" + estimatedCostCurrency + '\'' +
                ", currency='" + currency + '\'' +
                ", refunds=" + refunds +
                ", failedAttempts=" + failedAttempts +
                ", customer=" + customer +
                ", provider=" + provider +
                ", threeDsServerTransId='" + threeDsServerTransId + '\'' +
                ", threeDsVerified=" + threeDsVerified +
                ", authorisationAmount=" + authorisationAmount +
                ", initiator=" + initiator +
                ", visaInstallments=" + visaInstallments +
                ", descriptor=" + descriptor +
                ", threeDsRedirectUrl=" + threeDsRedirectUrl +
                ", paymentTokenId=" + paymentTokenId +
                ", subscriptionId=" + subscriptionId +
                ", metadata=" + metadata +
                '}';
    }
}
