package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.ChargeData;
import com.payfurl.payfurlsdk.models.ChargeList;
import com.payfurl.payfurlsdk.models.ChargeSearch;
import com.payfurl.payfurlsdk.models.NewChargeCapture;
import com.payfurl.payfurlsdk.models.NewChargeCardLeastCost;
import com.payfurl.payfurlsdk.models.NewChargeCardRequest;
import com.payfurl.payfurlsdk.models.NewChargeCustomer;
import com.payfurl.payfurlsdk.models.NewChargePaymentMethod;
import com.payfurl.payfurlsdk.models.NewChargeToken;
import com.payfurl.payfurlsdk.models.NewRefund;

import java.util.HashMap;
import java.util.Map;

public class ChargeApi extends BaseApi {
    private final String chargeApiBaseEndpoint;

    public ChargeApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        chargeApiBaseEndpoint = String.format("%s/%s", baseUri, "charge");
    }

    /**
     * Accept a payment using a card number
     *
     * @param newChargeCardRequest parameter for creating payment with card
     * @return ChargeData object details
     * @throws ApiException
     */
    public ChargeData createWithCard(NewChargeCardRequest newChargeCardRequest) throws ApiException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/card", newChargeCardRequest, ChargeData.class);
    }

    /**
     * Accept a payment using a card number using the least cost provider
     *
     * @param newChargeCardLeastCost
     * @return ChargeData object details
     * @throws ApiException
     */
    public ChargeData createWithCardLeastCost(NewChargeCardLeastCost newChargeCardLeastCost) throws ApiException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/card/least_cost", newChargeCardLeastCost, ChargeData.class);
    }

    /**
     * Accept a payment for a saved payment method
     *
     * @param newChargePaymentMethod
     * @return
     * @throws ApiException
     */
    public ChargeData createWithPaymentMethod(NewChargePaymentMethod newChargePaymentMethod) throws ApiException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/payment_method", newChargePaymentMethod, ChargeData.class);
    }

    /**
     * Accept a payment for a customer
     *
     * @param newChargeCustomer
     * @return
     * @throws ApiException
     */
    public ChargeData createWithCustomer(NewChargeCustomer newChargeCustomer) throws ApiException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/customer", newChargeCustomer, ChargeData.class);
    }

    /**
     * Accept a payment for payment token
     *
     * @param newChargeToken
     * @return
     * @throws ApiException
     */
    public ChargeData createWithToken(NewChargeToken newChargeToken) throws ApiException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/token", newChargeToken, ChargeData.class);
    }

    /**
     * Retrieve a single charge
     *
     * @param chargeId
     * @return
     * @throws ApiException
     */
    public ChargeData single(String chargeId) throws ApiException {
        return executeGetRequestWith(chargeApiBaseEndpoint + "/" + chargeId, null, ChargeData.class);
    }

    /**
     * Search for charges
     *
     * @param searchData
     * @return
     * @throws ApiException
     */
    public ChargeList search(ChargeSearch searchData) throws ApiException {
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

    /**
     * Refund a charge
     *
     * @param newRefund
     * @return
     * @throws ApiException
     */
    public ChargeData refund(NewRefund newRefund) throws ApiException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("amount", newRefund.getRefundAmount());
        return executeDeleteRequestWith(chargeApiBaseEndpoint + "/" + newRefund.getChargeId(), queryParameters, ChargeData.class);
    }

    /**
     * Capture a charge
     *
     * @param chargeId
     * @param newChargeCapture
     * @return
     * @throws ApiException
     */
    public ChargeData captureCharge(String chargeId, NewChargeCapture newChargeCapture) throws ApiException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/" + chargeId, newChargeCapture, ChargeData.class);
    }

    /**
     * Void/cancel charge
     *
     * @param chargeId
     * @return
     * @throws ApiException
     */
    public ChargeData voidCharge(String chargeId) throws ApiException {
        return executeDeleteRequestWith(chargeApiBaseEndpoint + "/" + chargeId, null, ChargeData.class);
    }
}
