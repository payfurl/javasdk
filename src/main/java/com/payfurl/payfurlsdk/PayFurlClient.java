package com.payfurl.payfurlsdk;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.payfurl.payfurlsdk.api.*;
import com.payfurl.payfurlsdk.auth.AuthHandler;
import com.payfurl.payfurlsdk.auth.AuthType;
import com.payfurl.payfurlsdk.auth.SecretKeyAuthHandler;
import com.payfurl.payfurlsdk.http.client.HttpClient;
import com.payfurl.payfurlsdk.http.client.OkClient;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.http.client.config.EnvironmentConfigKey;
import com.payfurl.payfurlsdk.http.client.config.HttpClientConfiguration;
import com.payfurl.payfurlsdk.http.client.config.Region;
import com.payfurl.payfurlsdk.http.client.support.Headers;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class PayFurlClient implements PayFurlClientSdk {
    private static final long DEFAULT_CALLS_TIMEOUT_MILLISECONDS = TimeUnit.SECONDS.toMillis(60);
    private static final String SDK_VERSION = "2022.0.1";
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String LOCAL_URL;
    private static final String GLOBAL_SANDBOX_URL;
    private static final String GLOBAL_PRODUCTION_URL;
    private static final String GLOBAL_DEVELOPMENT_URL;
    private static final String KEY_REGION_SEPARATOR = "-";

    private static final String DEVELOP_API_AU_URL;
    private static final String SANDBOX_API_AU_URL;
    private static final String PRODUCTION_API_AU_URL;

    static {
        Properties properties = new Properties();
        try (InputStream input = PayFurlClient.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new IllegalStateException("Unable to find " + PROPERTIES_FILE);
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new ExceptionInInitializerError("Failed to load properties file: " + ex.getMessage());
        }
        LOCAL_URL = properties.getProperty("local.url");
        GLOBAL_SANDBOX_URL = properties.getProperty("global.sandbox.url");
        GLOBAL_PRODUCTION_URL = properties.getProperty("global.production.url");
        GLOBAL_DEVELOPMENT_URL = properties.getProperty("global.development.url");
        DEVELOP_API_AU_URL = properties.getProperty("develop.api.au.url");
        SANDBOX_API_AU_URL = properties.getProperty("sandbox.api.au.url");
        PRODUCTION_API_AU_URL = properties.getProperty("production.api.au.url");
    }

    private static final ImmutableMap<EnvironmentConfigKey, String> ENV_CONFIG_TO_URL_MAPPING = ImmutableMap.<EnvironmentConfigKey, String>builder()
            .put(EnvironmentConfigKey.of(Region.NONE, Environment.LOCAL), LOCAL_URL)
            .put(EnvironmentConfigKey.of(Region.NONE, Environment.DEVELOPMENT), GLOBAL_DEVELOPMENT_URL)
            .put(EnvironmentConfigKey.of(Region.NONE, Environment.SANDBOX), GLOBAL_SANDBOX_URL)
            .put(EnvironmentConfigKey.of(Region.NONE, Environment.PRODUCTION), GLOBAL_PRODUCTION_URL)

            .put(EnvironmentConfigKey.of(Region.AU, Environment.DEVELOPMENT), DEVELOP_API_AU_URL)
            .put(EnvironmentConfigKey.of(Region.AU, Environment.SANDBOX), SANDBOX_API_AU_URL)
            .put(EnvironmentConfigKey.of(Region.AU, Environment.PRODUCTION), PRODUCTION_API_AU_URL)
            .build();

    private final Environment environment;
    private final Headers additionalHeaders;
    private final HttpClient httpClient;
    private final HttpClientConfiguration httpClientConfiguration;
    private final SecretKeyAuthHandler secretKeyAuthHandler;
    private final Map<AuthType, AuthHandler> authHandlerMap;
    private final String userAgentDetails;
    private final EnvironmentConfigKey environmentConfigKey;

    private ChargeApi chargeApi;
    private CustomerApi customerApi;
    private PaymentMethodApi paymentMethodApi;
    private TransferApi transferApi;
    private VaultApi vaultApi;
    private TokenApi tokenApi;
    private ProviderApi providerApi;
    private BatchApi batchApi;
    private SubscriptionApi subscriptionApi;
    private WebhookSubscriptionApi webhookSubscriptionApi;

    private static Optional<String> extractRegionFromKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return Optional.empty();
        }

        String[] parts = key.split(KEY_REGION_SEPARATOR, 2);

        if (parts.length < 2) {
            return Optional.empty();
        }

        return Optional.of(parts[1])
                .map(String::toLowerCase);
    }

    private PayFurlClient(Environment environment,
                          Headers additionalHeaders,
                          HttpClient httpClient,
                          HttpClientConfiguration httpClientConfiguration,
                          Map<AuthType, AuthHandler> authHandlerMap,
                          String userAgentDetails,
                          String secretKey) {
        this.environment = environment;
        this.additionalHeaders = additionalHeaders;
        this.httpClientConfiguration = httpClientConfiguration;
        this.httpClient = httpClient;

        this.authHandlerMap = MapUtils.isNotEmpty(authHandlerMap)
                ? new HashMap<>(authHandlerMap)
                : new HashMap<>();

        this.userAgentDetails = userAgentDetails;

        this.secretKeyAuthHandler = new SecretKeyAuthHandler(secretKey);
        this.authHandlerMap.put(AuthType.SECRET_KEY, secretKeyAuthHandler);

        Optional<String> rawRegionPart = extractRegionFromKey(secretKey);
        Region region = rawRegionPart.isPresent()
                ? Region.fromLabel(rawRegionPart.get())
                : Region.NONE;

        this.environmentConfigKey = EnvironmentConfigKey.of(region, environment);

        initializeApis();
    }

    public static void shutdown() {
        OkClient.shutdown();
    }

    private void initializeApis() {
        this.chargeApi = new ChargeApi(this, this.httpClient, this.authHandlerMap);
        this.customerApi = new CustomerApi(this, this.httpClient, this.authHandlerMap);
        this.paymentMethodApi = new PaymentMethodApi(this, this.httpClient, this.authHandlerMap);
        this.transferApi = new TransferApi(this, this.httpClient, this.authHandlerMap);
        this.vaultApi = new VaultApi(this, this.httpClient, this.authHandlerMap);
        this.tokenApi = new TokenApi(this, this.httpClient, this.authHandlerMap);
        this.providerApi = new ProviderApi(this, this.httpClient, this.authHandlerMap);
        this.batchApi = new BatchApi(this, this.httpClient, this.authHandlerMap);
        this.subscriptionApi = new SubscriptionApi(this, this.httpClient, this.authHandlerMap);
        this.webhookSubscriptionApi = new WebhookSubscriptionApi(this, this.httpClient, this.authHandlerMap);
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
    public long getTimeout() {
        return httpClientConfiguration.getTimeout();
    }

    @Override
    public String getSecretKey() {
        return getSecretKeyAuthHandler().getSecretKey();
    }

    @Override
    public String getBaseUri() {
        String baseUri = getBaseUriWithFallback();
        return StringUtils.defaultIfEmpty(baseUri, GLOBAL_SANDBOX_URL);
    }

    private String getBaseUriWithFallback() {
        String baseUri = ENV_CONFIG_TO_URL_MAPPING.get(environmentConfigKey);

        if (StringUtils.isEmpty(baseUri)) {
            return ENV_CONFIG_TO_URL_MAPPING.get(EnvironmentConfigKey.of(Region.NONE, environmentConfigKey.getEnvironment()));
        }

        return baseUri;
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
    public ProviderApi getProviderApi() {
        return this.providerApi;
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
    public BatchApi getBatchApi() {
        return batchApi;
    }

    @Override
    public SubscriptionApi getSubscriptionApi() {
        return subscriptionApi;
    }

    @Override
    public WebhookSubscriptionApi getWebhookSubscriptionApi() {
        return webhookSubscriptionApi;
    }

    @Override
    public String getSdkVersion() {
        return SDK_VERSION;
    }

    public static final class Builder {
        private final HttpClientConfiguration.Builder httpClientConfigurationBuilder = new HttpClientConfiguration.Builder();
        private final Map<AuthType, AuthHandler> authHandlerMap = null;
        private final String userAgentDetails = null;
        private final String publicKey = StringUtils.EMPTY;
        private Environment environment = Environment.PRODUCTION;
        private Headers additionalHeaders = new Headers();
        private String secretKey = StringUtils.EMPTY;

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

        public PayFurlClient build() {
            long timeout = getTimeoutFrom(httpClientConfigurationBuilder);
            HttpClientConfiguration httpClientConfig = httpClientConfigurationBuilder
                    .timeout(timeout)
                    .environment(environment)
                    .build();

            HttpClient httpClient = new OkClient(httpClientConfig);

            return new PayFurlClient(environment,
                    additionalHeaders,
                    httpClient,
                    httpClientConfig,
                    authHandlerMap,
                    userAgentDetails,
                    secretKey);
        }

        private static long getTimeoutFrom(HttpClientConfiguration.Builder httpClientConfigurationBuilder) {
            long userConfigTimeout = httpClientConfigurationBuilder.getTimeout();

            if (userConfigTimeout > 0) {
                return userConfigTimeout;
            }

            return DEFAULT_CALLS_TIMEOUT_MILLISECONDS;
        }
    }
}
