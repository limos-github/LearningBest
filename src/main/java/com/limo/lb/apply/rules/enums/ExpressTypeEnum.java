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
 * 快递类型
 * EZ(0, "EZ"),
 * SF(1, "SF"),
 * EX(2, "次日达"),
 * JS(3, "高端商务件"),
 * FD(4, "生鲜"),
 *
 * @Author: zyy
 * @Date: 2021/5/25 8:30
 */
public enum ExpressTypeEnum implements BaseEnum {
    EZ(0, "EZ"),
    SF(1, "SF"),
    EX(2, "EX"),
    JS(3, "JS"),
    FD(4, "FD"),
    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    ExpressTypeEnum(int code, String desc) {
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
        for (ExpressTypeEnum typeEnum : ExpressTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<ExpressTypeEnum> getEnums() {
        return Arrays.asList(ExpressTypeEnum.values());
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
    public static ExpressTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (ExpressTypeEnum item : values()) {
                if (item.name().equals(name.trim())) {
                    return item;
                }
            }
        }
        return null;
    }
}