package com.payfurl;

import com.payfurl.api.ChargeApi;
import com.payfurl.api.CustomerApi;

public interface PayFurlClientSdk extends Configuration {
    ChargeApi getChargeApi();

    CustomerApi getCustomerApi();

    String getSdkVersion();
}
