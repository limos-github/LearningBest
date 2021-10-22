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
public class DataStatisticsData extends TcmsData {
    private Long inStorage;
    private Long outStorage;
    private Long retentionParcel;
    private Long returnParcel;
    private Long sendParcel;
    private String stationCode;
    private String stationName;
    private LocalDateTime recieveDataTime;
}