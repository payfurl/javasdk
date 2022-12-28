package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.CustomerApi;
import com.payfurl.payfurlsdk.models.*;
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
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .build();

    private static final Address SAMPLE_ADDRESS = new Address.Builder()
            .withLine1("91  Gloucester Avenue")
            .withLine2("Apartment 2")
            .withCity("Melbourne")
            .withSate("Victoria")
            .withPostalCode("5006")
            .withCountry("Australia")
            .build();

    private static final Address SAMPLE_ADDRESS_UPDATED = new Address.Builder()
            .withLine1("91  Gloucester Avenue Updated")
            .withLine2("Apartment 2 Updated")
            .withCity("Melbourne  Updated")
            .withSate("Victoria Updated")
            .withPostalCode("50061")
            .withCountry("Australia  Updated")
            .build();

    private CustomerApi customerApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withAccessToken(TestConfigProvider.getKeyWithFallback())
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
        @DisplayName("When createWithCard request is executed then execute UpdateCustomer, Then return valid CustomerData")
        void testCreateWithCardAndUpdate() throws IOException {
            String reference = UUID.randomUUID().toString();
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withEmail("test"+reference+"@payfurl.com")
                    .withPhone("+61311111111")
                    .build();

            UpdateCustomer updateCustomer = new UpdateCustomer.Builder()
                    .withAddress(SAMPLE_ADDRESS_UPDATED)
                    .withPhone("+61311111112")
                    .withEmail("updated"+reference+"@payfurl.com")
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            CustomerData updatedCustomer = customerApi.updateCustomer(customerData.getCustomerId(), updateCustomer);

            // then
            then(customerData.getCustomerId()).isNotNull();
            then(updatedCustomer.getCustomerId()).isNotNull();
            then(updatedCustomer.getCustomerId()).isEqualTo(customerData.getCustomerId());
            then(updatedCustomer.getPhone()).isEqualTo("+61311111112");
            then(updatedCustomer.getEmail()).isEqualTo("updated"+reference+"@payfurl.com");
        }

        @Test
        @DisplayName("Delete Customer")
        void testDeleteCustomer() throws IOException {
            String reference = UUID.randomUUID().toString();
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withEmail("test"+reference+"@payfurl.com")
                    .withPhone("+61311111111")
                    .build();


            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            CustomerData updatedCustomer = customerApi.deleteCustomer(customerData.getCustomerId());

            // then
            then(customerData.getCustomerId()).isNotNull();
            then(updatedCustomer.getCustomerId()).isNotNull();
            then(updatedCustomer.getCustomerId()).isEqualTo(customerData.getCustomerId());
            then(updatedCustomer.getPhone()).isEqualTo("deleted");
            then(updatedCustomer.getEmail()).isEqualTo("deleted");
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
