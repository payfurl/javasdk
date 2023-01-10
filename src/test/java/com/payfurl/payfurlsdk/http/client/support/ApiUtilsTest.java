package com.payfurl.payfurlsdk.http.client.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;
import com.payfurl.payfurlsdk.models.CardData;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

class ApiUtilsTest {
    private static final String SAMPLE_URL_PART = "url";
    private static final String SAMPLE_SERIALIZED_STRING =
            "{\"cardNumber\":\"11111\",\"expiryDate\":\"2022-12-22\",\"cardholder\":\"cardHolder\",\"type\":\"type\"}";
    private static final String PAYFURL_URL_PART = "payfurl.com";
    private static final String PAYFURL_COM_URL = String.format("https://%s.com", PAYFURL_URL_PART);
    private static final String PAYFURL_COM_URL_WITH_FORWARD_SLASH = String.format("https://%s.com/", PAYFURL_URL_PART);

    private static Stream<Arguments> provideDataForTestCleanUrl() {
        return Stream.of(
                Arguments.of("Valid URL",
                        new StringBuilder(PAYFURL_COM_URL_WITH_FORWARD_SLASH),
                        PAYFURL_COM_URL_WITH_FORWARD_SLASH),
                Arguments.of("Valid URL without forward slash",
                        new StringBuilder(PAYFURL_COM_URL),
                        PAYFURL_COM_URL),
                Arguments.of("URL with 2 extra slashes",
                        new StringBuilder(PAYFURL_COM_URL_WITH_FORWARD_SLASH + "/"),
                        PAYFURL_COM_URL_WITH_FORWARD_SLASH)
        );
    }

    private static Stream<Arguments> provideDataForTestSerialize() {
        return Stream.of(
                Arguments.of("Valid serialized entity",
                        new CardData("11111", "2022-12-22", "cardHolder", "type"),
                        SAMPLE_SERIALIZED_STRING),
                Arguments.of("Null data",
                        null,
                        null)
        );
    }

    private static Stream<Arguments> provideDataForTestDeserialize() {
        return Stream.of(
                Arguments.of("Valid deserialized entity",
                        SAMPLE_SERIALIZED_STRING,
                        CardData.class,
                        new CardData("11111", "2022-12-22", "cardHolder", "type")),
                Arguments.of("Null data",
                        null,
                        CardData.class,
                        null)
        );
    }

    private static Stream<Arguments> provideDataForTestAppendUrlWithQueryParameters() {
        return Stream.of(
                Arguments.of("Query params with one key-value is passed",
                        SAMPLE_URL_PART,
                        ImmutableMap.of("k1", "v1"),
                        String.format("%s?%s=%s", SAMPLE_URL_PART, "k1", "v1")
                ),

                Arguments.of("No query params are passed",
                        SAMPLE_URL_PART,
                        ImmutableMap.of(),
                        SAMPLE_URL_PART
                ),

                Arguments.of("No query params and no URL part are passed",
                        StringUtils.EMPTY,
                        ImmutableMap.of(),
                        StringUtils.EMPTY
                ),

                Arguments.of("Query params with two different key-value are passed",
                        SAMPLE_URL_PART,
                        ImmutableMap.of(
                                "k1", "v1",
                                "k2", 25
                        ),
                        String.format("%s?%s=%s&%s=%d", SAMPLE_URL_PART, "k1", "v1", "k2", 25)
                )
        );
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("provideDataForTestSerialize")
    @DisplayName("Given sample card data When serialize is called Then return serialized content")
    void testSerialize(String testName, CardData cardData, String expectedSerializedString) throws JsonProcessingException {
        then(testName).isNotEmpty();

        String serializedString = ApiUtils.serialize(cardData);

        then(serializedString).isEqualTo(expectedSerializedString);
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("provideDataForTestDeserialize")
    @DisplayName("Given sample card data When deserialize is called Then return deserialized content")
    void deserialize(String testName, String jsonString, Class<CardData> clazz, CardData expectedData) throws JsonProcessingException {
        then(testName).isNotEmpty();

        CardData deserializedEntity = ApiUtils.deserialize(jsonString, clazz);

        then(deserializedEntity).usingRecursiveComparison()
                .isEqualTo(expectedData);
    }

    @DisplayName("Given url When CleanUrl is Called Then return correct URL")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("provideDataForTestCleanUrl")
    void testCleanUrl(String testName, StringBuilder urlBuilder, String expectedCleanedUrl) {
        then(testName).isNotEmpty();

        String cleanedUrl = ApiUtils.cleanUrl(urlBuilder);

        then(cleanedUrl).isEqualTo(expectedCleanedUrl);
    }

    @DisplayName("Given url without protocol schema When CleanUrl is Called Then throw exception")
    @Test
    void testCleanUrlWithoutSchema() {
        Throwable throwable = catchThrowable(() -> ApiUtils.cleanUrl(new StringBuilder(PAYFURL_URL_PART)));

        then(throwable).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid Url format.");
    }

    @DisplayName("Given query parameters When appendUrlWithQueryParameters is Called Then return constructed URL")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("provideDataForTestAppendUrlWithQueryParameters")
    void testAppendUrlWithQueryParameters(String testName,
                                          String urlPart,
                                          Map<String, Object> queryParams,
                                          String expectedFullUrl) {
        then(testName).isNotEmpty();

        StringBuilder queryBuilder = new StringBuilder(urlPart);

        ApiUtils.appendUrlWithQueryParameters(queryBuilder, queryParams);

        then(queryBuilder.toString()).isEqualTo(expectedFullUrl);
    }
}