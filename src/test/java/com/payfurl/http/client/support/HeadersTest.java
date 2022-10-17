package com.payfurl.http.client.support;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HeadersTest {
    private static final String KEY_1 = "key1";
    private static final String VALUE_1 = "value1";
    private static final String KEY_2 = "key2";
    private static final String VALUE_2 = "value2";
    private static final String KEY_3 = "key3";
    private static final String VALUE_3 = "value3";

    private Headers headers;

    private static Stream<Arguments> provideDataForTestValue() {
        return Stream.of(
                Arguments.of("Value is present by key",
                        KEY_1,
                        VALUE_1
                ),
                Arguments.of("Value is not present by key",
                        VALUE_1,
                        null
                )
        );
    }

    private static Headers createHeadersFrom(Map<String, String> data) {
        Headers headers = new Headers();
        data.forEach(headers::add);
        return headers;
    }

    @BeforeEach
    void setUp() {
        headers = createHeadersFrom(
                ImmutableMap.of(
                        KEY_1, VALUE_1,
                        KEY_2, VALUE_2
                )
        );
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("provideDataForTestValue")
    @DisplayName("Given key for headers When value is called Then return value")
    void testValue(String testName, String key, String expectedValue) {
        assertThat(testName).isNotEmpty();

        String value = headers.value(key);
        assertThat(value).isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("Given prefilled headers When add for new entry is called Then headers should contain new value")
    void testAddWithOneValue() {
        headers.add(KEY_3, VALUE_3);
        String value = headers.value(KEY_3);

        assertThat(value).isEqualTo(VALUE_3);
    }

    @Test
    @DisplayName("Given prefilled headers When add for new entry with existing key is called Then headers should contain new value")
    void testAddWithMultipleValueList() {
        headers.add(KEY_2, VALUE_3);
        String value = headers.value(KEY_2);
        Map<String, List<String>> multimap = headers.asMultimap();

        assertThat(value).isEqualTo(VALUE_2);
        assertThat(multimap).usingRecursiveComparison()
                .isEqualTo(ImmutableMap.of(
                        KEY_1, ImmutableList.of(VALUE_1),
                        KEY_2, ImmutableList.of(VALUE_2, VALUE_3)));
    }

    @Test
    @DisplayName("Given prefilled headers When add for new entry with existing key and list is called Then headers should contain new value")
    void testAddWithList() {
        headers.add(KEY_2, ImmutableList.of(VALUE_3, VALUE_1));
        String value = headers.value(KEY_2);
        Map<String, List<String>> multimap = headers.asMultimap();

        assertThat(value).isEqualTo(VALUE_2);
        assertThat(multimap).usingRecursiveComparison()
                .isEqualTo(ImmutableMap.of(
                        KEY_1, ImmutableList.of(VALUE_1),
                        KEY_2, ImmutableList.of(VALUE_2, VALUE_3, VALUE_1)));
    }

    @Test
    @DisplayName("Given existing headers When addAll is called with new headers Then headers should contain all values")
    void testAddAll() {
        Headers newHeaders = new Headers();
        List<String> newValues = ImmutableList.of(VALUE_1, VALUE_2, VALUE_3);
        newHeaders.add(KEY_3, newValues);
        headers.addAll(newHeaders);

        Map<String, List<String>> multimap = headers.asMultimap();

        assertThat(multimap).usingRecursiveComparison()
                .isEqualTo(ImmutableMap.of(
                        KEY_1, ImmutableList.of(VALUE_1),
                        KEY_2, ImmutableList.of(VALUE_2),
                        KEY_3, newValues));
    }
}
