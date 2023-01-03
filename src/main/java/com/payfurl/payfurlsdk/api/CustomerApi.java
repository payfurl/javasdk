package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerApi extends BaseApi {
    private final String customerApiBaseEndpoint;

    public CustomerApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        customerApiBaseEndpoint = String.format("%s/%s", baseUri, "customer");
    }

    /**
     * Add a customer using a card number
     * @param newCustomerCard
     * @return
     * @throws IOException
     */
    public CustomerData createWithCard(NewCustomerCard newCustomerCard) throws IOException {
        return executePostRequestWith(customerApiBaseEndpoint + "/card", newCustomerCard, CustomerData.class);
    }

    /**
     * Add a customer using a payment token
     * @param newCustomerToken
     * @return
     * @throws IOException
     */
    public CustomerData createWithToken(NewCustomerToken newCustomerToken) throws IOException {
        return executePostRequestWith(customerApiBaseEndpoint + "/token", newCustomerToken, CustomerData.class);
    }

    /**
     * Add a payment method using a card
     * @param customerId
     * @param newPaymentMethodCard
     * @return
     * @throws IOException
     */
    public PaymentMethodData createPaymentMethodWithCard(String customerId, NewPaymentMethodCard newPaymentMethodCard) throws IOException {
        String urlPath = String.format("%s/%s/payment_method/card", customerApiBaseEndpoint, customerId);
        return executePostRequestWith(urlPath, newPaymentMethodCard, PaymentMethodData.class);
    }

    /**
     * Add a payment method using a token
     * @param customerId
     * @param newPaymentMethodToken
     * @return
     * @throws IOException
     */
    public PaymentMethodData createWitPaymentMethodWithToken(String customerId, NewPaymentMethodToken newPaymentMethodToken) throws IOException {
        String urlPath = String.format("%s/%s/payment_method/token", customerApiBaseEndpoint, customerId);
        return executePostRequestWith(urlPath, newPaymentMethodToken, PaymentMethodData.class);
    }

    /**
     * Retrieve a single customer
     * @param customerId
     * @return
     * @throws IOException
     */
    public CustomerData single(String customerId) throws IOException {
        String urlPath = String.format("%s/%s",customerApiBaseEndpoint, customerId);
        return executeGetRequestWith(urlPath, null, CustomerData.class);
    }

    /**
     * Search for customers
     * @param searchData
     * @return
     * @throws IOException
     */
    public CustomerList search(CustomerSearch searchData) throws IOException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("Skip", searchData.getSkip());
        queryParams.put("Limit", searchData.getLimit());
        queryParams.put("Reference", searchData.getReference());
        queryParams.put("PaymentMethodId", searchData.getPaymentMethodId());
        queryParams.put("CustomerId", searchData.getCustomerId());
        queryParams.put("Email", searchData.getEmail());
        queryParams.put("AddedAfter", searchData.getAddedAfter());
        queryParams.put("AddedBefore", searchData.getAddedBefore());
        queryParams.put("Search", searchData.getSearch());

        return executeGetRequestWith(customerApiBaseEndpoint, queryParams, CustomerList.class);
    }

    /**
     * Retrieve payment methods for a customer
     * @param customerId
     * @return
     * @throws IOException
     */
    public List<PaymentMethodData> getPaymentMethods(String customerId) throws IOException {
        String urlPath = String.format("%s/%s/payment_method",customerApiBaseEndpoint, customerId);
        PaymentMethodData[] paymentMethodData = executeGetRequestWith(urlPath, null, PaymentMethodData[].class);
        return Arrays.asList(paymentMethodData);
    }

    /**
     * Update customer information
     * @param customerId
     * @param updateCustomer
     * @return
     * @throws IOException
     */
    public CustomerData updateCustomer(String customerId, UpdateCustomer updateCustomer) throws IOException {
        return executePutRequestWith(customerApiBaseEndpoint + "/"+customerId, updateCustomer, CustomerData.class);
    }

    /**
     * Delete customer
     * @param customerId
     * @return
     * @throws IOException
     */
    public CustomerData deleteCustomer(String customerId) throws IOException {
        return executeDeleteRequestWith(customerApiBaseEndpoint + "/"+customerId, null, CustomerData.class);
    }

    /**
     * Add a customer with provider token
     * @param customerProviderToken
     * @return
     * @throws IOException
     */
    public CustomerData createWithProviderToken(NewCustomerProviderToken customerProviderToken) throws IOException {
        return executePostRequestWith(customerApiBaseEndpoint + "/provider_token", customerProviderToken, CustomerData.class);
    }
}
