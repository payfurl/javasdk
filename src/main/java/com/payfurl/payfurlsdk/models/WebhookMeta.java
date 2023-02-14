package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookMeta {
    private final String messageId;
    private final String timestamp;
    private final String type;
    private final String eventType;

    @JsonCreator
    public WebhookMeta(@JsonProperty("MessageId") String messageId,
                       @JsonProperty("Timestamp") String timestamp,
                       @JsonProperty("Type") String type,
                       @JsonProperty("EventType") String eventType) {
        this.messageId = messageId;
        this.timestamp = timestamp;
        this.type = type;
        this.eventType = eventType;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "WebhookMeta{" +
                "messageId='" + messageId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", type='" + type + '\'' +
                ", eventType='" + eventType + '\'' +
                '}';
    }

    public static class Builder {

        private String messageId;
        private String timestamp;
        private String type;
        private String eventType;

        public Builder withMessageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder withTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withEventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public WebhookMeta build() {
            return new WebhookMeta(messageId, timestamp, type, eventType);
        }
    }
}
