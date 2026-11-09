package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum WebhookType {
    Transaction,
    Batch,
    Subscription,
    Customer,
    PaymentMethod,
    Dispute,
    /** A value this SDK version doesn't know: the API added it after this release. Response only: never send it. */
    @JsonEnumDefaultValue
    UNKNOWN
}
