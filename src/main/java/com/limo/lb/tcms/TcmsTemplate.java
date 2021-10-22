package com.limo.lb.tcms;

import com.limo.lb.tcms.config.ConstantPushConfig;
import com.limo.lb.tcms.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Tcms template.
 *
 * @description:
 * @author: libiao
 * @time: 2021 /10/20 16:56
 */
@Slf4j
public abstract class TcmsTemplate {
    @Autowired
    private ConstantPushConfig constantPushConfig;
    @Autowired
    private TcmsPushService tcmsService;

    private void execute(PostDTO t) {
        try {
            List<PostDTO> data = new ArrayList<>();
            /**判断是否有开关*/
            if (constantPushConfig.getDataEnable()) {
                /**处理日志数据*/
                tcmsService.operation(t, data);
                /**推送数据*/
                tcmsService.push(data);
            }
        } catch (Exception e) {
            log.error("发送下载日志结束--->终端控制塔:{}", e.getMessage());
        }
    }
}