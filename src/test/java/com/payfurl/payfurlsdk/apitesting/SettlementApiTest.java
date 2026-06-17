package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.api.SettlementApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.http.client.support.request.HttpMethod;
import com.payfurl.payfurlsdk.http.client.support.request.HttpRequest;
import com.payfurl.payfurlsdk.models.SettlementData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class SettlementApiTest {
    private static final String SETTLEMENTS_RESPONSE = "[{\"SettlementId\":\"settlement_123\",\"ProviderId\":\"provider_123\",\"ChargeId\":\"charge_123\",\"Amount\":\"12.34\"}]";

    @Test
    @DisplayName("When getSettlements is called with a start date Then omit a null end date")
    void testGetSettlementsWithStartDateOnly() throws ApiException {
        ApiTestSupport.FakeHttpClient httpClient = new ApiTestSupport.FakeHttpClient(SETTLEMENTS_RESPONSE);
        SettlementApi settlementApi = new SettlementApi(ApiTestSupport.testConfiguration(), httpClient, ApiTestSupport.authHandlers());

        List<SettlementData> settlements = settlementApi.getSettlements("2024-01-01");

        HttpRequest request = httpClient.getLastRequest();
        then(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
        then(ApiTestSupport.resolvedUrl(request)).isEqualTo(ApiTestSupport.BASE_URI + "/settlements?startDate=2024-01-01");
        then(settlements).hasSize(1);
        then(settlements.get(0).getSettlementId()).isEqualTo("settlement_123");
    }

    @Test
    @DisplayName("When getSettlements is called with start and end dates Then forward both dates")
    void testGetSettlementsWithStartAndEndDate() throws ApiException {
        ApiTestSupport.FakeHttpClient httpClient = new ApiTestSupport.FakeHttpClient(SETTLEMENTS_RESPONSE);
        SettlementApi settlementApi = new SettlementApi(ApiTestSupport.testConfiguration(), httpClient, ApiTestSupport.authHandlers());

        settlementApi.getSettlements("2024-01-01", "2024-01-31");

        HttpRequest request = httpClient.getLastRequest();
        then(request.getHttpMethod()).isEqualTo(HttpMethod.GET);
        then(ApiTestSupport.resolvedUrl(request)).contains(ApiTestSupport.BASE_URI + "/settlements?");
        then(ApiTestSupport.resolvedUrl(request)).contains("startDate=2024-01-01");
        then(ApiTestSupport.resolvedUrl(request)).contains("endDate=2024-01-31");
        then(request.getQueryParameters())
                .containsEntry("startDate", "2024-01-01")
                .containsEntry("endDate", "2024-01-31");
    }
}
