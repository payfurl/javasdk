package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClickToPayCardData {
    private final String panBin;
    private final String panLastFour;
    private final String scheme;
    private final String paymentCardType;

    @JsonCreator
    public ClickToPayCardData(@JsonProperty("PanBin") String panBin,
                              @JsonProperty("PanLastFour") String panLastFour,
                              @JsonProperty("Scheme") String scheme,
                              @JsonProperty("PaymentCardType") String paymentCardType) {
        this.panBin = panBin;
        this.panLastFour = panLastFour;
        this.scheme = scheme;
        this.paymentCardType = paymentCardType;
    }

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
}
