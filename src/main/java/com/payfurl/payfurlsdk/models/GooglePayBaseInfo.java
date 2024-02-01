package com.payfurl.payfurlsdk.models;

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

    public void setGatewayMerchantId(String gatewayMerchantId) {
        this.gatewayMerchantId = gatewayMerchantId;
    }
}
