package com.payfurl.http.client.support.response;

import com.payfurl.http.client.support.Headers;

import java.io.InputStream;

public class HttpStringResponse extends HttpResponse {
    private final String body;

    public HttpStringResponse(int statusCode, Headers headers, InputStream rawBody, String body) {
        super(statusCode, headers, rawBody);
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpStringResponse{"
                + "body=" + body
                + '}';
    }
}
