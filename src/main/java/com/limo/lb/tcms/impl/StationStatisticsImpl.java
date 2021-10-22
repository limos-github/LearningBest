package com.limo.lb.tcms.impl;

import com.limo.lb.tcms.StationStatisticsQueryService;
import com.limo.lb.tcms.TcmsClientService;
import com.limo.lb.tcms.TcmsPushService;
import com.limo.lb.tcms.config.ConstantPushConfig;
import com.limo.lb.tcms.dto.ResponseJson;
import com.limo.lb.tcms.dto.StationInfo;
import com.limo.lb.tcms.dto.StationStatisticsData;
import com.limo.lb.tcms.dto.StationStatisticsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: libiao
 * @time: 2021/10/21 10:56
 */
@Slf4j
@Service
public class StationStatisticsImpl implements TcmsPushService<StationStatisticsDto, List<StationStatisticsData>, StationStatisticsData> {
    @Autowired
    private TcmsClientService tcmsClientService;
    @Autowired
    private ConstantPushConfig constantPushConfig;
    @Autowired
    private StationStatisticsQueryService stationStatisticsQueryService;

    /**
     * Operation.
     *
     * @param dto  the t
     * @param list the k
     */
    @Override
    public void operation(StationStatisticsDto dto, List<StationStatisticsData> list) {
        List<StationInfo> stationList = stationStatisticsQueryService.statisticsStation();
        LocalDateTime startTime = LocalDateTime.now().minusDays(1).toLocalDate().atTime(0, 0);
        LocalDateTime endTime = LocalDateTime.now().toLocalDate().atTime(0, 0);
        for (StationInfo vo : stationList) {
            try {
                Long employeeAmount = stationStatisticsQueryService.queryEmAmount(vo.getStationCode(), startTime, endTime);
                list.add(new StationStatisticsData(vo.getStationCode(), vo.getStationName(), startTime, employeeAmount.intValue(),
                        vo.getBusinessTime(), vo.getBusinessPeriodTime(), vo.getSiteState(), vo.getCreateDate()));
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
    public void push(List<StationStatisticsData> list) {
        for (StationStatisticsData data : list) {
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
    public Map toMap(StationStatisticsData data) {
        Map<String, Object> map = new HashMap(16);
        map.put("applicationType", constantPushConfig.getApplicationType());
        map.put("stationCode", data.getStationCode());
        map.put("stationName", data.getStationName());
        map.put("recieveDataTime", data.getRecieveDataTime());
        map.put("employeeAmount", data.getEmployeeAmount());
        map.put("businessHours", data.getBusinessTime());
        map.put("businessDays", data.getBusinessPeriodTime());
        map.put("stationStatusInt", data.getSiteState());
        map.put("stationCreateDate", data.getCreateDate());
        return map;
    }
}