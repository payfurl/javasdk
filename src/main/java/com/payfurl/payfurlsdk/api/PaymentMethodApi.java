package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.Checkout;
import com.payfurl.payfurlsdk.models.NewCheckout;
import com.payfurl.payfurlsdk.models.NewPaymentMethodCard;
import com.payfurl.payfurlsdk.models.NewPaymentMethodVault;
import com.payfurl.payfurlsdk.models.PaymentMethodData;
import com.payfurl.payfurlsdk.models.PaymentMethodList;
import com.payfurl.payfurlsdk.models.PaymentMethodSearch;

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

    /**
     * Creates a new checkout
     * @param newCheckout
     * @return
     * @throws IOException
     */
    public Checkout checkout(NewCheckout newCheckout) throws IOException {
        String urlPath = paymentMethodApiBaseEndpoint + "/checkout";
        return executePostRequestWith(urlPath, newCheckout, Checkout.class);
    }

    /**
     * Add a payment method using a vault
     * @param newPaymentMethodVault
     * @return
     * @throws IOException
     */
    public PaymentMethodData createPaymentMethodWithVault(NewPaymentMethodVault newPaymentMethodVault) throws IOException {
        String urlPath = paymentMethodApiBaseEndpoint + "/vault";
        return executePostRequestWith(urlPath, newPaymentMethodVault, PaymentMethodData.class);
    }

    /**
     * Add a payment method using a card
     * @param newPaymentMethodCard
     * @return
     * @throws IOException
     */
    public PaymentMethodData createPaymentMethodWithCard(NewPaymentMethodCard newPaymentMethodCard) throws IOException {
        String urlPath = paymentMethodApiBaseEndpoint + "/card";
        return executePostRequestWith(urlPath, newPaymentMethodCard, PaymentMethodData.class);
    }

    /**
     * Retrieve a single payment method
     * @param paymentMethodId
     * @return
     * @throws IOException
     */
    public PaymentMethodData single(String paymentMethodId) throws IOException {
        String urlPath = String.format("%s/%s",paymentMethodApiBaseEndpoint, paymentMethodId);
        return executeGetRequestWith(urlPath, null, PaymentMethodData.class);
    }

    /**
     * Delete payment method
     * @param paymentMethodId
     * @return
     * @throws IOException
     */
    public PaymentMethodData deletePaymentMethod(String paymentMethodId) throws IOException {
        String urlPath = String.format("%s/%s",paymentMethodApiBaseEndpoint, paymentMethodId);
        return executeDeleteRequestWith(urlPath, null, PaymentMethodData.class);
    }

    /**
     * Search for payment methods
     * @param searchData
     * @return
     * @throws IOException
     */
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
