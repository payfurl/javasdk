package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NewWebhookSubscription {
    private String url;
    private String authorization;
    private List<WebhookType> types;

    @JsonCreator
    public NewWebhookSubscription(@JsonProperty("Url") String url,
                                  @JsonProperty("Authorization") String authorization,
                                  @JsonProperty("Types") List<WebhookType> types) {
        this.url = url;
        this.authorization = authorization;
        this.types = types;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public List<WebhookType> getTypes() {
        return types;
    }

    public void setTypes(List<WebhookType> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "NewWebhookSubscription{" +
                "url='" + url + '\'' +
                ", authorization='" + authorization + '\'' +
                ", types=" + types +
                '}';
    }

    public static class Builder {
        private String url;
        private String authorization;
        private List<WebhookType> types;

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withAuthorization(String authorization) {
            this.authorization = authorization;
            return this;
        }

        public Builder withTypes(List<WebhookType> types) {
            this.types = types;
            return this;
        }

        public NewWebhookSubscription build() {
            NewWebhookSubscription subscription = new NewWebhookSubscription();
            subscription.setUrl(url);
            subscription.setAuthorization(authorization);
            subscription.setTypes(types);
            return subscription;
        }
    }
}
