package com.limo.lb.apply.rules;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.limo.lb.apply.rules.content.BaseConstant;
import com.limo.lb.apply.rules.entity.*;
import com.limo.lb.apply.rules.enums.*;
import com.limo.lb.apply.rules.newRules.CommissionRecordCalculateBO;
import com.limo.lb.apply.rules.newRules.CommissionRulesChainVO;
import com.limo.lb.apply.rules.newRules.WeightRuleJson;
import com.limo.lb.apply.rules.service.*;
import com.limo.lb.apply.rules.utils.EmptyUtil;
import com.limo.lb.apply.rules.utils.ListUtils;
import com.limo.lb.apply.rules.utils.MoneyUtils;
import com.limo.lb.apply.rules.utils.NumberFormatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Deprecated
abstract class ChainRuleTemplate {
    @Autowired
    @Qualifier("nation")
    private AbstractChain nation;
    @Autowired
    @Qualifier("area")
    private AbstractChain area;
    @Autowired
    @Qualifier("station")
    private AbstractChain station;
    @Lazy
    @Autowired
    private CoreSiteService coreSiteService;
    @Lazy
    @Autowired
    private CoreScanRecordService coreScanRecordService;
    @Lazy
    @Autowired
    private CoreSenderOrderService coreSenderOrderService;
    @Lazy
    @Autowired
    private CoreCommissionRulesService coreCommissionRulesService;
    @Lazy
    @Autowired
    private CoreCommissionRecordService coreCommissionRecordService;

    /**
     * 设置责任链流程
     */
    private AbstractChain getSpecifyType() {
        station.setNextChain(area);
        area.setNextChain(nation);
        return station;
    }

    /**
     * Execute rules chain vo.
     * 模板
     *
     * @param dto the dto
     * @return the rules chain vo
     */
    @Deprecated
    public final CommissionRecordCalculateBO execute(RulesChainDTO dto) {
        log.info("{}Rule execute start --> {}", dto.getWayBillCode(), dto.getType().getDesc());
        CommissionRecordCalculateBO record = new CommissionRecordCalculateBO();

        //佣金预处理
        ScanRecord scanRecord = waybillCheck(dto, record);
        log.info("{}Rule execute waybillCheck end --> {}", dto.getWayBillCode(), JSONObject.toJSONString(scanRecord));
        if (EmptyUtil.isEmpty(scanRecord)) {
            return null;
        }
        if (CommCalculateTypeEnum.TRIAL.equals(dto.getType())) {
            scanRecord.setReceiveTime(dto.getTime());
        }
        //初始化入参
        RulesChainBO bo = initialize(scanRecord, dto.getType(), record);
        log.info("{}Rule execute initialize end --> {}", dto.getWayBillCode(), JSONObject.toJSONString(bo));
        //匹配规则
        RulesChainVO rule = getSpecifyType().getRules(bo, new RulesChainVO());
        log.info("{}Rule execute ruleChain end --> {}", dto.getWayBillCode(), JSONObject.toJSONString(rule));
        //根据规则计算佣金
        calculateCommission(rule, record);
        log.info("{}Rule execute calculateCommission end --> {}", dto.getWayBillCode(), JSONObject.toJSONString(record));
        return record;
    }

    private void calculateCommission(RulesChainVO rule, CommissionRecordCalculateBO record) {
        log.info("{}Rule execute calculateCommission start --> {}", record.getWayBillCode(), JSONObject.toJSONString(record));
        if (EmptyUtil.isEmpty(rule) || EmptyUtil.isEmpty(rule.getIRules())) {
            log.info("{}Rule execute calculateCommission end --> {}", record.getWayBillCode(), JSONObject.toJSONString(rule));
            throw new RuntimeException("BusinessErrorEnum.ERROR_MISMATCH_RULE");
        }
        getCommission(record, rule.getIRules());
    }

