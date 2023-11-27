package com.limo.lb.apply.rules.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limo.lb.apply.rules.entity.CommissionRecord;

/**
 * @description:
 * @author: Limo
 * @time: 2023-11-27 19:45
 */
public class CoreCommissionRecordService {
    public CommissionRecord getOne(LambdaQueryWrapper<CommissionRecord> eq) {
        return new CommissionRecord();
    }
}