//package com.limo.lb.tcms;
//
//import com.limo.lb.tcms.dto.ResponseJson;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.Map;
//
///**
// * The interface Tcms service.
// *
// * @param <K> the type parameter
// * @description:
// * @author: libiao
// * @time: 2021 /10/20 16:59
// */
//public interface TcmsClientService<K> {
//
//    @PostMapping({"/operationLog/receiveOperate"})
//    ResponseJson push(@RequestBody Map map);
//
//    @PostMapping({"/operationLog/dataStatistics"})
//    ResponseJson dataStatistics(@RequestBody Map<String, Object> map);
//
//    @PostMapping({"/operationLog/stationStatistics"})
//    ResponseJson stationStatistics(@RequestBody Map map);
//
//}
