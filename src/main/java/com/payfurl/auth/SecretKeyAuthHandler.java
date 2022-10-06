package com.payfurl.auth;

import com.payfurl.http.client.support.request.HttpRequest;

public class SecretKeyAuthHandler implements AuthHandler {
    private final String accessToken;

    public SecretKeyAuthHandler(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public HttpRequest apply(HttpRequest httpRequest) {
        httpRequest.getHeaders().add("x-secretkey", accessToken);
        httpRequest.getHeaders().add("Expect", "100-continue");
        return httpRequest;
    }
}
