package com.limo.lb.tcms.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * nacos常量配置
 *
 * @Author
 */

@Component
//@RefreshScope
@Data
public class ConstantPushConfig {

    @Value("${push.th.data_enable:false}")
    private Boolean dataEnable;

    @Value("${push.th.applicationType:THAILAND}")
    private String applicationType;
}
