package com.payfurl.client.support.request;

import com.payfurl.client.support.HeadersData;
import org.apache.commons.lang3.StringUtils;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    private HttpMethod httpMethod;
    private HeadersData headersData;
    private StringBuilder queryUrlBuilder;
    private List<SimpleEntry<String, Object>> parameters;
    private Map<String, Object> queryParameters;

    public HttpRequest(HttpMethod method, StringBuilder queryUrlBuilder, HeadersData headersData,
                       Map<String, Object> queryParameters, List<SimpleEntry<String, Object>> parameters) {
        this.httpMethod = method;
        this.queryUrlBuilder = queryUrlBuilder;
        this.headersData = headersData;
        this.queryParameters = queryParameters;
        this.parameters = parameters;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public HeadersData getHeaders() {
        return headersData;
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
        return String.format("HttpRequest [httpMethod=%s, headers=%s, queryUrlBuilder=%s, queryParameters=%s, parameters=%s]",
                httpMethod, headersData, queryUrlBuilder, queryParameters, parameters);
    }
}
