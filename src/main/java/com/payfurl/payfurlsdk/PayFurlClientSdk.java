package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.api.*;

public interface PayFurlClientSdk extends Configuration {
    ChargeApi getChargeApi();

    CustomerApi getCustomerApi();

    PaymentMethodApi getPaymentMethodApi();

    TransferApi getTransferApi();

    VaultApi getVaultApi();

    TokenApi getTokenApi();

    String getSdkVersion();
}
