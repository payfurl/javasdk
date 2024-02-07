package com.payfurl.payfurlsdk.api.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
}
