package com.github.neatlife.jframework.fundation.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author suxiaolin
 */
public class JsonUtil {

    private static final Map<String, JsonUtil> INSTANCES = new HashMap<>();

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        setTimeZone(Calendar.getInstance().getTimeZone());
    }

    static JsonUtil pick(String instanceId) {
        return INSTANCES.computeIfAbsent(instanceId, k -> new JsonUtil());
    }

    public static void setTimeZone(TimeZone tz) {
        mapper.setTimeZone(tz);
    }

    public static String toJsonString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        } else {
            try {
                return mapper.writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static JsonNode toJson(Object obj) {
        if (obj instanceof String) {
            try {
                return mapper.readTree((String) obj);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return mapper.valueToTree(obj);
        }
    }

    public static <E> List<E> toList(Object obj, Class<E> clazz) {
        return (List<E>) toGeneric(obj, List.class, clazz);
    }

    public static <E> Set<E> toSet(Object obj, Class<E> clazz) {
        return (Set<E>) toGeneric(obj, Set.class, clazz);
    }

    public static <K, V> Map<K, V> toMap(Object obj, Class<K> keyClazz, Class<V> valueClazz) {
        return (Map<K, V>) toGeneric(obj, Map.class, keyClazz, valueClazz);
    }

    public static Object toGeneric(Object obj, Class<?> parametrized, Class... parameterClasses) {
        JavaType type = mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
        return toGeneric(obj, type);
    }

    private static Object toGeneric(Object obj, JavaType type) {
        try {
            if (obj instanceof String) {
                return mapper.readValue((String) obj, type);
            } else if (obj instanceof JsonNode) {
                return mapper.readValue(obj.toString(), type);
            } else {
                return mapper.readValue(mapper.writeValueAsString(obj), type);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <E> E toObject(Object obj, Class<E> clazz) {
        try {
            if (obj instanceof String) {
                if (clazz == String.class) {
                    return (E) obj;
                } else {
                    return mapper.readValue((String) obj, clazz);
                }
            } else if (obj instanceof JsonNode) {
                return mapper.readValue(obj.toString(), clazz);
            } else {
                return mapper.readValue(mapper.writeValueAsString(obj), clazz);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode path(JsonNode jsonNode, String pathStr) {
        String[] splitPaths = pathStr.split("\\.");
        jsonNode = jsonNode.path(splitPaths[0]);
        if (jsonNode instanceof MissingNode) {
            return null;
        } else if (splitPaths.length == 1) {
            return jsonNode;
        } else {
            return path(jsonNode, pathStr.substring(pathStr.indexOf(".") + 1));
        }
    }

    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

}

