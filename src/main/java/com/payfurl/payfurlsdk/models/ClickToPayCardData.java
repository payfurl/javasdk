package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The card of a Click to Pay token: its BIN and last four digits, never the full number.
 */
public class ClickToPayCardData {
    @JsonProperty("panBin")
    private String panBin;
    @JsonProperty("panLastFour")
    private String panLastFour;
    @JsonProperty("scheme")
    private String scheme;
    @JsonProperty("paymentCardType")
    private String paymentCardType;

    public String getPanBin() {
        return panBin;
    }

    public String getPanLastFour() {
        return panLastFour;
    }

    public String getScheme() {
        return scheme;
    }

    public String getPaymentCardType() {
        return paymentCardType;
    }

    @Override
    public String toString() {
        return "ClickToPayCardData{" +
                "panBin='" + panBin + '\'' +
                ", panLastFour='" + panLastFour + '\'' +
                ", scheme='" + scheme + '\'' +
                ", paymentCardType='" + paymentCardType + '\'' +
                '}';
    }
}
