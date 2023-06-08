package com.payfurl.payfurlsdk.http.client.config;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Region {
    AU("au"),
    JP("jp"),
    US("us"),
    EU("eu"),
    NONE("none");

    private static final Map<String, Region> LABEL_TO_REGION_MAPPING = Arrays.stream(values())
            .collect(Collectors.toMap(region -> region.label, Function.identity()));

    public final String label;

    Region(String label) {
        this.label = label;
    }

    public static Region fromLabel(String label) {
        return LABEL_TO_REGION_MAPPING.get(label);
    }
}
