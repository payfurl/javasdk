# payFURL Java SDK

The [payFURL](https://payfurl.com) API for Java: charges, customers, payment methods, subscriptions, payment links,
batches, transfers, vaults, providers, tokens, disputes, settlements and webhook subscriptions. It covers the
endpoints a secret key can call, plus `GET /info/providers`. The API itself, its fields and its error codes are
documented at [docs.payfurl.com](https://docs.payfurl.com).

What changed in each release: [CHANGELOG.md](CHANGELOG.md).

## Upgrading from 1.0.x to 2.0

**2.0.0 is a breaking release.** It removes methods and fields of 1.0.x, so code written for 1.0.x may not compile
against it, and some calls behave differently. Nothing upgrades by itself: Maven and Gradle keep the version your
build names. Versions now follow [semver](https://semver.org) (`2.0.0`, not `1.0.<date>`), and Maven and Gradle sort
2.0.0 above every 1.0.x.

To stay on 1.0.x for now, keep your version, or cap it below 2.0: `[1.0,2.0)` in Maven and Gradle.

### Step 1: replace what was removed

Change these and your code compiles again:

| 1.0.x | 2.0 |
| --- | --- |
| `customerApi.createWitPaymentMethodWithToken(id, request)` | `customerApi.createPaymentMethodWithToken(id, request)` |
| `customerApi.createWitPaymentMethodWithPayTo(id, request)` | `customerApi.createPaymentMethodWithPayTo(id, request)` |
| `new InfoApi(...).GetProvidersInfo(amount, currency)` | `client.getInfoApi().getProvidersInfo(amount, currency)` |
| `InfoApi.getInfo()`, `GetProviderInfo`, `GetProviderTokenInfo`, `GetDefaultFallbackProvider` (and the `Info`, `InfoProvider`, `ProviderData`, `ProviderInfoModel`, `ProviderCostDataModel`, `ProviderVisibilityDataModel` and `CurrencyInformationData` models) | Removed: their endpoints are not part of the public API. `getProvidersInfo` covers what a checkout needs; `getProviderApi()` manages providers |
| `PayFurlClient.shutdown()` (static, closed the HTTP client every client in the JVM shared) | `client.close()` on the client you are done with, or try-with-resources |
| `chargeData.getThreeDsServerTransId()`, `chargeData.threeDsServerTransId` | `chargeData.getPaymentInformation().getCardData().getThreeDSServerTransID()` |
| `paymentData.getProviderType()`, `paymentData.providerType` | `chargeData.getProvider()` |
| `getUserId()` / `withUserId` on `PaymentMethodData` and `TokenData`; `getSecretKey()` / `withSecretKey` on `PaymentLinkData`; `getThreeDsProviderId()` / `withThreeDsProviderId` on `NewProvider` and `UpdateProvider` | Removed: the API never sent or read them, so they were always null |
| `PayToProviderOptions.getMinimumAmount()` | `getMaximumAmount()`: the API sends a maximum |
| `new ChargeData(...)`, `new PaymentData(...)`, `new TokenData(...)`, `new PaymentMethodData(...)`, `new PaymentLinkData(...)`, `new NewProvider(...)`, `new UpdateProvider(...)` | Their constructors lost the removed field. Build requests with their `Builder`; in tests, deserialize responses from JSON |
| A class implementing `PayFurlClientSdk` (a test double, say) | Also implement `getInfoApi()`, `getDisputeApi()`, `getSettlementApi()` and `getRootApi()` |

Before and after:

```java
// 1.0.x
PaymentMethodData card = payfurl.getCustomerApi().createWitPaymentMethodWithToken(customerId, request);
String threeDs = charge.getThreeDsServerTransId();
PayFurlClient.shutdown();

// 2.0
PaymentMethodData card = payfurl.getCustomerApi().createPaymentMethodWithToken(customerId, request);
String threeDs = charge.getPaymentInformation().getCardData().getThreeDSServerTransID();
payfurl.close();
```

### Step 2: check what behaves differently

These compile unchanged; check whether your code relies on the old behaviour:

- **Guava and commons-collections4 are gone from the SDK's dependencies.** If your code used them without declaring
  them (they came through the SDK), add them to your own build.
- **Jackson is 2.22** (was 2.12) and **commons-lang3 is 3.20** (was 3.14), which fixes their known CVEs. If your build
  pins older versions, Maven's nearest-wins rule keeps yours: check `mvn dependency:tree`.
- **`toString()` masks card data.** Card and bank account numbers print as their last four digits
  (`************1111`), and CVVs, webhook `Authorization` values and provider credentials as `***`, so logging a
  request no longer logs them. Code that parsed `toString()` output must read the getters.
- **GETs are retried.** A GET that fails with a connection error, a timeout or an error the API marks retryable is
  sent again, twice by default, after 0.5 and 1 second. Charges, refunds, voids and other writes are never retried.
  `withHttpClientConfiguration(c -> c.maxRetries(0))` turns it off.
- **Values the SDK does not know read as `UNKNOWN`.** A status the API adds later (a new `TransactionStatus`, say)
  no longer fails the call, and an error code the SDK does not know is `ErrorCode.UnknownError`. A `switch` over an
  SDK enum may want a `case UNKNOWN`.
- **TLS.** Only a client built with `Environment.LOCAL` accepts an untrusted certificate. In 1.0.x, creating a
  `LOCAL` client first turned off certificate checks for every client in the JVM.
- **Data that was dropped is sent.** A refund's comment, `NewChargeToken`'s `CheckoutTransfer` and the search
  date filters (`AddedAfter`, `AddedBefore`, now ISO-8601 in UTC) reach the API, so searches by date now filter.

### Step 3: test

Run your tests against 2.0.0 in sandbox before production: a charge, a refund with a comment, a search by date, and
the error handling your code relies on.

## Install

Maven:

```xml
<dependency>
    <groupId>com.payfurl</groupId>
    <artifactId>payfurlsdk</artifactId>
    <version>2.0.0</version>
</dependency>
```

Gradle:

```groovy
implementation 'com.payfurl:payfurlsdk:2.0.0'
```

It runs on Java 8 or later; CI tests it on Java 8, 11, 17, 21 and 25. Its dependencies are Jackson 2
(`jackson-databind`), OkHttp 4 and commons-lang3.

## Quick start

```java
import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.models.ChargeData;
import com.payfurl.payfurlsdk.models.NewChargeToken;

import java.math.BigDecimal;

PayFurlClient payfurl = new PayFurlClient.Builder()
        .withEnvironment(Environment.SANDBOX)
        .withSecretKey("sectest...-au")
        .build();

// A token from the browser SDK or a hosted payment page.
ChargeData charge = payfurl.getChargeApi().createWithToken(new NewChargeToken.Builder()
        .withAmount(new BigDecimal("20.00"))
        .withCurrency("AUD")
        .withToken(token)
        .withReference("order-1001")
        .build());

System.out.println(charge.getChargeId() + ": " + charge.getStatus()); // ...: SUCCESS
```

Every call blocks until the API answers, and returns the API's response as a model (`ChargeData`, `CustomerData`,
...). Request models are built with their `Builder`.

A client holds one account's key: make one per account or region, keep it for the life of the app, and share it
between threads. Clients share one connection pool.

## Environments and regions

| `Environment` | URL | with an `-au` key |
| --- | --- | --- |
| `PRODUCTION` (the default) | https://api.payfurl.com | https://api-au.payfurl.com |
| `SANDBOX` | https://sandbox-api.payfurl.com | https://sandbox-api-au.payfurl.com |

The key's last part (`sectest...-au`) is its account's region. `DEVELOPMENT` and `LOCAL` are payFURL's own
environments. `withBaseUrl("https://...")` sends requests to another URL, a proxy say; certificates are still
checked.

## The APIs

| `payfurl.get...()` | Endpoints |
| --- | --- |
| `getChargeApi()` | `charge`: card, least-cost card, token, customer, payment method, bank account and network token charges; get, search, refund, capture, void |
| `getCustomerApi()` | `customer`: create with a card, token, PayTo, provider token or bank account; get, search, update, delete; add and list payment methods |
| `getPaymentMethodApi()` | `payment_method`: create with a card, vault, token, PayTo, provider token or bank account; get, search, update, delete |
| `getSubscriptionApi()` | `subscription`: create, get, search, update, change status, delete |
| `getPaymentLinkApi()` | `payment_link`: create, get, search |
| `getBatchApi()` | `batch`: charge a list of payment methods, get, status, search |
| `getTransferApi()` | `transfer`: create, get, search |
| `getVaultApi()` | `vault`: create, get, delete |
| `getProviderApi()` | `provider`: create, update, delete |
| `getTokenApi()` | `token`: get, search |
| `getDisputeApi()` | `dispute`: get, search |
| `getSettlementApi()` | `settlements`: search by date |
| `getWebhookSubscriptionApi()` | `webhook/subscription`: create, get, search, delete |
| `getInfoApi()` | `info/providers`: the payment methods a checkout can offer for an amount and currency |
| `getRootApi()` | `GET /` and `GET /version`: whether the API is up, and its version |

## Examples

```java
// Charges. A card number on your server puts it in PCI DSS scope; a token from the browser SDK keeps it out.
ChargeData charge = payfurl.getChargeApi().createWithCardLeastCost(new NewChargeCardLeastCost.Builder()
        .withAmount(new BigDecimal("15"))
        .withCurrency("AUD")
        .withPaymentInformation(new CardRequestInformation.Builder()
                .withCardNumber("4111111111111111")
                .withExpiryDate("12/30")
                .withCcv("123")
                .build())
        .build());
payfurl.getChargeApi().refund(new NewRefund.Builder()
        .withChargeId(charge.getChargeId())
        .withRefundAmount(new BigDecimal("5"))
        .withComment("Returned one item")
        .build());
ChargeList page = payfurl.getChargeApi().search(new ChargeSearch.Builder()
        .withAddedAfter(Date.from(Instant.now().minus(Duration.ofDays(7))))
        .withLimit(20)
        .build());

// Customers and their payment methods
CustomerData customer = payfurl.getCustomerApi().createWithToken(new NewCustomerToken.Builder()
        .withToken(token)
        .withEmail("jo@example.com")
        .build());
List<PaymentMethodData> paymentMethods = payfurl.getCustomerApi().getPaymentMethods(customer.getCustomerId());
payfurl.getChargeApi().createWithCustomer(new NewChargeCustomer.Builder()
        .withAmount(BigDecimal.TEN)
        .withCurrency("AUD")
        .withCustomerId(customer.getCustomerId())
        .build());

// What a checkout can offer
InfoProviders providers = payfurl.getInfoApi().getProvidersInfo(new BigDecimal("20"), "AUD");
```

## Errors and retries

Every failed call throws `ApiException`, an unchecked exception:

| Method | What |
| --- | --- |
| `getHttpCode()` | the HTTP status; 500 when there was no answer |
| `getCode()` | the API's `ErrorCode` (`InsufficientFunds`, `InvalidCardNumber`, ...); `UnknownError` for a code the SDK does not know yet; `Timeout` when the API did not answer in time |
| `getMessage()`, `getDetails()` | what went wrong; `getDetails()` names the fields of a validation error |
| `getGatewayCode()`, `getGatewayMessage()` | the gateway's own decline code and message |
| `isRetryable()` | whether sending the same request again can succeed |
| `getType()` | a link to the error code's documentation |
| `getChargeId()`, `getTraceId()` | the declined charge's ID; the request's trace ID, to quote to payFURL support |

```java
try {
    payfurl.getChargeApi().createWithToken(request);
} catch (ApiException e) {
    if (e.getCode() == ErrorCode.InsufficientFunds) {
        // declined
    } else if (e.getCode() == ErrorCode.Timeout) {
        // the outcome is unknown: search by reference before charging again
    }
}
```

GETs are retried after a connection error, a timeout or an error the API marks retryable: twice by default, after
0.5 and then 1 second. Nothing else is retried: the API has no idempotency keys, so a retried charge could charge
twice. The timeout is 60 seconds by default. Both are set on the builder:

```java
PayFurlClient payfurl = new PayFurlClient.Builder()
        .withSecretKey(secretKey)
        .withHttpClientConfiguration(config -> config
                .timeout(30_000)      // milliseconds
                .maxRetries(3)
                .retryDelay(250))     // milliseconds, doubled for each retry
        .build();
```

## Your own OkHttp client

For a proxy, interceptors or your own connection pool, give the builder an `OkHttpClient`. The SDK derives its client
from yours (`newBuilder()`) with its own timeouts; yours is not changed, and `close()` leaves it open.

```java
OkHttpClient okHttp = new OkHttpClient.Builder()
        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.internal", 3128)))
        .build();

PayFurlClient payfurl = new PayFurlClient.Builder()
        .withSecretKey(secretKey)
        .withOkHttpClient(okHttp)
        .build();
```

## Webhooks

```java
// body: the request body as a string, signature: the X-Payfurl-Signature header,
// signingKey: the webhook signing key from the dashboard.
WebhookTransaction webhook = WebhookTools.deserializeTransaction(body, signature, signingKey);
```

It throws `IllegalArgumentException` when the signature does not match. The signature is compared in constant time.

## Testing your code

Point the client at a local server, OkHttp's [MockWebServer](https://github.com/square/okhttp/tree/parent-4.12.0/mockwebserver)
say, and nothing goes over the network:

```java
MockWebServer server = new MockWebServer();
server.enqueue(new MockResponse().setBody("{\"chargeId\":\"ch_1\",\"status\":\"SUCCESS\"}"));

PayFurlClient payfurl = new PayFurlClient.Builder()
        .withEnvironment(Environment.SANDBOX)
        .withSecretKey("sectest")
        .withBaseUrl(server.url("/").toString())
        .build();
```

The SDK's own tests do the same: [MockApiTest](src/test/java/com/payfurl/payfurlsdk/support/MockApiTest.java).

## Development

You need [just](https://github.com/casey/just), a JDK (8 or later) and Maven:

```bash
just test      # compile and the unit tests: what CI runs on every pull request
just package   # the jar, into target/
```

The unit tests never call an API. payFURL also runs integration tests against an API built from source before each
release.

## Releasing

Add the version's section to [CHANGELOG.md](CHANGELOG.md), merge it to `main`, then push a tag `vX.Y.Z` on that
commit. The release workflow runs the tests, publishes the signed jar to Maven Central with the tag's version, and
creates the GitHub release from the changelog.

## License

MIT
