package com.payfurl.payfurlsdk.http.client.support.request;

import com.payfurl.payfurlsdk.http.client.support.Headers;
import org.apache.commons.lang3.StringUtils;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    private final HttpMethod httpMethod;
    private final Headers headers;
    private final StringBuilder queryUrlBuilder;
    private final List<SimpleEntry<String, Object>> parameters;

    private Map<String, Object> queryParameters;

    public HttpRequest(HttpMethod method, StringBuilder queryUrlBuilder, Headers headers,
                       Map<String, Object> queryParameters, List<SimpleEntry<String, Object>> parameters) {
        this.httpMethod = method;
        this.queryUrlBuilder = queryUrlBuilder;
        this.headers = headers;
        this.queryParameters = queryParameters;
        this.parameters = parameters;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Headers getHeaders() {
        return headers;
    }

    public List<SimpleEntry<String, Object>> getParameters() {
        return parameters;
    }

    public Map<String, Object> getQueryParameters() {
        return queryParameters;
    }

    public String getQueryUrl() {
        return queryUrlBuilder.toString();
    }

    public void addQueryParameter(String key, Object value) {
        if (StringUtils.isEmpty(key) || value == null) {
            return;
        }

        if (queryParameters == null) {
            queryParameters = new HashMap<>();
        }

        queryParameters.put(key, value);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "httpMethod=" + httpMethod +
                ", headers=" + headers +
                ", queryUrlBuilder=" + queryUrlBuilder +
                ", parameters=" + parameters +
                ", queryParameters=" + queryParameters +
                '}';
    }
}
