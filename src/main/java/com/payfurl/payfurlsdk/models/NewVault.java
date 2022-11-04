package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NewVault {
    private final String cardNumber;
    private final String ccv;
    private final Date expireDate;
    private final Integer expireSeconds;

    @JsonCreator
    public NewVault(@JsonProperty("CardNumber") String cardNumber,
                    @JsonProperty("Ccv") String ccv,
                    @JsonProperty("ExpireDate") Date expireDate,
                    @JsonProperty("ExpireSeconds") Integer expireSeconds) {
        this.cardNumber = cardNumber;
        this.ccv = ccv;
        this.expireDate = expireDate;
        this.expireSeconds = expireSeconds;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCcv() {
        return ccv;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    @Override
    public String toString() {
        return "NewVault{" +
                "cardNumber='" + cardNumber + '\'' +
                ", ccv='" + ccv + '\'' +
                ", expireDate=" + expireDate +
                ", expireSeconds=" + expireSeconds +
                '}';
    }

    public static class Builder {
        private String cardNumber;
        private String ccv;
        private Date expireDate;
        private Integer expireSeconds;

        public Builder withCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder withCcv(String ccv) {
            this.ccv = ccv;
            return this;
        }

        public Builder withExpireDate(Date expireDate) {
            this.expireDate = expireDate;
            return this;
        }

        public Builder withExpireSeconds(Integer expireSeconds) {
            this.expireSeconds = expireSeconds;
            return this;
        }

        public NewVault build() {
            return new NewVault(cardNumber, ccv, expireDate, expireSeconds);
        }
    }
}
