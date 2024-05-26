package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.CustomerApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.models.Address;
import com.payfurl.payfurlsdk.models.CardRequestInformation;
import com.payfurl.payfurlsdk.models.CustomerData;
import com.payfurl.payfurlsdk.models.CustomerList;
import com.payfurl.payfurlsdk.models.CustomerSearch;
import com.payfurl.payfurlsdk.models.NewCustomerCard;
import com.payfurl.payfurlsdk.models.NewCustomerProviderToken;
import com.payfurl.payfurlsdk.models.NewCustomerToken;
import com.payfurl.payfurlsdk.models.NewPayToAgreement;
import com.payfurl.payfurlsdk.models.NewPaymentMethodCard;
import com.payfurl.payfurlsdk.models.NewPaymentMethodToken;
import com.payfurl.payfurlsdk.models.PayIdDetails;
import com.payfurl.payfurlsdk.models.PaymentMethodData;
import com.payfurl.payfurlsdk.models.UpdateCustomer;
import com.payfurl.payfurlsdk.models.BankPaymentInformation;
import com.payfurl.payfurlsdk.models.NewCustomerBankPayment;
import com.payfurl.payfurlsdk.models.NewPaymentMethodBankPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

public class CustomerApiTest {

    private static final HashMap<String, String> METADATA = new HashMap<String, String>() {{ put("merchant_id", "1234356"); }};
    private static final CardRequestInformation SAMPLE_PAYMENT_INFORMATION = new CardRequestInformation.Builder()
            .withCardNumber("4111111111111111")
            .withExpiryDate("12/35")
            .withCcv("123")
            .build();
            
    private static final BankPaymentInformation SAMPLE_BANK_PAYMENT_INFORMATION = new BankPaymentInformation.Builder()
            .withBankCode("123-456")
            .withAccountNumber("123456")
            .withAccountName("Bank Account")
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
            .withCountry("IN")
            .build();

