package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public class NewChargeNetworkToken {
    private final String networkTokenId;
    private final BigDecimal amount;
    private final String currency;
    private final String providerId;
    private final String reference;
    private final boolean capture;
    private final String ip;
    private final Address address;
    private final Order order;
    private final String customerCode;
    private final String invoiceNumber;
    private final Initiator initiator;
    private final WebhookConfig webhook;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final Map<String, String> metadata;
    private final String descriptor;
    private final String threeDSNotificationUrl;

    @JsonCreator
    public NewChargeNetworkToken(@JsonProperty("NetworkTokenId") String networkTokenId,
                                 @JsonProperty("Amount") BigDecimal amount,
                                 @JsonProperty("Currency") String currency,
                                 @JsonProperty("ProviderId") String providerId,
                                 @JsonProperty("Reference") String reference,
                                 @JsonProperty("Capture") boolean capture,
                                 @JsonProperty("Ip") String ip,
                                 @JsonProperty("Address") Address address,
                                 @JsonProperty("Order") Order order,
                                 @JsonProperty("CustomerCode") String customerCode,
                                 @JsonProperty("InvoiceNumber") String invoiceNumber,
                                 @JsonProperty("Initiator") Initiator initiator,
                                 @JsonProperty("Webhook") WebhookConfig webhook,
                                 @JsonProperty("FirstName") String firstName,
                                 @JsonProperty("LastName") String lastName,
                                 @JsonProperty("Email") String email,
                                 @JsonProperty("Phone") String phone,
                                 @JsonProperty("Metadata") Map<String, String> metadata,
                                 @JsonProperty("Descriptor") String descriptor,
                                 @JsonProperty("ThreeDSNotificationUrl") String threeDSNotificationUrl) {
        this.networkTokenId = networkTokenId;
        this.amount = amount;
        this.currency = currency;
        this.providerId = providerId;
        this.reference = reference;
        this.capture = capture;
        this.ip = ip;
        this.address = address;
        this.order = order;
        this.customerCode = customerCode;
        this.invoiceNumber = invoiceNumber;
        this.initiator = initiator;
        this.webhook = webhook;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.metadata = metadata;
        this.descriptor = descriptor;
        this.threeDSNotificationUrl = threeDSNotificationUrl;
    }

    public String getNetworkTokenId() {
        return networkTokenId;
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

    public boolean isCapture() {
        return capture;
    }

    public String getIp() {
        return ip;
    }

    public Address getAddress() {
        return address;
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

    public Initiator getInitiator() {
        return initiator;
    }

    public WebhookConfig getWebhook() {
        return webhook;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
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

    public static class Builder {
        private String networkTokenId;
        private BigDecimal amount;
        private String currency;
        private String providerId;
        private String reference;
        private boolean capture = true;
        private String ip;
        private Address address;
        private Order order;
        private String customerCode;
        private String invoiceNumber;
        private Initiator initiator;
        private WebhookConfig webhook;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private Map<String, String> metadata;
        private String descriptor;
        private String threeDSNotificationUrl;

        public Builder withNetworkTokenId(String networkTokenId) {
            this.networkTokenId = networkTokenId;
            return this;
        }

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

        public Builder withCapture(boolean capture) {
            this.capture = capture;
            return this;
        }

        public Builder withIp(String ip) {
            this.ip = ip;
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

        public Builder withInitiator(Initiator initiator) {
            this.initiator = initiator;
            return this;
        }

        public Builder withWebhook(WebhookConfig webhook) {
            this.webhook = webhook;
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

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
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

        public NewChargeNetworkToken build() {
            return new NewChargeNetworkToken(
                    networkTokenId, amount, currency, providerId, reference, capture, ip, address, order,
                    customerCode, invoiceNumber, initiator, webhook, firstName, lastName, email, phone,
                    metadata, descriptor, threeDSNotificationUrl
            );
        }
    }
}
