package com.payfurl.payfurlsdk;

import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.http.client.support.Headers;

public interface Configuration {
    Environment getEnvironment();

    String getPayFurlVersion();

    Headers getAdditionalHeaders();

    String getUserAgentDetail();

    long getTimeout();

    String getSecretKey();

    String getBaseUri();

    /** How many times a GET is retried after a connection error, a timeout or a retryable API error. */
    default int getMaxRetries() {
        return 0;
    }

    /** The delay before the first retry, in milliseconds; it doubles for each retry after it. */
    default long getRetryDelay() {
        return 0;
    }
}
