package com.payfurl.auth;

import com.payfurl.http.client.support.Headers;
import com.payfurl.http.client.support.request.HttpMethod;
import com.payfurl.http.client.support.request.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.function.BiPredicate;

import static org.assertj.core.api.Assertions.assertThat;

class SecretKeyAuthHandlerTest {
    private static final String DUMMY_ACCESS_KEY = "dog-food-123";
    private static final BiPredicate<StringBuilder, StringBuilder> STRING_BUILDER_COMPARE_STRATEGY =
            (sb1, sb2) -> sb1.toString().equals(sb2.toString());

    private SecretKeyAuthHandler secretKeyAuthHandler;

    @BeforeEach
    void setUp() {
        secretKeyAuthHandler = new SecretKeyAuthHandler(DUMMY_ACCESS_KEY);
    }

    private static HttpRequest prepareDummyHttpRequest(Headers headers) {
        return new HttpRequest(
                HttpMethod.POST,
                new StringBuilder(""),
                headers,
                Collections.emptyMap(),
                Collections.emptyList()
        );
    }

    @Test
    @DisplayName("Given SecretKeyAuthHandler When apply is called then verify extra added headers")
    void testApply() {
        HttpRequest httpRequest = secretKeyAuthHandler.apply(prepareDummyHttpRequest(new Headers()));

        Headers headers = new Headers();
        headers.add("Expect", "100-continue");
        headers.add("x-secretkey", DUMMY_ACCESS_KEY);
        HttpRequest expectedHttpRequest = prepareDummyHttpRequest(headers);
        assertThat(httpRequest).usingRecursiveComparison()
                .withEqualsForType(STRING_BUILDER_COMPARE_STRATEGY, StringBuilder.class)
                .isEqualTo(expectedHttpRequest);
    }
}
