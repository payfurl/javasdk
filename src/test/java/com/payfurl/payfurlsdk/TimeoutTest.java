package com.payfurl.payfurlsdk;

import com.google.common.collect.ImmutableMap;
import com.payfurl.payfurlsdk.api.ChargeApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.api.support.ErrorCode;
import com.payfurl.payfurlsdk.models.Address;
import com.payfurl.payfurlsdk.models.ApiError;
import com.payfurl.payfurlsdk.models.CardRequestInformation;
import com.payfurl.payfurlsdk.models.NewChargeCardLeastCost;
import com.payfurl.payfurlsdk.models.Order;
import com.payfurl.payfurlsdk.models.ProductItem;
import com.payfurl.payfurlsdk.models.TransactionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

public class TimeoutTest {
    private static final ImmutableMap<String, String> METADATA = ImmutableMap.of("merchant_id", "1234356");
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .build();

    private static final Address SAMPLE_ADDRESS = new Address.Builder()
            .withLine1("91  Gloucester Avenue")
            .withLine2("Apartment 2")
            .withCity("Melbourne")
            .withSate("Victoria")
            .withPostalCode("5006")
            .withCountry("Australia")
            .build();

    private static final List<ProductItem> ITEMS = Arrays.asList(new ProductItem.Builder()
                    .withAmount(BigDecimal.valueOf(123))
                    .withDescription("First item")
                    .withQuantity(1)
                    .withCommodityCode("asdf")
                    .withProductCode("PC1234")
                    .withUnitOfMeasure("kg")
                    .build(),
            new ProductItem.Builder()
                    .withAmount(BigDecimal.valueOf(33))
                    .withDescription("Second item")
                    .withQuantity(4)
                    .withCommodityCode("uuuu")
                    .withProductCode("PC15678")
                    .withUnitOfMeasure("kg")
                    .build());

    private static final Order SAMPLE_ORDER = new Order.Builder()
            .withOrderNumber("12345ON")
            .withDutyAmount(BigDecimal.valueOf(1))
            .withFreightAmount(BigDecimal.valueOf(2))
            .withItems(ITEMS)
            .build();

    @Test
    @DisplayName("Given PayFurlClient configuration When create OkHttClient is called Then return custom default client")
    void testDefaultTimeoutConfiguration() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .build();

        then(payFurlClient.getTimeout()).isEqualTo(TimeUnit.SECONDS.toMillis(60));
    }

    @Test
    @DisplayName("Given PayFurlClient configuration When create OkHttClient is called Then return custom timeout client")
    void testCustomTimeoutConfiguration() {
        long smallTimeoutMs = 40;
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withHttpClientConfiguration((config) -> config.timeout(smallTimeoutMs))
                .build();

        then(payFurlClient.getTimeout()).isEqualTo(smallTimeoutMs);
    }

    @Nested
    class FailFlow {

        @Test
        @DisplayName("Given PayFurlClient configuration with small timeout When create API is called Then throw client's timeout error")
        void testSmallTimeoutConfigurationToCauseException() {
            // given
            PayFurlClient lowTimeoutPayFurlClient = new PayFurlClient.Builder()
                    .withHttpClientConfiguration((config) -> config.timeout(10))
                    .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                    .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                    .build();

            ChargeApi lowTimeoutChargeApi = lowTimeoutPayFurlClient.getChargeApi();

            NewChargeCardLeastCost newChargeCardLeastCost = new NewChargeCardLeastCost.Builder()
                    .withAmount(BigDecimal.valueOf(258))
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withOrder(SAMPLE_ORDER)
                    .withAddress(SAMPLE_ADDRESS)
                    .withMetadata(METADATA)
                    .build();

            // when
            Throwable throwable = catchThrowable(() -> lowTimeoutChargeApi.createWithCardLeastCost(newChargeCardLeastCost));

            // then
            then(throwable).isInstanceOf(ApiException.class)
                    .usingRecursiveComparison()
                    .isEqualTo(new ApiException(ApiError.buildTimeoutError()));
        }
    }
}
