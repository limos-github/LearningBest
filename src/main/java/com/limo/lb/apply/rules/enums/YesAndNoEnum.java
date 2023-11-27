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
 * 短信类型(1-验证码短信|2-入柜通知)
 *
 * @Author: shaokui.chen
 * @Date: 2021/5/25 8:30
 */
public enum YesAndNoEnum implements BaseEnum {
    YES(0, "是"),
    NO(1, "否"),

    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    YesAndNoEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
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

    public static String valueOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (YesAndNoEnum typeEnum : YesAndNoEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<YesAndNoEnum> getEnums() {
        return Arrays.asList(YesAndNoEnum.values());
    }

    @JsonValue
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>(4);
        map.put("id", this.name());
        map.put("name", getDesc());
        map.put("code", String.valueOf(getCode()));
        return map;
    }

    @JsonCreator
    public static YesAndNoEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (YesAndNoEnum item : values()) {
                if (item.name().equals(name.trim())) {
                    return item;
                }
            }
        }
        return null;
    }
}