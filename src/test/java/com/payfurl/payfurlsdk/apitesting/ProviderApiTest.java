package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.ProviderApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.models.*;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;

public class ProviderApiTest {

    private ProviderApi providerApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        providerApi = payFurlClient.getProviderApi();
    }

    @Nested
    @DisplayName("Given valid request to Provider API")
    class SuccessFlow {
        @Test
        @DisplayName("When create provider request is executed, Then return valid Provider")
        void testCreateProvider() throws ApiException {
            // given
            Map<String, String> authenticationParameters = new HashMap<>();
            authenticationParameters.put("Password","abcd1234");
            authenticationParameters.put("MerchantId","XYZ0010");

            String name = "NabTransact" + randomUUID();
            NewProvider newProvider = new NewProvider.Builder()
                    .withType("nab_transact")
                    .withCurrency("AUD")
                    .withName(name)
                    .withProviderCountry("AUS")
                    .withEnvironment(Environment.SANDBOX)
                    .withAuthenticationParameters(authenticationParameters)
                    .build();

            // when
            Provider provider = providerApi.create(newProvider);

            // then
            then(provider).isNotNull();
            then(provider.getName()).isEqualTo(name);
            then(provider.getProviderId()).isNotNull();
        }
    }
}
