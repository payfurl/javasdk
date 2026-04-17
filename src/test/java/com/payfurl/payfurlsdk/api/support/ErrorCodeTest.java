package com.payfurl.payfurlsdk.api.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.models.ApiError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class ErrorCodeTest {
    @Test
    @DisplayName("Given new error code values When fromValue is called Then return the matching enum")
    void testFromValueWithNewCodes() {
        then(ErrorCode.fromValue(104)).isEqualTo(ErrorCode.InvalidSubscriptionId);
        then(ErrorCode.fromValue(114)).isEqualTo(ErrorCode.UnableToCreateNetworkToken);
        then(ErrorCode.fromValue(115)).isEqualTo(ErrorCode.UnableToCreatePaymentLink);
        then(ErrorCode.fromValue(116)).isEqualTo(ErrorCode.InvalidPaymentLinkId);
        then(ErrorCode.fromValue(117)).isEqualTo(ErrorCode.InvalidFallbackProviderId);
    }

    @Test
    @DisplayName("Given api error JSON with a new code When deserialized Then map the code correctly")
    void testApiErrorDeserializationWithNewCode() throws JsonProcessingException {
        ApiError apiError = ApiUtils.deserialize("{\"code\":117}", ApiError.class);

        then(apiError.getCode()).isEqualTo(ErrorCode.InvalidFallbackProviderId);
    }

    @Test
    @DisplayName("Given unknown error code value When fromValue is called Then return UnknownError")
    void testFromValueWithUnknownCode() {
        then(ErrorCode.fromValue(999999)).isEqualTo(ErrorCode.UnknownError);
    }
}
