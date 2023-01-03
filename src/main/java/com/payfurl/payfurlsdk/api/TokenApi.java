package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TokenApi extends BaseApi {
    private final String tokenApiBaseEndpoint;

    public TokenApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        tokenApiBaseEndpoint = String.format("%s/%s", baseUri, "token");
    }

    /**
     * Get Token by id
     * @param tokenId
     * @return
     * @throws IOException
     */
    public TokenData single(String tokenId) throws IOException {
        String urlPath = String.format("%s/%s", tokenApiBaseEndpoint, tokenId);
        return executeGetRequestWith(urlPath, null, TokenData.class);
    }


    /**
     * Search for tokens
     * @param searchData
     * @return
     * @throws IOException
     */
    public TokenList search(TokenSearch searchData) throws IOException {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("Skip", searchData.getSkip());
        queryParams.put("Limit", searchData.getLimit());
        queryParams.put("Status", searchData.getStatus());
        queryParams.put("ProviderId", searchData.getProviderId());
        queryParams.put("AddedAfter", searchData.getAddedAfter());
        queryParams.put("AddedBefore", searchData.getAddedBefore());
        queryParams.put("SortBy", searchData.getSortBy());

        return executeGetRequestWith(tokenApiBaseEndpoint, queryParams, TokenList.class);
    }
}
