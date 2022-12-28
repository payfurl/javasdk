package com.payfurl.payfurlsdk;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.payfurl.payfurlsdk.api.*;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.auth.PublicKeyAuthHandler;
import com.payfurl.payfurlsdk.auth.SecretKeyAuthHandler;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.http.client.OkClient;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.http.client.config.HttpClientConfiguration;
import com.payfurl.payfurlsdk.http.client.support.Headers;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class PayFurlClient implements PayFurlClientSdk {
    private static final String SDK_VERSION = "2022.0.1";
    private static final String LOCAL_URL = "https://localhost:5001";
    private static final String SANDBOX_URL = "https://sandbox-api.payfurl.com";
    private static final String PRODUCTION_URL = "https://api.payfurl.com";
    private static final Map<Environment, String> ENV_TO_URL_MAP = ImmutableMap.of(
            Environment.LOCAL, LOCAL_URL,
            Environment.SANDBOX, SANDBOX_URL,
            Environment.PRODUCTION, PRODUCTION_URL
    );

    private final Environment environment;
    private final Headers additionalHeaders;
    private final HttpClient httpClient;
    private final HttpClientConfiguration httpClientConfiguration;
    private final SecretKeyAuthHandler secretKeyAuthHandler;

    private final PublicKeyAuthHandler publicKeyAuthHandler;

    private final Map<AuthType, AuthHandler> authHandlerMap;
    private final String userAgentDetails;

    private ChargeApi chargeApi;
    private CustomerApi customerApi;
    private PaymentMethodApi paymentMethodApi;
    private TransferApi transferApi;
    private VaultApi vaultApi;
    private TokenApi tokenApi;

    private PayFurlClient(Environment environment,
                          Headers additionalHeaders,
                          HttpClient httpClient,
                          HttpClientConfiguration httpClientConfiguration,
                          Map<AuthType, AuthHandler> authHandlerMap,
                          String userAgentDetails,
                          String secretKey,
                          String publicKey) {
        this.environment = environment;
        this.additionalHeaders = additionalHeaders;
        this.httpClientConfiguration = httpClientConfiguration;
        this.httpClient = httpClient;

        this.authHandlerMap = MapUtils.isNotEmpty(authHandlerMap)
                ? new HashMap<>(authHandlerMap)
                : new HashMap<>();

        this.userAgentDetails = userAgentDetails;

        this.secretKeyAuthHandler = new SecretKeyAuthHandler(secretKey);
        this.publicKeyAuthHandler = new PublicKeyAuthHandler(publicKey);
        this.authHandlerMap.put(AuthType.SECRET_KEY, secretKeyAuthHandler);
        this.authHandlerMap.put(AuthType.PUBLIC_KEY, publicKeyAuthHandler);

        initializeApis();
    }

    private void initializeApis() {
        this.chargeApi = new ChargeApi(this, this.httpClient, this.authHandlerMap);
        this.customerApi = new CustomerApi(this, this.httpClient, this.authHandlerMap);
        this.paymentMethodApi = new PaymentMethodApi(this, this.httpClient, this.authHandlerMap);
        this.transferApi = new TransferApi(this, this.httpClient, this.authHandlerMap);
        this.vaultApi = new VaultApi(this, this.httpClient, this.authHandlerMap);
        this.tokenApi = new TokenApi(this, this.httpClient, this.authHandlerMap);
    }

    public static void shutdown() {
        OkClient.shutdown();
    }

    public SecretKeyAuthHandler getSecretKeyAuthHandler() {
        return secretKeyAuthHandler;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public String getPayFurlVersion() {
        return SDK_VERSION;
    }

    @Override
    public Headers getAdditionalHeaders() {
        return new Headers(additionalHeaders);
    }

    @Override
    public String getUserAgentDetail() {
        return userAgentDetails;
    }

    @Override
    public long timeout() {
        return httpClientConfiguration.getTimeout();
    }

    @Override
    public String getSecretKey() {
        return getSecretKeyAuthHandler().getSecretKey();
    }

    @Override
    public String getBaseUri() {
        return StringUtils.defaultIfEmpty(ENV_TO_URL_MAP.get(environment), SANDBOX_URL);
    }

    @Override
    public ChargeApi getChargeApi() {
        return chargeApi;
    }

    @Override
    public CustomerApi getCustomerApi() {
        return customerApi;
    }

    @Override
    public PaymentMethodApi getPaymentMethodApi() {
        return paymentMethodApi;
    }

    @Override
    public TransferApi getTransferApi() {
        return transferApi;
    }

    @Override
    public VaultApi getVaultApi() {
        return vaultApi;
    }

    @Override
    public TokenApi getTokenApi() {
        return tokenApi;
    }

    @Override
    public String getSdkVersion() {
        return SDK_VERSION;
    }

    public static final class Builder {

        private Environment environment = Environment.PRODUCTION;
        private Headers additionalHeaders = new Headers();
        private final HttpClientConfiguration.Builder httpClientConfigurationBuilder = new HttpClientConfiguration.Builder();
        private Map<AuthType, AuthHandler> authHandlerMap = null;
        private String userAgentDetails = null;
        private String secretKey = StringUtils.EMPTY;

        private String publicKey = StringUtils.EMPTY;

        public Builder withEnvironment(Environment environment) {
            this.environment = environment;
            return this;
        }

        public Builder withAdditionalHeaders(Headers additionalHeaders) {
            this.additionalHeaders = Preconditions.checkNotNull(additionalHeaders);
            return this;
        }

        public Builder withHttpClientConfiguration(Consumer<HttpClientConfiguration.Builder> action) {
            action.accept(httpClientConfigurationBuilder);
            return this;
        }

        public Builder withSecretKey(String secretKey) {
            this.secretKey = Preconditions.checkNotNull(secretKey);
            return this;
        }

        public Builder withPublicKey(String publicKey) {
            this.publicKey = Preconditions.checkNotNull(publicKey);
            return this;
        }

        public PayFurlClient build() {
            HttpClientConfiguration httpClientConfig = httpClientConfigurationBuilder
                    .environment(environment)
                    .build();
            HttpClient httpClient = new OkClient(httpClientConfig);

            return new PayFurlClient(environment,
                    additionalHeaders,
                    httpClient,
                    httpClientConfig,
                    authHandlerMap,
                    userAgentDetails,
                    secretKey,
                    publicKey);
        }
    }
}
