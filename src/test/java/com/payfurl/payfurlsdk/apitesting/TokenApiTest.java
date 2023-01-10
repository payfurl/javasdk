package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.TokenApi;
import com.payfurl.payfurlsdk.models.TokenData;
import com.payfurl.payfurlsdk.models.TokenList;
import com.payfurl.payfurlsdk.models.TokenSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
        @Disabled("Tokens expire, so this test needs to be adjusted each time it's run")
        @DisplayName("Get token by ID")
        void getTokenById() throws IOException {
            // given
            String tokenId = "5dc5cfbaec7c4d057cb00482";

            // when
            TokenData singleToken = tokenApi.single(tokenId);

            // then
            then(singleToken.getId()).isNotNull();
            then(singleToken.getId()).isEqualTo(tokenId);
        }

        @Test
        @Disabled("Tokens expire, so this test needs to be adjusted each time it's run")
        @DisplayName("When Search request is executed, Then return valid TokenList")
        void testSearch() throws IOException {
            // given

            // when
            TokenList tokenList = tokenApi.search(new TokenSearch.Builder()
                    .withProviderId("a26c371f-94f6-40da-add2-28ec8e9da8ed")
                    .build());

            // then
            then(tokenList.getCount()).isGreaterThan(0);
        }
    }
}
