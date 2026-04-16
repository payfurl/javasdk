package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.SettlementData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlementApi extends BaseApi {
    private final String settlementApiBaseEndpoint;

    public SettlementApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        settlementApiBaseEndpoint = String.format("%s/%s", baseUri, "settlements");
    }

    public List<SettlementData> getSettlements(String startDate) throws ApiException {
        return getSettlements(startDate, null);
    }

    public List<SettlementData> getSettlements(String startDate, String endDate) throws ApiException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("startDate", startDate);
        queryParams.put("endDate", endDate);
        SettlementData[] settlementData = executeGetRequestWith(settlementApiBaseEndpoint, queryParams, SettlementData[].class);
        return Arrays.asList(settlementData);
    }
}
