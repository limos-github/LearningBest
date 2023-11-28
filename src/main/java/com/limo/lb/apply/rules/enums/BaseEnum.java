package com.limo.lb.apply.rules.enums;

/**
 * 
 *
 * @Author: shaokui.chen
 * @Date: 2021/5/24 11:38
 */
public interface BaseEnum {
    /**
     * 获取code
     *
     * @return
     */
    int getCode();

    /**
     * 获取描述信息
     *
     * @return
     */
    String getDesc();

    /**
     * 根据国家话语言获取描述信息
     *
     * @return
     */
    default String getDesc(String language) {
        return getDesc();
    }

}