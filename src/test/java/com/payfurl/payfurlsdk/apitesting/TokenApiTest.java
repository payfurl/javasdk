package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.TokenApi;
import com.payfurl.payfurlsdk.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.then;

public class TokenApiTest {
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .withCardHolder("James Mason")
            .build();

    private TokenApi tokenApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .withPublicKey(TestConfigProvider.getPublicKeyWithFallback())
                .build();

        tokenApi = payFurlClient.getTokenApi();

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
            PaymentTokenData tokenData = tokenApi.createWithCard(tokenCardRequest);

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
            PaymentTokenData tokenData = tokenApi.createWithCardLeastCost(tokenCardRequest);

            // then
            then(tokenData.getTokenId()).isNotNull();
        }



        @Test
        @DisplayName("Get token by ID")
        void getTokenById() throws IOException {
            // given
            NewTokenCardRequest tokenCardRequest = new NewTokenCardRequest.Builder()
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            PaymentTokenData tokenData = tokenApi.createWithCard(tokenCardRequest);

            String tokenID = tokenData.getTokenId();

            // when
            TokenData singleToken = tokenApi.single(tokenID);

            // then
            then(singleToken.getId()).isNotNull();
            then(singleToken.getId()).isEqualTo(tokenID);
        }

        @Test
        @DisplayName("When Search request is executed, Then return valid TokenList")
        void testSearch() throws IOException {
            // given
            NewTokenCardRequest tokenCardRequest = new NewTokenCardRequest.Builder()
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            PaymentTokenData paymentTokenData = tokenApi.createWithCard(tokenCardRequest);
            TokenList tokenList = tokenApi.search(new TokenSearch.Builder()
                    .withProviderId(paymentTokenData.getProviderId())
                    .build());

            // then
            then(tokenList.getCount()).isGreaterThan(0);
        }
    }
}
