# Changelog

Every release of `com.payfurl:payfurlsdk`. The format follows
[Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and versions follow
[Semantic Versioning](https://semver.org/spec/v2.0.0.html) from 2.0.0. Entries before 2.0.0 were written back from
Maven Central's version history and the merged pull requests.

## [Unreleased]

## [2.0.0] - UNRELEASED

**Breaking.** Methods and fields of 1.0.x are removed (below), and some calls behave differently. The README's
[Upgrading from 1.0.x to 2.0](README.md#upgrading-from-10x-to-20) maps every removed member to its replacement and
lists the behaviour changes. Nothing upgrades by itself: a build that names 1.0.x keeps it. Java 8 is still the floor.

### Security

- Trust-all TLS is limited to `Environment.LOCAL` clients. Every `PayFurlClient` in a JVM shared one HTTP client,
  built from the first client's environment: when a `LOCAL` client came first, every later client, `PRODUCTION`
  included, accepted any certificate and any host name.
- `toString()` masks card numbers and bank account numbers (last four digits), CVVs, webhook `Authorization`
  values and provider credentials on every model, so logging a request no longer logs
  them. An HTTP request's `toString()` no longer prints its body or its header values (the secret key).
- Webhook signatures are compared in constant time (`MessageDigest.isEqual`); a missing signature or key is a
  failed check.
- Jackson 2.22.4 (was 2.12.7; CVE-2022-42003, CVE-2022-42004, CVE-2025-52999) and commons-lang3 3.20.0 (was 3.14.0;
  CVE-2025-48924).

### Fixed

- A refund's comment is sent; it was dropped.
- `NewChargeToken`'s `CheckoutTransfer` is sent as `Transfer`, the name the API reads; it was dropped.
- Response fields that were always null now read the API's: `PaymentData.getCardData()` (the API sends `card`),
  `ProviderCostData.getInternationalCard()`, `GooglePayBaseInfo`'s gateway merchant ID, and
  `PayToProviderOptions.getMaximumAmount()` (new; the API has no minimum).
- Search date filters (`AddedAfter`, `AddedBefore`) are sent as ISO-8601 in UTC (`2026-11-07T10:15:30Z`). They were
  sent as `Date.toString()` (`Sat Nov 07 21:15:30 AEDT 2026`). Decimal query values are sent without an exponent.
- A status, type or error code the API adds later reads as `UNKNOWN` (`ErrorCode.UnknownError`) instead of failing
  the call.
- `createWebhookSubscription` posts to `webhook/subscription`, without a trailing slash.
- The `User-Agent` carries the SDK's version (`PayFURL-Java-SDK-v.2.0.0 ...`); it said `2022.0.1`. The fixed
  `sdk-version: 4.5.7` header is gone.
- An error response that is not the API's JSON (a load balancer's 502, say) is retryable when its status is 5xx
  or 429.

### Added

- GETs are retried after a connection error, a timeout or a retryable API error: twice by default, after 0.5 and 1
  second (`HttpClientConfiguration.Builder.maxRetries` and `retryDelay`). Nothing else is retried.
- `PayFurlClient.Builder.withBaseUrl` and `withOkHttpClient`, for proxies, interceptors and tests.
- `PayFurlClient` is `AutoCloseable`: `close()` releases what that client owns and never affects another client.
- `client.getInfoApi().getProvidersInfo(amount, currency)` (`GET /info/providers`); `InfoApi` was not reachable from
  `PayFurlClient`.
- New endpoints: `ChargeApi.createWithNetworkToken` (`POST /charge/network_token`), `CustomerApi.createWithPayTo`
  (`POST /customer/payto`), `PaymentMethodApi.createPaymentMethodWithPayTo` (`POST /payment_method/payto`),
  `client.getDisputeApi()` (`GET /dispute`, `GET /dispute/{id}`), `client.getSettlementApi()` (`GET /settlements`)
  and `client.getRootApi()` (`GET /`, `GET /version`).
- `CustomerApi.createPaymentMethodWithToken` and `createPaymentMethodWithPayTo`, the names without the typo.
- `PayFurlClientSdk` has `getInfoApi()`, `getDisputeApi()`, `getSettlementApi()` and `getRootApi()`.
- Fields the API has and the SDK lacked:
  - `ip` on every charge request, and on customer and payment method requests;
  - `createNetworkToken`, `verify` and `webhook` on `NewCustomerCard` and `NewPaymentMethodCard`;
  - `fallbackPaymentMethodId` and `webhook` on the payment method requests;
  - `threeDSServerTransID` and `externalThreeDsData` on `CardRequestInformation`;
  - `invoiceId` on `CreatePaymentLink`; `countPayments` and `invoiceId` on `PaymentLinkData`;
  - `ip` and `ipInfo` on `ChargeData`; `comment` on `Refund`; `errorCode` and `isRetryable` on `FailureData`;
  - `ip` and `dateRemoved` on `CustomerData`; `cardType`, `cardIin`, `ccvCheck` and `threeDSServerTransID` on
    `CardData`; `clickToPay` on `TokenData`; `additionalParameters` on providers; `metadata` on `UpdatePaymentMethod`
    and `SubscriptionUpdate`; `success`, `failure` and `metadata` on `BatchSummary`;
  - search filters: `batchId`, `subscriptionId` and `sortOrder` on `ChargeSearch`, `sortBy` and `includeRemoved` on
    `CustomerSearch`, `sortOrder` and `includeRemoved` on `PaymentMethodSearch`, `status` on `BatchSearch`,
    `paymentMethodId` on `SubscriptionSearch`.
  Fields added in 2.0 are left out of a request when not set.
- `ApiException.getChargeId()` (the declined charge) and `getTraceId()` (quote it to support).
- `UNKNOWN` on `TransactionStatus`, `SubscriptionStatus`, `SubscriptionInterval`, `SubscriptionRetryInterval`,
  `Initiator`, `WebhookType` and `Environment` (the provider's); `WebhookType.Dispute`.

### Changed

- Java 8 stays the floor; the build compiles against Java 8's API on any newer JDK.
- The SDK no longer depends on Guava or commons-collections4.
- Versions follow semver: 2.0.0, not `1.0.<date>`. The version comes from the build, and releases are published
  from a `vX.Y.Z` git tag.

### Removed

- `PayFurlClient.shutdown()`, which was static and closed the HTTP client every client in the JVM shared: use
  `close()` on the client.
- `CustomerApi.createWitPaymentMethodWithToken` and `createWitPaymentMethodWithPayTo`: use
  `createPaymentMethodWithToken` and `createPaymentMethodWithPayTo`.
- `InfoApi.GetProvidersInfo`: use `getProvidersInfo`. `InfoApi.getInfo`, `GetProviderInfo`, `GetProviderTokenInfo`
  and `GetDefaultFallbackProvider`, and their models `Info`, `InfoProvider`, `ProviderData`, `ProviderInfoModel`,
  `ProviderCostDataModel`, `ProviderVisibilityDataModel` and `CurrencyInformationData`: their endpoints are not part
  of the public API.
- Members the API never sent or read, which were always null: `ChargeData.threeDsServerTransId` (use
  `CardData.getThreeDSServerTransID()`), `PaymentData.providerType`, `userId` on `PaymentMethodData` and `TokenData`,
  `PaymentLinkData.secretKey`, `threeDsProviderId` on `NewProvider` and `UpdateProvider`, and
  `PayToProviderOptions`' minimum amount (use `getMaximumAmount()`). The constructors of these models lost the
  parameter.
- The tests that called payFURL's development environment: unit tests against a local mock server replace them,
  and payFURL runs the integration tests against an API built from source.

## 1.0.x - 2022-12-18 to 2026-05-12

Versions were named `1.0.<release date>` and published by hand; none was tagged. Each line names the releases and
the pull requests in them, written back from Maven Central's version history.

- 1.0.20260512: dependency updates (#94).
- 1.0.20250805: metadata on payment links (#92).
- 1.0.20250707: payment links return their URL, payment link helpers, metadata on batches (#87, #89, #90, #91).
- 1.0.20250519 to 1.0.20250613: payment links (#85, #88), recurring flags (#86), metadata in responses (#82); the
  US region in sandbox and the JP region in development are gone (#84).
- 1.0.20240923 and 1.0.20240927: new search parameters (#70, #71), a missing method (#72), metadata on
  subscriptions (#73), deployment fixes (#74, #75).
- 1.0.20240617 and 1.0.20240816: subscriptions (#63), new parameters (#64), webhooks on batch transactions (#65),
  update a payment method (#68), error codes synced with the API (#66, #67).
- 1.0.20240521: batches (#58), `POST /payment_method/provider_token` (#60), card details (#55), bank accounts (#62).
- 1.0.20240118.2 and 1.0.20240208: delete a provider (#51), new parameters (#52), the error code list (#50), info
  methods (#53), 3DS after authorisation (#54).
- 1.0.20231108 to 1.0.20231128: descriptors (#46, #49), Visa Installments (#45, #47), metadata on provider tokens
  (#48).
- 1.0.20230517 to 1.0.20230609: timeout fixes (#40), webhook changes (#41), multiple regions (#42, #43), error
  codes rearranged (#44).
- 1.0.20230302 to 1.0.20230414: better errors (#31, #35), metadata on charges (#32), `/payment_method/checkout`
  removed (#34), `threeDsProviderId` (#36), timeouts (#38, #39).
- 1.0.20230123 to 1.0.20230214: `UpdateProvider` (#27), webhooks (#28, #29), default payment methods (#30).
- 1.0.20230104 to 1.0.20230118.1: refund comments (#15), `ApiException` mapping (#17), payment token checks (#19),
  initiator (#21), providers (#22), level 2 and 3 data (#25, #26).
- 1.0.20221104: the first release: charges, customers, payment methods, transfers, vaults, tokens (#1 to #12).

[Unreleased]: https://github.com/payfurl/javasdk/compare/v2.0.0...HEAD
[2.0.0]: https://github.com/payfurl/javasdk/releases/tag/v2.0.0
