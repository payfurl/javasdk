package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.NewTransferGroup;
import com.payfurl.payfurlsdk.models.TransferData;
import com.payfurl.payfurlsdk.models.TransferList;
import com.payfurl.payfurlsdk.models.TransferSearch;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferApi extends BaseApi {
    private final String transferApiBaseEndpoint;

    public TransferApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        transferApiBaseEndpoint = String.format("%s/%s", baseUri, "transfer");
    }

    /**
     * Add a transfer
     * @param newTransferGroup
     * @return
     * @throws IOException
     */
    public List<TransferData> create(NewTransferGroup newTransferGroup) throws IOException {
        TransferData[] transferData = executePostRequestWith(transferApiBaseEndpoint, newTransferGroup, TransferData[].class);
        return Arrays.asList(transferData);
    }

    /**
     * Retrieve a single transfer
     * @param transferId
     * @return
     * @throws IOException
     */
    public TransferData single(String transferId) throws IOException {
        String urlPath = String.format("%s/%s", transferApiBaseEndpoint, transferId);
        return executeGetRequestWith(urlPath, null, TransferData.class);
    }

    /**
     * Search for transfers
     * @param searchData
     * @return
     * @throws IOException
     */
    public TransferList search(TransferSearch searchData) throws IOException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("Skip", searchData.getSkip());
        queryParams.put("Limit", searchData.getLimit());
        queryParams.put("Reference", searchData.getReference());
        queryParams.put("ProviderId", searchData.getProviderId());
        queryParams.put("Status", searchData.getStatus());
        queryParams.put("AddedAfter", searchData.getAddedAfter());
        queryParams.put("AddedBefore", searchData.getAddedBefore());
        queryParams.put("SortBy", searchData.getSortBy());

        return executeGetRequestWith(transferApiBaseEndpoint, queryParams, TransferList.class);
    }
}