    private void getCommission(CommissionRecordCalculateBO record, CommissionRulesChainVO rule) {
        if (EmptyUtil.isEmpty(rule)) {
            return;
        }
        log.info("{}Rule execute getCommission start --> {} - {}", record.getWayBillCode(), rule.getSiteCommissionType(), JSONObject.toJSONString(rule));
        Long commission;
        Long percent = null;
        TierTypeEnum tierT = null;
        switch (rule.getCommissionType()) {
            case PERCENT:
                //百分比整数 0.15
                percent = MoneyUtils.rmToSen(rule.getCommissionTypeDetails());
                commission = MoneyUtils.againAmountByPercent(record.getDeliveryCosts(), percent);
                break;
            case FIXED_INCOME:
                commission = MoneyUtils.rmToSen(rule.getCommissionTypeDetails());
                break;
            case WEIGHT:
                BigDecimal weight = NumberFormatUtils.numberDivideFormat(record.getSettlementWeight(), 1000, 2);
                if (EmptyUtil.isEmpty(rule.getCommissionTypeDetails())) {
                    log.info("{}Rule execute getCommission {} --> end rule.getCommissionTypeDetails Empty", record.getWayBillCode(), rule.getCommissionType());
                    throw new RuntimeException("BusinessErrorEnum.ERROR_MISMATCH_RULE");
                }
                List<WeightRuleJson> wightRules = JSONObject.parseArray(rule.getCommissionTypeDetails(), WeightRuleJson.class);
                List<WeightRuleJson> commissions = wightRules.stream()
                        .filter(w -> (weight.compareTo(new BigDecimal(w.getWeightRangeStart())) > BaseConstant.ZERO
                                && weight.compareTo(new BigDecimal(w.getWeightRangeEnd())) <= BaseConstant.ZERO)
                                || BaseConstant.MINUS_ONE_STR.equals(w.getWeightRangeEnd())).collect(Collectors.toList());
                if (EmptyUtil.isNotEmpty(commissions) && commissions.size() > BaseConstant.ONE) {
                    ListUtils.sort(commissions, Boolean.TRUE, BaseConstant.WEIGHT_RANGE_START);
                }
                commission = MoneyUtils.rmToSen(commissions.get(BaseConstant.ZERO).getCommission());
                tierT = commissions.get(BaseConstant.ZERO).getTier();
                break;
            default:
                throw new RuntimeException("error");
        }
        record.setAmount(commission).setCommission(commission).setCommissionType(rule.getCommissionType()).setTier(tierT)
                .setCommissionPercent(percent).setTransportType(rule.getTransportType()).setTransportName(rule.getTransportName())
                .setRuleName(rule.getRuleName()).setSpecifyType(rule.getSpecifyType()).setAppointCode(rule.getAppointCode())
                .setCustomType(rule.getCustomType()).setCustomName(rule.getCustomName()).setBusinessType(rule.getBusinessType())
                .setJudge(rule.getJudge()).setConfigItem(rule.getConfigItem());
    }

    private ScanRecord waybillCheck(RulesChainDTO dto, CommissionRecordCalculateBO record) {
        log.info("{}Rule execute waybillCheck start --> {}", dto.getWayBillCode(), dto.getType().getDesc());
        // 查询收件是否完成
        ScanRecord scanRecord = EmptyUtil.isNotEmpty(dto.getScanRecord()) ? dto.getScanRecord() : coreScanRecordService.searchReceiptOrder(dto.getWayBillCode());
        if (EmptyUtil.isEmpty(scanRecord)) {
            log.info("{}Rule execute waybillCheck end --> {} scanRecord Empty", dto.getWayBillCode(), dto.getType().getDesc());
            throw new RuntimeException("error");
        }
        // 查询订单信息是否存在
        SenderOrder senderOrder = coreSenderOrderService.queryByWayBillCode(dto.getWayBillCode());
        if (EmptyUtil.isEmpty(senderOrder)) {
            log.info("{}Rule execute waybillCheck start --> {} senderOrder Empty", dto.getWayBillCode(), dto.getType().getDesc());
            throw new RuntimeException("error");
        }
        if (BaseConstant.ORDER_GOT.equals(senderOrder.getOrderStatusName())) {
            log.info("{}Rule execute waybillCheck end --> {}", dto.getWayBillCode(), senderOrder.getOrderStatusName());
            throw new RuntimeException("error");
        }
        record.setOrderNo(senderOrder.getOrderNo())
                .setOrderId(senderOrder.getId())
                .setPaymentMethod(senderOrder.getSettlementType())
                .setDestination(senderOrder.getDestinationNumber())
                .setOrderDate(senderOrder.getCreateTime());
        record.setWayBillCode(scanRecord.getWayBillCode())
                .setStationCode(scanRecord.getStationCode())
                .setStationName(scanRecord.getStationName())
                .setDeliveryCosts(scanRecord.getFreightCharge())
                .setSettlementWeight(scanRecord.getSettlementWeight());
        record.setSettlementStatus(SettlementStatusEnum.WAIT_BILL).setIsDeleted(IsDeletedTypeEnum.NOT_DELETE);
        if (CommCalculateTypeEnum.TRIAL.equals(dto.getType())) {
            return scanRecord;
        }
        CommissionRecord commission = coreCommissionRecordService.getOne(Wrappers.lambdaQuery(CommissionRecord.class)
                .eq(CommissionRecord::getWayBillCode, dto.getWayBillCode()).eq(CommissionRecord::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE));
        if (EmptyUtil.isNotEmpty(commission)) {
            if (!SettlementStatusEnum.WAIT_BILL.equals(commission.getSettlementStatus())) {
                log.info("{}Rule execute waybillCheck end --> 结算中", dto.getWayBillCode());
                throw new RuntimeException("error");
            }

//            orikaBeanMapperUtil.map(commission, record);
            commission = new CommissionRecord();
        }
        if (CommCalculateTypeEnum.AGAIN.equals(dto.getType())
                && (EmptyUtil.isEmpty(commission) || EmptyUtil.isNotEmpty(commission.getBillSerialNo()))) {
            log.info("{}Rule execute waybillCheck end --> {} CommissionRecord Empty or billSerialNo NotEmpty", dto.getWayBillCode(), dto.getType().getDesc());
            return null;
        }
        return scanRecord;
    }

