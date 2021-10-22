package com.limo.lb.tcms;

import com.limo.lb.tcms.dto.StationInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface Station statistics query service.
 *
 * @description:
 * @author: libiao
 * @time: 2021 /10/21 11:11
 */
public interface StationStatisticsQueryService {

    /**
     * Statistics station list.
     *
     * @return the list
     */
    List<StationInfo> statisticsStation();

    /**
     * Query em amount long.
     *
     * @param stationCode the station code
     * @param startTime   the start time
     * @param endTime     the end time
     * @return the long
     */
    Long queryEmAmount(String stationCode, LocalDateTime startTime, LocalDateTime endTime);

}
