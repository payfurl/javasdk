package com.payfurl.client.support.response;

import com.payfurl.client.support.HeadersData;

import java.io.InputStream;

public class HttpResponse {
    private final int statusCode;
    private final HeadersData headersData;
    private final InputStream rawBody;

    public HttpResponse(int statusCode, HeadersData headersData, InputStream rawBody) {
        this.statusCode = statusCode;
        this.headersData = headersData;
        this.rawBody = rawBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HeadersData getHeaders() {
        return headersData;
    }

    public InputStream getRawBody() {
        return rawBody;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", headers=" + headersData +
                ", rawBody=" + rawBody +
                '}';
    }
}
