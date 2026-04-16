package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.auth.SecretKeyAuthHandler;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.http.client.support.Headers;
import com.payfurl.payfurlsdk.http.client.support.request.HttpBodyRequest;
import com.payfurl.payfurlsdk.http.client.support.request.HttpMethod;
import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;
import com.payfurl.payfurlsdk.http.client.support.response.HttpResponse;
import com.payfurl.payfurlsdk.http.client.support.response.HttpStringResponse;
import com.payfurl.payfurlsdk.models.Address;
import com.payfurl.payfurlsdk.models.BankPaymentInformation;
import com.payfurl.payfurlsdk.models.ChargeSearch;
import com.payfurl.payfurlsdk.models.CustomerSearch;
import com.payfurl.payfurlsdk.models.NewChargeApplePay;
import com.payfurl.payfurlsdk.models.NewChargeNetworkToken;
import com.payfurl.payfurlsdk.models.NewCustomerPayTo;
import com.payfurl.payfurlsdk.models.NewPayToAgreement;
import com.payfurl.payfurlsdk.models.NewPaymentMethodBankPayment;
import com.payfurl.payfurlsdk.models.NewPaymentMethodToken;
import com.payfurl.payfurlsdk.models.NewRefund;
import com.payfurl.payfurlsdk.models.PayIdDetails;
import com.payfurl.payfurlsdk.models.PaymentMethodSearch;
import com.payfurl.payfurlsdk.models.WebhookConfig;
import com.payfurl.payfurlsdk.models.WebhookSubscriptionSearch;
import com.payfurl.payfurlsdk.models.Batch.BatchSearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

class RecentBranchApiCoverageTest {
    private static final String BASE_URI = "https://api.test.payfurl.com";
    private static final String CHARGE_RESPONSE = "{\"ChargeId\":\"charge_123\"}";
    private static final String CHARGE_LIST_RESPONSE = "{\"Limit\":10,\"Skip\":0,\"Count\":0,\"Charges\":[]}";
    private static final String CUSTOMER_RESPONSE = "{\"CustomerId\":\"customer_123\"}";
    private static final String CUSTOMER_LIST_RESPONSE = "{\"Limit\":10,\"Skip\":0,\"Count\":0,\"Customers\":[]}";
    private static final String PAYMENT_METHOD_RESPONSE = "{\"PaymentMethodId\":\"payment_method_123\"}";
    private static final String PAYMENT_METHOD_LIST_RESPONSE = "{\"Limit\":10,\"Skip\":0,\"Count\":0,\"PaymentMethods\":[]}";
    private static final String BATCH_LIST_RESPONSE = "{\"Limit\":10,\"Skip\":0,\"Count\":0,\"Batches\":[]}";
    private static final String WEBHOOK_SUBSCRIPTION_LIST_RESPONSE = "{\"WebhookSubscriptions\":[],\"Count\":0}";
    private static final String SETTLEMENTS_RESPONSE = "[{\"SettlementId\":\"settlement_123\",\"ProviderId\":\"provider_123\",\"ChargeId\":\"charge_123\",\"Amount\":\"12.34\"}]";

    @Nested
    @DisplayName("Charge API")
    class ChargeApiCoverage {
        @Test
        @DisplayName("When createWithApplePay is called Then use apple endpoint with custom headers")
        void testCreateWithApplePay() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(CHARGE_RESPONSE);
            ChargeApi chargeApi = new ChargeApi(new TestConfiguration(), httpClient, authHandlers());

