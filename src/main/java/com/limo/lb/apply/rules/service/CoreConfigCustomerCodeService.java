package com.limo.lb.apply.rules.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limo.lb.apply.rules.entity.ConfigCustomerCode;

/**
 * @description:
 * @author: Limo
 * @time: 2023-11-27 19:47
 */
public class CoreConfigCustomerCodeService {
    public ConfigCustomerCode getOne(LambdaQueryWrapper<ConfigCustomerCode> eq, boolean b) {
        return new ConfigCustomerCode();
    }
}