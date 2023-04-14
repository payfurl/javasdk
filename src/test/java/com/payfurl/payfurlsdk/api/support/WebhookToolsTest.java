package com.payfurl.payfurlsdk.api.support;

import com.payfurl.payfurlsdk.models.WebhookTransaction;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class WebhookToolsTest {
    private static final String TEST_WEBHOOK_SIGNATURE_KEY = "dCM6l9ngZMJXVappk73yS607k1K7byfyzTTdToaKMa8=";
    private static final String TEST_SAMPLE_REQUEST_BODY = "{\"data\":{\"chargeId\":\"3f83ab8fdf624c649bc70bbba81d6c2b\",\"providerChargeId\":\"ch_3MYd2tE9mXU4onpB0r5iTsiL\",\"amount\":20,\"providerId\":\"a26c371f-94f6-40da-add2-28ec8e9da8ed\",\"paymentInformation\":{\"paymentMethodId\":\"80da8c2d674b4d2e8c65a6520e89d070\",\"card\":{\"cardNumber\":\"4111********1111\",\"expiryDate\":\"12/25\",\"type\":\"VISA\",\"cardType\":\"CREDIT\",\"cardIin\":\"411\"},\"type\":\"CARD\"},\"customerId\":\"025c73d9cd0540e9a5a997f8ba97c732\",\"status\":\"SUCCESS\",\"dateAdded\":\"2023-02-06T22:20:19.0461561Z\",\"successDate\":\"2023-02-06T22:20:20.8655832Z\",\"estimatedCost\":0.20,\"estimatedCostCurrency\":\"AUD\",\"currency\":\"Aud\",\"refunds\":[],\"threeDsVerified\":false},\"meta\":{\"messageId\":\"bc4f056315d6e0205ab085dde45c4a46\",\"timestamp\":\"2023-01-19T20:37:12.8456589Z\",\"type\":\"transaction\",\"eventType\":\"transaction.status.changed\"}}";
    private static final String TEST_PAYFURL_REQUEST_HEADER_SIGNATURE = "rDYP2MxMKvvmoV2KrbOi4pnelHnVJoFYdBegvCK7IQk=";

    @Test
    @DisplayName("Given prefilled webhook When deserialize Then deserialize transaction")
    void testDeserializeTransactionWithValidEntity() {
        WebhookTransaction webhookTransaction = WebhookTools.deserializeTransaction(TEST_SAMPLE_REQUEST_BODY, TEST_PAYFURL_REQUEST_HEADER_SIGNATURE, TEST_WEBHOOK_SIGNATURE_KEY);

        assertThat(webhookTransaction).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("Given prefilled webhook When request body is incorrect Then throw exception")
    void testDeserializeTransactionWithInvalidEntity() {
        Throwable throwable = catchThrowable(() -> WebhookTools.deserializeTransaction(StringUtils.EMPTY, "InvalidSignature", TEST_WEBHOOK_SIGNATURE_KEY));

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Request body is not from PayFURL");
    }
}
