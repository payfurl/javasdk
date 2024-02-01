package com.payfurl.payfurlsdk.models;

public class CurrencyInformationData {
    private String id;
    private String currencyCode;
    private String currencyNumber;
    private String name;
    private boolean useConversion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyNumber() {
        return currencyNumber;
    }

    public void setCurrencyNumber(String currencyNumber) {
        this.currencyNumber = currencyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUseConversion() {
        return useConversion;
    }

    public void setUseConversion(boolean useConversion) {
        this.useConversion = useConversion;
    }
}
