package com.payfurl.api;

import com.payfurl.Configuration;
import com.payfurl.auth.AuthHandler;
import com.payfurl.auth.AuthType;
import com.payfurl.http.client.HttpClient;
import com.payfurl.models.Checkout;
import com.payfurl.models.NewCheckout;
import com.payfurl.models.NewPaymentMethodCard;
import com.payfurl.models.NewPaymentMethodVault;
import com.payfurl.models.PaymentMethodData;
import com.payfurl.models.PaymentMethodList;
import com.payfurl.models.PaymentMethodSearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PaymentMethodApi extends BaseApi {
    private final String paymentMethodApiBaseEndpoint;

    public PaymentMethodApi(Configuration config,
                            HttpClient httpClient,
                            Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        paymentMethodApiBaseEndpoint = String.format("%s/%s", baseUri, "payment_method");
    }

    public Checkout checkout(NewCheckout newCheckout) throws IOException {
        String urlPath = paymentMethodApiBaseEndpoint + "/checkout";
        return executePostRequestWith(urlPath, newCheckout, Checkout.class);
    }

    public PaymentMethodData createPaymentMethodWithVault(NewPaymentMethodVault newPaymentMethodVault) throws IOException {
        String urlPath = paymentMethodApiBaseEndpoint + "/vault";
        return executePostRequestWith(urlPath, newPaymentMethodVault, PaymentMethodData.class);
    }

    public PaymentMethodData createPaymentMethodWithCard(NewPaymentMethodCard newPaymentMethodCard) throws IOException {
        String urlPath = paymentMethodApiBaseEndpoint + "/card";
        return executePostRequestWith(urlPath, newPaymentMethodCard, PaymentMethodData.class);
    }

    public PaymentMethodData single(String paymentMethodId) throws IOException {
        String urlPath = String.format("%s/%s",paymentMethodApiBaseEndpoint, paymentMethodId);
        return executeGetRequestWith(urlPath, null, PaymentMethodData.class);
    }

    public PaymentMethodList search(PaymentMethodSearch searchData) throws IOException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("Skip", searchData.getSkip());
        queryParams.put("Limit", searchData.getLimit());
        queryParams.put("ProviderId", searchData.getProviderId());
        queryParams.put("CustomerId", searchData.getCustomerId());
        queryParams.put("Search", searchData.getSearch());
        queryParams.put("AddedAfter", searchData.getAddedAfter());
        queryParams.put("AddedBefore", searchData.getAddedBefore());
        queryParams.put("PaymentType", searchData.getPaymentType());
        queryParams.put("SortBy", searchData.getSortBy());

        return executeGetRequestWith(paymentMethodApiBaseEndpoint, queryParams, PaymentMethodList.class);
    }
}
