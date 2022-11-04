package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.ChargeApi;
import com.payfurl.payfurlsdk.api.CustomerApi;
import com.payfurl.payfurlsdk.models.CardRequestInformation;
import com.payfurl.payfurlsdk.models.ChargeData;
import com.payfurl.payfurlsdk.models.ChargeList;
import com.payfurl.payfurlsdk.models.ChargeSearch;
import com.payfurl.payfurlsdk.models.CustomerData;
import com.payfurl.payfurlsdk.models.NewChargeCardLeastCost;
import com.payfurl.payfurlsdk.models.NewChargeCardRequest;
import com.payfurl.payfurlsdk.models.NewChargePaymentMethod;
import com.payfurl.payfurlsdk.models.NewChargeToken;
import com.payfurl.payfurlsdk.models.NewCustomerCard;
import com.payfurl.payfurlsdk.models.PaymentMethodData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class ChargeApiTest {
    private static final String SUCCESS_MARKER = "SUCCESS";
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/22")
            .withCcv("123")
            .build();

    private ChargeApi chargeApi;
    private CustomerApi customerApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withAccessToken(TestConfigProvider.getKeyWithFallback())
                .build();

        chargeApi = payFurlClient.getChargeApi();
        customerApi = payFurlClient.getCustomerApi();
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
            then(chargeData).isNotNull();
            then(chargeData.status).isEqualTo(SUCCESS_MARKER);
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
            then(chargeData).isNotNull();
            then(chargeData.status).isEqualTo(SUCCESS_MARKER);
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
            then(chargeList.getSkip()).isEqualTo(0);
        }

        @Test
        @DisplayName("When createWitPaymentMethod request is executed, Then return valid charge data")
        void testCreateWitPaymentMethod() throws IOException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData createdCustomer = customerApi.createWithCard(newCustomerCard);
            List<PaymentMethodData> paymentMethods = customerApi.getPaymentMethods(createdCustomer.getCustomerId());

            NewChargePaymentMethod newChargePaymentMethod = new NewChargePaymentMethod.Builder()
                    .withAmount(BigDecimal.valueOf(5))
                    .withPaymentMethodId(paymentMethods.get(0).getPaymentMethodId())
                    .build();

            ChargeData chargeDataWithPaymentMethod = chargeApi.createWitPaymentMethod(newChargePaymentMethod);

            // then
            then(chargeDataWithPaymentMethod.status).isEqualTo(SUCCESS_MARKER);
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
            then(chargeData).isNotNull();
            then(chargeData.status).isEqualTo(SUCCESS_MARKER);
        }
    }
}
