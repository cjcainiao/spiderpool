package com.spiderpool.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;


/**
 * fastjson工具类
 */
@Slf4j
public class FastJsonUtils {
    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public static String objToJson(Object obj) {
        try {
            return JSON.toJSONString(obj,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullNumberAsZero,
                    SerializerFeature.WriteNullBooleanAsFalse,
                    SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteClassName);
        } catch (Exception e) {
            log.error("对象转换JSON字符串失败：", e);
            throw new RuntimeException("JSON序列化失败", e);
        }
    }

    public static <T> T jsonToObj(String jsonStr, Class<T> clazz) {
        if (jsonStr == null || jsonStr.trim().isEmpty()) {
            return null;
        }
        try {
            return JSON.parseObject(jsonStr, clazz);
        } catch (Exception e) {
            log.error("JSON字符串转换对象失败，JSON：{}，目标类型：{}", jsonStr, clazz.getName(), e);
            throw new RuntimeException("JSON反序列化失败", e);
        }
    }

    public static <T> T jsonToObj(String jsonStr, com.alibaba.fastjson.TypeReference<T> type) {
        if (jsonStr == null || jsonStr.trim().isEmpty()) {
            return null;
        }
        try {
            return JSON.parseObject(jsonStr, type);
        } catch (Exception e) {
            log.error("JSON字符串转换复杂对象失败，JSON：{}，目标类型：{}", jsonStr, type.getType().getTypeName(), e);
            throw new RuntimeException("JSON反序列化复杂对象失败", e);
        }
    }
}
