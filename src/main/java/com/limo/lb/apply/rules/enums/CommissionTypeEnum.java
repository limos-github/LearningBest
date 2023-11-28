package com.limo.lb.apply.rules.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @Author: shaokui.chen
 * @Date: 2021/5/25 8:30
 */
@Slf4j
public enum CommissionTypeEnum implements BaseEnum {
    PERCENT(0, "百分比"),
    FIXED_INCOME(1, "固定收益"),
    WEIGHT(2, "重量段"),
    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    CommissionTypeEnum(int code, String desc) {
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
        for (CommissionTypeEnum typeEnum : CommissionTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<CommissionTypeEnum> getEnums() {
        return Arrays.asList(CommissionTypeEnum.values());
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
    public static CommissionTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (CommissionTypeEnum item : values()) {
                if (item.name().equals(name.trim())) {
                    return item;
                }
            }
        }
        return null;
    }
}