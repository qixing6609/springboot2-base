package com.ts.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shan on 16/10/19
 */
public class EnumUtil {
    private static final Log LOG = LogFactory.getLog(EnumUtil.class);

    // cache
    static Map<Class, ConcurrentHashMap<String, Object>> stringMap = new ConcurrentHashMap<>();
    static Map<Class, ConcurrentHashMap<String, Object>> nameMap = new ConcurrentHashMap<>();

    private EnumUtil() {
    }

    /**
     * 根据value获取enum对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getEnumByValue(String value, Class<T> c) {
        if (!ValidateUtil.isEmpty(value)) {
            Map<String, Object> enumMap = getStringEnumMap(c);
            return (T) enumMap.get(value);
        }
        return null;
    }

    /**
     * 得到枚举String对枚举的映射Map
     */
    public static <T> Map<String, Object> getStringEnumMap(Class<T> c) {
        Map<String, Object> enumMap = stringMap.get(c);
        if (enumMap == null) {
            enumMap = initStringEnumMap(c);
        }
        return enumMap;
    }

    /**
     * 初始化
     */
    public static <T> Map<String, Object> initStringEnumMap(Class<T> c) {
        LOG.info("initStringEnumMap enum " + c.getName());
        T[] values = c.getEnumConstants(); // 耗时操作
        ConcurrentHashMap<String, Object> enumMap = new ConcurrentHashMap<>(
                values.length);
        for (T t : values) {
            enumMap.put(t.toString(), t);
        }
        stringMap.put(c, enumMap);
        return enumMap;
    }

    /**
     * 根据name获取enum对象
     */
    public static <T extends Enum<T>> T getEnumByName(String name, Class<T> c) {
        if (!ValidateUtil.isEmpty(name)) {
            Map<String, Object> enumMap = getNameEnumMap(c);
            return (T) enumMap.get(name);
        }
        return null;
    }

    /**
     * 得到枚举name对枚举的映射Map
     */
    public static <T> Map<String, Object> getNameEnumMap(Class<T> c) {
        Map<String, Object> enumMap = nameMap.get(c);
        if (enumMap == null) {
            enumMap = initNameEnumMap(c);
        }
        return enumMap;
    }

    /**
     * 初始化
     */
    public static <T> Map<String, Object> initNameEnumMap(Class<T> c) {
        LOG.info("initNameEnumMap enum " + c.getName());
        T[] values = c.getEnumConstants(); // 耗时操作
        ConcurrentHashMap<String, Object> enumMap = new ConcurrentHashMap<>(
                values.length);
        for (T t : values) {
            enumMap.put(((Enum) t).name(), t);
        }
        nameMap.put(c, enumMap);
        return enumMap;
    }

    /**
     * 根据下标取值
     */
    public static <T extends Enum<T>> T getEnumByOrdinal(Class<T> type,
                                                         int ordinal) {
        Map<Integer, T> codeMap = new HashMap<>();

        for (T val : EnumSet.allOf(type)) {
            codeMap.put(val.ordinal(), val);
        }

        return codeMap.get(ordinal);
    }

    /**
     * 根据下标集合取值
     */
    public static <T extends Enum<T>> EnumSet<T> getEnumSetByOrdinals(
            Class<T> type, String[] ordinals) {
        EnumSet<T> result = EnumSet.noneOf(type);
        if (ordinals == null || ordinals.length == 0)
            return result;

        Map<String, T> codeMap = new HashMap<>();
        for (T val : EnumSet.allOf(type)) {
            codeMap.put(String.valueOf(val.ordinal()), val);
        }

        for (String ordinal : ordinals) {
            result.add(codeMap.get(ordinal));
        }

        return result;
    }

    public static <T extends Enum<T>> String join(Set<Enum<T>> set,
                                                  String separator) {
        String result = "";
        if (set == null || set.isEmpty())
            return result;

        for (Enum<T> t : set) {
            result += t.ordinal() + separator;
        }
        if (result.length() > 0 && result.endsWith(separator))
            return result.substring(0, result.length() - separator.length());

        return result;
    }

}
