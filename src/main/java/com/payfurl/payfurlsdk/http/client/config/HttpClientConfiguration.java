package com.payfurl.payfurlsdk.http.client.config;

public class HttpClientConfiguration {
    private final long timeout;
    private final Environment environment;

    public HttpClientConfiguration(Builder builder) {
        timeout = builder.timeout;
        environment = builder.environment;
    }

    public long getTimeout() {
        return timeout;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public static final class Builder {
        private long timeout;
        private Environment environment;

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

        public HttpClientConfiguration build() {
            return new HttpClientConfiguration(this);
        }
    }
}
