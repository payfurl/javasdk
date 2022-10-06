package com.payfurl.http.client.support;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Headers {
    private final Map<String, List<String>> headers;

    public Headers() {
        this.headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public Headers(Map<String, List<String>> headers) {
        this();
        this.headers.putAll(headers);
    }

    public Headers(Headers newHeaders) {
        this.headers = cloneHeaderMap(newHeaders.headers);
    }

    public String value(String headerName) {
        List<String> valueList = headers.get(headerName);
        if (valueList == null) {
            return null;
        }

        return valueList.get(0);
    }

    public void add(String headerName, String value) {
        if (StringUtils.isEmpty(headerName) || StringUtils.isEmpty(value)) {
            return;
        }

        List<String> valueList = this.headers.get(headerName);
        if (valueList != null) {
            valueList.add(value);
            return;
        }

        this.headers.put(headerName, Lists.newArrayList(value));
    }

    public void add(String headerName, List<String> values) {
        if (headerName == null || values == null) {
            return;
        }

        if (this.headers.containsKey(headerName)) {
            for (String value : values) {
                if (value != null) {
                    this.headers.get(headerName).add(value);
                }
            }
            return;
        }

        List<String> copyOfValues = new ArrayList<>();
        for (String value : values) {
            if (value != null) {
                copyOfValues.add(value);
            }
        }

        if (!copyOfValues.isEmpty()) {
            this.headers.put(headerName, copyOfValues);
        }
    }

    public Map<String, List<String>> asMultimap() {
        return cloneHeaderMap(this.headers);
    }

    public void addAll(Headers headers) {
        headers.asMultimap().forEach(this::add);
    }

    private Map<String, List<String>> cloneHeaderMap(Map<String, List<String>> headerMap) {
        return headerMap.entrySet().stream()
                .filter(entry -> Objects.nonNull(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (key1, key2) -> key1, () -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER)));
    }
}
