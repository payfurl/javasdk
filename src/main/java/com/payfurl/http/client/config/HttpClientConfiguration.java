package com.payfurl.http.client.config;

public class HttpClientConfiguration {
    private final long timeout;

    public HttpClientConfiguration(Builder builder) {
        timeout = builder.timeout;
    }

    public long getTimeout() {
        return timeout;
    }

    public static final class Builder {
        private long timeout;

        public Builder(){
        }

        public Builder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpClientConfiguration build() {
            return new HttpClientConfiguration(this);
        }
    }
}
