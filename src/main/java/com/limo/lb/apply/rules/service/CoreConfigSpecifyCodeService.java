package com.limo.lb.apply.rules.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limo.lb.apply.rules.entity.ConfigSpecifyCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Limo
 * @time: 2023-11-27 19:43
 */
public class CoreConfigSpecifyCodeService {
    public List<ConfigSpecifyCode> list(LambdaQueryWrapper<ConfigSpecifyCode> eq) {
        return new ArrayList<>();
    }

    public ConfigSpecifyCode getOne(LambdaQueryWrapper<ConfigSpecifyCode> codeWrapper, boolean b) {
        return new ConfigSpecifyCode();
    }
}