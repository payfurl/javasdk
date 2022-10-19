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
    private static final String CHARGE_API_PATH_PART = "charge";

    private final String chargeApiBaseEndpoint;

    public ChargeApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        chargeApiBaseEndpoint = String.format("%s/%s", baseUri, CHARGE_API_PATH_PART);
    }

    public ChargeData createWithCard(NewChargeCardRequest newChargeCardRequest) throws IOException {
        return executePostRequestWith("/card", newChargeCardRequest);
    }

    public ChargeData createWithCardLeastCost(NewChargeCardLeastCost newChargeCardLeastCost) throws IOException {
        return executePostRequestWith("/card/least_cost", newChargeCardLeastCost);
    }

    public ChargeData createWitPaymentMethod(NewChargePaymentMethod newChargePaymentMethod) throws IOException {
        return executePostRequestWith("/payment_method", newChargePaymentMethod);
    }

    public ChargeData createWithCustomer(NewChargeCustomer newChargeCustomer) throws IOException {
        return executePostRequestWith("/customer", newChargeCustomer);
    }

    public ChargeData createWithToken(NewChargeToken newChargeToken) throws IOException {
        return executePostRequestWith("/token", newChargeToken);
    }

    public ChargeData single(String chargeId) throws IOException {
        return executeGetRequestWith("/" + chargeId, null, ChargeData.class);
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

        return executeGetRequestWith("", queryParameters, ChargeList.class);
    }

    public ChargeData refund(NewRefund newRefund) throws IOException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("amount", newRefund.getRefundAmount());
        return executeDeleteRequestWith("/" + newRefund.getChargeId(), queryParameters);
    }

    public ChargeData captureCharge(String chargeId, NewChargeCapture newChargeCapture) throws IOException {
        return executePostRequestWith("/" + chargeId, newChargeCapture);
    }

    public ChargeData voidCharge(String chargeId) throws IOException {
        return executeDeleteRequestWith("/" + chargeId, null);
    }

    private <T> ChargeData executePostRequestWith(String urlPath, T chargeApiRequest) throws IOException {
        HttpRequest request = createNewChargeApiPostRequest(urlPath, chargeApiRequest);

        HttpResponse response = getClientInstance().execute(request);

        return getChargeDataFrom(response, ChargeData.class);
    }

    private <T> T executeGetRequestWith(String urlPath, Map<String, Object> queryParams, Class<T> returnType) throws IOException {
        HttpRequest request = createNewChargeApiGetRequest(urlPath, queryParams);

        HttpResponse response = getClientInstance().execute(request);

        return getChargeDataFrom(response, returnType);
    }

    private ChargeData executeDeleteRequestWith(String urlPath, Map<String, Object> queryParams) throws IOException {
        HttpRequest request = createNewChargeApiDeleteRequest(urlPath, queryParams);

        HttpResponse response = getClientInstance().execute(request);

        return getChargeDataFrom(response, ChargeData.class);
    }

    private <T> T getChargeDataFrom(HttpResponse response, Class<T> clazz) throws JsonProcessingException {
        validateResponse(response);

        String responseBody = ((HttpStringResponse) response).getBody();
        return ApiUtils.deserialize(responseBody, clazz);
    }

    private <T> HttpRequest createNewChargeApiPostRequest(String urlPath, T chargeApiRequest) throws JsonProcessingException {
        StringBuilder queryBuilder = new StringBuilder(chargeApiBaseEndpoint + urlPath);

        Headers headers = getPopulatedHeaders();

        String bodyJson = ApiUtils.serialize(chargeApiRequest);
        HttpRequest request = getClientInstance().preparePostBodyRequest(queryBuilder, headers, null, bodyJson);

        addAuthDataTo(request);
        return request;
    }

    private HttpRequest createNewChargeApiGetRequest(String urlPath, Map<String, Object> queryParams) {
        StringBuilder queryBuilder = new StringBuilder(chargeApiBaseEndpoint + urlPath);

        Headers headers = getPopulatedHeaders();

        HttpRequest request = getClientInstance().prepareGetRequest(queryBuilder, headers, queryParams, null);

        addAuthDataTo(request);
        return request;
    }

    private HttpRequest createNewChargeApiDeleteRequest(String urlPath, Map<String, Object> queryParams) {
        StringBuilder queryBuilder = new StringBuilder(chargeApiBaseEndpoint + urlPath);

        Headers headers = getPopulatedHeaders();

        HttpRequest request = getClientInstance().prepareDeleteRequest(queryBuilder, headers, queryParams, null);

        addAuthDataTo(request);
        return request;
    }
}
