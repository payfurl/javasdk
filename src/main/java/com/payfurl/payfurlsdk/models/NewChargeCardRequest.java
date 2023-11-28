package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public class NewChargeCardRequest {
    private final BigDecimal amount;
    private final String currency;
    private final String providerId;
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
    private final WebhookConfig webhook;
    private final Map<String, String> metadata;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String descriptor;

    @JsonCreator
    public NewChargeCardRequest(@JsonProperty("Amount") BigDecimal amount,
                                @JsonProperty("Currency") String currency,
                                @JsonProperty("ProviderId") String providerId,
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
                                @JsonProperty("Webhook") WebhookConfig webhook,
                                @JsonProperty("Metadata") Map<String, String> metadata,
                                @JsonProperty("Descriptor") String descriptor) {
        this.amount = amount;
        this.currency = currency;
        this.providerId = providerId;
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
        this.webhook = webhook;
        this.metadata = metadata;
        this.descriptor = descriptor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getProviderId() {
        return providerId;
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

    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String toString() {
        return "NewChargeCardRequest{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", providerId='" + providerId + '\'' +
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
                ", webhook=" + webhook +
                ", metadata=" + metadata +
                ", descriptor=" + descriptor +
                '}';
    }

    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String providerId;
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
        private WebhookConfig webhook;
        private Map<String, String> metadata;
        private String descriptor;

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
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

        public Builder withCapture(boolean capture) {
            this.capture = capture;
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

        public Builder withDescriptor(String descriptor) {
            this.descriptor = descriptor;
            return this;
        }

        public NewChargeCardRequest build() {
            return new NewChargeCardRequest(
                    amount,
                    currency,
                    providerId,
                    reference,
                    paymentInformation,
                    address,
                    order,
                    customerCode,
                    invoiceNumber,
                    email,
                    phone,
                    capture,
                    initiator,
                    webhook,
                    metadata,
                    descriptor);
        }
    }
}
