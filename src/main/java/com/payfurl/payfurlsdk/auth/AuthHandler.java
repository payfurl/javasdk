package com.payfurl.payfurlsdk.auth;

import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;

public interface AuthHandler {
    HttpRequest apply(HttpRequest httpRequest);
}
