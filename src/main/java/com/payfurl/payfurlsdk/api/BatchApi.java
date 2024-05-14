package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.Batch.*;

import java.util.HashMap;
import java.util.Map;

public class BatchApi extends BaseApi {
    private final String batchApiBaseEndpoint;

    public BatchApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        batchApiBaseEndpoint = String.format("%s/%s", baseUri, "batch");
    }

    public BatchStatus createTransactionWithPaymentMethod(NewTransactionPaymentMethod data) throws ApiException {
        return executePostRequestWith(batchApiBaseEndpoint + "/transaction/payment_method", data, BatchStatus.class);
    }

    public BatchData getBatch(String batchId) throws ApiException {
        return executeGetRequestWith(batchApiBaseEndpoint + "/" + batchId, null, BatchData.class);
    }

    public BatchStatus getBatchStatus(String batchId) throws ApiException {
        return executeGetRequestWith(batchApiBaseEndpoint + "/" + batchId + "/status", null, BatchStatus.class);
    }

    public BatchList searchBatch(BatchSearch search) throws ApiException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("Skip", search.getSkip());
        queryParameters.put("Limit", search.getLimit());
        queryParameters.put("Description", search.getDescription());
        queryParameters.put("AddedAfter", search.getAddedAfter());
        queryParameters.put("AddedBefore", search.getAddedBefore());

        return executeGetRequestWith(batchApiBaseEndpoint, queryParameters, BatchList.class);
    }
}
