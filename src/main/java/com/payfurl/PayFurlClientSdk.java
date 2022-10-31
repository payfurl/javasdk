package com.payfurl;

import com.payfurl.api.ChargeApi;
import com.payfurl.api.CustomerApi;
import com.payfurl.api.PaymentMethodApi;
import com.payfurl.api.TransferApi;
import com.payfurl.api.VaultApi;

public interface PayFurlClientSdk extends Configuration {
    ChargeApi getChargeApi();

    CustomerApi getCustomerApi();

    PaymentMethodApi getPaymentMethodApi();

    TransferApi getTransferApi();

    VaultApi getVaultApi();

    String getSdkVersion();
}
