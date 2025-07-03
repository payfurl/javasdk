package com.payfurl.payfurlsdk.apitesting;


import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.PaymentLinkApi;
import com.payfurl.payfurlsdk.models.PaymentLink.CreatePaymentLink;
import com.payfurl.payfurlsdk.models.PaymentLink.PaymentLinkData;
import com.payfurl.payfurlsdk.models.PaymentLink.SearchPaymentLink;
import com.payfurl.payfurlsdk.models.PaymentLink.SearchPaymentLinkResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.file.Paths;

public class PaymentLinkApiTest {

    private PaymentLinkApi paymentLinkApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        paymentLinkApi = payFurlClient.getPaymentLinkApi();
    }

    @Test
    public void createPaymentLink() throws Exception {
        PaymentLinkData result = paymentLinkApi.create(generateCreatePaymentLink());
        Assertions.assertNotNull(result);
    }

    @Test
    public void getPaymentLink() throws Exception {
        PaymentLinkData result = paymentLinkApi.create(generateCreatePaymentLink());
        PaymentLinkData paymentLink = paymentLinkApi.single(result.getPaymentLinkId());
        Assertions.assertNotNull(paymentLink);
    }

    @Test
    public void searchPaymentLink() throws Exception {
        PaymentLinkData result = paymentLinkApi.create(generateCreatePaymentLink());
        SearchPaymentLinkResult searchResult = paymentLinkApi.search(new SearchPaymentLink());
        Assertions.assertNotNull(searchResult);
        Assertions.assertNotNull(searchResult.getPaymentLinks());
        Assertions.assertFalse(searchResult.getPaymentLinks().isEmpty());
        Assertions.assertTrue(
                searchResult.getPaymentLinks().stream()
                        .anyMatch(pl -> pl.getPaymentLinkId().equals(result.getPaymentLinkId()))
        );
    }

    private static CreatePaymentLink generateCreatePaymentLink() {
        return new CreatePaymentLink.Builder()
                .withTitle("Test Payment Link")
                .withAmount(BigDecimal.valueOf(1000))
                .withCurrency("USD")
                .withImage(CreatePaymentLink.encodeImage(getResourcePath("100x50.png")))
                .build();
    }

    private static String getResourcePath(String resourceName) {
        try {
            return Paths.get(PaymentLinkApiTest.class.getClassLoader()
                    .getResource(resourceName).toURI()).toString();
        } catch (Exception e) {
            throw new RuntimeException("Could not find resource: " + resourceName, e);
        }
    }
}
