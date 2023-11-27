package com.limo.lb.apply.rules.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 云路供应链科技有限公司 版权所有 © Copyright 2021
 * 规则状态
 *
 * @author libiao
 */
public enum JudgeEnum implements BaseEnum {
    /**
     * 等于
     */
    EQUAL(0, "等于"),
    /**
     * 不等于
     */
    NOT_EQUAL_TO(1, "不等于"),
    /**
     * 其它
     */
    OTHER(2, "其它"),
    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    JudgeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String valueOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (JudgeEnum typeEnum : JudgeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<JudgeEnum> getEnums() {
        return Arrays.asList(JudgeEnum.values());
    }

    @JsonCreator
    public static JudgeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (JudgeEnum item : values()) {
                if (item.name().equals(name.trim())) {
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String getDesc(String s) {
        return this.desc;
    }

    @JsonValue
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>(4);
        map.put("id", this.name());
        map.put("name", getDesc());
        map.put("code", String.valueOf(getCode()));
        return map;
    }
}