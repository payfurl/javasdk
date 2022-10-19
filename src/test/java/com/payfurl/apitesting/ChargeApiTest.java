package com.payfurl.apitesting;

import com.payfurl.PayFurlClient;
import com.payfurl.api.ChargeApi;
import com.payfurl.models.ChargeList;
import com.payfurl.models.ChargeSearch;
import com.payfurl.http.client.config.Environment;
import com.payfurl.models.CardRequestInformation;
import com.payfurl.models.ChargeData;
import com.payfurl.models.NewChargeCardLeastCost;
import com.payfurl.models.NewChargeCardRequest;
import com.payfurl.models.NewChargeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ChargeApiTest {
    private static final String LOCAL_ACCESS_TEST_TOKEN = "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    private static final String SUCCESS_MARKER = "SUCCESS";
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/22")
            .withCcv("123")
            .build();

    private ChargeApi chargeApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(Environment.LOCAL)
                .withAccessToken(LOCAL_ACCESS_TEST_TOKEN)
                .build();

        chargeApi = payFurlClient.getChargeApi();
    }

    @Nested
    @DisplayName("Given valid request to charge API")
    class SuccessFlow {
        @Test
        @DisplayName("When createWithCard request is executed, Then return valid charge data")
        void testCreateWithCardMethod() throws IOException {
            // given
            NewChargeCardRequest newChargeCardRequest = new NewChargeCardRequest.Builder()
                    .withAmount(BigDecimal.valueOf(20))
                    .withCurrency("USD")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            ChargeData chargeData = chargeApi.createWithCard(newChargeCardRequest);

            // then
            assertThat(chargeData).isNotNull();
            assertThat(chargeData.status).isEqualTo(SUCCESS_MARKER);
        }

        @Test
        @DisplayName("When createWithCardLeastCost request is executed, Then return valid charge data")
        void testCreateWithCardLeastCost() throws IOException {
            // given
            NewChargeCardLeastCost newChargeCardLeastCost = new NewChargeCardLeastCost.Builder()
                    .withAmount(BigDecimal.valueOf(20))
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            ChargeData chargeData = chargeApi.createWithCardLeastCost(newChargeCardLeastCost);

            // then
            assertThat(chargeData).isNotNull();
            assertThat(chargeData.status).isEqualTo(SUCCESS_MARKER);
        }

        @Test
        @DisplayName("When Search request is executed, Then return valid charge data list")
        void testSearch() throws IOException {
            // given
            ChargeSearch chargeSearch = new ChargeSearch.Builder()
                    .build();

            // when
            ChargeList chargeList = chargeApi.search(chargeSearch);

            // then
            assertThat(chargeList.getSkip()).isEqualTo(0);
        }

        @Test
        @Disabled("Tokens expire, so this test needs to be adjusted each time it's run")
        @DisplayName("When createWithToken request is executed, Then return valid charge data")
        void testCreateWithToken() throws IOException {
            // given
            NewChargeToken newChargeToken = new NewChargeToken.Builder()
                    .withAmount(BigDecimal.valueOf(20))
                    .withToken("5dc5cfbaec7c4d057cb00482")
                    .build();

            // when
            ChargeData chargeData = chargeApi.createWithToken(newChargeToken);

            // then
            assertThat(chargeData).isNotNull();
            assertThat(chargeData.status).isEqualTo(SUCCESS_MARKER);
        }
    }
}
