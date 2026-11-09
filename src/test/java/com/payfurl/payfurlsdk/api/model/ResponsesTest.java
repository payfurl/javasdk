package com.payfurl.payfurlsdk.api.model;

import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.models.*;
import com.payfurl.payfurlsdk.models.Subscription.SubscriptionData;
import com.payfurl.payfurlsdk.models.Subscription.SubscriptionStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/** Responses as the API writes them (camelCase, enums as their C# names), and what toString() may print. */
class ResponsesTest {
    private static final String CHARGE = "{"
            + "\"chargeId\":\"ch_1\",\"providerChargeId\":\"pch_1\",\"amount\":20.5,\"currency\":\"AUD\","
            + "\"providerId\":\"pr_1\",\"reference\":\"order-1\",\"customerId\":\"cu_1\",\"status\":\"PARTIAL_REFUND\","
            + "\"dateAdded\":\"2026-11-07T10:15:30.123Z\",\"refundedAmount\":5,"
            + "\"paymentInformation\":{\"paymentMethodId\":\"pm_1\",\"type\":\"CARD\","
            + "\"card\":{\"cardNumber\":\"411111******1111\",\"expiryDate\":\"12/30\",\"type\":\"VISA\",\"cardType\":\"CREDIT\"}},"
            + "\"refunds\":[{\"amount\":5,\"dateAdded\":\"2026-11-07T11:00:00Z\",\"comment\":\"returned\"}],"
            + "\"initiator\":\"Merchant\",\"metadata\":{\"order\":\"1\"},\"ip\":\"203.0.113.7\","
            + "\"ipInfo\":{\"ip\":\"203.0.113.7\",\"country\":\"AU\"},\"somethingNew\":1}";

    @Test
    void aChargeReadsEveryField() throws Exception {
        ChargeData charge = ApiUtils.deserialize(CHARGE, ChargeData.class);

        assertThat(charge.getChargeId()).isEqualTo("ch_1");
        assertThat(charge.getAmount()).isEqualByComparingTo(new BigDecimal("20.5"));
        assertThat(charge.getStatus()).isEqualTo(TransactionStatus.PARTIAL_REFUND);
        assertThat(charge.getDateAdded()).isNotNull();
        assertThat(charge.getPaymentInformation().getPaymentMethodId()).isEqualTo("pm_1");
        assertThat(charge.getPaymentInformation().getCardData().getCardNumber()).isEqualTo("411111******1111");
        assertThat(charge.getRefunds()).singleElement().extracting(Refund::getComment).isEqualTo("returned");
        assertThat(charge.getMetadata()).containsEntry("order", "1");
        assertThat(charge.getIp()).isEqualTo("203.0.113.7");
        assertThat(charge.getIpInfo().getCountry()).isEqualTo("AU");
    }

    @Test
    void aStatusTheSdkDoesNotKnowIsUnknown() throws Exception {
        ChargeData charge = ApiUtils.deserialize(CHARGE.replace("PARTIAL_REFUND", "SOMETHING_NEW"), ChargeData.class);
        SubscriptionData subscription = ApiUtils.deserialize("{\"subscriptionId\":\"su_1\",\"status\":\"Hibernating\"}", SubscriptionData.class);

        assertThat(charge.getStatus()).isEqualTo(TransactionStatus.UNKNOWN);
        assertThat(subscription.getStatus()).isEqualTo(SubscriptionStatus.UNKNOWN);
    }

    @Test
    void anApiErrorReadsItsChargeAndTraceIds() throws Exception {
        ApiError error = ApiUtils.deserialize("{\"message\":\"Declined\",\"code\":15,\"chargeId\":\"ch_1\",\"traceId\":\"abc\"}", ApiError.class);

        assertThat(error.getChargeId()).isEqualTo("ch_1");
        assertThat(error.getTraceId()).isEqualTo("abc");
    }

    @Test
    void toStringNeverPrintsACardNumberOrCcv() {
        CardRequestInformation card = new CardRequestInformation.Builder()
                .withCardNumber("4111111111111111").withExpiryDate("12/30").withCcv("123").withCardHolder("Jo").build();
        NewChargeCardRequest charge = new NewChargeCardRequest.Builder().withPaymentInformation(card).build();
        NewVault vault = new NewVault.Builder().withCardNumber("4111111111111111").withCcv("123").build();

        for (Object model : new Object[]{card, charge, vault}) {
            assertThat(model.toString())
                    .as(model.getClass().getSimpleName())
                    .doesNotContain("4111111111111111", "411111", "'123'", "=123")
                    .contains("1111");
        }
        assertThat(card.getCardNumber()).isEqualTo("4111111111111111");
    }
}
