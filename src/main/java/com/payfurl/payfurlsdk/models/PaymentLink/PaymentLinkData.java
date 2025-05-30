package com.payfurl.payfurlsdk.models.PaymentLink;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PaymentLinkData {
    private final String paymentLinkId;
    private final Date dateAdded;
    private final String accountId;
    private final String secretKey;
    private final String publicKey;
    private final String title;
    private final BigDecimal amount;
    private final String currency;
    private final List<String> allowedPaymentTypes;
    private final String description;
    private final String image;
    private final String confirmationMessage;
    private final String redirectUrl;
    private final String callToAction;
    private final Integer limitPayments;


    public PaymentLinkData(
            @JsonProperty("PaymentLinkId") String paymentLinkId,
            @JsonProperty("DateAdded") Date dateAdded,
            @JsonProperty("AccountId") String accountId,
            @JsonProperty("SecretKey") String secretKey,
            @JsonProperty("PublicKey") String publicKey,
            @JsonProperty("Title") String title,
            @JsonProperty("Amount") BigDecimal amount,
            @JsonProperty("Currency") String currency,
            @JsonProperty("AllowedPaymentTypes") List<String> allowedPaymentTypes,
            @JsonProperty("Description") String description,
            @JsonProperty("Image") String image,
            @JsonProperty("ConfirmationMessage") String confirmationMessage,
            @JsonProperty("RedirectUrl") String redirectUrl,
            @JsonProperty("CallToAction") String callToAction,
            @JsonProperty("LimitPayments") Integer limitPayments) {
        this.paymentLinkId = paymentLinkId;
        this.dateAdded = dateAdded;
        this.accountId = accountId;
        this.secretKey = secretKey;
        this.publicKey = publicKey;
        this.title = title;
        this.amount = amount;
        this.currency = currency;
        this.allowedPaymentTypes = allowedPaymentTypes;
        this.description = description;
        this.image = image;
        this.confirmationMessage = confirmationMessage;
        this.redirectUrl = redirectUrl;
        this.callToAction = callToAction;
        this.limitPayments = limitPayments;
    }

    public String getPaymentLinkId() {
        return paymentLinkId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public List<String> getAllowedPaymentTypes() {
        return allowedPaymentTypes;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getCallToAction() {
        return callToAction;
    }

    public Integer getLimitPayments() {
        return limitPayments;
    }

    @Override
    public String toString() {
        return "PaymentLinkData{" +
                "paymentLinkId='" + paymentLinkId + '\'' +
                ", dateAdded=" + dateAdded +
                ", accountId='" + accountId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", allowedPaymentTypes=" + allowedPaymentTypes +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", confirmationMessage='" + confirmationMessage + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", callToAction='" + callToAction + '\'' +
                ", limitPayments=" + limitPayments +
                '}';
    }

    public static class Builder {
        private String paymentLinkId;
        private Date dateAdded;
        private String accountId;
        private String secretKey;
        private String publicKey;
        private String title;
        private BigDecimal amount;
        private String currency;
        private List<String> allowedPaymentTypes;
        private String description;
        private String image;
        private String confirmationMessage;
        private String redirectUrl;
        private String callToAction;
        private Integer limitPayments;

        public Builder withPaymentLinkId(String paymentLinkId) {
            this.paymentLinkId = paymentLinkId;
            return this;
        }

        public Builder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder withSecretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public Builder withPublicKey(String publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
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

        public Builder withAllowedPaymentTypes(List<String> allowedPaymentTypes) {
            this.allowedPaymentTypes = allowedPaymentTypes;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withImage(String image) {
            this.image = image;
            return this;
        }

        public Builder withConfirmationMessage(String confirmationMessage) {
            this.confirmationMessage = confirmationMessage;
            return this;
        }

        public Builder withRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public Builder withCallToAction(String callToAction) {
            this.callToAction = callToAction;
            return this;
        }

        public Builder withLimitPayments(Integer limitPayments) {
            this.limitPayments = limitPayments;
            return this;
        }

        public PaymentLinkData build() {
            return new PaymentLinkData(
                    paymentLinkId, dateAdded, accountId, secretKey, publicKey,
                    title, amount, currency, allowedPaymentTypes, description, image,
                    confirmationMessage, redirectUrl, callToAction, limitPayments
            );
        }
    }
}
