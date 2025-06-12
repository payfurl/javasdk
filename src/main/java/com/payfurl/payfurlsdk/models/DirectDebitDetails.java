package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectDebitDetails {
    private final String bsb;
    private final String accountNumber;

    @JsonCreator
    public DirectDebitDetails(
            @JsonProperty("Bsb") String bsb,
            @JsonProperty("AccountNumber") String accountNumber) {
        this.bsb = bsb;
        this.accountNumber = accountNumber;
    }

    public String getBsb() {
        return bsb;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "DirectDebitDetails{" +
                "bsb='" + bsb + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

    public static class Builder {
        private String bsb;
        private String accountNumber;

        public Builder withBsb(String bsb) {
            this.bsb = bsb;
            return this;
        }

        public Builder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public DirectDebitDetails build() {
            return new DirectDebitDetails(bsb, accountNumber);
        }
    }
}
