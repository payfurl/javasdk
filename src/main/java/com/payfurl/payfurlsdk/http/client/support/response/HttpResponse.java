package com.payfurl.payfurlsdk.http.client.support.response;

import com.payfurl.payfurlsdk.http.client.support.Headers;

import java.io.InputStream;

public class HttpResponse {
    private final int statusCode;
    private final Headers headers;
    private final InputStream rawBody;

    public HttpResponse(int statusCode, Headers headers, InputStream rawBody) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.rawBody = rawBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Headers getHeaders() {
        return headers;
    }

    public InputStream getRawBody() {
        return rawBody;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", headers=" + headers +
                ", rawBody=" + rawBody +
                '}';
    }
}
