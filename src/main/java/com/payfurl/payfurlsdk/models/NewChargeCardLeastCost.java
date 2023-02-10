package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeCardLeastCost {
    private final BigDecimal amount;
    private final String currency;
    private final String reference;
    private final CardRequestInformation paymentInformation;
    private final Address address;
    private final Order order;
    private final String customerCode;
    private final String invoiceNumber;
    private final String email;
    private final String phone;
    private final boolean capture;
    private final Initiator initiator;
    private final WebhookConfig webhookConfig;

    @JsonCreator
    public NewChargeCardLeastCost(@JsonProperty("Amount") BigDecimal amount,
                                  @JsonProperty("Currency") String currency,
                                  @JsonProperty("Reference") String reference,
                                  @JsonProperty("PaymentInformation") CardRequestInformation paymentInformation,
                                  @JsonProperty("Address") Address address,
                                  @JsonProperty("Order") Order order,
                                  @JsonProperty("CustomerCode") String customerCode,
                                  @JsonProperty("InvoiceNumber") String invoiceNumber,
                                  @JsonProperty("Email") String email,
                                  @JsonProperty("Phone") String phone,
                                  @JsonProperty("Capture") boolean capture,
                                  @JsonProperty("Initiator") Initiator initiator,
                                  @JsonProperty("WebhookConfig") WebhookConfig webhookConfig) {
        this.amount = amount;
        this.currency = currency;
        this.reference = reference;
        this.paymentInformation = paymentInformation;
        this.address = address;
        this.order = order;
        this.customerCode = customerCode;
        this.invoiceNumber = invoiceNumber;
        this.email = email;
        this.phone = phone;
        this.capture = capture;
        this.initiator = initiator;
        this.webhookConfig = webhookConfig;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getReference() {
        return reference;
    }

    public CardRequestInformation getPaymentInformation() {
        return paymentInformation;
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

    public String getCustomerCode() {
        return customerCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Initiator getInitiator() {
        return initiator;
    }

    public WebhookConfig getWebhookConfig() {
        return webhookConfig;
    }

    @Override
    public String toString() {
        return "NewChargeCardLeastCost{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", reference='" + reference + '\'' +
                ", paymentInformation=" + paymentInformation +
                ", address=" + address +
                ", order=" + order +
                ", customerCode=" + customerCode +
                ", invoiceNumber=" + invoiceNumber +
                ", email=" + email +
                ", phone=" + phone +
                ", capture=" + capture +
                ", initiator=" + initiator +
                ", webhookConfig=" + webhookConfig +
                '}';
    }

    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String reference;
        private CardRequestInformation paymentInformation;
        private Address address;
        private Order order;
        private String customerCode;
        private String invoiceNumber;
        private String email;
        private String phone;
        private boolean capture = true;
        private Initiator initiator;
        private WebhookConfig webhookConfig;

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

        public Builder withPaymentInformation(CardRequestInformation paymentInformation) {
            this.paymentInformation = paymentInformation;
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

        public Builder withCustomerCode(String customerCode) {
            this.customerCode = customerCode;
            return this;
        }

        public Builder withInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withCapture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public Builder withInitiator(Initiator initiator) {
            this.initiator = initiator;
            return this;
        }

        public Builder withWebhookConfig(WebhookConfig webhookConfig) {
            this.webhookConfig = webhookConfig;
            return this;
        }

        public NewChargeCardLeastCost build() {
            return new NewChargeCardLeastCost(amount, currency, reference, paymentInformation, address, order, customerCode, invoiceNumber, email, phone, capture, initiator, webhookConfig);
        }
    }
}
