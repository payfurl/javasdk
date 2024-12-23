package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.api.*;


public interface PayFurlClientSdk extends Configuration {
    ChargeApi getChargeApi();

    CustomerApi getCustomerApi();

    PaymentMethodApi getPaymentMethodApi();

    TransferApi getTransferApi();

    ProviderApi getProviderApi();

    VaultApi getVaultApi();

    TokenApi getTokenApi();

    BatchApi getBatchApi();

    SubscriptionApi getSubscriptionApi();

    WebhookSubscriptionApi getWebhookSubscriptionApi();

    String getSdkVersion();
}
