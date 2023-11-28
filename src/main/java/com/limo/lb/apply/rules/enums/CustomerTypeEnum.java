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
public enum CustomerTypeEnum implements BaseEnum {
    VIP_CUSTOMER(0, "指定大客户"),
    NATION_CUSTOMER(1, "全国大客户"),
    OTHER_CUSTOMER(2, "其它大客户"),

    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    CustomerTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String valueOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (CustomerTypeEnum typeEnum : CustomerTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<CustomerTypeEnum> getEnums() {
        return Arrays.asList(CustomerTypeEnum.values());
    }

    @JsonCreator
    public static CustomerTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (CustomerTypeEnum item : values()) {
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