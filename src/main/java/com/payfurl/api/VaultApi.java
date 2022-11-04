package com.payfurl.api;

import com.payfurl.Configuration;
import com.payfurl.auth.AuthHandler;
import com.payfurl.auth.AuthType;
import com.payfurl.http.client.HttpClient;
import com.payfurl.models.NewVault;
import com.payfurl.models.VaultData;
import com.payfurl.models.VaultDataWithPci;

import java.io.IOException;
import java.util.Map;

public class VaultApi extends BaseApi {
    private final String vaultApiBaseEndpoint;

    public VaultApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        vaultApiBaseEndpoint = String.format("%s/%s", baseUri, "vault");
    }

    /**
     * Create a new vault item
     * @param newVault
     * @return
     * @throws IOException
     */
    public VaultData create(NewVault newVault) throws IOException {
        return executePostRequestWith(vaultApiBaseEndpoint, newVault, VaultData.class);
    }

    /**
     * Delete a vault item
     * @param vaultId
     * @return
     * @throws IOException
     */
    public VaultData delete(String vaultId) throws IOException {
        String urlPath = String.format("%s/%s", vaultApiBaseEndpoint, vaultId);
        return executeDeleteRequestWith(urlPath, null, VaultData.class);
    }

    /**
     * Get a vault item
     * @param vaultId
     * @return
     * @throws IOException
     */
    public VaultDataWithPci single(String vaultId) throws IOException {
        String urlPath = String.format("%s/%s", vaultApiBaseEndpoint, vaultId);
        return executeGetRequestWith(urlPath, null, VaultDataWithPci.class);
    }
}
