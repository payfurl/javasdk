package com.payfurl.payfurlsdk.apitesting;

import com.google.common.collect.ImmutableMap;
import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.CustomerApi;
import com.payfurl.payfurlsdk.api.PaymentMethodApi;
import com.payfurl.payfurlsdk.models.CardRequestInformation;
import com.payfurl.payfurlsdk.models.Checkout;
import com.payfurl.payfurlsdk.models.CustomerData;
import com.payfurl.payfurlsdk.models.NewCheckout;
import com.payfurl.payfurlsdk.models.NewCustomerCard;
import com.payfurl.payfurlsdk.models.NewPaymentMethodCard;
import com.payfurl.payfurlsdk.models.NewPaymentMethodVault;
import com.payfurl.payfurlsdk.models.PaymentMethodData;
import com.payfurl.payfurlsdk.models.PaymentMethodList;
import com.payfurl.payfurlsdk.models.PaymentMethodSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.then;

public class PaymentMethodApiTest {
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .build();

    private PaymentMethodApi paymentMethodApi;
    private CustomerApi customerApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withAccessToken(TestConfigProvider.getKeyWithFallback())
                .build();

        paymentMethodApi = payFurlClient.getPaymentMethodApi();
        customerApi = payFurlClient.getCustomerApi();
    }

    @Nested
    @DisplayName("Given valid request to PaymentMethod API")
    class SuccessFlow {
        @Test
        @DisplayName("When checkout request is executed, Then return valid Checkout")
        void testCheckout() throws IOException {
            // given
            NewCheckout newCheckout = new NewCheckout.Builder()
                    .withProviderId("1cf5deda-28cc-4214-adb5-1e597a37228c")
                    .withAmount(BigDecimal.valueOf(123))
                    .withCurrency("AUD")
                    .withReference("123123123")
                    .withOptions(ImmutableMap.of("HideShipping", "true"))
                    .build();

            // when
            Checkout checkout = paymentMethodApi.checkout(newCheckout);

            // then
            then(checkout.getCheckoutId()).isNotNull();
            then(checkout.getTransactionId()).isNotNull();
        }

        @Test
        @DisplayName("When createPaymentMethodWithCard request is executed, Then return valid PaymentMethodData")
        void testCreatePaymentMethodWithCard() throws IOException {
            // given
            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .build();
            // when
            PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);

            // then
            then(paymentMethodWithCard.getPaymentMethodId()).isNotNull();
        }

        @Test
        @DisplayName("When single request is executed, Then return valid PaymentMethodData")
        void testSingle() throws IOException {
            // given
            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();
            // when
            PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);
            PaymentMethodData paymentMethodData = paymentMethodApi.single(paymentMethodWithCard.getPaymentMethodId());

            // then
            then(paymentMethodData.getPaymentMethodId()).isEqualTo(paymentMethodWithCard.getPaymentMethodId());
        }

        @Test
        @DisplayName("When single request is executed, Then return valid PaymentMethodData")
        void testDeletePaymentMethod() throws IOException {
            // given
            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();
            // when
            PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);

            PaymentMethodData deletePaymentMethod = paymentMethodApi.deletePaymentMethod(paymentMethodWithCard.getPaymentMethodId());

            PaymentMethodData paymentMethodData = paymentMethodApi.single(paymentMethodWithCard.getPaymentMethodId());

            // then
            then(paymentMethodData.getPaymentMethodId()).isEqualTo(paymentMethodWithCard.getPaymentMethodId());
            then(deletePaymentMethod.getPaymentMethodId()).isEqualTo(paymentMethodWithCard.getPaymentMethodId());
            then(paymentMethodData.getEmail()).isEqualTo("deleted");
        }

        @Test
        @DisplayName("When createPaymentMethodWithVault request is executed, Then return valid PaymentMethodData")
        void testCreatePaymentMethodWithVault() throws IOException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withVaultCard(true)
                    .build();

            // when
            CustomerData customer = customerApi.createWithCard(newCustomerCard);

            NewPaymentMethodVault newPaymentMethodVault = new NewPaymentMethodVault.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentMethodId(customer.getDefaultPaymentMethod().getPaymentMethodId())
                    .withVaultId(customer.getDefaultPaymentMethod().getVaultId())
                    .build();
            PaymentMethodData paymentMethodWithVault = paymentMethodApi.createPaymentMethodWithVault(newPaymentMethodVault);

            // then
            then(paymentMethodWithVault.getPaymentMethodId()).isNotNull();
        }

        @Test
        @DisplayName("When Search request is executed, Then return valid PaymentMethodList")
        void testSearch() throws IOException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withVaultCard(true)
                    .build();

            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);
            PaymentMethodList paymentMethodList = paymentMethodApi.search(new PaymentMethodSearch.Builder()
                    .withProviderId(paymentMethodWithCard.getProviderId())
                    .build());

            // then
            then(paymentMethodList.getCount()).isGreaterThan(0);
        }
    }
}
