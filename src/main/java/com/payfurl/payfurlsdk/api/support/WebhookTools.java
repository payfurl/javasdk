package com.payfurl.payfurlsdk.api.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payfurl.payfurlsdk.http.client.support.ApiUtils;
import com.payfurl.payfurlsdk.models.WebhookTransaction;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class WebhookTools {

    /**
     * Deserialize webhook transaction request body from PayFURL and validate that a webhook event notification came
     * from PayFURL. Requests that fail validation will be discarded as they cannot be trusted.
     * @param requestBody HTTP request body
     * @param signatureHeader Webhook signature header <code>X-Payfurl-Signature</code>
     * @param signatureKey Webhook signature key (from dashboard)
     * @return Deserialized transaction message.
     * @throws IllegalArgumentException if signature validation is failed
     */
    public static WebhookTransaction deserializeTransaction(String requestBody, String signatureHeader, String signatureKey) {
        if (!isFromPayFurl(requestBody, signatureHeader, signatureKey)) {
            throw new IllegalArgumentException("Request body is not from PayFURL");
        }

        try {
            return ApiUtils.deserialize(requestBody, WebhookTransaction.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Request body could not be converted to WebhookTransaction object", e);
        }
    }

    private static boolean isFromPayFurl(String requestBody, String signatureHeader, String signatureKey) {
        if (requestBody == null || signatureHeader == null || signatureKey == null || signatureKey.isEmpty()) {
            return false;
        }
        byte[] requestBytes = requestBody.getBytes(StandardCharsets.UTF_8);
        byte[] secret = signatureKey.getBytes(StandardCharsets.UTF_8);

        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret, "HmacSHA256");
            hmac.init(secretKey);

            byte[] hash = hmac.doFinal(requestBytes);

            // Constant time, so the comparison doesn't tell an attacker how much of a forged signature is right.
            return MessageDigest.isEqual(Base64.getEncoder().encode(hash), signatureHeader.trim().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            return false;
        }
    }
}
