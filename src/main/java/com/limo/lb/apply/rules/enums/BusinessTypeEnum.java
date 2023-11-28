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
public enum BusinessTypeEnum implements BaseEnum {
    /**
     * 快件/业务类型
     */
    PRODUCT_TYPE(0, "快件/业务类型"),
    /**
     * 国际件/国内件
     */
    EXPRESS_TYPE(1, "国际件/国内件"),
    /**
     * 大客户
     */
    CUSTOMER_TYPE(2, "大客户"),
    /**
     * 区域类型
     */
    SPECIFY_TYPE(3, "区域类型"),
    /**
     * 物品类型
     */
    GOODS_TYPE(4, "物品类型"),
    /**
     * 物品类型
     */
    DEFAULT(5, "默认"),

    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    BusinessTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String valueOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (BusinessTypeEnum typeEnum : BusinessTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<BusinessTypeEnum> getEnums() {
        return Arrays.asList(BusinessTypeEnum.values());
    }

    @JsonCreator
    public static BusinessTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (BusinessTypeEnum item : values()) {
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