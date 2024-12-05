package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.PaymentMethodApi;
import com.payfurl.payfurlsdk.api.SubscriptionApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.models.CardRequestInformation;
import com.payfurl.payfurlsdk.models.NewPaymentMethodCard;
import com.payfurl.payfurlsdk.models.PaymentMethodData;
import com.payfurl.payfurlsdk.models.Subscription.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionApiTest {
    private SubscriptionApi subscriptionApi;
    private PaymentMethodApi paymentMethodApi;

    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .build();

    private static final HashMap<String, String> METADATA = new HashMap<String, String>() {{ put("merchant_id", "1234356"); }};

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        paymentMethodApi = payFurlClient.getPaymentMethodApi();
        subscriptionApi = payFurlClient.getSubscriptionApi();
    }

    @Test
    @DisplayName("When createSubscriptionWithPaymentMethod request is executed, Then return valid subscription status")
    void testCreateSubscriptionWithPaymentMethod() throws ApiException {
        // given
        NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                .withProviderId(TestConfigProvider.getProviderId())
                .withMetadata(METADATA)
                .build();

        PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);
        String paymentMethodId = paymentMethodWithCard.getPaymentMethodId();

        NewSubscription newSubscription = getNewSubscription(paymentMethodId);

        // when
        SubscriptionData subscriptionData = subscriptionApi.createSubscription(newSubscription);

        // then
        assertThat(subscriptionData.getPaymentMethodId()).isEqualTo(paymentMethodId);
        assertThat(subscriptionData.getStatus()).isEqualTo("Active");
    }

    @Test
    @DisplayName("When getSubscription request is executed, Then return valid subscription data")
    void testGetSubscription() throws ApiException {
        // given
        NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                .withProviderId(TestConfigProvider.getProviderId())
                .withMetadata(METADATA)
                .build();

        PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);
        String paymentMethodId = paymentMethodWithCard.getPaymentMethodId();

        NewSubscription newSubscription = getNewSubscription(paymentMethodId);

        SubscriptionData subscriptionData = subscriptionApi.createSubscription(newSubscription);

        // when
        SubscriptionData subscriptionDataResult = subscriptionApi.getSubscription(subscriptionData.getSubscriptionId());

        // then
        assertThat(subscriptionDataResult.getSubscriptionId()).isEqualTo(subscriptionData.getSubscriptionId());
        assertThat(subscriptionDataResult.getPaymentMethodId()).isEqualTo(subscriptionData.getPaymentMethodId());
    }

    @Test
    @DisplayName("When deleteSubscription request is executed, Then return valid subscription status")
    void testDeleteSubscription() throws ApiException {
        // given
        NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                .withProviderId(TestConfigProvider.getProviderId())
                .withMetadata(METADATA)
                .build();

        PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);
        String paymentMethodId = paymentMethodWithCard.getPaymentMethodId();

        NewSubscription newSubscription = getNewSubscription(paymentMethodId);

        SubscriptionData subscriptionData = subscriptionApi.createSubscription(newSubscription);

        // when
        SubscriptionData subscriptionDataResult = subscriptionApi.getSubscription(subscriptionData.getSubscriptionId());
        SubscriptionData subscriptionDataDeleted = subscriptionApi.deleteSubscription(subscriptionData.getSubscriptionId());

        // then
        assertThat(subscriptionDataResult.getSubscriptionId()).isEqualTo(subscriptionData.getSubscriptionId());
        assertThat(subscriptionDataDeleted.getPaymentMethodId()).isEqualTo(subscriptionData.getPaymentMethodId());
        assertThat(subscriptionDataDeleted.getStatus()).isEqualTo("Cancelled");
    }
    
    @Test
    @DisplayName("When updateSubscription request is executed, Then return valid updated subscription data")
    void testUpdateSubscription() throws ApiException {
        // given
        NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                .withProviderId(TestConfigProvider.getProviderId())
                .withMetadata(METADATA)
                .build();

        PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);
        String paymentMethodId = paymentMethodWithCard.getPaymentMethodId();

        NewSubscription newSubscription = getNewSubscription(paymentMethodId);
        SubscriptionData subscriptionData = subscriptionApi.createSubscription(newSubscription);

        // when
        SubscriptionUpdate subscriptionUpdate = new SubscriptionUpdate.Builder()
            .withAmount(BigDecimal.valueOf(200))
            .withInterval(SubscriptionInterval.Month)
            .withCurrency("AUD")
            .build();
        SubscriptionData subscriptionDataUpdated = subscriptionApi.updateSubscription(subscriptionData.getSubscriptionId(), subscriptionUpdate);

        // then
        assertThat(subscriptionDataUpdated.getSubscriptionId()).isEqualTo(subscriptionData.getSubscriptionId());
        assertThat(subscriptionData.getAmount()).isEqualTo(200);
        assertThat(subscriptionData.getCurrency()).isEqualTo("AUD");
        assertThat(subscriptionData.getInterval()).isEqualTo(SubscriptionInterval.Month);
        assertThat(subscriptionData.getFrequency()).isEqualTo(1);
        assertThat(subscriptionData.getEndAfter()).isNull();
        assertThat(subscriptionData.getRetry()).isNull();
        assertThat(subscriptionData.getWebhook()).isNull();
    }

    @Test
    @DisplayName("When updateSubscriptionStatus request is executed, Then return valid subscription status")
    void testPauseSubscription() throws ApiException {
        NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                .withProviderId(TestConfigProvider.getProviderId())
                .withMetadata(METADATA)
                .build();

        PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);
        String paymentMethodId = paymentMethodWithCard.getPaymentMethodId();

        NewSubscription newSubscription = getNewSubscription(paymentMethodId);
        SubscriptionData subscriptionData = subscriptionApi.createSubscription(newSubscription);
        SubscriptionData result = subscriptionApi.updateSubscriptionStatus(subscriptionData.getSubscriptionId(), new SubscriptionUpdateStatus.Builder().withStatus("Suspended").build());

        assertThat(result.getStatus()).isEqualTo("Suspended");
    }

    @Test
    @DisplayName("When updateSubscriptionStatus request is executed, Then return valid subscription status")
    void testReactivateSubscription() throws ApiException {
        NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                .withProviderId(TestConfigProvider.getProviderId())
                .withMetadata(METADATA)
                .build();

        PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);
        String paymentMethodId = paymentMethodWithCard.getPaymentMethodId();

        NewSubscription newSubscription = getNewSubscription(paymentMethodId);
        SubscriptionData subscriptionData = subscriptionApi.createSubscription(newSubscription);
        subscriptionApi.updateSubscriptionStatus(subscriptionData.getSubscriptionId(), new SubscriptionUpdateStatus.Builder().withStatus("Suspended").build());
        SubscriptionData result = subscriptionApi.updateSubscriptionStatus(subscriptionData.getSubscriptionId(), new SubscriptionUpdateStatus.Builder().withStatus("Active").build());

        assertThat(result.getStatus()).isEqualTo("Active");
    }

    private NewSubscription getNewSubscription(String paymentMethodId) {
        SubscriptionEnd subscriptionEnd = new SubscriptionEnd.Builder()
                .withCount(2)
                .build();

        SubscriptionRetryPolicy subscriptionRetryPolicy = new SubscriptionRetryPolicy.Builder()
                .withMaximum(3)
                .withFrequency(1)
                .withInterval(SubscriptionRetryInterval.Day)
                .build();

        WebhookConfig webhookConfig = new WebhookConfig.Builder()
                .withUrl("https://example.com/webhoo")
                .withAuthorization("secret")
                .build();

        return new NewSubscription.Builder()
                .withPaymentMethodId(paymentMethodId)
                .withAmount(BigDecimal.valueOf(100))
                .withCurrency("USD")
                .withInterval(SubscriptionInterval.Month)
                .withFrequency(1)
                .withEndAfter(subscriptionEnd)
                .withRetry(subscriptionRetryPolicy)
                .withWebhook(webhookConfig)
                .build();
    }
}
