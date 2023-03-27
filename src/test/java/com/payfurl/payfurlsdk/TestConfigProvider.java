package com.payfurl.payfurlsdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TestConfigProvider {
    private static final String TEST_DATA_FILE = "appsettings.json";
    private static final Map<String, Object> config = new HashMap<>();
    private static int numToken = 0;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = TestConfigProvider.class.getClassLoader().getResourceAsStream(TEST_DATA_FILE);
            config.putAll(mapper.readValue(inputStream, Map.class));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private TestConfigProvider() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String getSecretKeyWithFallback() {
        return StringUtils.defaultIfEmpty(config.get("SecretKey").toString(), "");
    }

    public static Environment getEnvironmentWithFallback() {
        String env = config.get("Environment").toString();
        return EnumUtils.getEnumIgnoreCase(Environment.class, env, Environment.LOCAL);
    }

    public static String getProviderId() {
        return StringUtils.defaultIfEmpty(config.get("ProviderId").toString(), "");
    }

    public static String getToken() {
        Object value = config.get("Tokens");
        if (value instanceof List) {
            List<?> list = (List<?>) value;
            String[] tokens = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Object element = list.get(i);
                if (element instanceof String) {
                    tokens[i] = (String) element;
                } else {
                    throw new RuntimeException("Read tokens error");
                }
            }
            return StringUtils.defaultIfEmpty(tokens[numToken++], "");
        }

        throw new RuntimeException("Read tokens error");
    }
}
