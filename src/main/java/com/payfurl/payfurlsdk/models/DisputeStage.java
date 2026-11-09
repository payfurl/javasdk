package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

/**
 * A dispute's stage, as the API names it (common.Models.DisputeStage).
 */
public enum DisputeStage {
    INQUIRY,
    CHARGEBACK,
    COMPLIANCE,
    BLOCK,
    RESOLUTION,
    /** The API's own fallback, and what a stage this SDK doesn't know reads as. */
    @JsonEnumDefaultValue
    UNKNOWN
}
