package com.limo.lb.apply.rules;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.limo.lb.apply.rules.content.BaseConstant;
import com.limo.lb.apply.rules.entity.ConfigCustomerCode;
import com.limo.lb.apply.rules.entity.ConfigSpecifyCode;
import com.limo.lb.apply.rules.entity.ConfigTransportType;
import com.limo.lb.apply.rules.enums.*;
import com.limo.lb.apply.rules.newRules.CommissionRulesChainVO;
import com.limo.lb.apply.rules.service.CoreConfigCustomerCodeService;
import com.limo.lb.apply.rules.service.CoreConfigSpecifyCodeService;
import com.limo.lb.apply.rules.service.CoreConfigTransportTypeService;
import com.limo.lb.apply.rules.utils.EmptyUtil;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
abstract class AbstractChain {
    /**
     * The Specify type.
     */
    protected ObjectTypeEnum specifyType;
    /**
     * The Next chain.
     * 责任链中的下一个元素
     */
    protected AbstractChain nextChain;
    @Lazy
    @Autowired
    private CoreConfigSpecifyCodeService coreConfigSpecifyCodeService;
    @Lazy
    @Autowired
    private CoreConfigTransportTypeService configTransportTypeService;
    @Lazy
    @Autowired
    private CoreConfigCustomerCodeService coreConfigCustomerCodeService;

    /**
     * Sets next chain.
     *
     * @param nextChain the next chain
     */
    public void setNextChain(AbstractChain nextChain) {
        this.nextChain = nextChain;
    }

    /**
     * Gets rules.
     * 调用此方法前需要
     *
     * @param bo the bo
     * @param vo the vo
     * @return the rules
     */
    public RulesChainVO getRules(@NotNull RulesChainBO bo, @NotNull RulesChainVO vo) {
        ObjectTypeEnum specifyType = ObjectTypeEnum.NATION;
        //station -> area -> nation
        //根据站点类型 筛选对应的规则
        if (this.specifyType.getCode() >= (specifyType.getCode())) {
            log.info("{}Rule execute ruleChain start --> {}", bo.getWayBillCode(), this.specifyType.getDesc());
            rule(bo, vo);
            if (EmptyUtil.isEmpty(vo) || EmptyUtil.isEmpty(vo.getIRules())) {
                nextChain.getRules(bo, vo);
            }
        }
        return vo;
    }

    /**
     * Rule commission rules.
     *
     * @param bo the bo
     * @param vo the vo
     */
    protected void rule(RulesChainBO bo, RulesChainVO vo) {
        log.info("{}Rule execute ruleChain rule --> {}", bo.getWayBillCode(), this.specifyType.getDesc());
        List<CommissionRulesChainVO> rulesList = bo.getSpecifyType().get(this.specifyType);
        if (EmptyUtil.isEmpty(rulesList)) {
            log.info("{}Rule execute ruleChain rule end--> {} rulesList Empty", bo.getWayBillCode(), this.specifyType.getDesc());
            return;
        }
        Map<SiteCommissionTypeEnum, List<CommissionRulesChainVO>> map = rulesList.stream().collect(Collectors.groupingBy(CommissionRulesChainVO::getSiteCommissionType, Collectors.toList()));
        map.forEach((k, v) -> filterRules(bo, k, v, vo));
    }

    /**
     * 时间符合 佣金类型符合 条件的阶段
     * 如果是站点需要找到对应的类型
     * 找到对应的规则
     *
     * @param bo   the bo
     * @param type the type
     * @param v
     * @param vo   the vo
     */
    protected void filterRules(RulesChainBO bo, SiteCommissionTypeEnum type, List<CommissionRulesChainVO> v, RulesChainVO vo) {
        log.info("{}Rule execute ruleChain filterRules --> {} - {}", bo.getWayBillCode(), type.getDesc(), JSONObject.toJSONString(v));
        //排序 阶段 -> 永久 优先级 大 -> 小
        List<CommissionRulesChainVO> rulesList = v.stream().sorted(Comparator.comparing(CommissionRulesChainVO::getCustomType)
                .thenComparing(CommissionRulesChainVO::getPriority).reversed()).collect(Collectors.toList());
        for (CommissionRulesChainVO r : rulesList) {
            CommissionRulesChainVO rule = ruleMatch(bo, r);
            //匹配规则 匹配到就结束
            if (EmptyUtil.isNotEmpty(rule)) {
                //站点类型与佣金类型相同
                if (bo.getSiteType().getCode() == type.getCode() && EmptyUtil.isEmpty(vo.getIRules())) {
                    vo.setIRules(rule);
                }
                //如果是SCP会多一个佣金规则 PCP_SCP
                if (SiteCommissionTypeEnum.PCP_SCP.equals(type) && EmptyUtil.isEmpty(vo.getUpRules())) {
                    vo.setUpRules(rule);
                }
                return;
            }
        }
    }

