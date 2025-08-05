package com.payfurl.payfurlsdk.models.PaymentLink;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class CreatePaymentLink {
    private final String title;
    private final BigDecimal amount;
    private final String currency;
    private final List<String> allowedPaymentTypes;
    private final String description;
    private final String image;
    private final String confirmationMessage;
    private final String redirectUrl;
    private final CallToAction callToAction;
    private final Integer limitPayments;
    private final Map<String, String> metadata;


    public CreatePaymentLink(
            @JsonProperty("Title") String title,
            @JsonProperty("Amount") BigDecimal amount,
            @JsonProperty("Currency") String currency,
            @JsonProperty("AllowedPaymentTypes") List<String> allowedPaymentTypes,
            @JsonProperty("Description") String description,
            @JsonProperty("Image") String image,
            @JsonProperty("ConfirmationMessage") String confirmationMessage,
            @JsonProperty("RedirectUrl") String redirectUrl,
            @JsonProperty("CallToAction") CallToAction callToAction,
            @JsonProperty("LimitPayments") Integer limitPayments,
            @JsonProperty("Metadata") Map<String, String> metadata) {
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
        this.metadata = metadata;
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

    public CallToAction getCallToAction() {
        return callToAction;
    }

    public Integer getLimitPayments() {
        return limitPayments;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public static String encodeImage(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }

        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File does not exist.");
        }

        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + e.getMessage(), e);
        }

        if (imageBytes.length < 4) {
            throw new IllegalArgumentException("Invalid file bytes.");
        }

        String contentType;
        if (imageBytes[0] == (byte) 0x89 && imageBytes[1] == (byte) 0x50 &&
                imageBytes[2] == (byte) 0x4E && imageBytes[3] == (byte) 0x47) {
            contentType = "image/png";
        } else if (imageBytes[0] == (byte) 0xFF && imageBytes[1] == (byte) 0xD8 &&
                imageBytes[imageBytes.length - 2] == (byte) 0xFF &&
                imageBytes[imageBytes.length - 1] == (byte) 0xD9) {
            contentType = "image/jpeg";
        } else if (imageBytes[0] == (byte) 0x47 && imageBytes[1] == (byte) 0x49 &&
                imageBytes[2] == (byte) 0x46 && imageBytes[3] == (byte) 0x38) {
            contentType = "image/gif";
        } else {
            throw new IllegalArgumentException("Unsupported image format.");
        }

        return String.format("data:%s;base64,%s", contentType, Base64.getEncoder().encodeToString(imageBytes));
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
                ", metadata=" + metadata +
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
        private CallToAction callToAction;
        private Integer limitPayments;
        private Map<String, String> metadata;

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

        public Builder withCallToAction(CallToAction callToAction) {
            this.callToAction = callToAction;
            return this;
        }

        public Builder withLimitPayments(Integer limitPayments) {
            this.limitPayments = limitPayments;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public CreatePaymentLink build() {
            return new CreatePaymentLink(
                    title, amount, currency, allowedPaymentTypes,
                    description, image, confirmationMessage, redirectUrl,
                    callToAction, limitPayments, metadata
            );
        }
    }
}
