package com.payfurl.apitesting;

import com.payfurl.PayFurlClient;
import com.payfurl.api.VaultApi;
import com.payfurl.http.client.config.Environment;
import com.payfurl.models.NewVault;
import com.payfurl.models.VaultData;
import com.payfurl.models.VaultDataWithPci;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.BDDAssertions.then;

public class VaultApiTest {
    private static final String LOCAL_ACCESS_TEST_TOKEN = "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    private VaultApi vaultApi;

    @BeforeEach
    void setUp() {
        PayFurlClient payFurlClient = new PayFurlClient.Builder()
                .withEnvironment(Environment.LOCAL)
                .withAccessToken(LOCAL_ACCESS_TEST_TOKEN)
                .build();

        vaultApi = payFurlClient.getVaultApi();
    }

    @Nested
    @DisplayName("Given valid request to PaymentMethod API")
    class SuccessFlow {
        @Test
        @DisplayName("When create request is executed, Then return valid vault")
        void testCreate() throws IOException {
            // when
            VaultData vaultData = createVault();

            // then
            then(vaultData.getVaultId()).isNotNull();
        }

        @DisplayName("When single request is executed, Then return valid vaultData")
        void testSingle() throws IOException {
            // given
            VaultData vaultData = createVault();

            // when
            VaultDataWithPci foundVaultData = vaultApi.single(vaultData.getVaultId());

            // then
            then(foundVaultData.getVaultId()).isEqualTo(vaultData.getVaultId());
        }

        @Test
        @DisplayName("When delete request is executed, Then return valid deleted vaultData")
        void testDelete() throws IOException {
            // given
            VaultData vaultData = createVault();

            // when
            VaultData deletedVaultData = vaultApi.delete(vaultData.getVaultId());

            // then
            then(deletedVaultData.getVaultId()).isEqualTo(vaultData.getVaultId());
        }

        private VaultData createVault() throws IOException {
            NewVault newVault = new NewVault.Builder()
                    .withCardNumber("4111111111111111")
                    .withCcv("123")
                    .build();

            return vaultApi.create(newVault);
        }
    }
}
