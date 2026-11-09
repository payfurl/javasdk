package com.payfurl.payfurlsdk.api;

import com.payfurl.payfurlsdk.PayFurlClient;
import com.payfurl.payfurlsdk.models.*;
import com.payfurl.payfurlsdk.models.Batch.BatchSearch;
import com.payfurl.payfurlsdk.models.Batch.NewTransactionPaymentMethod;
import com.payfurl.payfurlsdk.models.PaymentLink.CreatePaymentLink;
import com.payfurl.payfurlsdk.models.PaymentLink.SearchPaymentLink;
import com.payfurl.payfurlsdk.models.Subscription.NewSubscription;
import com.payfurl.payfurlsdk.models.Subscription.SubscriptionSearch;
import com.payfurl.payfurlsdk.models.Subscription.SubscriptionUpdate;
import com.payfurl.payfurlsdk.models.Subscription.SubscriptionUpdateStatus;
import com.payfurl.payfurlsdk.support.MockApiTest;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Every endpoint the SDK calls: its HTTP method and path, and that a POST or PUT sends a JSON body. */
class ApiRoutesTest extends MockApiTest {

    private static Arguments route(String method, String path, Consumer<PayFurlClient> call) {
        return Arguments.of(method + " " + path, method, path, call, "{}");
    }

    private static Arguments arrayRoute(String method, String path, Consumer<PayFurlClient> call) {
        return Arguments.of(method + " " + path, method, path, call, "[]");
    }

