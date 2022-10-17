package com.payfurl.http.client.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payfurl.models.CardData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ApiUtilsTest {
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

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("provideDataForTestSerialize")
    @DisplayName("Given sample card data When serialize is called Then return serialized content")
    void testSerialize(String testName, CardData cardData, String expectedSerializedString) throws JsonProcessingException {
        assertThat(testName).isNotEmpty();

        String serializedString = ApiUtils.serialize(cardData);

        assertThat(serializedString).isEqualTo(expectedSerializedString);
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("provideDataForTestDeserialize")
    @DisplayName("Given sample card data When deserialize is called Then return deserialized content")
    void deserialize(String testName, String jsonString, Class<CardData> clazz, CardData expectedData) throws JsonProcessingException {
        assertThat(testName).isNotEmpty();

        CardData deserializedEntity = ApiUtils.deserialize(jsonString, clazz);

        assertThat(deserializedEntity).usingRecursiveComparison()
                .isEqualTo(expectedData);
    }

    @DisplayName("Given url When CleanUrl is Called Then return correct URL")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("provideDataForTestCleanUrl")
    void testCleanUrl(String testName, StringBuilder urlBuilder, String expectedCleanedUrl) {
        assertThat(testName).isNotEmpty();

        String cleanedUrl = ApiUtils.cleanUrl(urlBuilder);

        assertThat(cleanedUrl).isEqualTo(expectedCleanedUrl);
    }

    @DisplayName("Given url without protocol schema When CleanUrl is Called Then throw exception")
    @Test
    void testCleanUrlWithoutSchema() {
        Throwable throwable = catchThrowable(() -> ApiUtils.cleanUrl(new StringBuilder(PAYFURL_URL_PART)));

        assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid Url format.");
    }
}