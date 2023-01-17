package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.api.ProviderApi;
import com.payfurl.payfurlsdk.api.ChargeApi;
import com.payfurl.payfurlsdk.api.TokenApi;
import com.payfurl.payfurlsdk.api.VaultApi;
import com.payfurl.payfurlsdk.api.CustomerApi;
import com.payfurl.payfurlsdk.api.TransferApi;
import com.payfurl.payfurlsdk.api.PaymentMethodApi;


public interface PayFurlClientSdk extends Configuration {
    ChargeApi getChargeApi();

    CustomerApi getCustomerApi();

    PaymentMethodApi getPaymentMethodApi();

    TransferApi getTransferApi();

    ProviderApi getProviderApi();

    VaultApi getVaultApi();

    TokenApi getTokenApi();

    String getSdkVersion();
}
