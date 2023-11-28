package com.limo.lb.apply.rules.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.limo.lb.apply.rules.utils.EmptyUtil;
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
public enum TierTypeEnum implements BaseEnum {
    T1(1, "T1"),
    T2(2, "T2"),
    T3(3, "T3"),
    T4(4, "T4"),
    T5(5, "T5"),
    T6(6, "T6"),
    T7(7, "T7"),
    T8(8, "T8"),
    T9(9, "T9"),
    ;
    @EnumValue
    private final Integer code;
    private final String desc;

    TierTypeEnum(int code, String desc) {
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
        for (TierTypeEnum typeEnum : TierTypeEnum.values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<TierTypeEnum> getEnums() {
        return Arrays.asList(TierTypeEnum.values());
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
    public static TierTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (TierTypeEnum item : values()) {
                if (item.name().equals(name.trim())) {
                    return item;
                }
            }
        }
        return null;
    }

    @JsonCreator
    public static TierTypeEnum jsonCreator(Integer code) {
        if (EmptyUtil.isNotEmpty(code)) {
            for (TierTypeEnum item : values()) {
                if (item.getCode() == code) {
                    return item;
                }
            }
        }
        return null;
    }
}