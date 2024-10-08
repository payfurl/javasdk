package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.CustomerApi;
import com.payfurl.payfurlsdk.api.PaymentMethodApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.BDDAssertions.then;

public class PaymentMethodApiTest {

    private static final HashMap<String, String> METADATA = new HashMap<String, String>() {{ put("merchant_id", "1234356"); }};
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .build();

    private static final BankPaymentInformation SAMPLE_BANK_PAYMENT_INFORMATION = new BankPaymentInformation.Builder()
            .withBankCode("123-456")
            .withAccountNumber("123456")
            .withAccountName("Bank Account")
            .build();

    private static final UpdatePaymentMethodCardRequestInformation SAMPLE_UPDATE_PAYMENT_METHOD_CARD_REQUEST_INFORMATION = new UpdatePaymentMethodCardRequestInformation.Builder()
            .withExpiryDate("01/31")
            .withCardholder("UpdatedCardholder")
            .build();

    private static final UpdatePaymentMethod SAMPLE_UPDATE_PAYMENT_METHOD = new UpdatePaymentMethod.Builder()
            .withCard(SAMPLE_UPDATE_PAYMENT_METHOD_CARD_REQUEST_INFORMATION)
            .build();

    private PaymentMethodApi paymentMethodApi;
    private CustomerApi customerApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        paymentMethodApi = payFurlClient.getPaymentMethodApi();
        customerApi = payFurlClient.getCustomerApi();
    }

    @Nested
    @DisplayName("Given valid request to PaymentMethod API")
    class SuccessFlow {
        @Test
        @DisplayName("When createPaymentMethodWithCard request is executed, Then return valid PaymentMethodData")
        void testCreatePaymentMethodWithCard() throws ApiException {
            // given
            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withMetadata(METADATA)
                    .build();
            // when
            PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);

            // then
            then(paymentMethodWithCard.getPaymentMethodId()).isNotNull();
        }

        @Test
        @DisplayName("When single request is executed, Then return valid PaymentMethodData")
        void testSingle() throws ApiException {
            // given
            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
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
        void testDeletePaymentMethod() throws ApiException {
            // given
            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
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
        void testCreatePaymentMethodWithVault() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withVaultCard(true)
                    .build();

            // when
            CustomerData customer = customerApi.createWithCard(newCustomerCard);

            NewPaymentMethodVault newPaymentMethodVault = new NewPaymentMethodVault.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentMethodId(customer.getDefaultPaymentMethod().getPaymentMethodId())
                    .build();
            PaymentMethodData paymentMethodWithVault = paymentMethodApi.createPaymentMethodWithVault(newPaymentMethodVault);

            // then
            then(paymentMethodWithVault.getPaymentMethodId()).isNotNull();
        }

        @Test
        @DisplayName("When Search request is executed, Then return valid PaymentMethodList")
        void testSearch() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withVaultCard(true)
                    .build();

            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
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

        @Test
        @DisplayName("When createPaymentMethodWithProviderToken request is executed, Then return valid PaymentMethodData")
        void testCreatePaymentMethodWithProviderToken() throws ApiException {
            // given
            NewProviderToken newProviderToken = new NewProviderToken.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withProviderToken("sampleProviderToken")
                    .build();

            // when
            PaymentMethodData paymentMethodWithProviderToken = paymentMethodApi.createPaymentMethodWithProviderToken(newProviderToken);

            // then
            then(paymentMethodWithProviderToken.getPaymentMethodId()).isNotNull();
        }

        @Test
        @DisplayName("When createPaymentMethodWithBankAccount request is executed, Then return valid PaymentMethodData")
        void testCreatePaymentMethodWithBankAccount() throws ApiException {
            // given
            NewPaymentMethodBankPayment newPaymentMethodBankPayment = new NewPaymentMethodBankPayment.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withBankPaymentInformation(SAMPLE_BANK_PAYMENT_INFORMATION)
                    .withMetadata(METADATA)
                    .build();
            // when
            PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithBankAccount(newPaymentMethodBankPayment);

            // then
            then(paymentMethodWithCard.getPaymentMethodId()).isNotNull();
        }

        @Test
        @DisplayName("When update payment method, Then return valid PaymentMethodData")
        void testUpdatePaymentMethod() throws ApiException {
            // given
            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withMetadata(METADATA)
                    .build();
            // when
            PaymentMethodData paymentMethodWithCard = paymentMethodApi.createPaymentMethodWithCard(newPaymentMethodCard);

            // then
            then(paymentMethodWithCard.getPaymentMethodId()).isNotNull();

            // give

            // when
            PaymentMethodData updatedPaymentMethod = paymentMethodApi.updatePaymentMethod(paymentMethodWithCard.getPaymentMethodId(), SAMPLE_UPDATE_PAYMENT_METHOD);

            // then
            then(updatedPaymentMethod.getPaymentMethodId()).isNotNull();
            then(updatedPaymentMethod.getPaymentMethodId()).isEqualTo(paymentMethodWithCard.getPaymentMethodId());
            then(updatedPaymentMethod.getCard().getExpiryDate()).isEqualTo(SAMPLE_UPDATE_PAYMENT_METHOD_CARD_REQUEST_INFORMATION.getExpiryDate());
            then(updatedPaymentMethod.getCard().getCardholder()).isEqualTo(SAMPLE_UPDATE_PAYMENT_METHOD_CARD_REQUEST_INFORMATION.getCardholder());
        }

        @Test
        @DisplayName("When createPaymentMethodWithToken request is executed, Then return valid PaymentMethodData")
        void testCreatePaymentMethodWithToken() throws ApiException {
            // given
            NewPaymentMethodToken newPaymentMethodToken = new NewPaymentMethodToken.Builder()
                    .withToken(TestConfigProvider.getToken())
                    .build();
            // when
            PaymentMethodData paymentMethodWithToken = paymentMethodApi.createPaymentMethodWithToken(newPaymentMethodToken);

            // then
            then(paymentMethodWithToken.getPaymentMethodId()).isNotNull();
        }
    }
}
