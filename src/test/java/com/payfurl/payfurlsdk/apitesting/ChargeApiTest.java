package com.payfurl.payfurlsdk.apitesting;

import com.google.common.collect.ImmutableMap;
import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.ChargeApi;
import com.payfurl.payfurlsdk.api.CustomerApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.api.support.ErrorCode;
import com.payfurl.payfurlsdk.api.support.ErrorLink;
import com.payfurl.payfurlsdk.models.Address;
import com.payfurl.payfurlsdk.models.ApiError;
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
import com.payfurl.payfurlsdk.models.WebhookConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

public class ChargeApiTest {
    private static final TransactionStatus SUCCESS_MARKER = TransactionStatus.SUCCESS;
    private static final TransactionStatus PENDING_MARKER = TransactionStatus.PENDING;
    private static final ImmutableMap<String, String> METADATA = ImmutableMap.of("merchant_id", "1234356");
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .build();
    
    private static final BankPaymentRequestInformation SAMPLE_BANK_PAYMENT_INFORMATION = new BankPaymentRequestInformation.Builder()
            .withBankCode("123-456")
            .withAccountNumber("123456")
            .withAccountName("Bank Account")
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
    private static final String HTML_ENDING = ".html";

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
                    .withAmount(BigDecimal.valueOf(258))
                    .withCurrency("USD")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withOrder(SAMPLE_ORDER)
                    .withMetadata(METADATA)
                    .build();

            // when
            ChargeData chargeData = chargeApi.createWithCard(newChargeCardRequest);

            // then
            then(chargeData).isNotNull();
            then(chargeData.status).isEqualTo(SUCCESS_MARKER);
        }

        @Test
        @DisplayName("When createWithCard and with webhook request is executed, Then return valid charge data")
        void testCreateWithCardMethodWithWebhook() throws ApiException {
            // given
            NewChargeCardRequest newChargeCardRequest = new NewChargeCardRequest.Builder()
                    .withAmount(BigDecimal.valueOf(258))
                    .withCurrency("USD")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withOrder(SAMPLE_ORDER)
                    .withWebhook(new WebhookConfig.Builder()
                            .withUrl("https://webhook.site/1da8cac9-fef5-47bf-a276-81856f73d7ca")
                            .withAuthorization("Basic user:password")
                            .build())
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
                    .withAmount(BigDecimal.valueOf(258))
                    .withCurrency("USD")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_FAILED_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withOrder(SAMPLE_ORDER)
                    .build();

            Throwable throwable = catchThrowable(() -> chargeApi.createWithCard(newChargeCardRequest));

            // then

            BiPredicate<String, String> typeComparator = (actual, expected) ->
                    actual.replace(HTML_ENDING, "")
                            .equals(expected.replace(HTML_ENDING, ""));

            then(throwable).isInstanceOf(ApiException.class)
                    .usingRecursiveComparison()
                    .withEqualsForFields(typeComparator, "type")
                    .isEqualTo(new ApiException(new ApiError.Builder()
                            .withCode(ErrorCode.InvalidCardNumber)
                            .withMessage("Invalid Card Number")
                            .withResource("/charge/card")
                            .withIsRetryable(false)
                            .withType(ErrorLink.buildWith(5))
                            .withHttpCode(400)
                            .build()));
        }

        @Test
        @DisplayName("When createWithCardLeastCost request is executed, Then return valid charge data")
        void testCreateWithCardLeastCost() throws ApiException {
            // given
            NewChargeCardLeastCost newChargeCardLeastCost = new NewChargeCardLeastCost.Builder()
                    .withAmount(BigDecimal.valueOf(258))
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withOrder(SAMPLE_ORDER)
                    .withAddress(SAMPLE_ADDRESS)
                    .withMetadata(METADATA)
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
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .build();

            // when
            CustomerData createdCustomer = customerApi.createWithCard(newCustomerCard);
            List<PaymentMethodData> paymentMethods = customerApi.getPaymentMethods(createdCustomer.getCustomerId());

            NewChargePaymentMethod newChargePaymentMethod = new NewChargePaymentMethod.Builder()
                    .withAmount(BigDecimal.valueOf(5))
                    .withPaymentMethodId(paymentMethods.get(0).getPaymentMethodId())
                    .withMetadata(METADATA)
                    .build();

            ChargeData chargeDataWithPaymentMethod = chargeApi.createWithPaymentMethod(newChargePaymentMethod);

            // then
            then(chargeDataWithPaymentMethod.status).isEqualTo(SUCCESS_MARKER);
        }

        @Test
        @DisplayName("When createWithToken request is executed, Then return valid charge data")
        void testCreateWithToken() throws ApiException {
            // given
            NewChargeToken newChargeToken = new NewChargeToken.Builder()
                    .withAmount(BigDecimal.valueOf(20))
                    .withToken(TestConfigProvider.getToken())
                    .build();

            // when
            ChargeData chargeData = chargeApi.createWithToken(newChargeToken);

            // then
            then(chargeData).isNotNull();
            then(chargeData.status).isEqualTo(SUCCESS_MARKER);
        }
        
        @Test
        @DisplayName("When createWithBankAccount request is executed, Then return valid charge data")
        void testCreateWithBankAccount() throws ApiException {
            // given
            NewChargeBankPaymentRequest newChargeBankPaymentRequest = new NewChargeBankPaymentRequest.Builder()
                    .withAmount(BigDecimal.valueOf(258))
                    .withCurrency("USD")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withBankPaymentInformation(SAMPLE_BANK_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withOrder(SAMPLE_ORDER)
                    .withMetadata(METADATA)
                    .build();

            // when
            ChargeData chargeData = chargeApi.createWithBankAccount(newChargeBankPaymentRequest);

            // then
            then(chargeData).isNotNull();
            then(chargeData.status).isEqualTo(PENDING_MARKER);
        }
    }
}
