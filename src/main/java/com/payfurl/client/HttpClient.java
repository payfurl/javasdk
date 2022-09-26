package com.payfurl.client;

import com.payfurl.client.support.HeadersData;
import com.payfurl.client.support.request.HttpRequest;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;

public interface HttpClient {

    public HttpRequest get(StringBuilder queryUrlBuilder,
                           HeadersData headersData,
                           Map<String, Object> queryParams,
                           List<SimpleEntry<String, Object>> parameters);

    public HttpRequest post(StringBuilder queryUrlBuilder,
                            HeadersData headersData,
                            Map<String, Object> queryParams,
                            List<SimpleEntry<String, Object>> parameters);

    public HttpRequest put(StringBuilder queryUrlBuilder,
                           HeadersData headersData,
                           Map<String, Object> queryParams,
                           List<SimpleEntry<String, Object>> parameters);

    public HttpRequest delete(StringBuilder queryUrlBuilder,
                              HeadersData headersData,
                              Map<String, Object> queryParams,
                              List<SimpleEntry<String, Object>> parameters);

}