    static Stream<Arguments> routes() {
        return Stream.of(
                // charges
                route("POST", "/charge/card", c -> c.getChargeApi().createWithCard(new NewChargeCardRequest.Builder().build())),
                route("POST", "/charge/card/least_cost", c -> c.getChargeApi().createWithCardLeastCost(new NewChargeCardLeastCost.Builder().build())),
                route("POST", "/charge/payment_method", c -> c.getChargeApi().createWithPaymentMethod(new NewChargePaymentMethod.Builder().build())),
                route("POST", "/charge/customer", c -> c.getChargeApi().createWithCustomer(new NewChargeCustomer.Builder().build())),
                route("POST", "/charge/token", c -> c.getChargeApi().createWithToken(new NewChargeToken.Builder().build())),
                route("POST", "/charge/bank_account", c -> c.getChargeApi().createWithBankAccount(new NewChargeBankPaymentRequest.Builder().build())),
                route("GET", "/charge/ch_1", c -> c.getChargeApi().single("ch_1")),
                route("GET", "/charge", c -> c.getChargeApi().search(new ChargeSearch.Builder().build())),
                route("DELETE", "/charge/ch_1", c -> c.getChargeApi().refund(new NewRefund.Builder().withChargeId("ch_1").build())),
                route("POST", "/charge/ch_1/capture", c -> c.getChargeApi().captureCharge("ch_1", new NewChargeCapture.Builder().withAmount(BigDecimal.ONE).build())),
                route("DELETE", "/charge/ch_1/void", c -> c.getChargeApi().voidCharge("ch_1")),

                // customers
                route("POST", "/customer/card", c -> c.getCustomerApi().createWithCard(new NewCustomerCard.Builder().build())),
                route("POST", "/customer/token", c -> c.getCustomerApi().createWithToken(new NewCustomerToken.Builder().build())),
                route("POST", "/customer/provider_token", c -> c.getCustomerApi().createWithProviderToken(new NewCustomerProviderToken.Builder().build())),
                route("POST", "/customer/provider_single_use_token", c -> c.getCustomerApi().createWithSingleUseToken(new NewCustomerProviderSingleUseToken.Builder().build())),
                route("POST", "/customer/bank_account", c -> c.getCustomerApi().createWithBankAccount(new NewCustomerBankPayment.Builder().build())),
                route("POST", "/customer/cu_1/payment_method/card", c -> c.getCustomerApi().createPaymentMethodWithCard("cu_1", new NewPaymentMethodCard.Builder().build())),
                route("POST", "/customer/cu_1/payment_method/token", c -> c.getCustomerApi().createPaymentMethodWithToken("cu_1", new NewPaymentMethodToken.Builder().build())),
                route("POST", "/customer/cu_1/payment_method/payto", c -> c.getCustomerApi().createPaymentMethodWithPayTo("cu_1", new NewPayToAgreement.Builder().build())),
                route("POST", "/customer/cu_1/bank_account", c -> c.getCustomerApi().createWithPaymentMethodWithBankAccount("cu_1", new NewPaymentMethodBankPayment.Builder().build())),
                route("GET", "/customer/cu_1", c -> c.getCustomerApi().single("cu_1")),
                route("GET", "/customer", c -> c.getCustomerApi().search(new CustomerSearch.Builder().build())),
                arrayRoute("GET", "/customer/cu_1/payment_method", c -> c.getCustomerApi().getPaymentMethods("cu_1")),
                route("PUT", "/customer/cu_1", c -> c.getCustomerApi().updateCustomer("cu_1", new UpdateCustomer.Builder().build())),
                route("DELETE", "/customer/cu_1", c -> c.getCustomerApi().deleteCustomer("cu_1")),

                // payment methods
                route("POST", "/payment_method/card", c -> c.getPaymentMethodApi().createPaymentMethodWithCard(new NewPaymentMethodCard.Builder().build())),
                route("POST", "/payment_method/vault", c -> c.getPaymentMethodApi().createPaymentMethodWithVault(new NewPaymentMethodVault.Builder().build())),
                route("POST", "/payment_method/token", c -> c.getPaymentMethodApi().createPaymentMethodWithToken(new NewPaymentMethodToken.Builder().build())),
                route("POST", "/payment_method/provider_single_use_token", c -> c.getPaymentMethodApi().createPaymentMethodWithSingleUseToken(new NewPaymentMethodProviderSingleUseToken.Builder().build())),
                route("POST", "/payment_method/provider_token", c -> c.getPaymentMethodApi().createPaymentMethodWithProviderToken(new NewProviderToken.Builder().build())),
                route("POST", "/payment_method/bank_account", c -> c.getPaymentMethodApi().createPaymentMethodWithBankAccount(new NewPaymentMethodBankPayment.Builder().build())),
                route("GET", "/payment_method/pm_1", c -> c.getPaymentMethodApi().single("pm_1")),
                route("GET", "/payment_method", c -> c.getPaymentMethodApi().search(new PaymentMethodSearch.Builder().build())),
                route("PUT", "/payment_method/pm_1", c -> c.getPaymentMethodApi().updatePaymentMethod("pm_1", new UpdatePaymentMethod.Builder().build())),
                route("DELETE", "/payment_method/pm_1", c -> c.getPaymentMethodApi().deletePaymentMethod("pm_1")),

                // the rest
                arrayRoute("POST", "/transfer", c -> c.getTransferApi().create(new NewTransferGroup.Builder().build())),
                route("GET", "/transfer/tr_1", c -> c.getTransferApi().single("tr_1")),
                route("GET", "/transfer", c -> c.getTransferApi().search(new TransferSearch.Builder().build())),
                route("POST", "/vault", c -> c.getVaultApi().create(new NewVault.Builder().build())),
                route("GET", "/vault/va_1", c -> c.getVaultApi().single("va_1")),
                route("DELETE", "/vault/va_1", c -> c.getVaultApi().delete("va_1")),
                route("GET", "/token/tok_1", c -> c.getTokenApi().single("tok_1")),
                route("GET", "/token", c -> c.getTokenApi().search(new TokenSearch.Builder().build())),
                route("POST", "/provider", c -> c.getProviderApi().create(new NewProvider.Builder().build())),
                route("PUT", "/provider/pr_1", c -> c.getProviderApi().update("pr_1", new UpdateProvider.Builder().build())),
                route("DELETE", "/provider/pr_1", c -> c.getProviderApi().delete("pr_1")),
                route("POST", "/batch/transaction/payment_method", c -> c.getBatchApi().createTransactionWithPaymentMethod(new NewTransactionPaymentMethod.Builder().build())),
                route("GET", "/batch/ba_1", c -> c.getBatchApi().getBatch("ba_1")),
                route("GET", "/batch/ba_1/status", c -> c.getBatchApi().getBatchStatus("ba_1")),
                route("GET", "/batch", c -> c.getBatchApi().searchBatch(new BatchSearch.Builder().build())),
                route("POST", "/subscription/payment_method", c -> c.getSubscriptionApi().createSubscription(new NewSubscription.Builder().build())),
                route("GET", "/subscription/su_1", c -> c.getSubscriptionApi().getSubscription("su_1")),
                route("GET", "/subscription", c -> c.getSubscriptionApi().searchSubscription(new SubscriptionSearch.Builder().build())),
                route("DELETE", "/subscription/su_1", c -> c.getSubscriptionApi().deleteSubscription("su_1")),
                route("PUT", "/subscription/su_1/status", c -> c.getSubscriptionApi().updateSubscriptionStatus("su_1", new SubscriptionUpdateStatus.Builder().build())),
                route("PUT", "/subscription/su_1", c -> c.getSubscriptionApi().updateSubscription("su_1", new SubscriptionUpdate.Builder().build())),
                route("POST", "/webhook/subscription", c -> c.getWebhookSubscriptionApi().createWebhookSubscription(new NewWebhookSubscription.Builder().build())),
                route("GET", "/webhook/subscription/wh_1", c -> c.getWebhookSubscriptionApi().getWebhookSubscription("wh_1")),
                route("GET", "/webhook/subscription", c -> c.getWebhookSubscriptionApi().searchWebhookSubscription(new WebhookSubscriptionSearch.Builder().build())),
                route("DELETE", "/webhook/subscription/wh_1", c -> c.getWebhookSubscriptionApi().deleteWebhookSubscription("wh_1")),
                route("POST", "/payment_link", c -> c.getPaymentLinkApi().create(new CreatePaymentLink.Builder().build())),
                route("GET", "/payment_link/pl_1", c -> c.getPaymentLinkApi().single("pl_1")),
                route("GET", "/payment_link", c -> c.getPaymentLinkApi().search(new SearchPaymentLink.Builder().build())),
                route("GET", "/info/providers", c -> c.getInfoApi().getProvidersInfo(BigDecimal.TEN, "AUD")),
                route("POST", "/charge/network_token", c -> c.getChargeApi().createWithNetworkToken(new NewChargeNetworkToken.Builder().build())),
                route("POST", "/customer/payto", c -> c.getCustomerApi().createWithPayTo(new NewCustomerPayTo.Builder().build())),
                route("POST", "/payment_method/payto", c -> c.getPaymentMethodApi().createPaymentMethodWithPayTo(new NewPayToAgreement.Builder().build())),
                route("GET", "/dispute/di_1", c -> c.getDisputeApi().single("di_1")),
                route("GET", "/dispute", c -> c.getDisputeApi().search(new DisputeSearch.Builder().build())),
                arrayRoute("GET", "/settlements", c -> c.getSettlementApi().search(new SettlementSearch.Builder().build())),
                route("GET", "/", c -> c.getRootApi().getStatus()),
                route("GET", "/version", c -> c.getRootApi().getVersion())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("routes")
    void callsTheEndpoint(String name, String method, String path, Consumer<PayFurlClient> call, String response) throws Exception {
        enqueueJson(response);

        call.accept(client);

        RecordedRequest request = takeRequest();
        assertThat(request.getMethod()).isEqualTo(method);
        assertThat(request.getRequestUrl().encodedPath()).isEqualTo(path);
        assertThat(request.getHeader("x-secretkey")).isEqualTo(SECRET_KEY);
        if ("POST".equals(method) || "PUT".equals(method)) {
            assertThat(request.getHeader("Content-Type")).startsWith("application/json");
            assertThat(request.getBody().readUtf8()).startsWith("{");
        }
    }
}
