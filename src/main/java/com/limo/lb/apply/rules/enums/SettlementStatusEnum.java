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
 * 结算状态 0-寄付 1-到付 2-月结
 *
 * @Author: shaokui.chen
 * @Date: 2021/5/25 8:30
 */
public enum SettlementStatusEnum implements BaseEnum {
    WAIT_BILL(0, "待下账"),

    WAIT_CONFIRM(1, "待确认"),

    APPEAL(2, "申诉中"),

    SETTLED(3, "已结算"),
    ;

    @EnumValue
    private final Integer code;
    private final String desc;


    SettlementStatusEnum(int code, String desc) {
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
        for (SettlementStatusEnum typeEnum : SettlementStatusEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<SettlementStatusEnum> getEnums() {
        return Arrays.asList(SettlementStatusEnum.values());
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
    public static SettlementStatusEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (SettlementStatusEnum item : values()) {
                if (item.name().equals(name.trim())) {
                    return item;
                }
            }
        }
        return null;
    }
}