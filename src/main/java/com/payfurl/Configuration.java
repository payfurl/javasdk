package com.payfurl;

import com.payfurl.http.client.config.Environment;
import com.payfurl.http.client.support.Headers;

public interface Configuration {
    Environment getEnvironment();
    String getPayFurlVersion();
    Headers getAdditionalHeaders();
    String getUserAgentDetail();
    long timeout();
    String getAccessToken();
    String getBaseUri();
}
