package com.payfurl.apitesting;

import com.payfurl.PayFurlClient;
import com.payfurl.api.ChargeApi;
import com.payfurl.http.client.config.Environment;
import com.payfurl.models.CardRequestInformation;
import com.payfurl.models.ChargeData;
import com.payfurl.models.NewChargeCardRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    private ChargeApi chargeApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(Environment.LOCAL)
                .withAccessToken(LOCAL_ACCESS_TEST_TOKEN)
                .build();

        chargeApi = payFurlClient.getChargeApi();
    }

    @Test
    @DisplayName("Given Valid request to Charge card API, When request is executed, Then return valid charge data")
    void testPayFurlChargeApiCreateWithCardMethodWithSuccessFlow() throws IOException {
        // given
        CardRequestInformation paymentInformation = new CardRequestInformation.Builder()
                .withCardNumber("4111111111111111")
                .withExpiryDate("12/22")
                .withCcv("123")
                .build();
        NewChargeCardRequest newChargeCardRequest = new NewChargeCardRequest.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withCurrency("USD")
                .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                .withPaymentInformation(paymentInformation)
                .build();

        // when
        ChargeData chargeData = chargeApi.createWithCard(newChargeCardRequest);

        // then
        assertThat(chargeData).isNotNull();
        assertThat(chargeData.status).isEqualTo(SUCCESS_MARKER);
    }
}
