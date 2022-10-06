package com.payfurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class FailureData {
    public final String providerId;
    public final Date dateAttempted;
    public final String errorMessage;
    public final String gatewayCode;
    public final String gatewayMessage;

    @JsonCreator
    public FailureData(@JsonProperty("ProviderId") String providerId,
                       @JsonProperty("DateAttempted") Date dateAttempted,
                       @JsonProperty("ErrorMessage") String errorMessage,
                       @JsonProperty("GatewayCode") String gatewayCode,
                       @JsonProperty("GatewayMessage") String gatewayMessage) {
        this.providerId = providerId;
        this.dateAttempted = dateAttempted;
        this.errorMessage = errorMessage;
        this.gatewayCode = gatewayCode;
        this.gatewayMessage = gatewayMessage;
    }

    public String getProviderId() {
        return providerId;
    }

    public Date getDateAttempted() {
        return dateAttempted;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getGatewayCode() {
        return gatewayCode;
    }

    public String getGatewayMessage() {
        return gatewayMessage;
    }

    @Override
    public String toString() {
        return "FailureData{" +
                "providerId='" + providerId + '\'' +
                ", dateAttempted=" + dateAttempted +
                ", errorMessage='" + errorMessage + '\'' +
                ", gatewayCode='" + gatewayCode + '\'' +
                ", gatewayMessage='" + gatewayMessage + '\'' +
                '}';
    }
}
