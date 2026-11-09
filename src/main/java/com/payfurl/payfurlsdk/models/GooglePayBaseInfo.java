package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public class GooglePayBaseInfo {
    private String gateway;
    private String gatewayMerchantId;

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getGatewayMerchantId() {
        return gatewayMerchantId;
    }

    /** The API sends it as {@code googlePayGatewayMerchantId}. */
    @JsonAlias("googlePayGatewayMerchantId")
    public void setGatewayMerchantId(String gatewayMerchantId) {
        this.gatewayMerchantId = gatewayMerchantId;
    }
}
