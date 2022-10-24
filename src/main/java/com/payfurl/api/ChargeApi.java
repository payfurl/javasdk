package com.payfurl.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payfurl.Configuration;
import com.payfurl.auth.AuthHandler;
import com.payfurl.auth.AuthType;
import com.payfurl.http.client.HttpClient;
import com.payfurl.http.client.support.ApiUtils;
import com.payfurl.http.client.support.Headers;
import com.payfurl.http.client.support.request.HttpRequest;
import com.payfurl.http.client.support.response.HttpResponse;
import com.payfurl.http.client.support.response.HttpStringResponse;
import com.payfurl.models.ChargeData;
import com.payfurl.models.ChargeList;
import com.payfurl.models.ChargeSearch;
import com.payfurl.models.NewChargeCapture;
import com.payfurl.models.NewChargeCardLeastCost;
import com.payfurl.models.NewChargeCardRequest;
import com.payfurl.models.NewChargeCustomer;
import com.payfurl.models.NewChargePaymentMethod;
import com.payfurl.models.NewChargeToken;
import com.payfurl.models.NewRefund;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChargeApi extends BaseApi {
    private final String chargeApiBaseEndpoint;

    public ChargeApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        chargeApiBaseEndpoint = String.format("%s/%s", baseUri, "charge");
    }

    public ChargeData createWithCard(NewChargeCardRequest newChargeCardRequest) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/card", newChargeCardRequest, ChargeData.class);
    }

    public ChargeData createWithCardLeastCost(NewChargeCardLeastCost newChargeCardLeastCost) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/card/least_cost", newChargeCardLeastCost, ChargeData.class);
    }

    public ChargeData createWitPaymentMethod(NewChargePaymentMethod newChargePaymentMethod) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/payment_method", newChargePaymentMethod, ChargeData.class);
    }

    public ChargeData createWithCustomer(NewChargeCustomer newChargeCustomer) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/customer", newChargeCustomer, ChargeData.class);
    }

    public ChargeData createWithToken(NewChargeToken newChargeToken) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/token", newChargeToken, ChargeData.class);
    }

    public ChargeData single(String chargeId) throws IOException {
        return executeGetRequestWith(chargeApiBaseEndpoint + "/" + chargeId, null, ChargeData.class);
    }

    public ChargeList search(ChargeSearch searchData) throws IOException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("Skip", searchData.getSkip());
        queryParameters.put("Limit", searchData.getLimit());
        queryParameters.put("Reference", searchData.getReference());
        queryParameters.put("PaymentMethodId", searchData.getPaymentMethodId());
        queryParameters.put("AmountGreaterThan", searchData.getAmountGreaterThan());
        queryParameters.put("AmountLessThan", searchData.getAmountLessThan());
        queryParameters.put("CustomerId", searchData.getCustomerId());
        queryParameters.put("Status", searchData.getStatus());
        queryParameters.put("AddedAfter", searchData.getAddedAfter());
        queryParameters.put("AddedBefore", searchData.getAddedBefore());
        queryParameters.put("SortBy", searchData.getSortBy());

        return executeGetRequestWith(chargeApiBaseEndpoint, queryParameters, ChargeList.class);
    }

    public ChargeData refund(NewRefund newRefund) throws IOException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("amount", newRefund.getRefundAmount());
        return executeDeleteRequestWith(chargeApiBaseEndpoint + "/" + newRefund.getChargeId(), queryParameters, ChargeData.class);
    }

    public ChargeData captureCharge(String chargeId, NewChargeCapture newChargeCapture) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/" + chargeId, newChargeCapture, ChargeData.class);
    }

    public ChargeData voidCharge(String chargeId) throws IOException {
        return executeDeleteRequestWith(chargeApiBaseEndpoint + "/" + chargeId, null, ChargeData.class);
    }
}
