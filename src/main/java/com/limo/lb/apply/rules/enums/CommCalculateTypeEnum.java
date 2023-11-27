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
public enum CommCalculateTypeEnum {
    AUTO(0, "自动收件"),
    TRIAL(1, "试算"),
    AGAIN(2, "重算"),
    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    CommCalculateTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String valueOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (CommCalculateTypeEnum typeEnum : CommCalculateTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<CommCalculateTypeEnum> getEnums() {
        return Arrays.asList(CommCalculateTypeEnum.values());
    }

    @JsonCreator
    public static CommCalculateTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (CommCalculateTypeEnum item : values()) {
                if (item.name().equals(name.trim())) {
                    return item;
                }
            }
        }
        return null;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
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