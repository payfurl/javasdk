package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.TokenApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.models.TokenData;
import com.payfurl.payfurlsdk.models.TokenList;
import com.payfurl.payfurlsdk.models.TokenSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class TokenApiTest {

    private TokenApi tokenApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        tokenApi = payFurlClient.getTokenApi();

    }

    @Nested
    @DisplayName("Given valid request to Token API")
    class SuccessFlow {

        @Test
        @DisplayName("Get token by ID")
        void getTokenById() throws ApiException {
            // given
            String tokenId = TestConfigProvider.getToken();

            // when
            TokenData singleToken = tokenApi.single(tokenId);

            // then
            then(singleToken.getId()).isNotNull();
            then(singleToken.getId()).isEqualTo(tokenId);
        }

        @Test
        @DisplayName("When Search request is executed, Then return valid TokenList")
        void testSearch() throws ApiException {
            // given

            // when
            TokenList tokenList = tokenApi.search(new TokenSearch.Builder()
                    .withProviderId(TestConfigProvider.getProviderId())
                    .build());

            // then
            then(tokenList.getCount()).isGreaterThan(0);
        }
    }
}
