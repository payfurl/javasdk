package com.payfurl.client.support.response;

import com.payfurl.client.support.HeadersData;

import java.io.InputStream;

public class HttpStringResponse extends HttpResponse {
    private final String body;

    public HttpStringResponse(int statusCode, HeadersData headersData, InputStream rawBody, String body) {
        super(statusCode, headersData, rawBody);
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
