package com.payfurl.payfurlsdk.http.client.config;

import okhttp3.OkHttpClient;

public class HttpClientConfiguration {
    public static final int DEFAULT_MAX_RETRIES = 2;
    public static final long DEFAULT_RETRY_DELAY_MILLISECONDS = 500;

    private final long timeout;
    private final Environment environment;
    private final OkHttpClient okHttpClient;
    private final int maxRetries;
    private final long retryDelay;

    public HttpClientConfiguration(Builder builder) {
        timeout = builder.timeout;
        environment = builder.environment;
        okHttpClient = builder.okHttpClient;
        maxRetries = builder.maxRetries;
        retryDelay = builder.retryDelay;
    }

    public long getTimeout() {
        return timeout;
    }

    public Environment getEnvironment() {
        return environment;
    }

    /** The OkHttp client requests are sent with (its timeouts are replaced), or null for the SDK's own. */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /** How many times a GET is retried after a connection error, a timeout or a retryable API error. */
    public int getMaxRetries() {
        return maxRetries;
    }

    /** The delay before the first retry, in milliseconds; it doubles for each retry after it. */
    public long getRetryDelay() {
        return retryDelay;
    }

    public static final class Builder {
        private long timeout;
        private Environment environment;
        private OkHttpClient okHttpClient;
        private int maxRetries = DEFAULT_MAX_RETRIES;
        private long retryDelay = DEFAULT_RETRY_DELAY_MILLISECONDS;

        public Builder() {
        }

        public Builder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public long getTimeout() {
            return timeout;
        }

        public Builder environment(Environment environment) {
            this.environment = environment;
            return this;
        }

        public Environment getEnvironment() {
            return environment;
        }

        /**
         * Send requests with this OkHttp client (for a proxy, interceptors or a shared connection pool). The SDK
         * derives its own client from it with {@link OkHttpClient#newBuilder()} and sets the timeouts; the given
         * client is not changed or closed.
         */
        public Builder okHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        /** How many times a GET is retried; 0 turns retries off. POST, PUT and DELETE are never retried. */
        public Builder maxRetries(int maxRetries) {
            if (maxRetries < 0) {
                throw new IllegalArgumentException("maxRetries must not be negative");
            }
            this.maxRetries = maxRetries;
            return this;
        }

        /** The delay before the first retry, in milliseconds; it doubles for each retry after it. */
        public Builder retryDelay(long retryDelay) {
            if (retryDelay < 0) {
                throw new IllegalArgumentException("retryDelay must not be negative");
            }
            this.retryDelay = retryDelay;
            return this;
        }

        public HttpClientConfiguration build() {
            return new HttpClientConfiguration(this);
        }
    }
}
