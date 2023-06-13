package com.payfurl.payfurlsdk.api.support;

import com.google.common.base.Preconditions;

public class ErrorLink {
    private static final String ERROR_CODES_URL_TEMPLATE = "https://docs.payfurl.com/errorcodes#%d";

    private ErrorLink() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String buildWith(int errorCode) {
        Preconditions.checkArgument(errorCode >= -1, "Error code can't be less than 0");
        return String.format(ERROR_CODES_URL_TEMPLATE, errorCode);
    }
}
