package com.payfurl;

import com.payfurl.api.ChargeApi;

public interface PayFurlClientSdk extends Configuration {
    ChargeApi getChargeApi();

    String getSdkVersion();
}
