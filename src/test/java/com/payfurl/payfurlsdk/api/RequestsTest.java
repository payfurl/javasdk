package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.models.*;
import com.payfurl.payfurlsdk.support.MockApiTest;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Request bodies and queries: the names the API reads. */
class RequestsTest extends MockApiTest {

    @Test
    void aTokenChargeSendsItsTransferAsTransferAndItsIp() throws Exception {
        enqueueJson("{}");

        client.getChargeApi().createWithToken(new NewChargeToken.Builder()
                .withAmount(new BigDecimal("20.00"))
                .withCurrency("AUD")
                .withToken("tok_1")
                .withCheckoutTransfer(new CheckoutTransfer.Builder().withAccount("acct_1").withAmount(BigDecimal.ONE).build())
                .withIp("203.0.113.7")
                .build());

        String body = takeRequest().getBody().readUtf8();
        assertThat(body).contains("\"Transfer\":{").doesNotContain("checkoutTransfer")
                .contains("\"token\":\"tok_1\"").contains("\"amount\":\"20.00\"").contains("\"ip\":\"203.0.113.7\"");
    }

    @Test
    void fieldsAddedIn11AreLeftOutWhenNotSet() throws Exception {
        enqueueJson("{}");

        client.getCustomerApi().createWithCard(new NewCustomerCard.Builder().build());

        String body = takeRequest().getBody().readUtf8();
        assertThat(body).doesNotContain("\"ip\"", "\"verify\"", "\"createNetworkToken\"", "\"webhook\"");
    }

    @Test
    void aCustomerCardSendsVerifyAndCreateNetworkToken() throws Exception {
        enqueueJson("{}");

        client.getCustomerApi().createWithCard(new NewCustomerCard.Builder().withVerify(true).withCreateNetworkToken(true).build());

        assertThat(takeRequest().getBody().readUtf8()).contains("\"verify\":true", "\"createNetworkToken\":true");
    }

    @Test
    void aSettlementSearchSendsWholeDays() throws Exception {
        enqueueJson("[{}]");

        List<SettlementData> settlements = client.getSettlementApi().search(new SettlementSearch.Builder()
                .withStartDate(new Date(1762500000000L)) // 2025-11-07T07:20:00Z
                .build());

        RecordedRequest request = takeRequest();
        assertThat(query(request, "startDate")).isEqualTo("2025-11-07");
        assertThat(query(request, "endDate")).isNull();
        assertThat(settlements).hasSize(1);
    }

    @Test
    void aDisputeSearchSendsItsFilters() throws Exception {
        enqueueJson("{\"disputes\":[],\"count\":0}");

        client.getDisputeApi().search(new DisputeSearch.Builder()
                .withStatus(DisputeStatus.values()[0])
                .withAddedAfter(new Date(0))
                .withLimit(5)
                .build());

        RecordedRequest request = takeRequest();
        assertThat(query(request, "Status")).isEqualTo(DisputeStatus.values()[0].toString());
        assertThat(query(request, "AddedAfter")).isEqualTo("1970-01-01T00:00:00Z");
        assertThat(query(request, "Limit")).isEqualTo("5");
    }

    @Test
    void searchesSendTheFiltersAddedIn11() throws Exception {
        enqueueJson("{}");
        enqueueJson("{}");

        client.getChargeApi().search(new ChargeSearch.Builder().withBatchId("ba_1").withSubscriptionId("su_1").withSortOrder("asc").build());
        client.getCustomerApi().search(new CustomerSearch.Builder().withIncludeRemoved(true).build());

        RecordedRequest charges = takeRequest();
        assertThat(query(charges, "BatchId")).isEqualTo("ba_1");
        assertThat(query(charges, "SubscriptionId")).isEqualTo("su_1");
        assertThat(query(charges, "SortOrder")).isEqualTo("asc");
        assertThat(query(takeRequest(), "IncludeRemoved")).isEqualTo("true");
    }

    @Test
    void theAccountStatusAndVersion() {
        enqueueJson("{\"status\":\"ok\"}");
        enqueueJson("{\"version\":\"1.10.15\",\"released\":\"2026-11-02T00:00:00Z\",\"region\":\"au\"}");

        assertThat(client.getRootApi().getStatus().getStatus()).isEqualTo("ok");
        ApplicationVersion version = client.getRootApi().getVersion();
        assertThat(version.getVersion()).isEqualTo("1.10.15");
        assertThat(version.getRegion()).isEqualTo("au");
    }

    @Test
    void metadataIsSent() throws Exception {
        enqueueJson("{}");

        client.getChargeApi().createWithToken(new NewChargeToken.Builder()
                .withMetadata(Collections.singletonMap("order", "1001")).build());

        assertThat(takeRequest().getBody().readUtf8()).contains("\"metadata\":{\"order\":\"1001\"}");
    }
}
