package com.payfurl.api;

import com.payfurl.Configuration;
import com.payfurl.auth.AuthHandler;
import com.payfurl.auth.AuthType;
import com.payfurl.http.client.HttpClient;
import com.payfurl.models.ChargeData;
import com.payfurl.models.NewChargeCardRequest;

import java.io.IOException;
import java.util.Map;

public class ChargeApi extends BaseApi {
    private static final String CHARGE_API_PATH_PART = "charge";

    private final String chargeApiBaseEndpoint;

    public ChargeApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);

        String baseUri = config.getBaseUri();
        chargeApiBaseEndpoint = String.format("%s/%s", baseUri, CHARGE_API_PATH_PART);
    }

    public ChargeData createWithCard(NewChargeCardRequest newChargeCardRequest) throws IOException {
        throw new UnsupportedOperationException();
    }
}
