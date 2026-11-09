package com.payfurl.payfurlsdk.api.support;

public class ErrorLink {
    private static final String ERROR_CODES_URL_TEMPLATE = "https://docs.payfurl.com/errorcodes#%d";

    private ErrorLink() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String buildWith(int errorCode) {
        if (errorCode < -1) {
            throw new IllegalArgumentException("Error code can't be less than 0");
        }
        return String.format(ERROR_CODES_URL_TEMPLATE, errorCode);
    }
}