            chargeApi.createWithApplePay(newApplePayRequest(), "provider_123", "user_123");

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.POST);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/charge/token/apple");
            then(request.getHeaders().value("providerId")).isEqualTo("provider_123");
            then(request.getHeaders().value("userId")).isEqualTo("user_123");
            then(request.getHeaders().value("x-secretkey")).isEqualTo("secret_key_test");
            then(bodyOf(request)).contains("token-data").contains("signature-value").contains("txn_123");
        }

        @Test
        @DisplayName("When createWithNetworkToken is called Then use network token endpoint")
        void testCreateWithNetworkToken() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(CHARGE_RESPONSE);
            ChargeApi chargeApi = new ChargeApi(new TestConfiguration(), httpClient, authHandlers());

            chargeApi.createWithNetworkToken(newNetworkTokenRequest());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.POST);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/charge/network_token");
            then(request.getHeaders().value("x-secretkey")).isEqualTo("secret_key_test");
            then(bodyOf(request))
                    .contains("network_token_123")
                    .contains("network-token-flow")
                    .contains("https://notify.example.com/3ds");
        }

        @Test
        @DisplayName("When search is called Then include new charge query parameters")
        void testSearchIncludesNewQueryParameters() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(CHARGE_LIST_RESPONSE);
            ChargeApi chargeApi = new ChargeApi(new TestConfiguration(), httpClient, authHandlers());

            chargeApi.search(new ChargeSearch.Builder()
                    .withBatchId("batch_123")
                    .withSubscriptionId("subscription_123")
                    .withSortBy("DateAdded")
                    .withSortOrder("DESC")
                    .build());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
            then(resolvedUrl(request)).startsWith(BASE_URI + "/charge?");
            then(resolvedUrl(request)).contains("BatchId=batch_123");
            then(resolvedUrl(request)).contains("SubscriptionId=subscription_123");
            then(resolvedUrl(request)).contains("SortBy=DateAdded");
            then(resolvedUrl(request)).contains("SortOrder=DESC");
            then(request.getQueryParameters())
                    .containsEntry("BatchId", "batch_123")
                    .containsEntry("SubscriptionId", "subscription_123")
                    .containsEntry("SortBy", "DateAdded")
                    .containsEntry("SortOrder", "DESC");
        }

        @Test
        @DisplayName("When refund is called Then include the refund comment query parameter")
        void testRefundIncludesComment() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(CHARGE_RESPONSE);
            ChargeApi chargeApi = new ChargeApi(new TestConfiguration(), httpClient, authHandlers());

            chargeApi.refund(new NewRefund.Builder()
                    .withChargeId("charge_123")
                    .withRefundAmount(new BigDecimal("12.34"))
                    .withComment("customer requested partial refund")
                    .build());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.DELETE);
            then(resolvedUrl(request)).startsWith(BASE_URI + "/charge/charge_123?");
            then(resolvedUrl(request)).contains("amount=12.34");
            then(resolvedUrl(request)).contains("comment=customer+requested+partial+refund");
            then(request.getQueryParameters())
                    .containsEntry("amount", new BigDecimal("12.34"))
                    .containsEntry("comment", "customer requested partial refund");
        }
    }

    @Nested
    @DisplayName("Customer API")
    class CustomerApiCoverage {
        @Test
        @DisplayName("When createWithPayTo is called Then use customer payto endpoint")
        void testCreateWithPayTo() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(CUSTOMER_RESPONSE);
            CustomerApi customerApi = new CustomerApi(new TestConfiguration(), httpClient, authHandlers());

            customerApi.createWithPayTo(newCustomerPayToRequest());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.POST);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/customer/payto");
            then(bodyOf(request))
                    .contains("customer-reference")
                    .contains("payto@example.com")
                    .contains("fallback_payment_method_123");
        }

        @Test
        @DisplayName("When search is called Then include customer sort and removal filters")
        void testSearchIncludesNewQueryParameters() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(CUSTOMER_LIST_RESPONSE);
            CustomerApi customerApi = new CustomerApi(new TestConfiguration(), httpClient, authHandlers());

            customerApi.search(new CustomerSearch.Builder()
                    .withSortBy("DateAdded")
                    .withIncludeRemoved(true)
                    .build());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
            then(request.getQueryParameters())
                    .containsEntry("SortBy", "DateAdded")
                    .containsEntry("IncludeRemoved", true);
        }

        @Test
        @DisplayName("When deleteCustomer is called with gatewayDelete false Then forward the flag")
        void testDeleteCustomerWithGatewayDelete() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(CUSTOMER_RESPONSE);
            CustomerApi customerApi = new CustomerApi(new TestConfiguration(), httpClient, authHandlers());

            customerApi.deleteCustomer("customer_123", false);

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.DELETE);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/customer/customer_123?GatewayDelete=false");
            then(request.getQueryParameters()).containsEntry("GatewayDelete", false);
        }

        @Test
        @DisplayName("When createPaymentMethodWithToken is called Then use the corrected token endpoint")
        void testCreatePaymentMethodWithTokenAlias() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(PAYMENT_METHOD_RESPONSE);
            CustomerApi customerApi = new CustomerApi(new TestConfiguration(), httpClient, authHandlers());

            customerApi.createPaymentMethodWithToken("customer_123", newPaymentMethodTokenRequest());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.POST);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/customer/customer_123/payment_method/token");
            then(bodyOf(request))
                    .contains("token_123")
                    .contains("fallback_payment_method_456")
                    .contains("https://notify.example.com/payment-method-token");
        }

        @Test
        @DisplayName("When createPaymentMethodWithPayTo is called Then use the corrected payto endpoint")
        void testCreatePaymentMethodWithPayToAlias() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(PAYMENT_METHOD_RESPONSE);
            CustomerApi customerApi = new CustomerApi(new TestConfiguration(), httpClient, authHandlers());

            customerApi.createPaymentMethodWithPayTo("customer_123", newPayToAgreementRequest());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.POST);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/customer/customer_123/payment_method/payto");
            then(bodyOf(request))
                    .contains("payto@example.com")
                    .contains("fallback_payment_method_123")
                    .contains("https://notify.example.com/payto");
        }

        @Test
        @DisplayName("When createPaymentMethodWithBankAccount is called Then use the corrected bank account endpoint")
        void testCreatePaymentMethodWithBankAccountAlias() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(PAYMENT_METHOD_RESPONSE);
            CustomerApi customerApi = new CustomerApi(new TestConfiguration(), httpClient, authHandlers());

            customerApi.createPaymentMethodWithBankAccount("customer_123", newPaymentMethodBankPaymentRequest());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.POST);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/customer/customer_123/bank_account");
            then(bodyOf(request))
                    .contains("123-456")
                    .contains("fallback_payment_method_789")
                    .contains("https://notify.example.com/payment-method-bank");
        }
    }

    @Nested
    @DisplayName("Payment method API")
    class PaymentMethodApiCoverage {
        @Test
        @DisplayName("When createPaymentMethodWithPayTo is called Then use top-level payto endpoint")
        void testCreatePaymentMethodWithPayTo() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(PAYMENT_METHOD_RESPONSE);
            PaymentMethodApi paymentMethodApi = new PaymentMethodApi(new TestConfiguration(), httpClient, authHandlers());

            paymentMethodApi.createPaymentMethodWithPayTo(newPayToAgreementRequest());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.POST);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/payment_method/payto");
            then(bodyOf(request)).contains("fallback_payment_method_123").contains("payto@example.com");
        }

        @Test
        @DisplayName("When search is called Then include payment method sort and removal filters")
        void testSearchIncludesNewQueryParameters() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(PAYMENT_METHOD_LIST_RESPONSE);
            PaymentMethodApi paymentMethodApi = new PaymentMethodApi(new TestConfiguration(), httpClient, authHandlers());

            paymentMethodApi.search(new PaymentMethodSearch.Builder()
                    .withSortBy("DateAdded")
                    .withSortOrder("ASC")
                    .withIncludeRemoved(true)
                    .build());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
            then(request.getQueryParameters())
                    .containsEntry("SortBy", "DateAdded")
                    .containsEntry("SortOrder", "ASC")
                    .containsEntry("IncludeRemoved", true);
        }

        @Test
        @DisplayName("When deletePaymentMethod is called with gatewayDelete false Then forward the flag")
        void testDeletePaymentMethodWithGatewayDelete() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(PAYMENT_METHOD_RESPONSE);
            PaymentMethodApi paymentMethodApi = new PaymentMethodApi(new TestConfiguration(), httpClient, authHandlers());

            paymentMethodApi.deletePaymentMethod("payment_method_123", false);

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.DELETE);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/payment_method/payment_method_123?GatewayDelete=false");
            then(request.getQueryParameters()).containsEntry("GatewayDelete", false);
        }
    }

    @Nested
    @DisplayName("Settlement API")
    class SettlementApiCoverage {
        @Test
        @DisplayName("When getSettlements is called with a start date Then omit a null end date")
        void testGetSettlementsWithStartDateOnly() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(SETTLEMENTS_RESPONSE);
            SettlementApi settlementApi = new SettlementApi(new TestConfiguration(), httpClient, authHandlers());

            List<com.payfurl.payfurlsdk.models.SettlementData> settlements = settlementApi.getSettlements("2024-01-01");

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
            then(resolvedUrl(request)).isEqualTo(BASE_URI + "/settlements?startDate=2024-01-01");
            then(settlements).hasSize(1);
            then(settlements.get(0).getSettlementId()).isEqualTo("settlement_123");
        }

        @Test
        @DisplayName("When getSettlements is called with start and end dates Then forward both dates")
        void testGetSettlementsWithStartAndEndDate() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(SETTLEMENTS_RESPONSE);
            SettlementApi settlementApi = new SettlementApi(new TestConfiguration(), httpClient, authHandlers());

            settlementApi.getSettlements("2024-01-01", "2024-01-31");

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
            then(resolvedUrl(request)).contains(BASE_URI + "/settlements?");
            then(resolvedUrl(request)).contains("startDate=2024-01-01");
            then(resolvedUrl(request)).contains("endDate=2024-01-31");
            then(request.getQueryParameters())
                    .containsEntry("startDate", "2024-01-01")
                    .containsEntry("endDate", "2024-01-31");
        }
    }

    @Nested
    @DisplayName("Supporting API changes")
    class SupportingApiCoverage {
        @Test
        @DisplayName("When searchBatch is called Then include batch status")
        void testBatchSearchIncludesStatus() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(BATCH_LIST_RESPONSE);
            BatchApi batchApi = new BatchApi(new TestConfiguration(), httpClient, authHandlers());

            batchApi.searchBatch(new BatchSearch.Builder()
                    .withStatus("COMPLETED")
                    .build());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
            then(request.getQueryParameters()).containsEntry("Status", "COMPLETED");
        }

        @Test
        @DisplayName("When searchWebhookSubscription is called Then use SortBy")
        void testWebhookSubscriptionSearchUsesSortBy() throws ApiException {
            FakeHttpClient httpClient = new FakeHttpClient(WEBHOOK_SUBSCRIPTION_LIST_RESPONSE);
            WebhookSubscriptionApi webhookSubscriptionApi = new WebhookSubscriptionApi(new TestConfiguration(), httpClient, authHandlers());

            webhookSubscriptionApi.searchWebhookSubscription(new WebhookSubscriptionSearch.Builder()
                    .withSortBy(WebhookSubscriptionSearch.SortBy.DATE)
                    .build());

            HttpRequest request = httpClient.getLastRequest();
            then(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
            then(request.getQueryParameters())
                    .containsEntry("SortBy", WebhookSubscriptionSearch.SortBy.DATE)
                    .doesNotContainKey("Sort");
        }
    }

    private static Map<AuthType, AuthHandler> authHandlers() {
        return Collections.singletonMap(AuthType.SECRET_KEY, new SecretKeyAuthHandler("secret_key_test"));
    }

    private static String resolvedUrl(HttpRequest request) {
        StringBuilder urlBuilder = new StringBuilder(request.getQueryUrl());
        ApiUtils.appendUrlWithQueryParameters(urlBuilder, request.getQueryParameters());
        return ApiUtils.cleanUrl(urlBuilder);
    }

    private static String bodyOf(HttpRequest request) {
        return ((HttpBodyRequest) request).getBody().toString();
    }

    private static NewChargeApplePay newApplePayRequest() {
        return new NewChargeApplePay.Builder()
                .withAmount(new BigDecimal("12.34"))
                .withCurrency("USD")
                .withEmail("applepay@example.com")
                .withUserAgent("Safari")
                .withToken(new NewChargeApplePay.AppleToken(
                        new NewChargeApplePay.ApplePaySource(
                                "token-data",
                                "signature-value",
                                new NewChargeApplePay.ApplePayHeader("public-key-hash", "ephemeral-key", "txn_123"),
                                "EC_v1"),
                        new NewChargeApplePay.ApplePaymentMethod("Visa 1111", "Visa", "debit")))
                .build();
    }

    private static NewChargeNetworkToken newNetworkTokenRequest() {
        return new NewChargeNetworkToken.Builder()
                .withNetworkTokenId("network_token_123")
                .withAmount(new BigDecimal("12.34"))
                .withCurrency("USD")
                .withProviderId("provider_123")
                .withMetadata(Collections.singletonMap("flow", "network-token-flow"))
                .withWebhook(new WebhookConfig.Builder()
                        .withUrl("https://notify.example.com/network-token")
                        .withAuthorization("Bearer token")
                        .build())
                .withThreeDSNotificationUrl("https://notify.example.com/3ds")
                .build();
    }

    private static NewCustomerPayTo newCustomerPayToRequest() {
        return new NewCustomerPayTo.Builder()
                .withPayToAgreement(newPayToAgreementRequest())
                .withReference("customer-reference")
                .withFirstName("Jane")
                .withLastName("Doe")
                .withEmail("payto@example.com")
                .withAddress(new Address.Builder()
                        .withLine1("123 Test Street")
                        .withCity("Melbourne")
                        .withCountry("AU")
                        .build())
                .withWebhook(new WebhookConfig.Builder()
                        .withUrl("https://notify.example.com/customer-payto")
                        .withAuthorization("Bearer customer")
                        .build())
                .build();
    }

    private static NewPayToAgreement newPayToAgreementRequest() {
        return new NewPayToAgreement.Builder()
                .withProviderId("provider_123")
                .withPayerName("Jane Doe")
                .withDescription("Subscription")
                .withMaximumAmount(500)
                .withPayerPayIdDetails(new PayIdDetails.Builder()
                        .withPayId("payto@example.com")
                        .withPayIdType("EMAIL")
                        .build())
                .withFallbackPaymentMethodId("fallback_payment_method_123")
                .withMetadata(Collections.singletonMap("source", "payto"))
                .withWebhook(new WebhookConfig.Builder()
                        .withUrl("https://notify.example.com/payto")
                        .withAuthorization("Bearer payto")
                        .build())
                .build();
    }

    private static NewPaymentMethodToken newPaymentMethodTokenRequest() {
        return new NewPaymentMethodToken.Builder()
                .withToken("token_123")
                .withFallbackPaymentMethodId("fallback_payment_method_456")
                .withMetadata(Collections.singletonMap("source", "payment-method-token"))
                .withWebhook(new WebhookConfig.Builder()
                        .withUrl("https://notify.example.com/payment-method-token")
                        .withAuthorization("Bearer payment-method-token")
                        .build())
                .build();
    }

    private static NewPaymentMethodBankPayment newPaymentMethodBankPaymentRequest() {
        return new NewPaymentMethodBankPayment.Builder()
                .withProviderId("provider_123")
                .withBankPaymentInformation(new BankPaymentInformation.Builder()
                        .withBankCode("123-456")
                        .withAccountNumber("123456")
                        .withAccountName("Primary account")
                        .build())
                .withFallbackPaymentMethodId("fallback_payment_method_789")
                .withMetadata(Collections.singletonMap("source", "payment-method-bank"))
                .withWebhook(new WebhookConfig.Builder()
                        .withUrl("https://notify.example.com/payment-method-bank")
                        .withAuthorization("Bearer payment-method-bank")
                        .build())
                .build();
    }

    private static class FakeHttpClient implements HttpClient {
        private final String responseBody;
        private HttpRequest lastRequest;

        private FakeHttpClient(String responseBody) {
            this.responseBody = responseBody;
        }

        private HttpRequest getLastRequest() {
            return lastRequest;
        }

        @Override
        public HttpResponse execute(HttpRequest httpRequest) throws IOException {
            this.lastRequest = httpRequest;
            byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
            return new HttpStringResponse(200, new Headers(), new ByteArrayInputStream(bytes), responseBody);
        }

        @Override
        public HttpRequest prepareGetRequest(StringBuilder queryUrlBuilder,
                                             Headers headers,
                                             Map<String, Object> queryParams,
                                             List<SimpleEntry<String, Object>> parameters) {
            return new HttpRequest(HttpMethod.GET, queryUrlBuilder, headers, queryParams, parameters);
        }

        @Override
        public HttpRequest preparePostRequest(StringBuilder queryUrlBuilder,
                                              Headers headers,
                                              Map<String, Object> queryParams,
                                              List<SimpleEntry<String, Object>> parameters) {
            return new HttpRequest(HttpMethod.POST, queryUrlBuilder, headers, queryParams, parameters);
        }

        @Override
        public HttpBodyRequest preparePostBodyRequest(StringBuilder queryUrlBuilder,
                                                      Headers headers,
                                                      Map<String, Object> queryParams,
                                                      Object body) {
            return new HttpBodyRequest(HttpMethod.POST, queryUrlBuilder, headers, queryParams, body);
        }

        @Override
        public HttpBodyRequest preparePutBodyRequest(StringBuilder queryUrlBuilder,
                                                     Headers headers,
                                                     Map<String, Object> queryParams,
                                                     Object body) {
            return new HttpBodyRequest(HttpMethod.PUT, queryUrlBuilder, headers, queryParams, body);
        }

        @Override
        public HttpRequest prepareDeleteRequest(StringBuilder queryUrlBuilder,
                                               Headers headers,
                                               Map<String, Object> queryParams,
                                               List<SimpleEntry<String, Object>> parameters) {
            return new HttpRequest(HttpMethod.DELETE, queryUrlBuilder, headers, queryParams, parameters);
        }
    }

    private static class TestConfiguration implements Configuration {
        @Override
        public Environment getEnvironment() {
            return Environment.SANDBOX;
        }

        @Override
        public String getPayFurlVersion() {
            return "test-version";
        }

        @Override
        public Headers getAdditionalHeaders() {
            return new Headers();
        }

        @Override
        public String getUserAgentDetail() {
            return "";
        }

        @Override
        public long getTimeout() {
            return 60_000;
        }

        @Override
        public String getSecretKey() {
            return "secret_key_test";
        }

        @Override
        public String getBaseUri() {
            return BASE_URI;
        }
    }
}
