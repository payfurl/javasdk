package com.payfurl.payfurlsdk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.payfurl.payfurlsdk.http.client.config.Environment;
import com.payfurl.payfurlsdk.utils.TestConfigKeys;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public final class TestConfigProvider {
    private static final String TEST_DATA_FILE = "appsettings.json";
    private static final InputStream TEST_DATA_INPUT_STREAM =
            TestConfigProvider.class.getClassLoader().getResourceAsStream(TEST_DATA_FILE);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ImmutableMap<String, Object> CONFIG;

    private static int numToken = 0;

    static {
        try {
            CONFIG = ImmutableMap.<String, Object>builder()
                    .putAll(MAPPER.readValue(TEST_DATA_INPUT_STREAM, new TypeReference<HashMap<String, Object>>() {
                    }))
                    .build();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private TestConfigProvider() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String getSecretKeyWithFallback() {
        return MapUtils.getString(CONFIG, TestConfigKeys.SECRET_KEY, StringUtils.EMPTY);
    }

    public static Environment getEnvironmentWithFallback() {
        String env = MapUtils.getString(CONFIG, TestConfigKeys.ENVIRONMENT);
        return EnumUtils.getEnumIgnoreCase(Environment.class, env, Environment.LOCAL);
    }

    public static String getProviderId() {
        return MapUtils.getString(CONFIG, TestConfigKeys.PROVIDER_ID, StringUtils.EMPTY);
    }

    public static String getToken() {
        Object value = CONFIG.get(TestConfigKeys.TOKENS);

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

            return StringUtils.defaultIfEmpty(tokens[numToken++], StringUtils.EMPTY);
        }

        throw new RuntimeException("Read tokens error");
    }
}
