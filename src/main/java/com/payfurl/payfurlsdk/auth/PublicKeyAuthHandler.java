package com.payfurl.payfurlsdk.auth;

import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;

public class PublicKeyAuthHandler implements AuthHandler {
    private final String publicKey;

    public PublicKeyAuthHandler(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    @Override
    public HttpRequest apply(HttpRequest httpRequest) {
        httpRequest.getHeaders().add("x-publickey", publicKey);
        httpRequest.getHeaders().add("Expect", "100-continue");
        return httpRequest;
    }
}
