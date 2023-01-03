package com.payfurl.payfurlsdk.auth;

import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;

public class SecretKeyAuthHandler implements AuthHandler {
    private final String secretKey;

    public SecretKeyAuthHandler(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public HttpRequest apply(HttpRequest httpRequest) {
        httpRequest.getHeaders().add("x-secretkey", secretKey);
        httpRequest.getHeaders().add("Expect", "100-continue");
        return httpRequest;
    }
}

