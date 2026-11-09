package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum SubscriptionStatus {
    Complete,
    Active,
    Cancelled,
    Suspended,
    /**
     * The API's own {@code Unknown} status, or a value this SDK version doesn't know (the API added it after this
     * release). Response only: never send it.
     */
    @JsonEnumDefaultValue
    UNKNOWN
}
