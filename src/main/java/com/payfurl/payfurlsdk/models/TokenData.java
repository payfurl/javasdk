package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class TokenData {

    private final String tokenId;
    private final String userId;
    private final CardData card;
    private final ProviderSummary provider;
    private final Date dateAdded;
    private final Date dateUsed;
    private final String payToStatus;
    private final Map<String, String> metadata;

    @JsonCreator
    public TokenData(@JsonProperty("TokenId") String tokenId,
                     @JsonProperty("UserId") String userId,
                     @JsonProperty("Card") CardData card,
                     @JsonProperty("Provider") ProviderSummary provider,
                     @JsonProperty("DateAdded") Date dateAdded,
                     @JsonProperty("DateUsed") Date dateUsed,
                     @JsonProperty("PayToStatus") String payToStatus,
                     @JsonProperty("Metadata") Map<String, String> metadata) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.card = card;
        this.provider = provider;
        this.dateAdded = dateAdded;
        this.dateUsed = dateUsed;
        this.payToStatus = payToStatus;
        this.metadata = metadata;
    }

    public String getTokenId() {
        return tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public CardData getCard() {
        return card;
    }

    public ProviderSummary getProvider() {
        return provider;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Date getDateUsed() {
        return dateUsed;
    }

    public String getPayToStatus() {
        return payToStatus;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "TokenData{" +
                "tokenId=" + tokenId +
                ", userId=" + userId +
                ", card=" + card +
                ", provider=" + provider +
                ", dateAdded=" + dateAdded +
                ", dateUsed=" + dateUsed +
                ", payToStatus=" + payToStatus +
                ", metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private String tokenId;
        private String userId;
        private CardData card;
        private ProviderSummary provider;
        private Date dateAdded;
        private Date dateUsed;
        private String payToStatus;
        private Map<String, String> metadata;

        public Builder withTokenId(String tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withCard(CardData card) {
            this.card = card;
            return this;
        }

        public Builder withProvider(ProviderSummary provider) {
            this.provider = provider;
            return this;
        }

        public Builder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withDateUsed(Date dateUsed) {
            this.dateUsed = dateUsed;
            return this;
        }

        public Builder withPayToStatus(String payToStatus) {
            this.payToStatus = payToStatus;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public TokenData build() {
            return new TokenData(tokenId, userId, card, provider, dateAdded, dateUsed, payToStatus, metadata);
        }
    }
}