    /**
     * 根据优先级顺序匹配当前运单中的参数类型 信息是否匹配 当前规则
     */
    private CommissionRulesChainVO ruleMatch(RulesChainBO bo, CommissionRulesChainVO rule) {
        boolean result = false;
        boolean contains;
        switch (rule.getBusinessType()) {
            case PRODUCT_TYPE:
                if (StringUtils.isEmpty(bo.getExpressType()) || EmptyUtil.isEmpty(bo.getProductType())) {
                    break;
                }
                contains = rule.getConfigItem().equals(bo.getExpressType().name() + "/" + bo.getProductType());
                if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
                        || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
                    result = true;
                }
                break;
            case EXPRESS_TYPE:
                contains = bo.getWayBillCode().startsWith(BaseConstant.STR_SIX_SIX);
                switch (rule.getConfigItem()) {
                    case BaseConstant.FOREIGN:
                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
                            result = true;
                        }
                        break;
                    case BaseConstant.DOMESTIC:
                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && !contains)
                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && contains)) {
                            result = true;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case CUSTOMER_TYPE:
                if (EmptyUtil.isEmpty(bo.getVipCustomerCode())) {
                    bo.setVipCustomerCode(BaseConstant.ZERO_STR);
                }
                if (EmptyUtil.isEmpty(bo.getCustomerCodes())) {
                    //查询大客户内容
                    LambdaQueryWrapper<ConfigCustomerCode> customerWrapper = Wrappers.lambdaQuery(ConfigCustomerCode.class).eq(ConfigCustomerCode::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE);
                    if (CommCalculateTypeEnum.AGAIN.equals(bo.getType())) {
                        customerWrapper.le(ConfigCustomerCode::getUpdateTime, bo.getReceiveTime());
                    } else {
                        customerWrapper.eq(ConfigCustomerCode::getEffectiveType, EffectiveTypeEnum.EFFECTIVE);
                    }
                    ConfigCustomerCode customerCode = coreConfigCustomerCodeService.getOne(customerWrapper
                            .select(ConfigCustomerCode::getCustomerCode)
                            .eq(ConfigCustomerCode::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE), false);
                    if (EmptyUtil.isEmpty(customerCode)) {
                        log.info("{}Rule execute ruleChain ruleMatch end -->  ConfigCustomerCode Empty", bo.getWayBillCode());
                        throw new RuntimeException("BusinessErrorEnum.ERROR_CONFIG");
                    }
                    bo.setCustomerCodes(customerCode.getCustomerCode());
                }
                switch (rule.getConfigItem()) {
                    case BaseConstant.VIP_CUSTOMER:
                        contains = Arrays.asList(bo.getCustomerCodes().split(",")).stream().anyMatch(l -> l.equals(bo.getVipCustomerCode()));
                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
                            result = true;
                        }
                        break;
                    case BaseConstant.NATION_CUSTOMER:
                        contains = bo.getVipCustomerCode().startsWith(BaseConstant.NATION_CUSTOMER_STR);
                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
                            result = true;
                        }
                        break;
                    case BaseConstant.OTHER_CUSTOMER:
                        boolean vip = Arrays.asList(bo.getCustomerCodes().split(",")).stream().anyMatch(l -> l.equals(bo.getVipCustomerCode()));
                        boolean nation = bo.getVipCustomerCode().startsWith(BaseConstant.NATION_CUSTOMER_STR);
                        boolean equals = BaseConstant.ZERO_STR.equals(bo.getVipCustomerCode());
                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && !nation && !vip && !equals)
                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && (equals || nation || vip))) {
                            result = true;
                        }
                    default:
                        break;
                }
                break;
            case SPECIFY_TYPE:
                //配置类型直接查询 如果查询为空那就是没有
                if (EmptyUtil.isAnyEmpty(rule.getConfigItem(), bo.getDropPointDestination(), bo.getPickUpDP())) {
                    break;
                }
                if (EmptyUtil.isEmpty(bo.getSpecifyCodes())) {
                    LambdaQueryWrapper<ConfigSpecifyCode> codeWrapper = Wrappers.lambdaQuery(ConfigSpecifyCode.class).eq(ConfigSpecifyCode::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE);
                    //如果通过收件时间去计算的话 可以处理
                    if (CommCalculateTypeEnum.AGAIN.equals(bo.getType())) {
                        codeWrapper.le(ConfigSpecifyCode::getUpdateTime, bo.getReceiveTime());
                    } else {
                        codeWrapper.eq(ConfigSpecifyCode::getEffectiveType, EffectiveTypeEnum.EFFECTIVE);
                    }
                    //查询对应的网点编号
                    ConfigSpecifyCode specifyCode = coreConfigSpecifyCodeService.getOne(codeWrapper, false);
                    if (EmptyUtil.isEmpty(specifyCode)) {
                        log.info("{}Rule execute ruleChain ruleMatch end --> ConfigSpecifyCode Empty", bo.getWayBillCode());
                        throw new RuntimeException("BusinessErrorEnum.ERROR_CONFIG");
                    }
                    List<ConfigSpecifyCode> specifyCodes = coreConfigSpecifyCodeService.list(Wrappers.lambdaQuery(ConfigSpecifyCode.class)
                            .eq(ConfigSpecifyCode::getSnapshotVersion, specifyCode.getSnapshotVersion())
                            .eq(ConfigSpecifyCode::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE));
                    if (EmptyUtil.isEmpty(specifyCodes)) {
                        log.info("{}Rule execute ruleChain ruleMatch end -->  List<ConfigSpecifyCode> Empty", bo.getWayBillCode());
                        throw new RuntimeException("BusinessErrorEnum.ERROR_CONFIG");
                    }
                    bo.setSpecifyCodes(specifyCodes.stream().collect(Collectors.toMap(k -> k.getTransceiverCode(), k -> k.getSpecifyCode())));
                }
                String drop = bo.getSpecifyCodes().get(obtainDestination(bo.getDropPointDestination()));
                String pick = bo.getSpecifyCodes().get(obtainDestination(bo.getPickUpDP()));
                if (EmptyUtil.isAnyEmpty(drop, pick)) {
                    break;
                }
                if (Arrays.asList(rule.getConfigItem().split(",")).stream().anyMatch(l -> l.equals(pick + drop))) {
                    result = true;
                }
                break;
            case GOODS_TYPE:
                contains = rule.getConfigItem().equals(bo.getKind().name());
                if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
                        || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
                    result = true;
                }
                break;
            default:
                if (YesAndNoEnum.YES.equals(rule.getGuaranteed())) {
                    result = true;
                }
                break;
        }
        if (result) {
            if (EmptyUtil.isEmpty(bo.getTransportTypeMap())) {
                log.info("{}Rule execute ruleChain ruleMatch --> {} - {} ", bo.getWayBillCode(), result, JSONObject.toJSONString(rule));
                LambdaQueryWrapper<ConfigTransportType> wrapper = Wrappers.lambdaQuery(ConfigTransportType.class)
                        .eq(ConfigTransportType::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE);
                //查询运输类型map
                if (CommCalculateTypeEnum.AGAIN.equals(bo.getType())) {
                    wrapper.ge(ConfigTransportType::getUpdateTime, bo.getReceiveTime());
                } else {
                    wrapper.eq(ConfigTransportType::getEffectiveType, EffectiveTypeEnum.EFFECTIVE);
                }
                ConfigTransportType one = configTransportTypeService.getOne(wrapper, false);
                if (EmptyUtil.isEmpty(one)) {
                    log.info("{}Rule execute calculateCommission end --> ConfigTransportType Empty", bo.getWayBillCode());
                    throw new RuntimeException("BusinessErrorEnum.ERROR_CALCULATE_TRANSPORT");
                }
                List<ConfigTransportType> list = configTransportTypeService.list(Wrappers.lambdaQuery(ConfigTransportType.class)
                        .eq(ConfigTransportType::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE)
                        .eq(ConfigTransportType::getSnapshotVersion, one.getSnapshotVersion()));
                if (EmptyUtil.isEmpty(list)) {
                    log.info("{}Rule execute calculateCommission end --> ConfigTransportType list Empty", bo.getWayBillCode());
                    throw new RuntimeException("BusinessErrorEnum.ERROR_CALCULATE_TRANSPORT");
                }
                Map<String, ConfigTransportType> map = list.stream().collect(Collectors.toMap(k -> String.valueOf(k.getId()), k -> k));
                if (CommCalculateTypeEnum.AGAIN.equals(bo.getType())) {
                    map = list.stream().collect(Collectors.toMap(k -> k.getTransportType(), k -> k));
                }
                bo.setTransportTypeMap(map);
            }
            ConfigTransportType transportType = bo.getTransportTypeMap().get(String.valueOf(rule.getConfigTransportTypeId()));
            if (EmptyUtil.isNotEmpty(transportType)) {
                if (EmptyUtil.isAnyEmpty(transportType.getTransportType(), transportType.getTransportName())) {
                    log.info("{}Rule execute calculateCommission end --> ConfigTransportType {}", bo.getWayBillCode(), JSONObject.toJSONString(transportType));
                    throw new RuntimeException("BusinessErrorEnum.ERROR_CALCULATE_TRANSPORT");
                }
                rule.setTransportType(transportType.getTransportType()).setTransportName(transportType.getTransportName());
            } else {
                log.info("{}Rule execute calculateCommission end --> ConfigTransportType Empty", bo.getWayBillCode());
                throw new RuntimeException("BusinessErrorEnum.ERROR_CALCULATE_TRANSPORT");
            }
            return rule;
        }
        return null;
    }

    /**
     * 获取收发地
     *
     * @param destination
     * @return
     */
    private String obtainDestination(String destination) {
        if (destination == null) {
            return null;
        }
        if (destination.startsWith(BaseConstant.STR_SIX)) {
            return destination;
        }
        return destination.substring(BaseConstant.ZERO, BaseConstant.THREE);
    }
}