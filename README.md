# PayFURL Java SDK

Library for integrating with PayFURL payments in your app. It includes the following APIs:

1. Charge API
2. Customer API
3. PaymentMethod API
4. Transfer API
5. Vault API

## 📄 Requirements

Use of the PayFURL Java SDK requires:

* Java 8 or higher
* Maven or Gradle

## 🧰 Installation

To use the PayFURL Java SDK in your project please do the following steps:

1. Edit the `pom.xml` file and add a new dependency:

```xml
<dependency>
    <groupId>com.payfurl</groupId>
    <artifactId>payfurlsdk</artifactId>
    <version>SDK_VERSION</version>
    <scope>compile</scope>
</dependency>
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
import com.payfurl.api.ChargeApi;
import com.payfurl.api.CustomerApi;
import com.payfurl.models.NewChargeToken;

import java.math.BigDecimal;

class Example {
    // Initialize
    PayFurlClient payFurlClient = new PayFurlClient.Builder()
            .withEnvironment(Environment.SANDBOX)
            .withAccessToken(PAYFURL_ACCESS_TEST_TOKEN)
            .build();
    ChargeApi chargeApi = payFurlClient.getChargeApi();

    public ChargeData chargeToken() {
        NewChargeToken newChargeToken = new NewChargeToken.Builder()
                .withAmount(BigDecimal.valueOf(20))
                // Collected from payment form using Client SDK
                .withToken("5db53c06443c8f28c0cba6e5")
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

Before running the tests, get a sandbox token and set a `PAYFURL_ACCESS_TEST_TOKEN` environment
variable.

```sh
export PAYFURL_ENVIRONMENT=sandbox
export PAYFURL_ACCESS_TEST_TOKEN=YOUR_SANDBOX_ACCESS_TOKEN
```

If you are using Maven, run the tests with below command

```sh
mvn test
```
