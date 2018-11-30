package com.ts.utils;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * json util
 *
 * @author shan
 */
public class JSONUtil {
    private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);
    private JSONUtil() {
    }

    /**
     * Convert bean to json with jackson(jackson has better performance than
     * gson)
     */
    public static String beanToJsonWithJackson(Object obj) {
        if (obj == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,
                false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(obj);
        } catch (IOException e) {
            logger.error("beanToJsonWithJackson error",e);
        }
        return json;
    }

    /**
     * bean to json 适用于毫秒
     */
    public static String beanWithDateToJson(Object obj,
                                            boolean serializeNullValue) {
        if (obj == null) {
            return null;
        }
        // date serializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue ? gsonBuilder.serializeNulls()
                .registerTypeAdapter(Date.class, new DateSerializerUtils())
                .setDateFormat(DateFormat.LONG).create() : gsonBuilder
                .registerTypeAdapter(Date.class, new DateSerializerUtils())
                .setDateFormat(DateFormat.LONG).create();
        // Bean -> Json
        return gson.toJson(obj);
    }

    /**
     * json to bean
     */
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        StringReader strReader = new StringReader(json);
        JsonReader jsonReader = new JsonReader(strReader);
        return jsonToBean(jsonReader, clazz);
    }

    /**
     * json to bean
     */
    public static <T> T jsonToBeanWithJackson(String json, Class<T> clazz) {
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            mapper.setDateFormat(dateFormat);
            mapper.configure(
                    SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING,
                    false);
            // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
            mapper.getDeserializationConfig().set(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("jsonToBeanWithJackson error",e);
            return null;
        }
    }

    /**
     * json to bean
     */
    public static <T> T jsonToBeanWithJacksonJavaTypeAndEnumName(String json,
                                                                 Class<T> clazz, Class<?> type) {
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            mapper.setDateFormat(dateFormat);
            JavaType javaType = mapper.getTypeFactory()
                    .constructParametricType(clazz, type);
            mapper.configure(
                    SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING,
                    false);
            // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
            mapper.getDeserializationConfig().set(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            logger.error("jsonToBeanWithJacksonJavaTypeAndEnumName error",e);
            return null;
        }
    }

    private static <T> T jsonToBean(JsonReader json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        // Json -> Bean
        Gson gson = new Gson();
        return (T) gson.fromJson(json, clazz);
    }

    /**
     * json to bean 适用于毫秒
     */
    public static <T> T jsonWithDateToBean(String json, Class<T> clazz) {
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        StringReader strReader = new StringReader(json);
        JsonReader jsonReader = new JsonReader(strReader);
        return jsonWithDateToBean(jsonReader, clazz);
    }

    private static <T> T jsonWithDateToBean(JsonReader json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        // date deserializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .registerTypeAdapter(Date.class, new DateDeserializerUtils())
                .setDateFormat(DateFormat.LONG).create();
        // Json -> Bean
        return (T) gson.fromJson(json, clazz);
    }

    /**
     * list to json
     */
    public static <T> String listToJson(List<T> list, boolean serializeNullValue) {
        if (list == null) {
            return null;
        }
        // List -> Json
        Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls()
                .create() : new Gson();
        return gson.toJson(list);
    }

    /**
     * list to json
     */
    public static <T> String listWithDateToJson(List<T> list,
                                                boolean serializeNullValue) {
        if (list == null) {
            return null;
        }
        // date serializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue ? gsonBuilder.serializeNulls()
                .registerTypeAdapter(Date.class, new DateSerializerUtils())
                .setDateFormat(DateFormat.LONG).create() : gsonBuilder
                .registerTypeAdapter(Date.class, new DateSerializerUtils())
                .setDateFormat(DateFormat.LONG).create();
        // List -> Json
        return gson.toJson(list);
    }

    /**
     * json to list
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        if (json == null || "".equals(json.trim())) {
            return Collections.emptyList();
        }
        // json -> List
        StringReader strReader = new StringReader(json);
        List<T> list = null;
        try {
            list = readForList(strReader, false, clazz);
        } catch (IOException e) {
            logger.error("jsonToList error",e);
        }
        return list;
    }

    /**
     * json to list
     */
    public static <T> List<T> jsonWithDateToList(String json, Class<T> clazz) {
        if (json == null || "".equals(json.trim())) {
            return Collections.emptyList();
        }

        // json -> List
        StringReader strReader = new StringReader(json);
        List<T> list = null;
        try {
            list = readForList(strReader, true, clazz);
        } catch (IOException e) {
            logger.error("jsonWithDateToList error",e);
        }
        return list;
    }

    private static <T> List<T> readForList(Reader reader, boolean hasDate,
                                           Class<T> clazz) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        List<T> objs = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            T obj = null;
            if (hasDate) {
                obj = jsonWithDateToBean(jsonReader, clazz);
            } else {
                obj = jsonToBean(jsonReader, clazz);
            }
            if (obj != null)
                objs.add(obj);
        }
        jsonReader.endArray();
        jsonReader.close();
        return objs;
    }

    /**
     * set to json
     */
    public static <T> String setToJson(Set<T> set, boolean serializeNullValue) {
        if (set == null) {
            return null;
        }
        // set -> Json
        Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls()
                .create() : new Gson();
        return gson.toJson(set);
    }

    /**
     * set to json
     */
    public static <T> String setWithDateToJson(Set<T> set,
                                               boolean serializeNullValue) {
        if (set == null) {
            return null;
        }
        // date serializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue ? gsonBuilder.serializeNulls()
                .registerTypeAdapter(Date.class, new DateSerializerUtils())
                .setDateFormat(DateFormat.LONG).create() : gsonBuilder
                .registerTypeAdapter(Date.class, new DateSerializerUtils())
                .setDateFormat(DateFormat.LONG).create();
        // List -> Json
        return gson.toJson(set);
    }

    /**
     * json to set
     */
    public static <T> Set<T> jsonToSet(String json, Class<T> clazz) {
        if (json == null || "".equals(json.trim())) {
            return Collections.emptySet();
        }
        // json -> set
        StringReader strReader = new StringReader(json);
        Set<T> set = null;
        try {
            set = readForSet(strReader, false, clazz);
        } catch (IOException e) {
            logger.error("jsonToSet error",e);
        }
        return set;
    }

    /**
     * json to set
     */
    public static <T> Set<T> jsonWithDateToSet(String json, Class<T> clazz) {
        if (json == null || "".equals(json.trim())) {
            return Collections.emptySet();
        }

        // json -> set
        StringReader strReader = new StringReader(json);
        Set<T> set = null;
        try {
            set = readForSet(strReader, true, clazz);
        } catch (IOException e) {
            logger.error("jsonWithDateToSet error",e);
        }
        return set;
    }

    private static <T> Set<T> readForSet(Reader reader, boolean hasDate,
                                         Class<T> clazz) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        Set<T> objs = new HashSet<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            T obj = null;
            if (hasDate) {
                obj = jsonWithDateToBean(jsonReader, clazz);
            } else {
                obj = jsonToBean(jsonReader, clazz);
            }
            if (obj != null)
                objs.add(obj);
        }
        jsonReader.endArray();
        jsonReader.close();
        return objs;
    }

    /**
     * map to json
     */
    public static <T> String mapToJson(Map<String, T> map,
                                       boolean serializeNullValue) {
        if (map == null) {
            return null;
        }
        // Map -> Json
        Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls()
                .create() : new Gson();
        return gson.toJson(map);
    }

    /**
     * map to json
     */
    public static <T> String mapWithDateToJson(Map<String, T> map,
                                               boolean serializeNullValue) {
        if (map == null) {
            return null;
        }
        // date serializable
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = serializeNullValue ? gsonBuilder.serializeNulls()
                .registerTypeAdapter(Date.class, new DateSerializerUtils())
                .setDateFormat(DateFormat.LONG).create() : gsonBuilder
                .registerTypeAdapter(Date.class, new DateSerializerUtils())
                .setDateFormat(DateFormat.LONG).create();
        // map -> json
        return gson.toJson(map);
    }

    /**
     * 日期解序列实用工具类
     */
    static class DateSerializerUtils implements JsonSerializer<Date> {
        @Override
        public JsonElement serialize(Date date, Type type,
                                     JsonSerializationContext content) {
            return new JsonPrimitive(date.getTime());
        }

    }

    /**
     * 日期序列化实用工具类
     */
    static class DateDeserializerUtils implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement json, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }

}