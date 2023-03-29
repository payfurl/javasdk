# PayFURL Java SDK

Library for integrating with PayFURL payments in your app. It includes the following APIs:

1. Charge API
2. Customer API
3. PaymentMethod API
4. Transfer API
5. Vault API
6. Token API
7. Provider API

## 📄 Requirements

Use of the PayFURL Java SDK requires:

* Java 8 or higher
* Maven or Gradle

## 🧰 Installation

To use the PayFURL Java SDK in your project please do the following steps:

1. Install PayFURL Java SDK:

- Edit the `pom.xml` file:

```xml
<dependency>
    <groupId>com.payfurl</groupId>
    <artifactId>payfurlsdk</artifactId>
    <version>SDK_VERSION</version>
    <scope>compile</scope>
</dependency>
```
or 

- Edit the `build.gradle` file:

```groovy
dependencies {
    implementation 'com.payfurl:payfurlsdk:SDK_VERSION'
}
```


2. Replace `SDK_VERSION` with the latest version of the PayFURL Java SDK.

`compile` scope in Maven resolves all dependencies and makes the PayFURL Java SDK available to your project.

Alternatively, you can use Maven to download the PayFURL Java SDK, and all other dependencies, to your local Maven
repository.

```shell
mvn install -DskipTests
```

## 👷 Usage

An example of creating a PayFURL client with **sandbox** environment.

```java
import com.payfurl.payfurlsdk.api.ChargeApi;
import com.payfurl.payfurlsdk.models.NewChargeToken;

import java.math.BigDecimal;

class Example {
    // Initialize
    PayFurlClient payFurlClient = new PayFurlClient.Builder()
            .withEnvironment(Environment.SANDBOX)
            .withSecretKey(PAYFURL_SECRET_KEY)
            .build();
    ChargeApi chargeApi = payFurlClient.getChargeApi();

    public ChargeData chargeToken() {
        NewChargeToken newChargeToken = new NewChargeToken.Builder()
                .withAmount(BigDecimal.valueOf(20))
                // Collected from payment form using Client SDK
                .withToken("5db53c06443c8f28c0cba6e5")
                
                // Optional webhook
                .withWebhookConfig(new WebhookConfig.Builder()
                        .withUrl("https://webhook.site/1da8cac9-fef5-47bf-a276-81856f73d7ca")
                        
                        // Optional authorization
                        .withAuthorization("Basic user:password")
                        .build())
                .build();
        
        // Charge a token
        return chargeApi.createWithToken(newChargeToken);
    }
}
```

❗ If `Environment` is not set, then by default it will use a `PRODUCTION` environment.


## 🔨 Tests

Clone the repo locally and `cd` into the directory.

```sh
git clone https://github.com/payfurl/javasdk.git
cd javasdk
```

Before running the tests, create `appsettings.json` file in `src/test/resources` folder with the following contents:

```json
{
  "Environment": "Development",
  "SecretKey": "PAYFURL_SECRET_KEY",
  "ProviderId": "DUMMY_PROVIDER_ID",
  "Tokens": ["PAYMENT_TOKEN1","PAYMENT_TOKEN2"]
}
```

We recommend to have 8 payment tokens to make all tests passed.


If you are using Maven, run the tests with below command

```sh
mvn test
```
