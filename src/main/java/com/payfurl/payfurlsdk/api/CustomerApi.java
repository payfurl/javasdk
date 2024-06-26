package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.CustomerData;
import com.payfurl.payfurlsdk.models.CustomerList;
import com.payfurl.payfurlsdk.models.CustomerSearch;
import com.payfurl.payfurlsdk.models.NewCustomerCard;
import com.payfurl.payfurlsdk.models.NewCustomerProviderToken;
import com.payfurl.payfurlsdk.models.NewCustomerToken;
import com.payfurl.payfurlsdk.models.NewPayToAgreement;
import com.payfurl.payfurlsdk.models.NewPaymentMethodCard;
import com.payfurl.payfurlsdk.models.NewPaymentMethodToken;
import com.payfurl.payfurlsdk.models.PaymentMethodData;
import com.payfurl.payfurlsdk.models.UpdateCustomer;
import com.payfurl.payfurlsdk.models.NewCustomerProviderSingleUseToken;
import com.payfurl.payfurlsdk.models.NewCustomerBankPayment;
import com.payfurl.payfurlsdk.models.NewPaymentMethodBankPayment;

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
     *
     * @param newCustomerCard
     * @return
     * @throws ApiException
     */
    public CustomerData createWithCard(NewCustomerCard newCustomerCard) throws ApiException {
        return executePostRequestWith(customerApiBaseEndpoint + "/card", newCustomerCard, CustomerData.class);
    }

    /**
     * Add a customer using a payment token
     *
     * @param newCustomerToken
     * @return
     * @throws ApiException
     */
    public CustomerData createWithToken(NewCustomerToken newCustomerToken) throws ApiException {
        return executePostRequestWith(customerApiBaseEndpoint + "/token", newCustomerToken, CustomerData.class);
    }

    /**
     * Add a payment method using a card
     *
     * @param customerId
     * @param newPaymentMethodCard
     * @return
     * @throws ApiException
     */
    public PaymentMethodData createPaymentMethodWithCard(String customerId, NewPaymentMethodCard newPaymentMethodCard) throws ApiException {
        String urlPath = String.format("%s/%s/payment_method/card", customerApiBaseEndpoint, customerId);
        return executePostRequestWith(urlPath, newPaymentMethodCard, PaymentMethodData.class);
    }

    /**
     * Add a payment method using a token
     *
     * @param customerId
     * @param newPaymentMethodToken
     * @return
     * @throws ApiException
     */
    public PaymentMethodData createWitPaymentMethodWithToken(String customerId, NewPaymentMethodToken newPaymentMethodToken) throws ApiException {
        String urlPath = String.format("%s/%s/payment_method/token", customerApiBaseEndpoint, customerId);
        return executePostRequestWith(urlPath, newPaymentMethodToken, PaymentMethodData.class);
    }

    /**
     * Add a payment method using a payTo
     *
     * @param customerId
     * @param newPayToAgreement
     * @return
     * @throws ApiException
     */
    public PaymentMethodData createWitPaymentMethodWithPayTo(String customerId, NewPayToAgreement newPayToAgreement) throws ApiException {
        String urlPath = String.format("%s/%s/payment_method/payto", customerApiBaseEndpoint, customerId);
        return executePostRequestWith(urlPath, newPayToAgreement, PaymentMethodData.class);
    }

    /**
     * Retrieve a single customer
     *
     * @param customerId
     * @return
     * @throws ApiException
     */
    public CustomerData single(String customerId) throws ApiException {
        String urlPath = String.format("%s/%s", customerApiBaseEndpoint, customerId);
        return executeGetRequestWith(urlPath, null, CustomerData.class);
    }

    /**
     * Search for customers
     *
     * @param searchData
     * @return
     * @throws ApiException
     */
    public CustomerList search(CustomerSearch searchData) throws ApiException {
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
     *
     * @param customerId
     * @return
     * @throws ApiException
     */
    public List<PaymentMethodData> getPaymentMethods(String customerId) throws ApiException {
        String urlPath = String.format("%s/%s/payment_method", customerApiBaseEndpoint, customerId);
        PaymentMethodData[] paymentMethodData = executeGetRequestWith(urlPath, null, PaymentMethodData[].class);
        return Arrays.asList(paymentMethodData);
    }

    /**
     * Update customer information
     *
     * @param customerId
     * @param updateCustomer
     * @return
     * @throws ApiException
     */
    public CustomerData updateCustomer(String customerId, UpdateCustomer updateCustomer) throws ApiException {
        return executePutRequestWith(customerApiBaseEndpoint + "/" + customerId, updateCustomer, CustomerData.class);
    }

    /**
     * Delete customer
     *
     * @param customerId
     * @return
     * @throws ApiException
     */
    public CustomerData deleteCustomer(String customerId) throws ApiException {
        return executeDeleteRequestWith(customerApiBaseEndpoint + "/" + customerId, null, CustomerData.class);
    }

    /**
     * Add a customer with provider token
     *
     * @param customerProviderToken
     * @return
     * @throws ApiException
     */
    public CustomerData createWithProviderToken(NewCustomerProviderToken customerProviderToken) throws ApiException {
        return executePostRequestWith(customerApiBaseEndpoint + "/provider_token", customerProviderToken, CustomerData.class);
    }

    /**
     * Add a customer with provider single use token
     *
     * @param customerProviderSingleUseToken
     * @return
     * @throws ApiException
     */
    public CustomerData createWithSingleUseToken(NewCustomerProviderSingleUseToken customerProviderSingleUseToken) throws ApiException {
        return executePostRequestWith(customerApiBaseEndpoint + "/provider_single_use_token", customerProviderSingleUseToken, CustomerData.class);
    }
    
    /**
     * Add a customer with a bank account
     *
     * @param customerBankPayment
     * @return
     * @throws ApiException
     */
    public CustomerData createWithBankAccount(NewCustomerBankPayment customerBankPayment) throws ApiException {
        return executePostRequestWith(customerApiBaseEndpoint + "/bank_account", customerBankPayment, CustomerData.class);
    }
    
    /**
     * Add a payment method using a bank account
     *
     * @param customerId
     * @param newPaymentMethodBankPayment
     * @return
     * @throws ApiException
     */
    public PaymentMethodData createWithPaymentMethodWithBankAccount(String customerId, NewPaymentMethodBankPayment newPaymentMethodBankPayment) throws ApiException {
        String urlPath = String.format("%s/%s/bank_account", customerApiBaseEndpoint, customerId);
        return executePostRequestWith(urlPath, newPaymentMethodBankPayment, PaymentMethodData.class);
    }
}