    private CustomerApi customerApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        customerApi = payFurlClient.getCustomerApi();
    }

    @Nested
    @DisplayName("Given valid request to Customer API")
    class SuccessFlow {
        @Test
        @DisplayName("When createWithCard request is executed, Then return valid CustomerData")
        void testCreateWithCard() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withMetadata(METADATA)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            // then
            then(customerData.getCustomerId()).isNotNull();
        }

        @Test
        @DisplayName("When createWithCard request is executed then execute UpdateCustomer, Then return valid CustomerData")
        void testCreateWithCardAndUpdate() throws ApiException {
            String reference = UUID.randomUUID().toString();
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withEmail("test" + reference + "@payfurl.com")
                    .withPhone("+61311111111")
                    .build();

            UpdateCustomer updateCustomer = new UpdateCustomer.Builder()
                    .withAddress(SAMPLE_ADDRESS_UPDATED)
                    .withPhone("+61311111112")
                    .withEmail("updated" + reference + "@payfurl.com")
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            CustomerData updatedCustomer = customerApi.updateCustomer(customerData.getCustomerId(), updateCustomer);

            // then
            then(customerData.getCustomerId()).isNotNull();
            then(updatedCustomer.getCustomerId()).isNotNull();
            then(updatedCustomer.getCustomerId()).isEqualTo(customerData.getCustomerId());
            then(updatedCustomer.getPhone()).isEqualTo("+61311111112");
            then(updatedCustomer.getEmail()).isEqualTo("updated" + reference + "@payfurl.com");
            then(updatedCustomer.getAddress().getCountry()).isEqualTo("India");
        }

        @Test
        @DisplayName("When createWithCard request is executed then execute UpdateCustomer, Then return valid CustomerData, Then verify DefaultPaymentMethodId")
        void testCreateWithCardAndUpdate_SetDefault() throws ApiException {
            String reference = UUID.randomUUID().toString();
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withEmail("test" + reference + "@payfurl.com")
                    .withPhone("+61311111111")
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            // Create payment method for the customer, but do not apply as Default
            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            PaymentMethodData paymentMethodData = customerApi.createPaymentMethodWithCard(customerData.getCustomerId(), newPaymentMethodCard);

            // then
            then(paymentMethodData.getPaymentMethodId()).isNotNull();
            // Current Customer.DefaultPaymentMethodId is not the same as new payment method id
            customerData = customerApi.single(customerData.getCustomerId());
            then(customerData.getDefaultPaymentMethod().getPaymentMethodId()).isNotEqualTo(paymentMethodData.getPaymentMethodId());

            UpdateCustomer updateCustomer = new UpdateCustomer.Builder()
                    .withAddress(SAMPLE_ADDRESS_UPDATED)
                    .withPhone("+61311111112")
                    .withEmail("updated" + reference + "@payfurl.com")
                    .withDefaultPaymentMethodId(paymentMethodData.getPaymentMethodId())
                    .build();

            CustomerData updatedCustomer = customerApi.updateCustomer(customerData.getCustomerId(), updateCustomer);

            // then
            then(customerData.getCustomerId()).isNotNull();
            then(updatedCustomer.getCustomerId()).isNotNull();
            then(updatedCustomer.getCustomerId()).isEqualTo(customerData.getCustomerId());
            then(updatedCustomer.getPhone()).isEqualTo("+61311111112");
            then(updatedCustomer.getEmail()).isEqualTo("updated" + reference + "@payfurl.com");
            then(updatedCustomer.getAddress().getCountry()).isEqualTo("India");
            // Verify if new payment method is default now for the customer
            then(updatedCustomer.getDefaultPaymentMethod().getPaymentMethodId()).isEqualTo(paymentMethodData.getPaymentMethodId());
        }

        @Test
        @DisplayName("Delete Customer")
        void testDeleteCustomer() throws ApiException {
            String reference = UUID.randomUUID().toString();
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withAddress(SAMPLE_ADDRESS)
                    .withEmail("test" + reference + "@payfurl.com")
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
        void testSearch() throws ApiException {
            // given
            String reference = UUID.randomUUID().toString();

            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
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
        @DisplayName("When createPaymentMethodWithCard request is executed, Then return valid paymentMethodData")
        void testCreatePaymentMethodWithCard() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            PaymentMethodData paymentMethodData = customerApi.createPaymentMethodWithCard(customerData.getCustomerId(), newPaymentMethodCard);

            // then
            then(paymentMethodData.getPaymentMethodId()).isNotNull();
        }

        @Test
        @DisplayName("When createPaymentMethodWithCard request is executed, Then return valid paymentMethodData")
        void testCreatePaymentMethodWithCard_SetDefault() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            NewPaymentMethodCard newPaymentMethodCard = new NewPaymentMethodCard.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .withSetDefault(true)
                    .build();

            PaymentMethodData paymentMethodData = customerApi.createPaymentMethodWithCard(customerData.getCustomerId(), newPaymentMethodCard);

            // then
            then(paymentMethodData.getPaymentMethodId()).isNotNull();

            // Get updated customer and verify default payment method id
            customerData = customerApi.single(customerData.getCustomerId());
            then(customerData.getDefaultPaymentMethod().getPaymentMethodId()).isEqualTo(paymentMethodData.getPaymentMethodId());
        }

        @Test
        @Disabled("tokens expire, so this test needs to be adjusted each time it's run")
        @DisplayName("When createPaymentMethodWithToken request is executed, Then return valid paymentMethodData")
        void testCreatePaymentMethodWithToken() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            NewPaymentMethodToken newPaymentMethodCard = new NewPaymentMethodToken.Builder()
                    .withToken(TestConfigProvider.getToken())
                    .build();

            PaymentMethodData paymentMethodData = customerApi.createWitPaymentMethodWithToken(customerData.getCustomerId(), newPaymentMethodCard);

            // then
            then(paymentMethodData.getPaymentMethodId()).isNotNull();
        }

        @Test
        @DisplayName("When createPaymentMethodWithToken request is executed, Then return valid paymentMethodData")
        void testCreatePaymentMethodWithToken_SetDefault() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            NewPaymentMethodToken newPaymentMethodCard = new NewPaymentMethodToken.Builder()
                    .withToken(TestConfigProvider.getToken())
                    .withSetDefault(true)
                    .build();

            PaymentMethodData paymentMethodData = customerApi.createWitPaymentMethodWithToken(customerData.getCustomerId(), newPaymentMethodCard);

            // then
            then(paymentMethodData.getPaymentMethodId()).isNotNull();

            // Get updated customer and verify default payment method id
            customerData = customerApi.single(customerData.getCustomerId());
            then(customerData.getDefaultPaymentMethod().getPaymentMethodId()).isEqualTo(paymentMethodData.getPaymentMethodId());
        }

        @Test
        @DisplayName("When createWitPaymentMethodWithPayTo request is executed, Then return valid paymentMethodData")
        void testCreatePaymentMethodWithPayTo() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            NewPayToAgreement newPayToAgreement = new NewPayToAgreement.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPayerName("This is a name")
                    .withDescription("This is a description")
                    .withMaximumAmount(500)
                    .withPayerPayIdDetails(new PayIdDetails.Builder()
                            .withPayId("david_jones@email.com")
                            .withPayIdType("EMAIL")
                            .build())
                    .build();

            PaymentMethodData paymentMethodData = customerApi.createWitPaymentMethodWithPayTo(customerData.getCustomerId(), newPayToAgreement);

            // then
            then(paymentMethodData.getPaymentMethodId()).isNotNull();
        }

        @Test
        @DisplayName("When createWitPaymentMethodWithPayTo request is executed, Then return valid paymentMethodData")
        void testCreatePaymentMethodWithPayTo_SetDefault() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPaymentInformation(SAMPLE_PAYMENT_INFORMATION)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithCard(newCustomerCard);

            NewPayToAgreement newPayToAgreement = new NewPayToAgreement.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withPayerName("This is a name")
                    .withDescription("This is a description")
                    .withMaximumAmount(500)
                    .withPayerPayIdDetails(new PayIdDetails.Builder()
                            .withPayId("david_jones@email.com")
                            .withPayIdType("EMAIL")
                            .build())
                    .withSetDefault(true)
                    .build();

            PaymentMethodData paymentMethodData = customerApi.createWitPaymentMethodWithPayTo(customerData.getCustomerId(), newPayToAgreement);

            // then
            then(paymentMethodData.getPaymentMethodId()).isNotNull();

            // Get updated customer and verify default payment method id
            customerData = customerApi.single(customerData.getCustomerId());
            then(customerData.getDefaultPaymentMethod().getPaymentMethodId()).isEqualTo(paymentMethodData.getPaymentMethodId());
        }

        @Test
        @DisplayName("When createWithToken request is executed, Then return valid customerData")
        void testCreateWithToken() throws ApiException {
            // given
            NewCustomerToken newCustomerToken = new NewCustomerToken.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withToken(TestConfigProvider.getToken())
                    .build();

            // when
            CustomerData customerData = customerApi.createWithToken(newCustomerToken);

            // then
            then(customerData.getCustomerId()).isNotNull();
        }

        @Test
        @DisplayName("When getPaymentMethods request is executed, Then return valid CustomerList")
        void testGetPaymentMethods() throws ApiException {
            // given
            String reference = UUID.randomUUID().toString();

            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
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
        void testSingle() throws ApiException {
            // given
            NewCustomerCard newCustomerCard = new NewCustomerCard.Builder()
                    .withFirstName("test")
                    .withLastName("test")
                    .withProviderId(TestConfigProvider.getProviderId())
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

        @Test
        @Disabled("For manual running only")
        @DisplayName("Create customer with provider token, Then return valid CustomerData")
        void testCustomerWithProviderToken() throws ApiException {
            // given
            NewCustomerProviderToken customerProviderToken = new NewCustomerProviderToken.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withProviderToken("some_test_token")
                    .withProviderTokenData(new HashMap<String, String>() {{
                        put("test", "test");
                    }})
                    .build();

            // when
            CustomerData customerData = customerApi.createWithProviderToken(customerProviderToken);
            CustomerData foundCustomerData = customerApi.single(customerData.getCustomerId());

            // then
            then(customerData.getCustomerId()).isEqualTo(foundCustomerData.getCustomerId());
        }
        
        @Test
        @DisplayName("When createWithBankAccount request is executed, Then return valid CustomerData")
        void testCreateWithBankAccount() throws ApiException {
            // given
            NewCustomerBankPayment newCustomerCard = new NewCustomerBankPayment.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withBankPaymentInformation(SAMPLE_BANK_PAYMENT_INFORMATION)
                    .withMetadata(METADATA)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithBankAccount(newCustomerCard);

            // then
            then(customerData.getCustomerId()).isNotNull();
        }
        
        @Test
        @DisplayName("When createWithPaymentMethodWithBankAccount request is executed, Then return valid CustomerData")
        void testCreatePaymentMethodWithBankAccount() throws ApiException {
            // given
            NewCustomerBankPayment newCustomerCard = new NewCustomerBankPayment.Builder()
                                .withProviderId(TestConfigProvider.getProviderId())
                                .withBankPaymentInformation(SAMPLE_BANK_PAYMENT_INFORMATION)
                                .withMetadata(METADATA)
                                .build();
                                
            NewPaymentMethodBankPayment newPaymentMethodBankPayment = new NewPaymentMethodBankPayment.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .withBankPaymentInformation(SAMPLE_BANK_PAYMENT_INFORMATION)
                    .withMetadata(METADATA)
                    .build();

            // when
            CustomerData customerData = customerApi.createWithBankAccount(newCustomerCard);
            CustomerData customerDataWithPaymentMethod = customerApi.createWithPaymentMethodWithBankAccount(customerData.getCustomerId(), newPaymentMethodBankPayment);

            // then
            then(customerDataWithPaymentMethod.getCustomerId()).isNotNull();
        }
    }
}
