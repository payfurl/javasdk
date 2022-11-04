package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.http.client.support.Headers;

public interface Configuration {
    Environment getEnvironment();
    String getPayFurlVersion();
    Headers getAdditionalHeaders();
    String getUserAgentDetail();
    long timeout();
    String getAccessToken();
    String getBaseUri();
}
