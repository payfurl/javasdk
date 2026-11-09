package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A dispute (chargeback): {@code GET /dispute/{disputeId}}, and the items of {@link DisputeList}
 * (the API's common.Models.DisputeResponse).
 */
public class DisputeData {
    @JsonProperty("disputeId")
    private String disputeId;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("providerId")
    private String providerId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("gatewayDisputeId")
    private String gatewayDisputeId;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("fee")
    private BigDecimal fee;
    @JsonProperty("status")
    private DisputeStatus status;
    @JsonProperty("stage")
    private DisputeStage stage;
    @JsonProperty("reasonCategory")
    private String reasonCategory;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("networkReasonCode")
    private String networkReasonCode;
    @JsonProperty("cardBrand")
    private String cardBrand;
    @JsonProperty("disputeDate")
    private Date disputeDate;
    @JsonProperty("evidenceDueBy")
    private Date evidenceDueBy;
    @JsonProperty("submissionCount")
    private Integer submissionCount;
    @JsonProperty("isChargeRefundable")
    private Boolean isChargeRefundable;
    @JsonProperty("dateAdded")
    private Date dateAdded;
    @JsonProperty("dateUpdated")
    private Date dateUpdated;

    public String getDisputeId() {
        return disputeId;
    }

    /** The disputed charge's ID. */
    public String getTransactionId() {
        return transactionId;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getType() {
        return type;
    }

    public String getGatewayDisputeId() {
        return gatewayDisputeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public DisputeStatus getStatus() {
        return status;
    }

    public DisputeStage getStage() {
        return stage;
    }

    /** E.g. {@code FRAUDULENT} or {@code PRODUCT_NOT_RECEIVED}. */
    public String getReasonCategory() {
        return reasonCategory;
    }

    public String getReason() {
        return reason;
    }

    public String getNetworkReasonCode() {
        return networkReasonCode;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public Date getDisputeDate() {
        return disputeDate;
    }

    public Date getEvidenceDueBy() {
        return evidenceDueBy;
    }

    public Integer getSubmissionCount() {
        return submissionCount;
    }

    public Boolean getIsChargeRefundable() {
        return isChargeRefundable;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public String toString() {
        return "DisputeData{" +
                "disputeId='" + disputeId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", providerId='" + providerId + '\'' +
                ", type='" + type + '\'' +
                ", gatewayDisputeId='" + gatewayDisputeId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", fee=" + fee +
                ", status=" + status +
                ", stage=" + stage +
                ", reasonCategory='" + reasonCategory + '\'' +
                ", reason='" + reason + '\'' +
                ", networkReasonCode='" + networkReasonCode + '\'' +
                ", cardBrand='" + cardBrand + '\'' +
                ", disputeDate=" + disputeDate +
                ", evidenceDueBy=" + evidenceDueBy +
                ", submissionCount=" + submissionCount +
                ", isChargeRefundable=" + isChargeRefundable +
                ", dateAdded=" + dateAdded +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
