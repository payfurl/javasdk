package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.DisputeData;
import com.payfurl.payfurlsdk.models.DisputeList;
import com.payfurl.payfurlsdk.models.DisputeSearch;

import java.util.HashMap;
import java.util.Map;

/** Disputes (chargebacks) raised against the account's charges. */
public class DisputeApi extends BaseApi {
    private final String disputeApiBaseEndpoint;

    public DisputeApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);
        disputeApiBaseEndpoint = config.getBaseUri() + "/dispute";
    }

    /** {@code GET /dispute/{disputeId}} */
    public DisputeData single(String disputeId) throws ApiException {
        return executeGetRequestWith(disputeApiBaseEndpoint + "/" + disputeId, null, DisputeData.class);
    }

    /** {@code GET /dispute}: the disputes that match every filter set. */
    public DisputeList search(DisputeSearch search) throws ApiException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("TransactionId", search.getTransactionId());
        queryParameters.put("ProviderId", search.getProviderId());
        queryParameters.put("Status", search.getStatus());
        queryParameters.put("Stage", search.getStage());
        queryParameters.put("ReasonCategory", search.getReasonCategory());
        queryParameters.put("Currency", search.getCurrency());
        queryParameters.put("AmountGreaterThan", search.getAmountGreaterThan());
        queryParameters.put("AmountLessThan", search.getAmountLessThan());
        queryParameters.put("AddedAfter", search.getAddedAfter());
        queryParameters.put("AddedBefore", search.getAddedBefore());
        queryParameters.put("DueBefore", search.getDueBefore());
        queryParameters.put("SortBy", search.getSortBy());
        queryParameters.put("SortOrder", search.getSortOrder());
        queryParameters.put("Limit", search.getLimit());
        queryParameters.put("Skip", search.getSkip());
        return executeGetRequestWith(disputeApiBaseEndpoint, queryParameters, DisputeList.class);
    }
}
