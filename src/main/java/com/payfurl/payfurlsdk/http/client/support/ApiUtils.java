package com.payfurl.payfurlsdk.http.client.support;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiUtils {
    private static final Pattern ABSOLUTE_URL_VALIDATION_PATTERN = Pattern.compile("^(https?://[^/]+)");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            // A value the API adds to an enum later reads as that enum's UNKNOWN instead of failing the response.
            .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);

    static {
        OBJECT_MAPPER.configOverride(BigDecimal.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
    }

    public static String serialize(Object obj) throws JsonProcessingException {
        if (obj == null) {
            return null;
        }

        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    public static <T> T deserialize(String jsonString, Class<T> clazz) throws JsonProcessingException {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(jsonString, clazz);
    }

    public static void appendUrlWithQueryParameters(StringBuilder queryBuilder,
                                                    Map<String, Object> parameters) {
        Objects.requireNonNull(queryBuilder);

        if (parameters == null || parameters.isEmpty()) {
            return;
        }

        boolean hasParams = queryBuilder.indexOf("?") > 0;
        queryBuilder.append(hasParams ? '&' : '?');

        encodeObjectAsQueryString(parameters, queryBuilder);
    }

    private static void encodeObjectAsQueryString(Map<String, Object> parameters, StringBuilder objBuilder) {
        if (parameters == null) {
            return;
        }

        boolean areParamsPresent = false;
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }

            areParamsPresent = true;
            String key = entry.getKey();
            String keyValuePair = String.format("%s=%s&", key, tryUrlEncode(toQueryValue(value), false));
            objBuilder.append(keyValuePair);
        }

        if (areParamsPresent) {
            objBuilder.setLength(objBuilder.length() - 1);
        }
    }

    /**
     * A query value as the API's binders read it: a date as ISO-8601 in UTC ({@code 2026-11-07T10:15:30Z}), a decimal
     * without an exponent ({@code 1000}, not {@code 1E+3}).
     */
    static String toQueryValue(Object value) {
        if (value instanceof Date) {
            return ((Date) value).toInstant().toString();
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).toPlainString();
        }
        return value.toString();
    }

    public static String cleanUrl(StringBuilder urlBuilder) {
        Matcher matcher = ABSOLUTE_URL_VALIDATION_PATTERN.matcher(urlBuilder);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid Url format.");
        }

        String httpProtocol = matcher.group(1);
        String query = cleanRedundantForwardSlashes(urlBuilder, httpProtocol);

        return httpProtocol.concat(query);
    }

    private static String cleanRedundantForwardSlashes(StringBuilder urlBuilder, String httpProtocol) {
        String query = urlBuilder.substring(httpProtocol.length());
        query = query.replaceAll("//+", "/");
        return query;
    }

    private static String tryUrlEncode(String value, boolean spaceAsPercentEncoded) {
        try {
            String encodedUrl = URLEncoder.encode(value, "UTF-8");

            return spaceAsPercentEncoded
                    ? encodedUrl.replace("+", "%20")
                    : encodedUrl;

        } catch (UnsupportedEncodingException ex) {
            return value;
        }
    }
}
