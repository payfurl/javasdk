package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

/**
 * A dispute's status, as the API names it (common.Models.DisputeStatus).
 */
public enum DisputeStatus {
    WARNING_NEEDS_RESPONSE,
    WARNING_UNDER_REVIEW,
    WARNING_CLOSED,
    NEEDS_RESPONSE,
    UNDER_REVIEW,
    WON,
    LOST,
    PREVENTED,
    /** The API's own fallback, and what a status this SDK doesn't know reads as. */
    @JsonEnumDefaultValue
    UNKNOWN
}
