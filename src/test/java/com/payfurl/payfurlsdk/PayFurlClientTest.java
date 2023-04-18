package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.api.ChargeApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.api.support.ErrorCode;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.models.ApiError;
import com.payfurl.payfurlsdk.models.NewChargeCardLeastCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

class PayFurlClientTest {
    private static final String SDK_VERSION = "2022.0.1";
    private static final String LOCAL_URL = "https://localhost:5001";
    private static final String SANDBOX_URL = "https://sandbox-api.payfurl.com";
    private static final String PRODUCTION_URL = "https://api.payfurl.com";

    private PayFurlClient dummyProdConfiguredClient;

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
                Arguments.of("Local environment",
                        new PayFurlClient.Builder()
                                .withEnvironment(Environment.LOCAL)
                                .build(),
                        LOCAL_URL),
                Arguments.of("Use Production environment by default if no environment is set",
                        new PayFurlClient.Builder()
                                .build(),
                        PRODUCTION_URL)
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
