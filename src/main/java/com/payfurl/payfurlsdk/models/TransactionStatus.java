package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum TransactionStatus
{
    PENDING,
    FAILED,
    SUCCESS,
    PARTIAL_REFUND,
    REFUND,
    PENDING_CHECKOUT,
    AUTHORISE,
    AUTHORISE_CANCELLED,
    /** A value this SDK version doesn't know: the API added it after this release. */
    @JsonEnumDefaultValue
    UNKNOWN
}