    /**
     * 筛选出
     * 符合时间
     * 与站点类型匹配的佣金规则
     * 进行区域分组
     */
    private RulesChainBO initialize(ScanRecord scanRecord, CommCalculateTypeEnum type, CommissionRecordCalculateBO record) {
        log.info("{}Rule execute initialize start --> start", scanRecord.getWayBillCode());
        //查询站点对应的区域编码和上级站点的编码
        Site site = coreSiteService.searchCommAll(scanRecord.getStationCode());
        if (EmptyUtil.isEmpty(site)) {
            log.info("{}Rule execute initialize searchCommAll end --> Site Empty", scanRecord.getWayBillCode());
            throw new RuntimeException("error");
        }
        RulesChainBO bo = RulesChainBO.builder().siteType(site.getSiteType()).receiveTime(scanRecord.getReceiveTime())
                .expressType(scanRecord.getExpressType()).productType(scanRecord.getProductType())
                .vipCustomerCode(scanRecord.getVipCustomerCode()).wayBillCode(scanRecord.getWayBillCode())
                .dropPointDestination(scanRecord.getDestinationNumber()).pickUpDP(site.getNetCode())
                .kind(scanRecord.getKind()).type(type)
                .build();
        //查询收件时间对应的规则
        LambdaQueryWrapper<CommissionRules> wrapper = Wrappers.lambdaQuery(CommissionRules.class)
                .ne(CommissionRules::getEffectiveType, EffectiveTypeEnum.INVALID)
                .le(CommissionRules::getEffectiveTime, scanRecord.getReceiveTime())
                .ge(CommissionRules::getExpiredTime, scanRecord.getReceiveTime())
                .eq(CommissionRules::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE);
        if (SiteTypeEnum.SCP.equals(site.getSiteType())) {
            wrapper.in(CommissionRules::getAppointCode, Arrays.asList(site.getStationCode(), site.getAreaCode(), ObjectTypeEnum.NATION.name()))
                    .in(CommissionRules::getSiteCommissionType, Arrays.asList(SiteCommissionTypeEnum.SCP, SiteCommissionTypeEnum.PCP_SCP));
        } else {
            wrapper.in(CommissionRules::getAppointCode, Arrays.asList(site.getStationCode(), site.getAreaCode(), ObjectTypeEnum.NATION.name()))
                    .eq(CommissionRules::getSiteCommissionType, SiteTypeEnum.CP.equals(site.getSiteType()) ? SiteCommissionTypeEnum.CP : SiteCommissionTypeEnum.PCP);
        }
        //如果通过收件时间去计算的话 可以处理
        if (CommCalculateTypeEnum.AUTO.equals(type)) {
            wrapper.in(CommissionRules::getEffectiveType, Arrays.asList(EffectiveTypeEnum.EFFECTIVE, EffectiveTypeEnum.PENDING));
            record.setCommissionCalcDate(LocalDateTime.now());
        }
        List<CommissionRules> rules = coreCommissionRulesService.list(wrapper);
        if (EmptyUtil.isEmpty(rules)) {
            log.info("{}Rule execute initialize commissionList end --> CommissionRules list Empty", scanRecord.getWayBillCode());
            throw new RuntimeException("BusinessErrorEnum.ERROR_MISSING_FOREVER");
        }
        List<CommissionRulesChainVO> rulesChainVOList = new ArrayList<>();
//                orikaBeanMapperUtil.mapAsList(rules, CommissionRulesChainVO.class);
        Map<ObjectTypeEnum, List<CommissionRulesChainVO>> objectTypeEnumListMap = rulesChainVOList.stream().filter(
                r -> scanRecord.getReceiveTime().compareTo(r.getExpiredTime()) <= BaseConstant.ZERO
                        && scanRecord.getReceiveTime().compareTo(r.getEffectiveTime()) >= BaseConstant.ZERO
                        && checkSite(site.getSiteType(), r.getSiteCommissionType()))
                .filter(Objects::nonNull).collect(Collectors.groupingBy(CommissionRulesChainVO::getSpecifyType));
        return bo.setSpecifyType(objectTypeEnumListMap);
    }


    /**
     * 筛选符合站点条件的站点佣金类型
     *
     * @param siteType           the site type
     * @param siteCommissionType the site commission type
     * @return the boolean
     */
    private Boolean checkSite(SiteTypeEnum siteType, SiteCommissionTypeEnum siteCommissionType) {
        //检查佣金规则类型与站点是否匹配
        Boolean result = false;
        switch (siteType) {
            case CP:
                if (SiteCommissionTypeEnum.CP.equals(siteCommissionType)) {
                    result = true;
                }
            case SCP:
                if (SiteCommissionTypeEnum.SCP.equals(siteCommissionType)
                        || SiteCommissionTypeEnum.PCP_SCP.equals(siteCommissionType)) {
                    result = true;
                }
                break;
            case PCP:
                if (SiteCommissionTypeEnum.PCP.equals(siteCommissionType)) {
                    result = true;
                }
            default:
        }
        return result;
    }
}