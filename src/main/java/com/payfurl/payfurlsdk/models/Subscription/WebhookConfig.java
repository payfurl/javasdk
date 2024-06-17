package com.payfurl.payfurlsdk.models.Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookConfig {

    private String url;
    private String authorization;

    @JsonCreator
    public WebhookConfig(
            @JsonProperty("Url") String url,
            @JsonProperty("Authorization") String authorization) {
        this.url = url;
        this.authorization = authorization;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthorization() {
        return authorization;
    }

    @Override
    public String toString() {
        return "WebhookConfig{" +
                "url='" + url + '\'' +
                ", authorization='" + authorization +
                '}';
    }

    public static class Builder {
        private String url;
        private String authorization;

        public WebhookConfig build() {
            return new WebhookConfig(url, authorization);
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withAuthorization(String authorization) {
            this.authorization = authorization;
            return this;
        }
    }
}