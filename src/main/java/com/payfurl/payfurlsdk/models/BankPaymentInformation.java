package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankPaymentInformation {
    private final String bankCode;
    private final String accountNumber;
    private final String accountName;

    @JsonCreator
    public BankPaymentInformation(@JsonProperty("BankCode") String bankCode,
                                  @JsonProperty("AccountNumber") String accountNumber,
                                  @JsonProperty("AccountName") String accountName) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String toString() {
        return "BankPaymentInformation{" +
                "bankCode='" + bankCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountName='" + accountName + '\'' +
                '}';
    }

    public static class Builder {
        private String bankCode;
        private String accountNumber;
        private String accountName;

        public Builder withBankCode(String bankCode) {
            this.bankCode = bankCode;
            return this;
        }

        public Builder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder withAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public BankPaymentInformation build() {
            return new BankPaymentInformation(bankCode, accountNumber, accountName);
        }
    }
}
