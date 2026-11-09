package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum SubscriptionInterval
{
    Day,
    Week,
    Month,
    Year,
    /** A value this SDK version doesn't know: the API added it after this release. Response only: never send it. */
    @JsonEnumDefaultValue
    UNKNOWN
}