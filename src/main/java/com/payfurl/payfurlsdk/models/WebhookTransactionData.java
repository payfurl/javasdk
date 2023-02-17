package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class WebhookTransactionData {
    private final String chargeId;
    private final String providerChargeId;
    private final BigDecimal amount;
    private final String providerId;
    private final String reference;
    private final WebhookPaymentData paymentInformation;
    private final String customerId;
    private final String status;
    private final Date dateAdded;
    private final Date successDate;
    private final Date voidDate;
    private final BigDecimal refundedAmount;
    private final BigDecimal estimatedCost;
    private final String estimatedCostCurrency;
    private final String currency;
    private final List<WebhookRefund> refunds;
    private final String threeDSServerTransID;
    private final boolean threeDsVerified;
    private final BigDecimal authorisationAmount;
    private final String initiator;

    @JsonCreator
    public WebhookTransactionData(@JsonProperty("ChargeId") String chargeId,
                                  @JsonProperty("ProviderChargeId") String providerChargeId,
                                  @JsonProperty("Amount") BigDecimal amount,
                                  @JsonProperty("ProviderId") String providerId,
                                  @JsonProperty("Reference") String reference,
                                  @JsonProperty("PaymentInformation") WebhookPaymentData paymentInformation,
                                  @JsonProperty("CustomerId") String customerId,
                                  @JsonProperty("Status") String status,
                                  @JsonProperty("DateAdded") Date dateAdded,
                                  @JsonProperty("SuccessDate") Date successDate,
                                  @JsonProperty("VoidDate") Date voidDate,
                                  @JsonProperty("RefundedAmount") BigDecimal refundedAmount,
                                  @JsonProperty("EstimatedCost") BigDecimal estimatedCost,
                                  @JsonProperty("EstimatedCostCurrency") String estimatedCostCurrency,
                                  @JsonProperty("Currency") String currency,
                                  @JsonProperty("Refunds") List<WebhookRefund> refunds,
                                  @JsonProperty("ThreeDSServerTransId") String threeDsServerTransId,
                                  @JsonProperty("ThreeDsVerified") boolean threeDsVerified,
                                  @JsonProperty("AuthorisationAmount") BigDecimal authorisationAmount,
                                  @JsonProperty("Initiator") String initiator) {
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
        this.threeDSServerTransID = threeDsServerTransId;
        this.threeDsVerified = threeDsVerified;
        this.authorisationAmount = authorisationAmount;
        this.initiator = initiator;
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

    public WebhookPaymentData getPaymentInformation() {
        return paymentInformation;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getStatus() {
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

    public List<WebhookRefund> getRefunds() {
        return refunds;
    }

    public String getThreeDSServerTransID() {
        return threeDSServerTransID;
    }

    public boolean isThreeDsVerified() {
        return threeDsVerified;
    }

    public BigDecimal getAuthorisationAmount() {
        return authorisationAmount;
    }

    public String getInitiator() {
        return initiator;
    }

    @Override
    public String toString() {
        return "WebhookTransactionData{" +
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
                ", threeDSServerTransID='" + threeDSServerTransID + '\'' +
                ", threeDsVerified=" + threeDsVerified +
                ", authorisationAmount=" + authorisationAmount +
                ", initiator='" + initiator + '\'' +
                '}';
    }

    public static class Builder {

        private String chargeId;
        private String providerChargeId;
        private BigDecimal amount;
        private String providerId;
        private String reference;
        private WebhookPaymentData paymentInformation;
        private String customerId;
        private String status;
        private Date dateAdded;
        private Date successDate;
        private Date voidDate;
        private BigDecimal refundedAmount;
        private BigDecimal estimatedCost;
        private String estimatedCostCurrency;
        private String currency;
        private List<WebhookRefund> refunds;
        private String threeDsServerTransId;
        private boolean threeDsVerified;
        private BigDecimal authorisationAmount;
        private String initiator;

        public Builder withChargeId(String chargeId) {
            this.chargeId = chargeId;
            return this;
        }

        public Builder withProviderChargeId(String providerChargeId) {
            this.providerChargeId = providerChargeId;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder withPaymentInformation(WebhookPaymentData paymentInformation) {
            this.paymentInformation = paymentInformation;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withSuccessDate(Date successDate) {
            this.successDate = successDate;
            return this;
        }

        public Builder withVoidDate(Date voidDate) {
            this.voidDate = voidDate;
            return this;
        }

        public Builder withRefundedAmount(BigDecimal refundedAmount) {
            this.refundedAmount = refundedAmount;
            return this;
        }

        public Builder withEstimatedCost(BigDecimal estimatedCost) {
            this.estimatedCost = estimatedCost;
            return this;
        }

        public Builder withEstimatedCostCurrency(String estimatedCostCurrency) {
            this.estimatedCostCurrency = estimatedCostCurrency;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withRefunds(List<WebhookRefund> refunds) {
            this.refunds = refunds;
            return this;
        }

        public Builder withThreeDsServerTransId(String threeDsServerTransId) {
            this.threeDsServerTransId = threeDsServerTransId;
            return this;
        }

        public Builder withThreeDsVerified(boolean threeDsVerified) {
            this.threeDsVerified = threeDsVerified;
            return this;
        }

        public Builder withAuthorisationAmount(BigDecimal authorisationAmount) {
            this.authorisationAmount = authorisationAmount;
            return this;
        }

        public Builder withInitiator(String initiator) {
            this.initiator = initiator;
            return this;
        }

        public WebhookTransactionData build() {
            return new WebhookTransactionData(chargeId, providerChargeId, amount, providerId, reference, paymentInformation, customerId, status, dateAdded, successDate, voidDate, refundedAmount, estimatedCost, estimatedCostCurrency, currency, refunds, threeDsServerTransId, threeDsVerified, authorisationAmount, initiator);
        }
    }
}
