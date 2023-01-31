package com.payfurl.payfurlsdk.api.support;

import com.payfurl.payfurlsdk.models.ApiError;

import java.io.IOException;
import java.util.Map;

public class ApiException extends RuntimeException {
    private static final ErrorCode UNKNOWN_ERROR = ErrorCode.UnknownError;
    private final String message;
    private final Map<String, String> details;
    private final String resource;
    private final String gatewayCode;
    private final String gatewayMessage;
    private final ErrorCode code;
    private final Boolean isRetryable;
    private final String type;
    private final int httpCode;


    public ApiException(ApiError apiError) {
        this.message = apiError.getMessage();
        this.details = apiError.getDetails();
        this.resource = apiError.getResource();
        this.gatewayCode = apiError.getGatewayCode();
        this.gatewayMessage = apiError.getGatewayMessage();
        this.code = apiError.getCode();
        this.isRetryable = apiError.isRetryable();
        this.type = apiError.getType();
        this.httpCode = apiError.getHttpCode();
    }

    public ApiException(IOException ioException) {
        this.message = ioException.getMessage();
        this.details = null;
        this.resource = null;
        this.gatewayCode = null;
        this.gatewayMessage = null;
        this.code = UNKNOWN_ERROR;
        this.isRetryable = false;
        this.type = String.format("https://docs.payfurl.com/errorcodes.html#%d", UNKNOWN_ERROR);
        this.httpCode = 500;
    }

    @Override
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
    public int getHttpCode() {
        return httpCode;
    }
}
