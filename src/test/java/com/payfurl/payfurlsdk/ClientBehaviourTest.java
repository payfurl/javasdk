package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.api.support.ErrorCode;
import com.payfurl.payfurlsdk.models.ChargeSearch;
import com.payfurl.payfurlsdk.models.NewChargeToken;
import com.payfurl.payfurlsdk.models.NewRefund;
import com.payfurl.payfurlsdk.support.MockApiTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import okhttp3.mockwebserver.SocketPolicy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

/** What every call does: headers, query encoding, retries, error mapping and timeouts. */
class ClientBehaviourTest extends MockApiTest {
    private static final String CHARGE = "{\"chargeId\":\"ch_1\",\"amount\":10.5,\"currency\":\"AUD\",\"status\":\"SUCCESS\"}";

    @Test
    void sendsTheSecretKeyAndAUserAgentWithTheBuildVersion() throws Exception {
        enqueueJson(CHARGE);

        client.getChargeApi().single("ch_1");

        RecordedRequest request = takeRequest();
        assertThat(request.getMethod()).isEqualTo("GET");
        assertThat(request.getPath()).isEqualTo("/charge/ch_1");
        assertThat(request.getHeader("x-secretkey")).isEqualTo(SECRET_KEY);
        assertThat(request.getHeader("User-Agent")).startsWith("PayFURL-Java-SDK-v." + client.getSdkVersion() + " JRE/");
        assertThat(request.getHeader("sdk-version")).isNull();
    }

    @Test
    void searchDatesAreIso8601InUtcAndDecimalsHaveNoExponent() throws Exception {
        enqueueJson("{\"charges\":[],\"count\":0}");

        client.getChargeApi().search(new ChargeSearch.Builder()
                .withAddedAfter(new Date(1762500000000L))
                .withAmountGreaterThan(new BigDecimal("1E+3"))
                .withReference("a&b #1")
                .build());

        RecordedRequest request = takeRequest();
        assertThat(query(request, "AddedAfter")).isEqualTo("2025-11-07T07:20:00Z");
        assertThat(query(request, "AmountGreaterThan")).isEqualTo("1000");
        assertThat(query(request, "Reference")).isEqualTo("a&b #1");
        assertThat(request.getRequestUrl().queryParameterNames()).doesNotContain("AddedBefore", "CustomerId");
    }

    @Test
    void aRefundSendsItsAmountAndComment() throws Exception {
        enqueueJson(CHARGE);

        client.getChargeApi().refund(new NewRefund.Builder()
                .withChargeId("ch_1")
                .withRefundAmount(new BigDecimal("10.50"))
                .withComment("damaged & returned")
                .build());

        RecordedRequest request = takeRequest();
        assertThat(request.getMethod()).isEqualTo("DELETE");
        assertThat(request.getRequestUrl().encodedPath()).isEqualTo("/charge/ch_1");
        assertThat(query(request, "amount")).isEqualTo("10.50");
        assertThat(query(request, "comment")).isEqualTo("damaged & returned");
    }

