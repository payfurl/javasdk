package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.payfurl.payfurlsdk.api.support.ErrorCode;

import java.util.Map;

public class ApiError {

    private final String message;
    private final Map<String, String> details;
    private final String resource;
    private final String gatewayCode;
    private final String gatewayMessage;
    private final ErrorCode code;
    private final Boolean isRetryable;
    private final String type;

    @JsonCreator
    public ApiError(@JsonProperty("message") String message,
                    @JsonProperty("details") Map<String, String> details,
                    @JsonProperty("resource") String resource,
                    @JsonProperty("gatewayCode") String gatewayCode,
                    @JsonProperty("gatewayMessage") String gatewayMessage,
                    @JsonProperty("code") ErrorCode code,
                    @JsonProperty("isRetryable") Boolean isRetryable,
                    @JsonProperty("type") String type) {
        this.message = message;
        this.details = details;
        this.resource = resource;
        this.gatewayCode = gatewayCode;
        this.gatewayMessage = gatewayMessage;
        this.code = code;
        this.isRetryable = isRetryable;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public String getResource() {
        return resource;
    }

    public String getGatewayCode() {
        return gatewayCode;
    }

    public String getGatewayMessage() {
        return gatewayMessage;
    }

    public ErrorCode getCode() {
        return code;
    }

    public Boolean isRetryable() {
        return isRetryable;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "message='" + message + '\'' +
                ", details=" + details +
                ", resource='" + resource + '\'' +
                ", gatewayCode='" + gatewayCode + '\'' +
                ", gatewayMessage='" + gatewayMessage + '\'' +
                ", code='" + code + '\'' +
                ", isRetryable='" + isRetryable + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public static class Builder {
        private String message;
        private Map<String, String> details;
        private String resource;
        private String gatewayCode;
        private String gatewayMessage;
        private ErrorCode code;
        private Boolean isRetryable;
        private String type;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withDetails(Map<String, String> details) {
            this.details = details;
            return this;
        }

        public Builder withResource(String resource) {
            this.resource = resource;
            return this;
        }

        public Builder withGatewayCode(String gatewayCode) {
            this.gatewayCode = gatewayCode;
            return this;
        }

        public Builder withGatewayMessage(String gatewayMessage) {
            this.gatewayMessage = gatewayMessage;
            return this;
        }

        public Builder withCode(ErrorCode code) {
            this.code = code;
            return this;
        }

        public Builder withIsRetryable(Boolean isRetryable) {
            this.isRetryable = isRetryable;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public ApiError build() {
            return new ApiError(message, details, resource, gatewayCode, gatewayMessage, code, isRetryable, type);
        }
    }
}
