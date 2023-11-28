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
 * 
 * 规则状态
 *
 * @author libiao
 */
public enum EffectiveTypeEnum implements BaseEnum {
    EFFECTIVE(0, "生效中"),
    EXPIRED(1, "已失效"),
    PENDING(2, "待生效"),
    INVALID(3, "作废"),

    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    EffectiveTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String valueOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (EffectiveTypeEnum typeEnum : EffectiveTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<EffectiveTypeEnum> getEnums() {
        return Arrays.asList(EffectiveTypeEnum.values());
    }

    @JsonCreator
    public static EffectiveTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (EffectiveTypeEnum item : values()) {
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