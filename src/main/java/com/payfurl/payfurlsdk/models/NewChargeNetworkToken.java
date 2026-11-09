package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Map;

/**
 * A charge with a network token payFURL holds: {@code POST /charge/network_token} (the API's api.Models.NewChargeNetworkToken).
 * Null fields are left out of the request, so the API's defaults apply (e.g. {@code capture} is true).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewChargeNetworkToken {
    private final String networkTokenId;
    private final BigDecimal amount;
    private final String currency;
    private final String providerId;
    private final String reference;
    private final Boolean capture;
    private final String ip;
    private final Address address;
    private final Order order;
    private final String customerCode;
    private final String invoiceNumber;
    private final Initiator initiator;
    private final WebhookConfig webhook;
    private final String email;
    private final String phone;
    private final String firstName;
    private final String lastName;
    private final Map<String, String> metadata;
    private final String descriptor;
    private final String threeDSNotificationUrl;

    private NewChargeNetworkToken(Builder builder) {
        this.networkTokenId = builder.networkTokenId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.providerId = builder.providerId;
        this.reference = builder.reference;
        this.capture = builder.capture;
        this.ip = builder.ip;
        this.address = builder.address;
        this.order = builder.order;
        this.customerCode = builder.customerCode;
        this.invoiceNumber = builder.invoiceNumber;
        this.initiator = builder.initiator;
        this.webhook = builder.webhook;
        this.email = builder.email;
        this.phone = builder.phone;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.metadata = builder.metadata;
        this.descriptor = builder.descriptor;
        this.threeDSNotificationUrl = builder.threeDSNotificationUrl;
    }

    /** Required. */
    public String getNetworkTokenId() {
        return networkTokenId;
    }

    /** Required. */
    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    /** Required: the gateway to charge with. */
    public String getProviderId() {
        return providerId;
    }

    public String getReference() {
        return reference;
    }

    /** False to only authorise; null means true. */
    public Boolean getCapture() {
        return capture;
    }

    /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    @Override
    public String toString() {
        return "NewChargeNetworkToken{" +
                "networkTokenId=" + networkTokenId +
                ", amount=" + amount +
                ", currency=" + currency +
                ", providerId=" + providerId +
                ", reference=" + reference +
                ", capture=" + capture +
                ", ip=" + ip +
                ", address=" + address +
                ", order=" + order +
                ", customerCode=" + customerCode +
                ", invoiceNumber=" + invoiceNumber +
                ", initiator=" + initiator +
                ", webhook=" + webhook +
                ", email=" + email +
                ", phone=" + phone +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", metadata=" + metadata +
                ", descriptor=" + descriptor +
                ", threeDSNotificationUrl=" + threeDSNotificationUrl +
                '}';
    }

    public static class Builder {
        private String networkTokenId;
        private BigDecimal amount;
        private String currency;
        private String providerId;
        private String reference;
        private Boolean capture;
        private String ip;
        private Address address;
        private Order order;
        private String customerCode;
        private String invoiceNumber;
        private Initiator initiator;
        private WebhookConfig webhook;
        private String email;
        private String phone;
        private String firstName;
        private String lastName;
        private Map<String, String> metadata;
        private String descriptor;
        private String threeDSNotificationUrl;

        /** Required. */
        public Builder withNetworkTokenId(String networkTokenId) {
            this.networkTokenId = networkTokenId;
            return this;
        }

        /** Required. */
        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        /** Required: the gateway to charge with. */
        public Builder withProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        /** False to only authorise; null means true. */
        public Builder withCapture(Boolean capture) {
            this.capture = capture;
            return this;
        }

        /** The payer's IP address (IPv4 or IPv6), used for fraud checks. */
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

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
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
            return new NewChargeNetworkToken(this);
        }
    }
}
