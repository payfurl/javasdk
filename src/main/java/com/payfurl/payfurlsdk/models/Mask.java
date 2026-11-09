package com.payfurl.payfurlsdk.models;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * What the models' {@code toString()} prints for card and secret data, so that a logged request or response holds
 * no full card number, CCV, account number or credential.
 */
final class Mask {
    private Mask() {
    }

    /** All but the last four characters starred: {@code ************1111}. Null stays null. */
    static String lastFour(String value) {
        if (value == null) {
            return null;
        }
        int keep = value.length() > 4 ? 4 : 0;
        StringBuilder masked = new StringBuilder(value.length());
        for (int i = 0; i < value.length() - keep; i++) {
            masked.append('*');
        }
        return masked.append(value, value.length() - keep, value.length()).toString();
    }

    /** {@code ***} when set. Null stays null. */
    static String secret(Object value) {
        return value == null ? null : "***";
    }

    /** The keys, with every value {@code ***}. Null stays null. */
    static Map<String, String> secretValues(Map<String, String> values) {
        if (values == null) {
            return null;
        }
        Map<String, String> masked = new LinkedHashMap<>();
        for (String key : values.keySet()) {
            masked.put(key, "***");
        }
        return masked;
    }
}
