package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.http.client.config.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

class PayFurlClientTest {
    private static final String SDK_VERSION = "2022.0.1";
    private static final String LOCAL_URL = "https://localhost:5001";
    private static final String SANDBOX_URL = "https://sandbox-api.payfurl.com";
    private static final String PRODUCTION_URL = "https://api.payfurl.com";
    private static final String SECRET_KEY = "secret_key12345";

    private PayFurlClient dummyProdConfiguredClient;

    private static String getSecretKeyWithRegion(Region region) {
        return String.format("%s-%s", SECRET_KEY, region.label);
    }

    private static String getSecretKeyWithRegion(String region) {
        return String.format("%s-%s", SECRET_KEY, region);
    }

    private static Stream<Arguments> provideDataForTestUriBasedOnEnvironmentMapping() {
        return Stream.of(
                Arguments.of("Production environment",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.PRODUCTION)
                                .build(),
                        PRODUCTION_URL),
                Arguments.of("Sandbox environment",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.SANDBOX)
                                .build(),
                        SANDBOX_URL),
                Arguments.of("Sandbox environment with secret key passed",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.SANDBOX)
                                .withSecretKey(SECRET_KEY)
                                .build(),
                        SANDBOX_URL),
                Arguments.of("Local environment",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.LOCAL)
                                .build(),
                        LOCAL_URL),
                Arguments.of("Use Production environment by default if no environment is set",
                        new PayFurlClient.Builder()
                                .build(),
                        PRODUCTION_URL),
                Arguments.of("AU - Development environment",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.DEVELOPMENT)
                                .withSecretKey(getSecretKeyWithRegion(Region.AU))
                                .build(),
                        "https://develop-api-au.payfurl.com"),
                Arguments.of("AU - Sandbox environment",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.SANDBOX)
                                .withSecretKey(getSecretKeyWithRegion(Region.AU))
                                .build(),
                        "https://sandbox-api-au.payfurl.com"),
                Arguments.of("AU - Production environment",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.PRODUCTION)
                                .withSecretKey(getSecretKeyWithRegion(Region.AU))
                                .build(),
                        "https://api-au.payfurl.com"),
                Arguments.of("AU - Production environment with suffix region part in key in arbitrary format",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.PRODUCTION)
                                .withSecretKey(getSecretKeyWithRegion("aU"))
                                .build(),
                        "https://api-au.payfurl.com"),
                Arguments.of("JP - Non-existing Sandbox environment with fallback to global sandbox url",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.SANDBOX)
                                .withSecretKey(getSecretKeyWithRegion(Region.JP))
                                .build(),
                        "https://sandbox-api.payfurl.com"),
                Arguments.of("JP - Non-existing Production environment with fallback to global production url",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.PRODUCTION)
                                .withSecretKey(getSecretKeyWithRegion(Region.JP))
                                .build(),
                        "https://api.payfurl.com"),
                Arguments.of("US - Non-existing Production environment and incorrectly formatted key with fallback to global production url",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.PRODUCTION)
                                .withSecretKey(getSecretKeyWithRegion(Region.US) + "-123")
                                .build(),
                        "https://api.payfurl.com")
        );
    }

    @BeforeEach
    void setUp() {
        dummyProdConfiguredClient = new PayFurlClient.Builder()
                .build();
    }

    @DisplayName("Given newly created PayFurlClient When getBaseUri is Called Then return correct base URI")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("provideDataForTestUriBasedOnEnvironmentMapping")
    void testUriBasedOnEnvironmentMapping(String testName,
                                          PayFurlClient payFurlClient,
                                          String expectedBaseUri) {
        then(testName).isNotEmpty();
        then(payFurlClient.getBaseUri()).isEqualTo(expectedBaseUri);
    }

    @Test
    @DisplayName("Given PayFurlClient When getSdkVersion is called Then return sdk version")
    void testGetSdkVersion() {
        then(dummyProdConfiguredClient.getSdkVersion()).isEqualTo(SDK_VERSION);
    }

    @Test
    @DisplayName("Given PayFurlClient When getSecretKeyAuthHandler is called Then return one secret key auth handler")
    void testGetSecretKeyAuthHandler() {
        then(dummyProdConfiguredClient.getSecretKeyAuthHandler().getSecretKey()).isEmpty();
    }
}
