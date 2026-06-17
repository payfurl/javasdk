package com.payfurl.payfurlsdk.api.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.BDDAssertions.then;

public class ApiModelTest {

    @Test
    @DisplayName("Do not send descriptor if it is null in NewChargePaymentMethod")
    void testNewChargePaymentMethodNullDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargePaymentMethod newChargePaymentMethod = new NewChargePaymentMethod.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor(null)
                .build();

        // when
        String jsonBody = ApiUtils.serialize(newChargePaymentMethod);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Do not send descriptor if it is empty in NewChargePaymentMethod")
    void testNewChargePaymentMethodEmptyDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargePaymentMethod newChargePaymentMethod = new NewChargePaymentMethod.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor("")
                .build();

        // when
        String jsonBody = ApiUtils.serialize(newChargePaymentMethod);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Do not send descriptor if it is null in NewChargeCardLeastCost")
    void testNewChargeCardLeastCostNullDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargeCardLeastCost object = new NewChargeCardLeastCost.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor(null)
                .build();

        // when
        String jsonBody = ApiUtils.serialize(object);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Do not send descriptor if it is empty in NewChargeCardLeastCost")
    void testNewChargeCardLeastCostEmptyDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargeCardLeastCost object = new NewChargeCardLeastCost.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor("")
                .build();

        // when
        String jsonBody = ApiUtils.serialize(object);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Do not send descriptor if it is null in NewChargeCardRequest")
    void testNewChargeCardRequestNullDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargeCardRequest object = new NewChargeCardRequest.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor(null)
                .build();

        // when
        String jsonBody = ApiUtils.serialize(object);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Do not send descriptor if it is empty in NewChargeCardRequest")
    void testNewChargeCardRequestEmptyDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargeCardRequest object = new NewChargeCardRequest.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor("")
                .build();

        // when
        String jsonBody = ApiUtils.serialize(object);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Do not send descriptor if it is null in NewChargeCustomer")
    void testNewChargeCustomerNullDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargeCustomer object = new NewChargeCustomer.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor(null)
                .build();

        // when
        String jsonBody = ApiUtils.serialize(object);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Do not send descriptor if it is empty in NewChargeCustomer")
    void testNewChargeCustomerEmptyDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargeCustomer object = new NewChargeCustomer.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor("")
                .build();

        // when
        String jsonBody = ApiUtils.serialize(object);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Do not send descriptor if it is null in NewChargeToken")
    void testNewChargeTokenNullDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargeToken object = new NewChargeToken.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor(null)
                .build();

        // when
        String jsonBody = ApiUtils.serialize(object);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Do not send descriptor if it is empty in NewChargeToken")
    void testNewChargeTokenEmptyDescriptor() throws ApiException, JsonProcessingException {
        // given
        NewChargeToken object = new NewChargeToken.Builder()
                .withAmount(BigDecimal.valueOf(20))
                .withDescriptor("")
                .build();

        // when
        String jsonBody = ApiUtils.serialize(object);

        // then
        then(jsonBody).doesNotContain("descriptor");
    }

    @Test
    @DisplayName("Serialize NewChargeApplePay with nested token data")
    void testNewChargeApplePaySerialization() throws ApiException, JsonProcessingException {
        NewChargeApplePay object = new NewChargeApplePay.Builder()
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

        String jsonBody = ApiUtils.serialize(object);

        then(jsonBody).contains("token-data").contains("signature-value").contains("txn_123");
    }

    @Test
    @DisplayName("Serialize NewChargeNetworkToken with branch-specific fields")
    void testNewChargeNetworkTokenSerialization() throws ApiException, JsonProcessingException {
        NewChargeNetworkToken object = new NewChargeNetworkToken.Builder()
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

        String jsonBody = ApiUtils.serialize(object);

        then(jsonBody)
                .contains("network_token_123")
                .contains("network-token-flow")
                .contains("https://notify.example.com/3ds");
    }

    @Test
    @DisplayName("Serialize NewCustomerPayTo with branch-specific fields")
    void testNewCustomerPayToSerialization() throws ApiException, JsonProcessingException {
        NewCustomerPayTo object = new NewCustomerPayTo.Builder()
                .withPayToAgreement(new NewPayToAgreement.Builder()
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
                        .build())
                .withReference("customer-reference")
                .withEmail("payto@example.com")
                .build();

        String jsonBody = ApiUtils.serialize(object);

        then(jsonBody)
                .contains("customer-reference")
                .contains("fallback_payment_method_123")
                .contains("https://notify.example.com/payto");
    }

    @Test
    @DisplayName("Deserialize SettlementData")
    void testSettlementDataDeserialization() throws ApiException, JsonProcessingException {
        SettlementData settlementData = ApiUtils.deserialize(
                "{\"SettlementId\":\"settlement_123\",\"ProviderId\":\"provider_123\",\"ChargeId\":\"charge_123\",\"Amount\":\"12.34\"}",
                SettlementData.class);

        then(settlementData.getSettlementId()).isEqualTo("settlement_123");
        then(settlementData.getProviderId()).isEqualTo("provider_123");
        then(settlementData.getAmount()).isEqualByComparingTo("12.34");
    }

    @Test
    @DisplayName("Deserialize ChargeData with IpInfo")
    void testChargeDataDeserializationWithIpInfo() throws ApiException, JsonProcessingException {
        ChargeData chargeData = ApiUtils.deserialize(
                "{\"ChargeId\":\"charge_123\",\"Ip\":\"1.2.3.4\",\"IpInfo\":{\"Ip\":\"1.2.3.4\",\"City\":\"Warsaw\",\"Country\":\"PL\"}}",
                ChargeData.class);

        then(chargeData.getIp()).isEqualTo("1.2.3.4");
        then(chargeData.getIpInfo()).isNotNull();
        then(chargeData.getIpInfo().getCity()).isEqualTo("Warsaw");
        then(chargeData.getIpInfo().getCountry()).isEqualTo("PL");
    }

    @Test
    @DisplayName("Deserialize TokenData with ClickToPay data")
    void testTokenDataDeserializationWithClickToPay() throws ApiException, JsonProcessingException {
        TokenData tokenData = ApiUtils.deserialize(
                "{\"TokenId\":\"token_123\",\"ClickToPay\":{\"BillingAddress\":{\"City\":\"Melbourne\",\"Country\":\"AU\"},\"CardData\":{\"PanBin\":\"411111\",\"PanLastFour\":\"1111\",\"Scheme\":\"Visa\",\"PaymentCardType\":\"credit\"}}}",
                TokenData.class);

        then(tokenData.getTokenId()).isEqualTo("token_123");
        then(tokenData.getClickToPay()).isNotNull();
        then(tokenData.getClickToPay().getBillingAddress().getCity()).isEqualTo("Melbourne");
        then(tokenData.getClickToPay().getCardData().getPanBin()).isEqualTo("411111");
    }
}
