package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class WebhookSubscriptionData {
    private String webhookSubscriptionId;
    private String accountId;
    private String url;
    private String authorization;
    private List<WebhookType> types;
    private Date createdDate;
    private Date dateRemoved;

    @JsonCreator
    public WebhookSubscriptionData(@JsonProperty("WebhookSubscriptionId") String webhookSubscriptionId,
                                   @JsonProperty("AccountId") String accountId,
                                   @JsonProperty("Url") String url,
                                   @JsonProperty("Authorization") String authorization,
                                   @JsonProperty("Types") List<WebhookType> types,
                                   @JsonProperty("CreatedDate") Date createdDate,
                                   @JsonProperty("DateRemoved") Date dateRemoved) {
        this.webhookSubscriptionId = webhookSubscriptionId;
        this.accountId = accountId;
        this.url = url;
        this.authorization = authorization;
        this.types = types;
        this.createdDate = createdDate;
        this.dateRemoved = dateRemoved;
    }

    public String getWebhookSubscriptionId() {
        return webhookSubscriptionId;
    }

    public void setWebhookSubscriptionId(String webhookSubscriptionId) {
        this.webhookSubscriptionId = webhookSubscriptionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDateRemoved() {
        return dateRemoved;
    }

    public void setDateRemoved(Date dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

    @Override
    public String toString() {
        return "WebhookSubscriptionData{" +
                "webhookSubscriptionId='" + webhookSubscriptionId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", url='" + url + '\'' +
                ", authorization='" + authorization + '\'' +
                ", types=" + types +
                ", createdDate=" + createdDate +
                ", dateRemoved=" + dateRemoved +
                '}';
    }

    public static class Builder {
        private String webhookSubscriptionId;
        private String accountId;
        private String url;
        private String authorization;
        private List<WebhookType> types;
        private Date createdDate;
        private Date dateRemoved;

        public Builder withWebhookSubscriptionId(String webhookSubscriptionId) {
            this.webhookSubscriptionId = webhookSubscriptionId;
            return this;
        }

        public Builder withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

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

        public Builder withCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder withDateRemoved(Date dateRemoved) {
            this.dateRemoved = dateRemoved;
            return this;
        }

        public WebhookSubscriptionData build() {
            return new WebhookSubscriptionData(
                    webhookSubscriptionId,
                    accountId,
                    url,
                    authorization,
                    types,
                    createdDate,
                    dateRemoved);
        }
    }
}
