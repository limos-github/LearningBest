package com.limo.lb.tcms.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: libiao
 * @time: 2021/10/21 11:15
 */
@Data
public class StationInfo {

    /**
     * 站点编码
     */
    private String stationCode;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 营业时间(周一~周五|周一~周六|周一~周日))
     */
    private String businessTime;

    /**
     * 营业时间段
     */
    private String businessPeriodTime;

    /**
     * 站点状态0-启用，1-禁用
     */
    private Integer siteState;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;
}