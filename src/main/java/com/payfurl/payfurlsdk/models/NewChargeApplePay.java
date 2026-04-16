package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class NewChargeApplePay {
    private final AppleToken token;
    private final BigDecimal amount;
    private final String currency;
    private final String descriptor;
    private final String ip;
    private final String userAgent;
    private final String firstName;
    private final String lastName;
    private final Order order;
    private final Address address;
    private final String email;
    private final String phone;

    @JsonCreator
    public NewChargeApplePay(@JsonProperty("Token") AppleToken token,
                             @JsonProperty("Amount") BigDecimal amount,
                             @JsonProperty("Currency") String currency,
                             @JsonProperty("Descriptor") String descriptor,
                             @JsonProperty("Ip") String ip,
                             @JsonProperty("UserAgent") String userAgent,
                             @JsonProperty("FirstName") String firstName,
                             @JsonProperty("LastName") String lastName,
                             @JsonProperty("Order") Order order,
                             @JsonProperty("Address") Address address,
                             @JsonProperty("Email") String email,
                             @JsonProperty("Phone") String phone) {
        this.token = token;
        this.amount = amount;
        this.currency = currency;
        this.descriptor = descriptor;
        this.ip = ip;
        this.userAgent = userAgent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.order = order;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public AppleToken getToken() {
        return token;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Order getOrder() {
        return order;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public static class Builder {
        private AppleToken token;
        private BigDecimal amount;
        private String currency;
        private String descriptor;
        private String ip;
        private String userAgent;
        private String firstName;
        private String lastName;
        private Order order;
        private Address address;
        private String email;
        private String phone;

        public Builder withToken(AppleToken token) {
            this.token = token;
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

        public Builder withDescriptor(String descriptor) {
            this.descriptor = descriptor;
            return this;
        }

        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder withUserAgent(String userAgent) {
            this.userAgent = userAgent;
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

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
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

        public NewChargeApplePay build() {
            return new NewChargeApplePay(token, amount, currency, descriptor, ip, userAgent, firstName, lastName, order, address, email, phone);
        }
    }

    public static class AppleToken {
        private final ApplePaySource paymentData;
        private final ApplePaymentMethod paymentMethod;

        @JsonCreator
        public AppleToken(@JsonProperty("PaymentData") ApplePaySource paymentData,
                          @JsonProperty("PaymentMethod") ApplePaymentMethod paymentMethod) {
            this.paymentData = paymentData;
            this.paymentMethod = paymentMethod;
        }

        public ApplePaySource getPaymentData() {
            return paymentData;
        }

        public ApplePaymentMethod getPaymentMethod() {
            return paymentMethod;
        }
    }

    public static class ApplePaymentMethod {
        private final String displayName;
        private final String network;
        private final String type;

        @JsonCreator
        public ApplePaymentMethod(@JsonProperty("DisplayName") String displayName,
                                  @JsonProperty("Network") String network,
                                  @JsonProperty("Type") String type) {
            this.displayName = displayName;
            this.network = network;
            this.type = type;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getNetwork() {
            return network;
        }

        public String getType() {
            return type;
        }
    }

    public static class ApplePaySource {
        private final String data;
        private final String signature;
        private final ApplePayHeader header;
        private final String version;

        @JsonCreator
        public ApplePaySource(@JsonProperty("Data") String data,
                              @JsonProperty("Signature") String signature,
                              @JsonProperty("Header") ApplePayHeader header,
                              @JsonProperty("Version") String version) {
            this.data = data;
            this.signature = signature;
            this.header = header;
            this.version = version;
        }

        public String getData() {
            return data;
        }

        public String getSignature() {
            return signature;
        }

        public ApplePayHeader getHeader() {
            return header;
        }

        public String getVersion() {
            return version;
        }
    }

    public static class ApplePayHeader {
        private final String publicKeyHash;
        private final String ephemeralPublicKey;
        private final String transactionId;

        @JsonCreator
        public ApplePayHeader(@JsonProperty("PublicKeyHash") String publicKeyHash,
                              @JsonProperty("EphemeralPublicKey") String ephemeralPublicKey,
                              @JsonProperty("TransactionId") String transactionId) {
            this.publicKeyHash = publicKeyHash;
            this.ephemeralPublicKey = ephemeralPublicKey;
            this.transactionId = transactionId;
        }

        public String getPublicKeyHash() {
            return publicKeyHash;
        }

        public String getEphemeralPublicKey() {
            return ephemeralPublicKey;
        }

        public String getTransactionId() {
            return transactionId;
        }
    }
}
