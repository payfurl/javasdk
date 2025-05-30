package com.payfurl.payfurlsdk.models.PaymentLink;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class CreatePaymentLink {
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

    public CreatePaymentLink(
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
        return "CreatePaymentLink{" +
                "title='" + title + '\'' +
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

        public CreatePaymentLink build() {
            return new CreatePaymentLink(
                    title, amount, currency, allowedPaymentTypes,
                    description, image, confirmationMessage, redirectUrl,
                    callToAction, limitPayments
            );
        }
    }
}
