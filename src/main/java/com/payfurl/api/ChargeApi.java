package com.payfurl.api;

import com.payfurl.Configuration;
import com.payfurl.auth.AuthHandler;
import com.payfurl.auth.AuthType;
import com.payfurl.http.client.HttpClient;
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

    /**
     * Accept a payment using a card number
     * @param newChargeCardRequest parameter for creating payment with card
     * @return ChargeData object details
     * @throws IOException
     */
    public ChargeData createWithCard(NewChargeCardRequest newChargeCardRequest) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/card", newChargeCardRequest, ChargeData.class);
    }

    /**
     * Accept a payment using a card number using the least cost provider
     * @param newChargeCardLeastCost
     * @return ChargeData object details
     * @throws IOException
     */
    public ChargeData createWithCardLeastCost(NewChargeCardLeastCost newChargeCardLeastCost) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/card/least_cost", newChargeCardLeastCost, ChargeData.class);
    }

    /**
     * Accept a payment for a saved payment method
     * @param newChargePaymentMethod
     * @return
     * @throws IOException
     */
    public ChargeData createWitPaymentMethod(NewChargePaymentMethod newChargePaymentMethod) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/payment_method", newChargePaymentMethod, ChargeData.class);
    }

    /**
     * Accept a payment for a customer
     * @param newChargeCustomer
     * @return
     * @throws IOException
     */
    public ChargeData createWithCustomer(NewChargeCustomer newChargeCustomer) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/customer", newChargeCustomer, ChargeData.class);
    }

    /**
     * Accept a payment for payment token
     * @param newChargeToken
     * @return
     * @throws IOException
     */
    public ChargeData createWithToken(NewChargeToken newChargeToken) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/token", newChargeToken, ChargeData.class);
    }

    /**
     * Retrieve a single charge
     * @param chargeId
     * @return
     * @throws IOException
     */
    public ChargeData single(String chargeId) throws IOException {
        return executeGetRequestWith(chargeApiBaseEndpoint + "/" + chargeId, null, ChargeData.class);
    }

    /**
     * Search for charges
     * @param searchData
     * @return
     * @throws IOException
     */
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

    /**
     * Refund a charge
     * @param newRefund
     * @return
     * @throws IOException
     */
    public ChargeData refund(NewRefund newRefund) throws IOException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("amount", newRefund.getRefundAmount());
        return executeDeleteRequestWith(chargeApiBaseEndpoint + "/" + newRefund.getChargeId(), queryParameters, ChargeData.class);
    }

    /**
     * Capture a charge
     * @param chargeId
     * @param newChargeCapture
     * @return
     * @throws IOException
     */
    public ChargeData captureCharge(String chargeId, NewChargeCapture newChargeCapture) throws IOException {
        return executePostRequestWith(chargeApiBaseEndpoint + "/" + chargeId, newChargeCapture, ChargeData.class);
    }

    /**
     * Void/cancel charge
     * @param chargeId
     * @return
     * @throws IOException
     */
    public ChargeData voidCharge(String chargeId) throws IOException {
        return executeDeleteRequestWith(chargeApiBaseEndpoint + "/" + chargeId, null, ChargeData.class);
    }
}
