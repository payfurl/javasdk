package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Initiator {
    CUSTOMER,
    MERCHANT,
    /** A value this SDK version doesn't know: the API added it after this release. Response only: never send it. */
    @JsonEnumDefaultValue
    UNKNOWN
}
