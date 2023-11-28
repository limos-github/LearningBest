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
 * 站点佣金类型
 *
 * @author libiao
 */

public enum SiteCommissionTypeEnum implements BaseEnum {
    CP(0, "CP"),
    PCP(1, "PCP"),
    SCP(2, "SCP"),
    PCP_SCP(3, "PCP-SCP"),

    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    SiteCommissionTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String valueOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (SiteCommissionTypeEnum typeEnum : SiteCommissionTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<SiteCommissionTypeEnum> getEnums() {
        return Arrays.asList(SiteCommissionTypeEnum.values());
    }

    @JsonCreator
    public static SiteCommissionTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (SiteCommissionTypeEnum item : values()) {
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