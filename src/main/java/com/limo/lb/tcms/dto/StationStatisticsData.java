package com.limo.lb.tcms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: libiao
 * @time: 2021/10/20 17:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationStatisticsData extends TcmsData {
    /**
     * 站点编码
     */
    private String stationCode;

    /**
     * 站点名称
     */
    private String stationName;

    private LocalDateTime recieveDataTime;

    private Integer employeeAmount;
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