package com.limo.lb.tcms;

import com.limo.lb.tcms.dto.ReceiptParcelInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface Data statistics query service.
 *
 * @description:
 * @author: libiao
 * @time: 2021 /10/21 11:11
 */
public interface DataStatisticsQueryService {

    /**
     * Query station list.
     *
     * @return the list
     */
    List<ReceiptParcelInfo> queryStation();

    /**
     * Statistics in storage long.
     *
     * @param stationCode the station code
     * @param startTime   the start time
     * @param endTime     the end time
     * @return the long
     */
    Long statisticsInStorage(String stationCode, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Statistics out storage long.
     *
     * @param stationCode the station code
     * @param startTime   the start time
     * @param endTime     the end time
     * @return the long
     */
    Long statisticsOutStorage(String stationCode, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Statistics retention parcel long.
     *
     * @param stationCode the station code
     * @param startTime   the start time
     * @param endTime     the end time
     * @return the long
     */
    Long statisticsRetentionParcel(String stationCode, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Statistics return parcel long.
     *
     * @param stationCode the station code
     * @param startTime   the start time
     * @param endTime     the end time
     * @return the long
     */
    Long statisticsReturnParcel(String stationCode, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Statistics send parcel long.
     *
     * @param stationCode the station code
     * @param startTime   the start time
     * @param endTime     the end time
     * @return the long
     */
    Long statisticsSendParcel(String stationCode, LocalDateTime startTime, LocalDateTime endTime);

}
