package com.limo.lb.apply.rules.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limo.lb.apply.rules.entity.ConfigTransportType;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Limo
 * @time: 2023-11-27 19:47
 */
public class CoreConfigTransportTypeService {
    public List<ConfigTransportType> list(LambdaQueryWrapper<ConfigTransportType> eq) {
        return new ArrayList<>();
    }

    public ConfigTransportType getOne(LambdaQueryWrapper<ConfigTransportType> wrapper, boolean b) {
        return new ConfigTransportType();
    }
}