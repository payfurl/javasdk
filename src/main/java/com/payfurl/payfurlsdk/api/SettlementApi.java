package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.Configuration;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.models.SettlementData;
import com.payfurl.payfurlsdk.models.SettlementSearch;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/** Settlements: what the gateways paid out to the account. */
public class SettlementApi extends BaseApi {
    private final String settlementApiBaseEndpoint;

    public SettlementApi(Configuration config, HttpClient httpClient, Map<AuthType, AuthHandler> authHandlers) {
        super(config, httpClient, authHandlers);
        settlementApiBaseEndpoint = config.getBaseUri() + "/settlements";
    }

    /**
     * {@code GET /settlements}: the settlements from the start date to the end date (today when not set). The API
     * takes whole days, so only the dates count, in UTC.
     */
    public List<SettlementData> search(SettlementSearch search) throws ApiException {
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("startDate", day(search.getStartDate()));
        queryParameters.put("endDate", day(search.getEndDate()));
        SettlementData[] settlements = executeGetRequestWith(settlementApiBaseEndpoint, queryParameters, SettlementData[].class);
        return settlements == null ? null : Arrays.asList(settlements);
    }

    /** The API parses these with the exact format yyyy-MM-dd. */
    private static String day(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(date);
    }
}