    @Test
    void aGetIsRetriedAfterAServerErrorWithoutABody() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(503).setBody("<html>Service Unavailable</html>"));
        enqueueJson(CHARGE);

        assertThat(client.getChargeApi().single("ch_1").getChargeId()).isEqualTo("ch_1");
        assertThat(server.getRequestCount()).isEqualTo(2);
    }

    @Test
    void aGetIsRetriedAfterAConnectionError() throws Exception {
        server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
        enqueueJson(CHARGE);

        assertThat(client.getChargeApi().single("ch_1").getChargeId()).isEqualTo("ch_1");
    }

    @Test
    void aGetIsRetriedOnlyWhenTheApiSaysTheErrorIsRetryable() {
        enqueueJson(400, "{\"message\":\"Invalid\",\"code\":2,\"isRetryable\":false}");

        ApiException error = catchThrowableOfType(ApiException.class, () -> client.getChargeApi().single("ch_1"));

        assertThat(error.isRetryable()).isFalse();
        assertThat(server.getRequestCount()).isEqualTo(1);
    }

    @Test
    void aGetGivesUpAfterMaxRetries() {
        for (int i = 0; i < 3; i++) {
            enqueueJson(500, "{\"message\":\"Busy\",\"code\":1,\"isRetryable\":true}");
        }

        ApiException error = catchThrowableOfType(ApiException.class, () -> client.getChargeApi().single("ch_1"));

        assertThat(error.getMessage()).isEqualTo("Busy");
        assertThat(server.getRequestCount()).isEqualTo(1 + 2);
    }

    @Test
    void aChargeIsNeverRetried() {
        server.enqueue(new MockResponse().setResponseCode(503));
        enqueueJson(CHARGE);

        ApiException error = catchThrowableOfType(ApiException.class, () -> client.getChargeApi().createWithToken(
                new NewChargeToken.Builder().withAmount(BigDecimal.TEN).withToken("tok").build()));

        assertThat(error.getHttpCode()).isEqualTo(503);
        assertThat(error.isRetryable()).isTrue();
        assertThat(server.getRequestCount()).isEqualTo(1);
    }

    @Test
    void aRefundIsNeverRetried() {
        server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
        enqueueJson(CHARGE);

        catchThrowableOfType(ApiException.class, () -> client.getChargeApi().refund(
                new NewRefund.Builder().withChargeId("ch_1").build()));

        assertThat(server.getRequestCount()).isEqualTo(1);
    }

    @Test
    void anApiErrorIsMappedToApiException() {
        enqueueJson(400, "{\"message\":\"Invalid card number\",\"details\":{\"CardNumber\":\"invalid\"},"
                + "\"resource\":\"/charge/card\",\"gatewayCode\":\"05\",\"gatewayMessage\":\"Do not honour\","
                + "\"code\":5,\"isRetryable\":false,\"type\":\"https://docs.payfurl.com/errorcodes#5\"}");

        ApiException error = catchThrowableOfType(ApiException.class, () -> client.getChargeApi().single("ch_1"));

        assertThat(error.getCode()).isEqualTo(ErrorCode.InvalidCardNumber);
        assertThat(error.getHttpCode()).isEqualTo(400);
        assertThat(error.getMessage()).isEqualTo("Invalid card number");
        assertThat(error.getDetails()).containsEntry("CardNumber", "invalid");
        assertThat(error.getResource()).isEqualTo("/charge/card");
        assertThat(error.getGatewayCode()).isEqualTo("05");
        assertThat(error.getGatewayMessage()).isEqualTo("Do not honour");
    }

    @Test
    void anErrorCodeTheSdkDoesNotKnowIsUnknownError() {
        enqueueJson(400, "{\"message\":\"Something new\",\"code\":99999,\"isRetryable\":false}");

        ApiException error = catchThrowableOfType(ApiException.class, () -> client.getChargeApi().single("ch_1"));

        assertThat(error.getCode()).isEqualTo(ErrorCode.UnknownError);
        assertThat(error.getMessage()).isEqualTo("Something new");
    }

    @Test
    void anErrorThatIsNotJsonKeepsItsStatus() {
        server.enqueue(new MockResponse().setResponseCode(502).setBody("<html>Bad Gateway</html>"));

        ApiException error = catchThrowableOfType(ApiException.class, () -> client.getChargeApi().createWithToken(
                new NewChargeToken.Builder().withAmount(BigDecimal.TEN).withToken("tok").build()));

        assertThat(error.getHttpCode()).isEqualTo(502);
        assertThat(error.getCode()).isEqualTo(ErrorCode.UnknownError);
        assertThat(error.getMessage()).contains("Bad Gateway");
    }

    @Test
    void aSlowResponseIsATimeout() throws Exception {
        try (PayFurlClient fast = clientBuilder()
                .withHttpClientConfiguration(config -> config.timeout(300).maxRetries(0))
                .build()) {
            server.enqueue(new MockResponse().setBody(CHARGE).setHeadersDelay(2, TimeUnit.SECONDS));

            ApiException error = catchThrowableOfType(ApiException.class, () -> fast.getChargeApi().single("ch_1"));

            assertThat(error.getCode()).isEqualTo(ErrorCode.Timeout);
            assertThat(error.isRetryable()).isTrue();
            assertThat(fast.getTimeout()).isEqualTo(300);
        }
    }

    @Test
    void theDefaultTimeoutIsSixtySeconds() {
        assertThat(new PayFurlClient.Builder().build().getTimeout()).isEqualTo(60_000);
    }

    @Test
    void aGatewayTimeoutIsATimeout() {
        try (PayFurlClient noRetries = clientBuilder()
                .withHttpClientConfiguration(config -> config.maxRetries(0))
                .build()) {
            server.enqueue(new MockResponse().setResponseCode(504));

            ApiException error = catchThrowableOfType(ApiException.class, () -> noRetries.getChargeApi().single("ch_1"));

            assertThat(error.getCode()).isEqualTo(ErrorCode.Timeout);
        }
    }
}
