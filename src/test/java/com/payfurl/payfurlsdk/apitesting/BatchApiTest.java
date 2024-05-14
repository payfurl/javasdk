package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.BatchApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.models.Batch.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BatchApiTest {
    private BatchApi batchApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        batchApi = payFurlClient.getBatchApi();
    }

    @Test
    @DisplayName("When createTransactionWithPaymentMethod request is executed, Then return valid batch status")
    void testCreateTransactionWithPaymentMethod() throws ApiException {
        // given
        NewTransactionPaymentMethod newTransactionPaymentMethod = getNewTransactionPaymentMethod();

        // when
        BatchStatus batchStatus = batchApi.createTransactionWithPaymentMethod(newTransactionPaymentMethod);

        // then
        assertThat(batchStatus.getStatus()).isEqualTo("RECEIVED");
    }

    @Test
    @DisplayName("When getBatch request is executed, Then return valid batch data")
    void testGetBatch() throws ApiException {
        // given
        NewTransactionPaymentMethod newTransactionPaymentMethod = getNewTransactionPaymentMethod();
        BatchStatus createdBatchStatus = batchApi.createTransactionWithPaymentMethod(newTransactionPaymentMethod);

        // when
        BatchData batchData = batchApi.getBatch(createdBatchStatus.getBatchId());

        // then
        assertThat(batchData.getStatus()).isEqualTo("RECEIVED");
        assertThat(batchData.getResults()).isEqualTo("PaymentMethodId,Amount,Currency,Reference,Status,TransactionId\r\n");
    }

    @Test
    @DisplayName("When getBatchStatus request is executed, Then return valid batch status")
    void testGetBatchStatus() throws ApiException {
        // given
        NewTransactionPaymentMethod newTransactionPaymentMethod = getNewTransactionPaymentMethod();
        BatchStatus createdBatchStatus = batchApi.createTransactionWithPaymentMethod(newTransactionPaymentMethod);

        // when
        BatchStatus batchStatus = batchApi.getBatchStatus(createdBatchStatus.getBatchId());

        // then
        assertThat(batchStatus.getStatus()).isEqualTo("RECEIVED");
    }

    @Test
    @DisplayName("When searchBatch request is executed, Then return valid batch list")
    void testSearchBatch() throws ApiException {
        // given
        String description = UUID.randomUUID().toString();
        NewTransactionPaymentMethod newTransactionPaymentMethod = getNewTransactionPaymentMethod(description);
        batchApi.createTransactionWithPaymentMethod(newTransactionPaymentMethod);

        BatchSearch batchSearch = new BatchSearch.Builder()
                .withDescription(description)
                .build();

        // when
        BatchList batchList = batchApi.searchBatch(batchSearch);

        // then
        assertThat(batchList.getCount()).isEqualTo(1);
        assertThat(batchList.getBatches().get(0).getDescription()).isEqualTo(description);
    }

    private NewTransactionPaymentMethod getNewTransactionPaymentMethod(String description) {
        return new NewTransactionPaymentMethod.Builder()
                .withCount(1)
                .withDescription(description)
                .withBatch("PaymentMethodId,Amount,Currency,Reference\ntest,123.4,AUD,reference")
                .build();
    }

    private NewTransactionPaymentMethod getNewTransactionPaymentMethod() {
        return getNewTransactionPaymentMethod("Test");
    }
}