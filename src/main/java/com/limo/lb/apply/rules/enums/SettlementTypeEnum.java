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
 * 结算类型 0-寄付 1-到付 2-月结
 *
 * @Author: shaokui.chen
 * @Date: 2021/5/25 8:30
 */
public enum SettlementTypeEnum implements BaseEnum {
    //PP_CASH-0-寄付
    PP_CASH(0, "PP_CASH", "CASH"),
    //CC_CASH-1-到付
    CC_CASH(1, "CC_CASH", "DFOD"),
    //PP_PM-2-月结
    PP_PM(2, "PP_PM", "MONTHLY"),

    ;

    @EnumValue
    private final Integer code;
    private final String desc;
    private final String en;

    SettlementTypeEnum(int code, String desc, String en) {
        this.code = code;
        this.desc = desc;
        this.en = en;
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

    public String getEn() {
        return this.en;
    }

    public static String valueOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (SettlementTypeEnum typeEnum : SettlementTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<SettlementTypeEnum> getEnums() {
        return Arrays.asList(SettlementTypeEnum.values());
    }

    @JsonValue
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>(4);
        map.put("id", this.name());
        map.put("name", getDesc());
        map.put("en", getEn());
        map.put("code", String.valueOf(getCode()));
        return map;
    }

    @JsonCreator
    public static SettlementTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (SettlementTypeEnum item : values()) {
                if (item.name().equals(name.trim())) {
                    return item;
                }
            }
        }
        return null;
    }
}