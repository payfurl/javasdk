package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeCustomer {
    private final BigDecimal amount;
    private final String currency;
    private final String customerId;
    private final String reference;
    private final Address address;

    private final Order order;
    private final BigDecimal taxAmount;
    private final String customerCode;
    private final String invoiceNumber;
    private final boolean capture;

    @JsonCreator
    public NewChargeCustomer(@JsonProperty("Amount") BigDecimal amount,
                             @JsonProperty("Currency") String currency,
                             @JsonProperty("CustomerId") String customerId,
                             @JsonProperty("Reference") String reference,
                             @JsonProperty("Address") Address address,
                             @JsonProperty("Order") Order order,
                             @JsonProperty("TaxAmount") BigDecimal taxAmount,
                             @JsonProperty("CustomerCode") String customerCode,
                             @JsonProperty("InvoiceNumber") String invoiceNumber,
                             @JsonProperty("Capture") boolean capture) {
        this.amount = amount;
        this.currency = currency;
        this.customerId = customerId;
        this.reference = reference;
        this.address = address;
        this.order = order;
        this.taxAmount = taxAmount == null ? BigDecimal.valueOf(0) : taxAmount;
        this.customerCode = customerCode;
        this.invoiceNumber = invoiceNumber;
        this.capture = capture;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getReference() {
        return reference;
    }

    public Address getAddress() {
        return address;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isCapture() {
        return capture;
    }

    @Override
    public String toString() {
        return "NewChargeCustomer{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", customerId='" + customerId + '\'' +
                ", reference='" + reference + '\'' +
                ", address=" + address +
                ", order=" + order +
                ", taxAmount=" + taxAmount +
                ", customerCode=" + customerCode +
                ", invoiceNumber=" + invoiceNumber +
                ", capture=" + capture +
                '}';
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }


    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String customerId;
        private String reference;
        private Address address;
        private Order order;
        private BigDecimal taxAmount;
        private String customerCode;
        private String invoiceNumber;
        private boolean capture = true;

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder withTaxAmount(BigDecimal taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public Builder withCustomerCode(String customerCode) {
            this.customerCode = customerCode;
            return this;
        }

        public Builder withInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withCapture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public NewChargeCustomer build() {
            return new NewChargeCustomer(amount, currency, customerId, reference, address, order, taxAmount, customerCode, invoiceNumber, capture);
        }
    }
}
