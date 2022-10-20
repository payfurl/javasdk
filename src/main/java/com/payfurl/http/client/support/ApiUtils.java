package com.payfurl.http.client.support;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiUtils {
    private static final Pattern ABSOLUTE_URL_VALIDATION_PATTERN = Pattern.compile("^(https?://[^/]+)");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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
        Preconditions.checkNotNull(queryBuilder);

        if (MapUtils.isEmpty(parameters)) {
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
            String keyValuePair = String.format("%s=%s&", key, tryUrlEncode(value.toString(), false));
            objBuilder.append(keyValuePair);
        }

        if (areParamsPresent) {
            objBuilder.setLength(objBuilder.length() - 1);
        }
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
