package com.payfurl.auth;

import com.payfurl.http.client.support.request.HttpRequest;

public interface AuthHandler {
    HttpRequest apply(HttpRequest httpRequest);
}
