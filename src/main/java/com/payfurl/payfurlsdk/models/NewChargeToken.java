package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public class NewChargeToken {
    private final BigDecimal amount;
    private final String currency;
    private final String reference;
    private final String token;
    private final CheckoutTransfer checkoutTransfer;
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
    private final String threeDSNotificationUrl;
    private final String firstName;
    private final String lastName;
    private final Geolocation geolocation;

    @JsonCreator
    public NewChargeToken(@JsonProperty("Amount") BigDecimal amount,
                          @JsonProperty("Currency") String currency,
                          @JsonProperty("Reference") String reference,
                          @JsonProperty("PaymentMethodId") String token,
                          @JsonProperty("CheckoutTransfer") CheckoutTransfer checkoutTransfer,
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
                          @JsonProperty("Descriptor") String descriptor,
                          @JsonProperty("ThreeDSNotificationUrl") String threeDSNotificationUrl,
                          @JsonProperty("FirstName") String firstName,
                          @JsonProperty("LastName") String lastName,
                          @JsonProperty("Geolocation") Geolocation geolocation) {
        this.amount = amount;
        this.currency = currency;
        this.reference = reference;
        this.token = token;
        this.checkoutTransfer = checkoutTransfer;
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
        this.threeDSNotificationUrl = threeDSNotificationUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.geolocation = geolocation;
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

    public String getToken() {
        return token;
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

    public CheckoutTransfer getCheckoutTransfer() {
        return checkoutTransfer;
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

    public String getThreeDSNotificationUrl() {
        return threeDSNotificationUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    @Override
    public String toString() {
        return "NewChargeToken{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", paymentMethodId='" + token + '\'' +
                ", reference='" + reference + '\'' +
                ", checkoutTransfer='" + checkoutTransfer + '\'' +
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
                ", threeDSNotificationUrl=" + threeDSNotificationUrl +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", geolocation=" + geolocation +
                '}';
    }

    public static class Builder {
        private BigDecimal amount;
        private String currency;
        private String reference;
        private String token;
        private CheckoutTransfer checkoutTransfer;
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
        private String threeDSNotificationUrl;
        private String firstName;
        private String lastName;
        private Geolocation geolocation;

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

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withCheckoutTransfer(CheckoutTransfer checkoutTransfer) {
            this.checkoutTransfer = checkoutTransfer;
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

        public Builder withDescriptor(String descriptor) {
            this.descriptor = descriptor;
            return this;
        }

        public Builder withThreeDSNotificationUrl(String threeDSNotificationUrl) {
            this.threeDSNotificationUrl = threeDSNotificationUrl;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withGeolocation(Geolocation geolocation) {
            this.geolocation = geolocation;
            return this;
        }

        public NewChargeToken build() {
            return new NewChargeToken(
                    amount,
                    currency,
                    reference,
                    token,
                    checkoutTransfer,
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
                    descriptor,
                    threeDSNotificationUrl,
                    firstName,
                    lastName,
                    geolocation);
        }
    }
}
