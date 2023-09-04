package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VisaInstallmentsPlanTermsAndConditionsType {
    private final String url;
    private final Integer version;
    private final String text;
    private final String languageCode;

    @JsonCreator
    public VisaInstallmentsPlanTermsAndConditionsType(
            @JsonProperty("Url") String url,
            @JsonProperty("Version") Integer version,
            @JsonProperty("Text") String text,
            @JsonProperty("LanguageCode") String languageCode
    ) {
        this.url = url;
        this.version = version;
        this.text = text;
        this.languageCode = languageCode;
    }

    public String getUrl() {
        return url;
    }

    public Integer getVersion() {
        return version;
    }

    public String getText() {
        return text;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public String toString() {
        return "VisaInstallmentsPlanTermsAndConditionsType{" +
                "url=" + url +
                ", version=" + version +
                ", text=" + text +
                ", languageCode=" + languageCode +
                '}';
    }
}
