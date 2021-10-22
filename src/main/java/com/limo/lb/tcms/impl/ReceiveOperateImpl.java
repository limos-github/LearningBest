package com.limo.lb.tcms.impl;

import com.limo.lb.tcms.TcmsClientService;
import com.limo.lb.tcms.TcmsPushService;
import com.limo.lb.tcms.config.ConstantPushConfig;
import com.limo.lb.tcms.dto.ReceiveOperateData;
import com.limo.lb.tcms.dto.ReceiveOperateDto;
import com.limo.lb.tcms.dto.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Receive operate.
 *
 * @description:
 * @author: libiao
 * @time: 2021 /10/20 18:04
 */
@Slf4j
@Service
public class ReceiveOperateImpl implements TcmsPushService<ReceiveOperateDto, List<ReceiveOperateData>, ReceiveOperateData> {

    @Autowired
    private TcmsClientService tcmsClientService;
    @Autowired
    private ConstantPushConfig constantPushConfig;

    /**
     * Operation.
     *
     * @param dto  the t
     * @param list the k
     */
    @Override
    public void operation(ReceiveOperateDto dto, List<ReceiveOperateData> list) {
        ReceiveOperateData data = new ReceiveOperateData();
        switch (dto.getOperationType()) {
            case "LOGIN":
                data.setIp(dto.getIp());
                break;
            case "MODIFY":
                data.setStationCode(dto.getStationCode());
                data.setStationName(dto.getStationName());
                data.setSiteState(dto.getSiteState());
                break;
            case "EXPORT":
                data.setExcelCount(dto.getExcelCount());
                data.setExcelName(dto.getExcelName());
                break;
            case "DOWNLOAD":
                data.setFilePath(dto.getFilePath());
                break;
            default:
                break;
        }
    }

    /**
     * Push.
     *
     * @param list the k
     * @return the response json
     */
    @Override
    public void push(List<ReceiveOperateData> list) {
        for (ReceiveOperateData data : list) {
            Map map = toMap(data);
            log.info("发送日志结束--->终端控制塔:{}", data);
            ResponseJson responseJson = tcmsClientService.push(map);
            log.info("发送日志结束--->终端控制塔:{}", responseJson);
        }
    }

    /**
     * To map map.
     *
     * @param data the v
     * @return the map
     */
    @Override
    public Map toMap(ReceiveOperateData data) {
        /**公用参数*/
        Map<String, Object> map = new HashMap(16);
        map.put("applicationType", constantPushConfig.getApplicationType());
        map.put("operationName", data.getOperationName());
        map.put("operationTime", LocalDateTime.now());
        map.put("operationType", data.getOperationType());
        map.put("operationModule", data.getOperationModule() == 0 ? "WEB" : "APP");
        switch (data.getOperationType()) {
            case "LOGIN":
                map.put("ip", data.getIp());
                break;
            case "MODIFY":
                map.put("stationCode", data.getStationCode());
                map.put("stationName", data.getStationName());
                map.put("siteState", data.getSiteState());
                break;
            case "EXPORT":
                map.put("excelCount", data.getExcelCount());
                map.put("excelName", data.getExcelName());
                break;
            case "DOWNLOAD":
                map.put("filePath", data.getFilePath());
                break;
            default:
                break;
        }
        return map;
    }
}