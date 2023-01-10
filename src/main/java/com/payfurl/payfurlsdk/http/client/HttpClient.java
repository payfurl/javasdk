package com.payfurl.payfurlsdk.http.client;

import com.payfurl.payfurlsdk.http.client.support.Headers;
import com.payfurl.payfurlsdk.http.client.support.request.HttpBodyRequest;
import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;
import com.payfurl.payfurlsdk.http.client.support.response.HttpResponse;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;

public interface HttpClient {

    HttpResponse execute(HttpRequest httpRequest) throws IOException;

    HttpRequest prepareGetRequest(StringBuilder queryUrlBuilder,
                                  Headers headers,
                                  Map<String, Object> queryParams,
                                  List<SimpleEntry<String, Object>> parameters);

    HttpRequest preparePostRequest(StringBuilder queryUrlBuilder,
                                   Headers headers,
                                   Map<String, Object> queryParams,
                                   List<SimpleEntry<String, Object>> parameters);

    HttpBodyRequest preparePostBodyRequest(StringBuilder queryUrlBuilder,
                                           Headers headers,
                                           Map<String, Object> queryParams,
                                           Object body);

    HttpBodyRequest preparePutBodyRequest(StringBuilder queryUrlBuilder,
                                          Headers headers,
                                          Map<String, Object> queryParams,
                                          Object body);

    HttpRequest prepareDeleteRequest(StringBuilder queryUrlBuilder,
                                     Headers headers,
                                     Map<String, Object> queryParams,
                                     List<SimpleEntry<String, Object>> parameters);

}
