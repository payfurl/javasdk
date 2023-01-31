package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.ChargeApi;
import com.payfurl.payfurlsdk.api.CustomerApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.api.support.ErrorCode;
import com.payfurl.payfurlsdk.models.Address;
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
import com.payfurl.payfurlsdk.models.Order;
import com.payfurl.payfurlsdk.models.PaymentMethodData;
import com.payfurl.payfurlsdk.models.ProductItem;
import com.payfurl.payfurlsdk.models.TransactionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class ChargeApiTest {
    private static final TransactionStatus SUCCESS_MARKER = TransactionStatus.SUCCESS;
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .build();

    private static final CardRequestInformation SAMPLE_FAILED_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4000000000000000")
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
    private static final List<ProductItem> Items = Arrays.asList(new ProductItem.Builder()
                    .withAmount(BigDecimal.valueOf(123))
                    .withDescription("First item")
                    .withQuantity(Integer.valueOf(1))
                    .withCommodityCode("asdf")
                    .withProductCode("PC1234")
                    .withUnitOfMeasure("kg")
                    .build(),
            new ProductItem.Builder()
                    .withAmount(BigDecimal.valueOf(33))
                    .withDescription("Second item")
                    .withQuantity(Integer.valueOf(4))
                    .withCommodityCode("uuuu")
                    .withProductCode("PC15678")
                    .withUnitOfMeasure("kg")
                    .build());
    private static final Order SAMPLE_ORER = new Order.Builder()
            .withOrderNumber("12345ON")
            .withDutyAmount(BigDecimal.valueOf(1))
            .withFreightAmount(BigDecimal.valueOf(2))
            .withItems(Items)
            .build();

    private ChargeApi chargeApi;
    private CustomerApi customerApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        chargeApi = payFurlClient.getChargeApi();
        customerApi = payFurlClient.getCustomerApi();
    }

    @Nested
    @DisplayName("Given valid request to charge API")
    class SuccessFlow {
        @Test
        @DisplayName("When createWithCard request is executed, Then return valid charge data")
        void testCreateWithCardMethod() throws ApiException {
            // given
            NewChargeCardRequest newChargeCardRequest = new NewChargeCardRequest.Builder()
                    .withAmount(BigDecimal.valueOf(20))
                    .withCurrency("USD")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withOrder(SAMPLE_ORER)
                    .build();

            // when
            ChargeData chargeData = chargeApi.createWithCard(newChargeCardRequest);

            // then
            then(chargeData).isNotNull();
            then(chargeData.status).isEqualTo(SUCCESS_MARKER);
        }

        @Test
        @DisplayName("When createWithCard request is executed with failed credit card, Then throw ApiException")
        void testCreateWithCardMethodThrowApiException() throws ApiException {
            // given
            NewChargeCardRequest newChargeCardRequest = new NewChargeCardRequest.Builder()
                    .withAmount(BigDecimal.valueOf(20))
                    .withCurrency("USD")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_FAILED_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withOrder(SAMPLE_ORER)
                    .build();

            ApiException exception = null;
            // when
            try {
                ChargeData chargeData = chargeApi.createWithCard(newChargeCardRequest);
            } catch (ApiException apiException) {
                exception = apiException;
            }

            // then
            then(exception).isNotNull();
            then(exception.getCode()).isEqualTo(ErrorCode.InvalidCardNumber);
            then(exception.getMessage()).isEqualTo("Invalid Card Number");
            then(exception.getResource()).isEqualTo("/charge/card");
            then(exception.isRetryable()).isEqualTo(false);
            then(exception.getType()).isEqualTo("https://docs.payfurl.com/errorcodes.html#5");
            then(exception.getHttpCode()).isEqualTo(400);
        }

        @Test
        @DisplayName("When createWithCardLeastCost request is executed, Then return valid charge data")
        void testCreateWithCardLeastCost() throws ApiException {
            // given
            NewChargeCardLeastCost newChargeCardLeastCost = new NewChargeCardLeastCost.Builder()
                    .withAmount(BigDecimal.valueOf(20))
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withOrder(SAMPLE_ORER)
                    .withAddress(SAMPLE_ADDRESS)
                    .build();

            // when
            ChargeData chargeData = chargeApi.createWithCardLeastCost(newChargeCardLeastCost);

            // then
            then(chargeData).isNotNull();
            then(chargeData.status).isEqualTo(SUCCESS_MARKER);
        }

        @Test
        @DisplayName("When Search request is executed, Then return valid charge data list")
        void testSearch() throws ApiException {
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
        void testCreateWithPaymentMethod() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .build();

            // when
            CustomerData createdCustomer = customerApi.createWithCard(newCustomerCard);
            List<PaymentMethodData> paymentMethods = customerApi.getPaymentMethods(createdCustomer.getCustomerId());

            NewChargePaymentMethod newChargePaymentMethod = new NewChargePaymentMethod.Builder()
                    .withAmount(BigDecimal.valueOf(5))
                    .withPaymentMethodId(paymentMethods.get(0).getPaymentMethodId())
                    .build();

            ChargeData chargeDataWithPaymentMethod = chargeApi.createWithPaymentMethod(newChargePaymentMethod);

            // then
            then(chargeDataWithPaymentMethod.status).isEqualTo(SUCCESS_MARKER);
        }

        @Test
        @Disabled("Tokens expire, so this test needs to be adjusted each time it's run")
        @DisplayName("When createWithToken request is executed, Then return valid charge data")
        void testCreateWithToken() throws ApiException {
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
