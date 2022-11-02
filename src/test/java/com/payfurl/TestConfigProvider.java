package com.payfurl;

import com.payfurl.http.client.config.Environment;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

public final class TestConfigProvider {
    private static final String LOCAL_ACCESS_TEST_TOKEN = "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    private static final String PAYFURL_ACCESS_TEST_TOKEN_KEY = "PAYFURL_ACCESS_TEST_TOKEN";
    private static final String PAYFURL_ENVIRONMENT_KEY = "PAYFURL_ENVIRONMENT";

    private TestConfigProvider() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String getKeyWithFallback() {
        return StringUtils.defaultIfEmpty(System.getProperty(PAYFURL_ACCESS_TEST_TOKEN_KEY), LOCAL_ACCESS_TEST_TOKEN);
    }

    public static Environment getEnvironmentWithFallback() {
        return EnumUtils.getEnumIgnoreCase(Environment.class, System.getProperty(PAYFURL_ENVIRONMENT_KEY), Environment.LOCAL);
    }
}
