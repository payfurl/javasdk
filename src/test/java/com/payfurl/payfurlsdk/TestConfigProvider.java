package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.http.client.config.Environment;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

public final class TestConfigProvider {
    private static final String LOCAL_ACCESS_SECRET_KEY = "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    private static final String LOCAL_ACCESS_PUBLIC_KEY = "900b638601a4405b9f7cf71bc436298b";
    private static final String PAYFURL_ACCESS_TEST_SECRET_KEY = "LOCAL_ACCESS_SECRET_KEY";
    private static final String PAYFURL_ACCESS_TEST_PUBLIC_KEY = "LOCAL_ACCESS_PUBLIC_KEY";
    private static final String PAYFURL_ENVIRONMENT_KEY = "PAYFURL_ENVIRONMENT";

    private TestConfigProvider() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String getSecretKeyWithFallback() {
        return StringUtils.defaultIfEmpty(System.getProperty(PAYFURL_ACCESS_TEST_SECRET_KEY), LOCAL_ACCESS_SECRET_KEY);
    }

    public static String getPublicKeyWithFallback() {
        return StringUtils.defaultIfEmpty(System.getProperty(PAYFURL_ACCESS_TEST_PUBLIC_KEY), LOCAL_ACCESS_PUBLIC_KEY);
    }

    public static Environment getEnvironmentWithFallback() {
        return EnumUtils.getEnumIgnoreCase(Environment.class, System.getProperty(PAYFURL_ENVIRONMENT_KEY), Environment.LOCAL);
    }
}
