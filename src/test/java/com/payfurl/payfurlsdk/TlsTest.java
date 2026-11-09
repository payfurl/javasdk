package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.tls.HandshakeCertificates;
import okhttp3.tls.HeldCertificate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

/**
 * An HTTPS server with a self-signed certificate, as the local API has: only a LOCAL client accepts it, and creating
 * one never changes the TLS checks of any other client.
 */
class TlsTest {
    private static final String CHARGE = "{\"chargeId\":\"ch_1\",\"status\":\"SUCCESS\"}";

    private MockWebServer server;

    @BeforeEach
    void startServer() throws IOException {
        HeldCertificate selfSigned = new HeldCertificate.Builder().addSubjectAlternativeName("localhost").build();
        server = new MockWebServer();
        server.useHttps(new HandshakeCertificates.Builder().heldCertificate(selfSigned).build().sslSocketFactory(), false);
        server.start();
    }

    @AfterEach
    void stopServer() throws IOException {
        server.shutdown();
    }

    private PayFurlClient client(Environment environment) {
        return client(environment, new PayFurlClient.Builder());
    }

    private PayFurlClient client(Environment environment, PayFurlClient.Builder builder) {
        return builder
                .withEnvironment(environment)
                .withSecretKey("sk")
                .withBaseUrl("https://localhost:" + server.getPort())
                .withHttpClientConfiguration(config -> config.maxRetries(0))
                .build();
    }

    @Test
    void aLocalClientAcceptsTheLocalCertificate() {
        server.enqueue(new MockResponse().setBody(CHARGE));

        try (PayFurlClient local = client(Environment.LOCAL)) {
            assertThat(local.getChargeApi().single("ch_1").getChargeId()).isEqualTo("ch_1");
        }
    }

    @Test
    void aLocalClientCreatedFirstNeverChangesAProductionOne() {
        server.enqueue(new MockResponse().setBody(CHARGE));
        PayFurlClient local = client(Environment.LOCAL);
        local.getChargeApi().single("ch_1");

        for (Environment environment : new Environment[]{Environment.PRODUCTION, Environment.SANDBOX, Environment.DEVELOPMENT}) {
            PayFurlClient verified = client(environment);

            ApiException error = catchThrowableOfType(ApiException.class, () -> verified.getChargeApi().single("ch_1"));

            assertThat(error).as(environment.name()).isNotNull();
        }
        assertThat(server.getRequestCount()).isEqualTo(1);
    }

    @Test
    void aLocalClientDoesNotChangeTheOkHttpClientItWasGiven() {
        server.enqueue(new MockResponse().setBody(CHARGE));
        OkHttpClient mine = new OkHttpClient();

        try (PayFurlClient local = client(Environment.LOCAL, new PayFurlClient.Builder().withOkHttpClient(mine))) {
            local.getChargeApi().single("ch_1");
        }

        // Same OkHttpClient, same connection pool: the LOCAL client's trust-all connection must not be reused.
        PayFurlClient production = client(Environment.PRODUCTION, new PayFurlClient.Builder().withOkHttpClient(mine));
        assertThat(catchThrowableOfType(ApiException.class, () -> production.getChargeApi().single("ch_1"))).isNotNull();
        assertThat(server.getRequestCount()).isEqualTo(1);
    }

    @Test
    void closingOneLocalClientLeavesAnotherWorking() {
        server.enqueue(new MockResponse().setBody(CHARGE));
        server.enqueue(new MockResponse().setBody(CHARGE));
        PayFurlClient first = client(Environment.LOCAL);
        PayFurlClient second = client(Environment.LOCAL);

        first.getChargeApi().single("ch_1");
        first.close();

        assertThat(second.getChargeApi().single("ch_1").getChargeId()).isEqualTo("ch_1");
        second.close();
    }
}
