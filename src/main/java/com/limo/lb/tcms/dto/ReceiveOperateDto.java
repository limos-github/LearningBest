package com.limo.lb.tcms.dto;

import lombok.Data;

/**
 * @description:
 * @author: libiao
 * @time: 2021/10/20 17:50
 */
@Data
public class ReceiveOperateDto extends PostDTO {
    private String operator;
    private String operatorId;
    private String loginAccount;
    /**公共 参数*/
    private String operationType;
    private String operationName;
    private Integer operationModule;
    /**LOGIN 参数*/
    private String ip;
    /**MODIFY 参数*/
    private String stationCode;
    private String stationName;
    private String siteState;
    /**EXPORT 参数*/
    private Long excelCount;
    private String excelName;
    /**DOWNLOAD 参数*/
    private String filePath;
}