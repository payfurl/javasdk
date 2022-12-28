package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.ChargeApi;
import com.payfurl.payfurlsdk.api.CustomerApi;
import com.payfurl.payfurlsdk.api.PaymentMethodApi;
import com.payfurl.payfurlsdk.api.TokenApi;
import com.payfurl.payfurlsdk.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

public class TokenApiTest {
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .withCardHolder("James Mason")
            .build();

    private PaymentMethodApi paymentMethodApi;
    private CustomerApi customerApi;
    private TokenApi tokenApi;

    private ChargeApi chargeApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .withPublicKey(TestConfigProvider.getPublicKeyWithFallback())
                .build();

        paymentMethodApi = payFurlClient.getPaymentMethodApi();
        customerApi = payFurlClient.getCustomerApi();
        tokenApi = payFurlClient.getTokenApi();
        chargeApi = payFurlClient.getChargeApi();

    }

    @Nested
    @DisplayName("Given valid request to Token API")
    class SuccessFlow {
        @Test
        @DisplayName("When create token card request is executed, Then return valid Token")
        void createNewToken() throws IOException {
            // given
            NewTokenCardRequest tokenCardRequest = new NewTokenCardRequest.Builder()
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            PaymentTokenData tokenData = tokenApi.create(tokenCardRequest);

            // then
            then(tokenData.getTokenId()).isNotNull();
        }

        @Test
        @DisplayName("When create token card with list cost request is executed, Then return valid Token")
        void createNewTokenWithLeastCost() throws IOException {
            // given
            NewTokenCardLeastCostRequest tokenCardRequest = new NewTokenCardLeastCostRequest.Builder()
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAmount(BigDecimal.valueOf(20))
                    .withCurrency("USD")
                    .build();

            // when
            PaymentTokenData tokenData = tokenApi.createLeastCost(tokenCardRequest);

            // then
            then(tokenData.getTokenId()).isNotNull();
        }

        @Test
        @DisplayName("Get token by reference ID")
        void getTokenByReferenceId() throws IOException {
            // given
            NewTokenCardRequest tokenCardRequest = new NewTokenCardRequest.Builder()
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            PaymentTokenData tokenData = tokenApi.create(tokenCardRequest);

            NewChargeToken newChargeToken = new NewChargeToken.Builder()
                    .withAmount(BigDecimal.valueOf(20))
                    .withToken(tokenData.getTokenId())
                    .build();

            ChargeData chargeWithToken = chargeApi.createWithToken(newChargeToken);

            String referenceId = chargeWithToken.getProviderChargeId();

            // when
            PaymentTokenData tokenDataByReferenceId = tokenApi.getByReferenceId(referenceId);

            // then
            then(tokenDataByReferenceId.getTokenId()).isNotNull();
        }

        @Test
        @DisplayName("Get token by ID")
        void getTokenById() throws IOException {
            // given
            NewTokenCardRequest tokenCardRequest = new NewTokenCardRequest.Builder()
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            PaymentTokenData tokenData = tokenApi.create(tokenCardRequest);

            String tokenID = tokenData.getTokenId();

            // when
            TokenData singleToken = tokenApi.single(tokenID);

            // then
            then(singleToken.getId()).isNotNull();
            then(singleToken.getId()).isEqualTo(tokenID);
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
