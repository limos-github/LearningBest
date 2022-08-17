package com.limo.lb.learn.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

/**
 * 云路供应链科技有限公司 版权所有 © Copyright 2021
 *
 * @Author: shaokui.chen
 * @Date: 2021/5/25 8:30
 */
public enum ObjectTypeEnum  {
    NATION(0, "全国"),
    AREA(1, "区域"),
    STATION(2, "站点"),
    ;
    @EnumValue
    private Integer code;
    private String desc;

    ObjectTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }


    public static List<ObjectTypeEnum> getEnums() {
        return Arrays.asList(ObjectTypeEnum.values());
    }

    @JsonCreator
    public static ObjectTypeEnum jsonCreator(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (ObjectTypeEnum item : values()) {
                if (item.name().equals(name.trim())) {
                    return item;
                }
            }
        }
        return null;
    }
}