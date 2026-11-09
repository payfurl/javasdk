package com.payfurl.payfurlsdk.support;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * A PayFurlClient pointed at a MockWebServer: each test queues the API's responses and reads back the requests the
 * SDK sent. Nothing leaves the machine.
 */
public abstract class MockApiTest {
    protected static final String SECRET_KEY = "sk_test_123";

    protected MockWebServer server;
    protected PayFurlClient client;

    @BeforeEach
    void startServer() throws IOException {
        server = new MockWebServer();
        server.start();
        client = clientBuilder().build();
    }

    @AfterEach
    void stopServer() throws IOException {
        client.close();
        server.shutdown();
    }

    /** A client for the mock server: no retry delay, so retry tests don't sleep. */
    protected PayFurlClient.Builder clientBuilder() {
        return new PayFurlClient.Builder()
                .withEnvironment(Environment.SANDBOX)
                .withSecretKey(SECRET_KEY)
                .withBaseUrl("http://127.0.0.1:" + server.getPort())
                .withHttpClientConfiguration(config -> config.retryDelay(0));
    }

    protected void enqueueJson(int status, String body) {
        server.enqueue(new MockResponse().setResponseCode(status)
                .setHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(body));
    }

    protected void enqueueJson(String body) {
        enqueueJson(200, body);
    }

    protected RecordedRequest takeRequest() throws InterruptedException {
        RecordedRequest request = server.takeRequest(5, TimeUnit.SECONDS);
        if (request == null) {
            throw new AssertionError("the SDK sent no request");
        }
        return request;
    }

    /** The decoded value of a query parameter of the request, or null. */
    protected static String query(RecordedRequest request, String name) {
        return request.getRequestUrl().queryParameter(name);
    }
}
