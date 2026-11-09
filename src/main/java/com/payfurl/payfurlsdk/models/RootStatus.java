package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The API's status: {@code GET /} (RootController), {@code "ok"} when it is up.
 */
public class RootStatus {
    @JsonProperty("status")
    private String status;

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RootStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
