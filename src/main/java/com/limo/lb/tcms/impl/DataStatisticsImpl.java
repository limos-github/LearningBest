package com.limo.lb.tcms.impl;

import com.limo.lb.tcms.DataStatisticsQueryService;
import com.limo.lb.tcms.TcmsClientService;
import com.limo.lb.tcms.TcmsPushService;
import com.limo.lb.tcms.config.ConstantPushConfig;
import com.limo.lb.tcms.dto.DataStatisticsData;
import com.limo.lb.tcms.dto.DataStatisticsDto;
import com.limo.lb.tcms.dto.ReceiptParcelInfo;
import com.limo.lb.tcms.dto.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Data statistics.
 *
 * @description:
 * @author: libiao
 * @time: 2021 /10/21 10:21
 */
@Slf4j
@Service
public class DataStatisticsImpl implements TcmsPushService<DataStatisticsDto, List<DataStatisticsData>, DataStatisticsData> {
    @Autowired
    private TcmsClientService tcmsClientService;
    @Autowired
    private ConstantPushConfig constantPushConfig;
    @Autowired
    private DataStatisticsQueryService dataStatisticsQueryService;

    /**
     * Operation.
     *
     * @param dto  the t
     * @param list the k
     */
    @Override
    public void operation(DataStatisticsDto dto, List<DataStatisticsData> list) {
        List<ReceiptParcelInfo> stationList = dataStatisticsQueryService.queryStation();
        LocalDateTime startTime = LocalDateTime.now().toLocalDate().atTime(LocalDateTime.now().minusHours(1).getHour(), 0, 0);
        LocalDateTime endTime = LocalDateTime.now().toLocalDate().atTime(LocalDateTime.now().getHour(), 0, 0);
        for (ReceiptParcelInfo vo : stationList) {
            try {
                String stationCode = vo.getStationCode();
                Long inStorage = dataStatisticsQueryService.statisticsInStorage(stationCode, startTime, endTime);
                Long outStorage = dataStatisticsQueryService.statisticsOutStorage(stationCode, startTime, endTime);
                Long retentionParcel = dataStatisticsQueryService.statisticsRetentionParcel(stationCode, startTime, endTime);
                Long returnParcel = dataStatisticsQueryService.statisticsReturnParcel(stationCode, startTime, endTime);
                Long sendParcel = dataStatisticsQueryService.statisticsSendParcel(stationCode, startTime, endTime);
                list.add(new DataStatisticsData(inStorage, outStorage, retentionParcel, returnParcel, sendParcel, vo.getStationCode(), vo.getStationName(), startTime));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Push.
     *
     * @param list the k
     * @return the response json
     */
    @Override
    public void push(List<DataStatisticsData> list) {
        for (DataStatisticsData data : list) {
            Map map = toMap(data);
            log.info("发送日志结束--->终端控制塔:{}", data);
            ResponseJson responseJson = tcmsClientService.dataStatistics(map);
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
    public Map toMap(DataStatisticsData data) {
        Map<String, Object> map = new HashMap(16);
        map.put("applicationType", constantPushConfig.getApplicationType());
        map.put("inStorage", data.getInStorage());
        map.put("outStorage", data.getOutStorage());
        map.put("retentionParcel", data.getRetentionParcel());
        map.put("returnParcel", data.getReturnParcel());
        map.put("sendParcel", data.getSendParcel());
        map.put("stationCode", data.getStationCode());
        map.put("stationName", data.getStationName());
        map.put("recieveDataTime", data.getRecieveDataTime());
        return map;
    }
}