package com.payfurl.payfurlsdk.api.support;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class WebhookTools {

    /**
     * Validate that a webhook event notification came from PayFURL.Requests that fail validation
     * should be discarded as they cannot be trusted.
     *
     * @param requestBody     HTTP request body
     * @param signatureHeader Webhook signature header (X-Payfurl-Signature)
     * @param signatureKey    Webhook signature key (from dashboard)
     * @return If the request from PayFURL returns true, otherwise false
     */
    public static boolean isFromPayFurl(String requestBody, String signatureHeader, String signatureKey) {
        byte[] requestBytes = requestBody.getBytes(StandardCharsets.UTF_8);
        byte[] secret = signatureKey.getBytes(StandardCharsets.UTF_8);

        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret, "HmacSHA256");
            hmac.init(secretKey);

            byte[] hash = hmac.doFinal(requestBytes);

            String hashString = Base64.getEncoder().encodeToString(hash);
            return hashString.equals(signatureHeader);
        } catch (Exception e) {
            return false;
        }
    }
}
