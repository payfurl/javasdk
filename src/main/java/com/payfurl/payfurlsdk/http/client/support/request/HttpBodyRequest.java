package com.payfurl.payfurlsdk.http.client.support.request;

import com.payfurl.payfurlsdk.http.client.support.Headers;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class HttpBodyRequest extends HttpRequest {
    private final Object body;

    public HttpBodyRequest(HttpMethod method,
                           StringBuilder queryUrlBuilder,
                           Headers headers,
                           Map<String, Object> queryParameters,
                           Object body) {
        super(method, queryUrlBuilder, headers, queryParameters, null);
        this.body = ObjectUtils.defaultIfNull(body, StringUtils.EMPTY);
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
