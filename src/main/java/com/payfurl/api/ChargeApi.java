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
import com.payfurl.models.NewChargeCardRequest;

import java.io.IOException;
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
        HttpRequest request = createNewChargeCardRequestFrom(newChargeCardRequest);

        HttpResponse response = getClientInstance().execute(request);

        return getChargeDataFrom(response);
    }

    private ChargeData getChargeDataFrom(HttpResponse response) throws JsonProcessingException {
        validateResponse(response);
        String responseBody = ((HttpStringResponse) response).getBody();
        return ApiUtils.deserialize(responseBody, ChargeData.class);
    }

    private HttpRequest createNewChargeCardRequestFrom(NewChargeCardRequest newChargeCardRequest) throws JsonProcessingException {
        StringBuilder queryBuilder = new StringBuilder(chargeApiBaseEndpoint + "/card");

        Headers headers = getPopulatedHeaders();

        String bodyJson = ApiUtils.serialize(newChargeCardRequest);
        HttpRequest request = getClientInstance().preparePostBodyRequest(queryBuilder, headers, null, bodyJson);

        addAuthDataTo(request);
        return request;
    }

    private Headers getPopulatedHeaders() {
        Headers headers = new Headers();
        headers.add("content-type", "application/json; charset=utf-8");
        headers.add("user-agent", internalUserAgent);
        headers.addAll(config.getAdditionalHeaders());
        return headers;
    }

    private void addAuthDataTo(HttpRequest request) {
        AuthHandler authHandler = authHandlers.get(AuthType.SECRET_KEY);
        authHandler.apply(request);
    }
}
