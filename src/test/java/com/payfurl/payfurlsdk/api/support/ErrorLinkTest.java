package com.payfurl.payfurlsdk.api.support;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

class ErrorLinkTest {
    private static final String ERROR_CODES_URL_LINK = "https://docs.payfurl.com/errorcodes#";

    @Test
    @DisplayName("Given positive error code number When constructWith is Called, Then return valid error URL")
    void testConstructWithValidError() {
        int errorCode = 1;

        String errorCodesUrl = ErrorLink.buildWith(errorCode);

        then(errorCodesUrl).isEqualTo(ERROR_CODES_URL_LINK + 1);
    }

    @Test
    @DisplayName("Given negative error code number When constructWith is Called, Then fail with Exception")
    void testConstructWithInvalidErrorCodeNumber() {
        int errorCode = -100;

        Throwable throwable = catchThrowable(() -> ErrorLink.buildWith(errorCode));

        then(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Error code can't be less than 0");
    }
}