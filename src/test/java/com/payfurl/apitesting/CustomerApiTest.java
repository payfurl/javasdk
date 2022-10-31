package com.payfurl.apitesting;

import com.payfurl.PayFurlClient;
import com.payfurl.api.CustomerApi;
import com.payfurl.http.client.config.Environment;
import com.payfurl.models.CardRequestInformation;
import com.payfurl.models.CustomerData;
import com.payfurl.models.CustomerList;
import com.payfurl.models.CustomerSearch;
import com.payfurl.models.NewCustomerCard;
import com.payfurl.models.NewCustomerToken;
import com.payfurl.models.NewPaymentMethodCard;
import com.payfurl.models.NewPaymentMethodToken;
import com.payfurl.models.PaymentMethodData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

public class CustomerApiTest {
    private static final String LOCAL_ACCESS_TEST_TOKEN = "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/22")
            .withCcv("123")
            .build();

    private CustomerApi customerApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(Environment.LOCAL)
                .withAccessToken(LOCAL_ACCESS_TEST_TOKEN)
                .build();

        customerApi = payFurlClient.getCustomerApi();
    }

    @Nested
    @DisplayName("Given valid request to Customer API")
    class SuccessFlow {
        @Test
        @DisplayName("When createWithCard request is executed, Then return valid CustomerData")
        void testCreateWithCard() throws IOException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            // then
            then(customerData.getCustomerId()).isNotNull();
        }

        @Test
        @DisplayName("When search request is executed, Then return valid CustomerList")
        void testSearch() throws IOException {
            // given
            String reference = UUID.randomUUID().toString();

            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withReference(reference)
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            CustomerSearch customerSearch = new CustomerSearch.Builder()
                    .withReference(reference)
                    .build();
            CustomerList customerList = customerApi.search(customerSearch);

            // then
            then(customerData).isNotNull();
            then(customerList.getCount()).isEqualTo(1);
        }

        @Test
        @DisplayName("When createPaymentMethodWithCard request is executed, Then return valid CustomerList")
        void testCreatePaymentMethodWithCard() throws IOException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            PaymentMethodData paymentMethodData = customerApi.createPaymentMethodWithCard(customerData.getCustomerId(), newPaymentMethodCard);

            // then
            then(paymentMethodData.getPaymentMethodId()).isNotNull();
        }

        @Test
        @Disabled("tokens expire, so this test needs to be adjusted each time it's run")
        @DisplayName("When createPaymentMethodWithToken request is executed, Then return valid paymentMethodData")
        void testCreatePaymentMethodWithToken() throws IOException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            NewPaymentMethodToken newPaymentMethodCard = new NewPaymentMethodToken.Builder()
                    .withToken("4f0fb10355224034a1df949852de34e1")
                    .build();

            PaymentMethodData paymentMethodData = customerApi.createWitPaymentMethodWithToken(customerData.getCustomerId(), newPaymentMethodCard);

            // then
            then(paymentMethodData.getPaymentMethodId()).isNotNull();
        }

        @Test
        @Disabled("tokens expire, so this test needs to be adjusted each time it's run")
        @DisplayName("When createWithToken request is executed, Then return valid customerData")
        void testCreateWithToken() throws IOException {
            // given
            NewCustomerToken newCustomerToken = new NewCustomerToken.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withToken("5dc5d0d4ec7c4d057cb00484")
                    .build();

            // when
            CustomerData customerData = customerApi.createWithToken(newCustomerToken);

            // then
            then(customerData.getCustomerId()).isNotNull();
        }

        @Test
        @DisplayName("When getPaymentMethods request is executed, Then return valid CustomerList")
        void testGetPaymentMethods() throws IOException {
            // given
            String reference = UUID.randomUUID().toString();

            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withReference(reference)
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            CustomerSearch customerSearch = new CustomerSearch.Builder()
                    .withReference(reference)
                    .build();
            CustomerList customerList = customerApi.search(customerSearch);

            String customerId = customerList.getCustomers().get(0).getCustomerId();
            List<PaymentMethodData> paymentMethodDataList = customerApi.getPaymentMethods(customerId);

            // then
            then(customerData).isNotNull();
            then(paymentMethodDataList).hasSize(1);
        }

        @Test
        @DisplayName("When Single request is executed, Then return valid CustomerData")
        void testSingle() throws IOException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    //      .withReference(reference)
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);
            CustomerData foundCustomerData = customerApi.single(customerData.getCustomerId());

            // then
            then(customerData.getCustomerId()).isEqualTo(foundCustomerData.getCustomerId());
            then(customerData.getFirstName()).isEqualTo(foundCustomerData.getFirstName());
            then(customerData.getLastName()).isEqualTo(foundCustomerData.getLastName());
        }
    }
}
