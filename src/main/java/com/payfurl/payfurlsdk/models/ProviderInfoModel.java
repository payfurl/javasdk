package com.payfurl.payfurlsdk.models;

public class ProviderInfoModel {
    private String id;
    private String name;
    private String type;
    private boolean applyCurrencyConversion;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isApplyCurrencyConversion() {
        return this.applyCurrencyConversion;
    }

    public void setApplyCurrencyConversion(boolean applyCurrencyConversion) {
        this.applyCurrencyConversion = applyCurrencyConversion;
    }
}
