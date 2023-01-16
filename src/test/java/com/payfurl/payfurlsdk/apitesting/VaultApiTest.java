package com.payfurl.payfurlsdk.apitesting;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.TestConfigProvider;
import com.payfurl.payfurlsdk.api.VaultApi;
import com.payfurl.payfurlsdk.api.support.ApiException;
import com.payfurl.payfurlsdk.models.NewVault;
import com.payfurl.payfurlsdk.models.VaultData;
import com.payfurl.payfurlsdk.models.VaultDataWithPci;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class VaultApiTest {
    private VaultApi vaultApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(TestConfigProvider.getEnvironmentWithFallback())
                .withSecretKey(TestConfigProvider.getSecretKeyWithFallback())
                .build();

        vaultApi = payFurlClient.getVaultApi();
    }

    @Nested
    @DisplayName("Given valid request to PaymentMethod API")
    class SuccessFlow {
        @Test
        @DisplayName("When create request is executed, Then return valid vault")
        void testCreate() throws ApiException {
            // when
            VaultData vaultData = createVault();

            // then
            then(vaultData.getVaultId()).isNotNull();
        }

        @DisplayName("When single request is executed, Then return valid vaultData")
        void testSingle() throws ApiException {
            // given
            VaultData vaultData = createVault();

            // when
            VaultDataWithPci foundVaultData = vaultApi.single(vaultData.getVaultId());

            // then
            then(foundVaultData.getVaultId()).isEqualTo(vaultData.getVaultId());
        }

        @Test
        @DisplayName("When delete request is executed, Then return valid deleted vaultData")
        void testDelete() throws ApiException {
            // given
            VaultData vaultData = createVault();

            // when
            VaultData deletedVaultData = vaultApi.delete(vaultData.getVaultId());

            // then
            then(deletedVaultData.getVaultId()).isEqualTo(vaultData.getVaultId());
        }

        private VaultData createVault() throws ApiException {
            NewVault newVault = new NewVault.Builder()
                    .withCardNumber("4111111111111111")
                    .withCcv("123")
                    .build();

            return vaultApi.create(newVault);
        }
    }
}
