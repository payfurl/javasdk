package com.payfurl.client.support;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class HeadersData {
    private final Map<String, List<String>> headers;

    public HeadersData() {
        this.headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public HeadersData(Map<String, List<String>> headers) {
        this();
        this.headers.putAll(headers);
    }

    public boolean has(String headerName) {
        return this.headers.containsKey(headerName);
    }

    public Set<String> names() {
        return headers.keySet();
    }

    public String value(String headerName) {
        if (headers.containsKey(headerName)) {
            return headers.get(headerName).get(0);
        }

        return null;
    }

    public void add(String headerName, String value) {
        if (StringUtils.isEmpty(headerName) || StringUtils.isEmpty(value)) {
            return;
        }

        if (this.headers.containsKey(headerName)) {
            this.headers.get(headerName).add(value);
            return;
        }

        this.headers.put(headerName, Lists.newArrayList(value));
    }

    public Map<String, List<String>> asMultimap() {
        return cloneHeaderMap(this.headers);
    }

    private Map<String, List<String>> cloneHeaderMap(Map<String, List<String>> headerMap) {
        return headerMap.entrySet().stream()
                .filter(entry -> Objects.nonNull(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (key1, key2) -> key1, () -> new TreeMap<>(String.CASE_INSENSITIVE_ORDER)));
    }
}
