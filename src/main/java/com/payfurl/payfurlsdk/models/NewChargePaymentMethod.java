package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public class NewChargePaymentMethod {
    private final BigDecimal amount;
    private final String currency;
    private final String paymentMethodId;
    private final String reference;
    private final Address address;
    private final Order order;
    private final String customerCode;
    private final String invoiceNumber;
    private final String email;
    private final String phone;
    private final boolean capture;
    private final Initiator initiator;
    private final WebhookConfig webhook;
    private final Map<String, String> metadata;

    @JsonCreator
    public NewChargePaymentMethod(@JsonProperty("Amount") BigDecimal amount,
                                  @JsonProperty("Currency") String currency,
                                  @JsonProperty("Reference") String reference,
                                  @JsonProperty("PaymentMethodId") String paymentMethodId,
                                  @JsonProperty("Address") Address address,
                                  @JsonProperty("Order") Order order,
                                  @JsonProperty("CustomerCode") String customerCode,
                                  @JsonProperty("InvoiceNumber") String invoiceNumber,
                                  @JsonProperty("Email") String email,
                                  @JsonProperty("Phone") String phone,
                                  @JsonProperty("Capture") boolean capture,
                                  @JsonProperty("Initiator") Initiator initiator,
                                  @JsonProperty("Webhook") WebhookConfig webhook,
                                  @JsonProperty("Metadata") Map<String, String> metadata) {
        this.amount = amount;
        this.currency = currency;
        this.reference = reference;
        this.paymentMethodId = paymentMethodId;
        this.address = address;
        this.order = order;
        this.customerCode = customerCode;
        this.invoiceNumber = invoiceNumber;
        this.email = email;
        this.phone = phone;
        this.capture = capture;
        this.initiator = initiator;
        this.webhook = webhook;
        this.metadata = metadata;
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

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isCapture() {
        return capture;
    }

    public Order getOrder() {
        return order;
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

    public WebhookConfig getWebhook() {
        return webhook;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "NewChargePaymentMethod{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                ", reference='" + reference + '\'' +
                ", address=" + address +
                ", order=" + order +
                ", customerCode=" + customerCode +
                ", invoiceNumber=" + invoiceNumber +
                ", email=" + email +
                ", phone=" + phone +
                ", capture=" + capture +
                ", initiator=" + initiator +
                ", webhook=" + webhook +
                ", metadata=" + metadata +
                '}';
    }


    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String reference;
        private String paymentMethodId;
        private Address address;
        private Order order;
        private String customerCode;
        private String invoiceNumber;
        private String email;
        private String phone;
        private boolean capture = true;
        private Initiator initiator;
        private WebhookConfig webhook;
        private Map<String, String> metadata;

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

        public Builder withPaymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
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

        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
            return this;
        }
        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public NewChargePaymentMethod build() {
            return new NewChargePaymentMethod(amount, currency, reference, paymentMethodId, address, order, customerCode, invoiceNumber, email, phone, capture, initiator, webhook, metadata);
        }
    }
}
