package com.payfurl.client.support.request;

import com.payfurl.client.support.HeadersData;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;

public class HttpBodyRequest extends HttpRequest {
    private Object body;

    public HttpBodyRequest(HttpMethod method,
                           StringBuilder queryUrlBuilder,
                           HeadersData headersData,
                           Map<String, Object> queryParameters,
                           Object body) {
        super(method, queryUrlBuilder, headersData, queryParameters, null);
        this.body = ObjectUtils.defaultIfNull(body, "");
    }

    public Object getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpBodyRequest{"
                + "body=" + body
                + '}';
    }
}
