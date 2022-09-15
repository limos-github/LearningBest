
///**
// * The type Chain demo.
// */
//@Slf4j
//abstract class ChainRuleTemplate {
//
//    @Autowired
//    @Qualifier("nation")
//    private AbstractChain nation;
//    @Autowired
//    @Qualifier("area")
//    private AbstractChain area;
//    @Autowired
//    @Qualifier("station")
//    private AbstractChain station;
//    @Autowired
//    private CoreSiteService coreSiteService;
//    @Autowired
//    private OrikaBeanMapperUtil orikaBeanMapperUtil;
//    @Autowired
//    private CoreScanRecordService coreScanRecordService;
//    @Autowired
//    private CoreSenderOrderService coreSenderOrderService;
//    @Autowired
//    private CoreCommissionRulesService coreCommissionRulesService;
//    @Autowired
//    private CoreCommissionRecordService coreCommissionRecordService;
//
//    /**
//     * 设置责任链流程
//     */
//    private AbstractChain getSpecifyType() {
//        station.setNextChain(area);
//        area.setNextChain(nation);
//        return station;
//    }
//
//    /**
//     * Execute rules chain vo.
//     * 模板
//     *
//     * @param dto the dto
//     * @return the rules chain vo
//     */
//    public final CommissionRecordCalculateBO execute(RulesChainDTO dto) {
//        log.info("{}Rule execute start --> {}", dto.getWayBillCode(), dto.getType().getDesc());
//        CommissionRecordCalculateBO record = new CommissionRecordCalculateBO();
//
//        //佣金预处理
//        ScanRecord scanRecord = waybillCheck(dto, record);
//        log.info("{}Rule execute waybillCheck end --> {}", dto.getWayBillCode(), JSONObject.toJSONString(scanRecord));
//        if (EmptyUtil.isEmpty(scanRecord)) {
//            return null;
//        }
//        if (CommCalculateTypeEnum.TRIAL.equals(dto.getType())) {
//            scanRecord.setReceiveTime(dto.getTime());
//        }
//        //初始化入参
//        RulesChainBO bo = initialize(scanRecord, dto.getType(), record);
//        log.info("{}Rule execute initialize end --> {}", dto.getWayBillCode(), JSONObject.toJSONString(bo));
//        //匹配规则
//        RulesChainVO rule = getSpecifyType().getRules(bo, new RulesChainVO());
//        log.info("{}Rule execute ruleChain end --> {}", dto.getWayBillCode(), JSONObject.toJSONString(rule));
//        //根据规则计算佣金
//        calculateCommission(rule, record);
//        log.info("{}Rule execute calculateCommission end --> {}", dto.getWayBillCode(), JSONObject.toJSONString(record));
//        return record;
//    }
//
//    private void calculateCommission(RulesChainVO rule, CommissionRecordCalculateBO record) {
//        log.info("{}Rule execute calculateCommission start --> {}", record.getWayBillCode(), JSONObject.toJSONString(record));
//        if (EmptyUtil.isEmpty(rule) || EmptyUtil.isEmpty(rule.getIRules())) {
//            log.info("{}Rule execute calculateCommission end --> {}", record.getWayBillCode(), JSONObject.toJSONString(rule));
//            throw new BusinessException(BusinessErrorEnum.ERROR_MISMATCH_RULE);
//        }
//        getCommission(record, rule.getIRules());
//    }
//
//    private void getCommission(CommissionRecordCalculateBO record, CommissionRulesChainVO rule) {
//        if (EmptyUtil.isEmpty(rule)) {
//            return;
//        }
//        log.info("{}Rule execute getCommission start --> {} - {}", record.getWayBillCode(), rule.getSiteCommissionType(), JSONObject.toJSONString(rule));
//        Long commission;
//        Long percent = null;
//        TierTypeEnum tierT = null;
//        switch (rule.getCommissionType()) {
//            case PERCENT:
//                //百分比整数 0.15
//                percent = MoneyUtils.rmToSen(rule.getCommissionTypeDetails());
//                commission = MoneyUtils.againAmountByPercent(record.getDeliveryCosts(), percent);
//                break;
//            case FIXED_INCOME:
//                commission = MoneyUtils.rmToSen(rule.getCommissionTypeDetails());
//                break;
//            case WEIGHT:
//                BigDecimal weight = NumberFormatUtils.numberDivideFormat(record.getSettlementWeight(), 1000, 2);
//                if (EmptyUtil.isEmpty(rule.getCommissionTypeDetails())) {
//                    log.info("{}Rule execute getCommission {} --> end rule.getCommissionTypeDetails Empty", record.getWayBillCode(), rule.getCommissionType());
//                    throw new BusinessException(BusinessErrorEnum.ERROR_MISMATCH_RULE);
//                }
//                List<WeightRuleJson> wightRules = JSONObject.parseArray(rule.getCommissionTypeDetails(), WeightRuleJson.class);
//                List<WeightRuleJson> commissions = wightRules.stream()
//                        .filter(w -> (weight.compareTo(new BigDecimal(w.getWeightRangeStart())) > BaseConstant.ZERO
//                                && weight.compareTo(new BigDecimal(w.getWeightRangeEnd())) <= BaseConstant.ZERO)
//                                || CoreConstant.MINUS_ONE_STR.equals(w.getWeightRangeEnd())).collect(Collectors.toList());
//                if (EmptyUtil.isNotEmpty(commissions) && commissions.size() > BaseConstant.ONE) {
//                    ListUtils.sort(commissions, Boolean.TRUE, CoreConstant.WEIGHT_RANGE_START);
//                }
//                commission = MoneyUtils.rmToSen(commissions.get(BaseConstant.ZERO).getCommission());
//                tierT = commissions.get(BaseConstant.ZERO).getTier();
//                break;
//            default:
//                throw new BusinessException(CommissionExceptionEnum.RULE_IS_EXCEPTION);
//        }
////        if (SiteCommissionTypeEnum.PCP_SCP.equals(rule.getSiteCommissionType())) {
////            record.setHigherCommissions(commission).setHigherTier(tierT)
////                    .setHigherCommissionPercent(percent).setHigherCommissionType(rule.getCommissionType())
////                    .setHigherTransportType(rule.getTransportType()).setHigherTransportName(rule.getTransportName())
////                    .setHigherRuleName(rule.getRuleName()).setHigherSpecifyType(rule.getSpecifyType()).setHigherAppointCode(rule.getAppointCode())
////                    .setHigherCustomType(rule.getCustomType()).setCustomName(rule.getCustomName())
////            ;
////        } else {
//        record.setAmount(commission).setCommission(commission).setCommissionType(rule.getCommissionType()).setTier(tierT)
//                .setCommissionPercent(percent).setTransportType(rule.getTransportType()).setTransportName(rule.getTransportName())
//                .setRuleName(rule.getRuleName()).setSpecifyType(rule.getSpecifyType()).setAppointCode(rule.getAppointCode())
//                .setCustomType(rule.getCustomType()).setCustomName(rule.getCustomName()).setBusinessType(rule.getBusinessType())
//        ;
////        }
//    }
//
//    private ScanRecord waybillCheck(RulesChainDTO dto, CommissionRecordCalculateBO record) {
//        log.info("{}Rule execute waybillCheck start --> {}", dto.getWayBillCode(), dto.getType().getDesc());
//        // 查询收件是否完成
//        ScanRecord scanRecord = coreScanRecordService.searchReceiptOrder(dto.getWayBillCode());
//        if (EmptyUtil.isEmpty(scanRecord)) {
//            log.info("{}Rule execute waybillCheck start --> {} scanRecord Empty", dto.getWayBillCode(), dto.getType().getDesc());
//            throw new BusinessException(OrderExceptionEnum.NOT_HAS_SEND);
//        }
//        // 查询订单信息是否存在
//        SenderOrder senderOrder = coreSenderOrderService.queryByWayBillCode(dto.getWayBillCode());
//        if (EmptyUtil.isEmpty(senderOrder)) {
//            log.info("{}Rule execute waybillCheck start --> {} senderOrder Empty", dto.getWayBillCode(), dto.getType().getDesc());
//            throw new BusinessException(OrderExceptionEnum.ORDER_NOT_EXIST);
//        }
//        if (BaseConstant.ORDER_GOT.equals(senderOrder.getOrderStatusName())) {
//            log.info("{}Rule execute waybillCheck end --> {}", dto.getWayBillCode(), senderOrder.getOrderStatusName());
//            return null;
//        }
//        if (CommCalculateTypeEnum.AGAIN.equals(dto.getType())) {
//            CommissionRecord commission = coreCommissionRecordService.getOne(Wrappers.lambdaQuery(CommissionRecord.class)
//                    .eq(CommissionRecord::getWayBillCode, dto.getWayBillCode()).eq(CommissionRecord::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE));
//            if (EmptyUtil.isEmpty(record) || EmptyUtil.isNotEmpty(commission.getBillSerialNo())) {
//                log.info("{}Rule execute waybillCheck end --> {} CommissionRecord Empty or billSerialNo NotEmpty", dto.getWayBillCode(), dto.getType().getDesc());
//                return null;
//            }
//            orikaBeanMapperUtil.map(commission, record);
//        } else {
//            record.setOrderNo(senderOrder.getOrderNo())
//                    .setPaymentMethod(senderOrder.getSettlementType())
//                    .setDestination(senderOrder.getDestinationNumber())
//                    .setOrderDate(senderOrder.getCreateTime());
//            record.setWayBillCode(scanRecord.getWayBillCode())
//                    .setStationCode(scanRecord.getStationCode())
//                    .setStationName(scanRecord.getStationName())
//                    .setDeliveryCosts(scanRecord.getFreightCharge())
//                    .setSettlementWeight(scanRecord.getSettlementWeight());
//            record.setSettlementStatus(SettlementStatusEnum.WAIT_BILL).setIsDeleted(IsDeletedTypeEnum.NOT_DELETE);
//        }
//        // 检测账单是否处于结算中 试算不校验
//        if (!CommCalculateTypeEnum.TRIAL.equals(dto.getType())) {
//            if (BaseConstant.ZERO <= coreCommissionRecordService.count(Wrappers.lambdaQuery(CommissionRecord.class)
//                    .eq(CommissionRecord::getWayBillCode, dto.getWayBillCode()).eq(CommissionRecord::getStationCode, scanRecord.getStationCode())
//                    .eq(CommissionRecord::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE).ne(CommissionRecord::getSettlementStatus, SettlementStatusEnum.WAIT_BILL))) {
//                log.info("{}Rule execute waybillCheck end --> 结算中", dto.getWayBillCode());
//                throw new BusinessException(FinancialExceptionEnum.BILL_SETTLEMENT);
//            }
//        }
//        return scanRecord;
//    }
//
//    /**
//     * 筛选出
//     * 符合时间
//     * 与站点类型匹配的佣金规则
//     * 进行区域分组
//     */
//    private RulesChainBO initialize(ScanRecord scanRecord, CommCalculateTypeEnum type, CommissionRecordCalculateBO record) {
//        log.info("{}Rule execute initialize start --> start", scanRecord.getWayBillCode());
//        //查询站点对应的区域编码和上级站点的编码
//        Site site = coreSiteService.searchCommAll(scanRecord.getStationCode());
//        if (EmptyUtil.isEmpty(site)) {
//            log.info("{}Rule execute initialize searchCommAll end --> Site Empty", scanRecord.getWayBillCode());
//            throw new BusinessException(SiteExceptionEnum.SITE_NOT_EXIST);
//        }
//
//        RulesChainBO bo = RulesChainBO.builder().siteType(site.getSiteType()).receiveTime(scanRecord.getReceiveTime())
//                .expressType(scanRecord.getExpressType()).productType(scanRecord.getProductType())
//                .vipCustomerCode(scanRecord.getVipCustomerCode()).wayBillCode(scanRecord.getWayBillCode())
//                .dropPointDestination(scanRecord.getDestinationNumber()).pickUpDP(site.getNetCode())
//                .kind(scanRecord.getKind()).type(type)
//                .build();
//        //查询收件时间对应的规则
//        LambdaQueryWrapper<CommissionRules> wrapper = Wrappers.lambdaQuery(CommissionRules.class)
//                .ne(CommissionRules::getEffectiveType, EffectiveTypeEnum.INVALID)
//                .le(CommissionRules::getEffectiveTime, scanRecord.getReceiveTime())
//                .ge(CommissionRules::getExpiredTime, scanRecord.getReceiveTime())
//                .eq(CommissionRules::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE);
//        if (SiteTypeEnum.SCP.equals(site.getSiteType())) {
//            wrapper.in(CommissionRules::getAppointCode, Arrays.asList(site.getStationCode(), site.getAreaCode(), ObjectTypeEnum.NATION.name()))
//                    .in(CommissionRules::getSiteCommissionType, Arrays.asList(SiteCommissionTypeEnum.SCP, SiteCommissionTypeEnum.PCP_SCP));
//        } else {
//            wrapper.in(CommissionRules::getAppointCode, Arrays.asList(site.getStationCode(), site.getAreaCode(), ObjectTypeEnum.NATION.name()))
//                    .eq(CommissionRules::getSiteCommissionType, SiteTypeEnum.CP.equals(site.getSiteType()) ? SiteCommissionTypeEnum.CP : SiteCommissionTypeEnum.PCP);
//        }
//        //如果通过收件时间去计算的话 可以处理
//        if (CommCalculateTypeEnum.AUTO.equals(type)) {
//            wrapper.in(CommissionRules::getEffectiveType, Arrays.asList(EffectiveTypeEnum.EFFECTIVE, EffectiveTypeEnum.PENDING));
//            record.setCommissionCalcDate(LocalDateTime.now());
//        }
//        List<CommissionRules> rules = coreCommissionRulesService.list(wrapper);
//        if (EmptyUtil.isEmpty(rules)) {
//            log.info("{}Rule execute initialize commissionList end --> CommissionRules list Empty", scanRecord.getWayBillCode());
//            throw new BusinessException(BusinessErrorEnum.ERROR_MISSING_FOREVER);
//        }
//        List<CommissionRulesChainVO> rulesChainVOList = orikaBeanMapperUtil.mapAsList(rules, CommissionRulesChainVO.class);
//        Map<ObjectTypeEnum, List<CommissionRulesChainVO>> objectTypeEnumListMap = rulesChainVOList.stream().filter(
//                r -> scanRecord.getReceiveTime().compareTo(r.getExpiredTime()) <= BaseConstant.ZERO
//                        && scanRecord.getReceiveTime().compareTo(r.getEffectiveTime()) >= BaseConstant.ZERO
//                        && checkSite(site.getSiteType(), r.getSiteCommissionType()))
//                .filter(Objects::nonNull).collect(Collectors.groupingBy(CommissionRulesChainVO::getSpecifyType));
//        return bo.setSpecifyType(objectTypeEnumListMap);
//    }
//
//
//    /**
//     * 筛选符合站点条件的站点佣金类型
//     *
//     * @param siteType           the site type
//     * @param siteCommissionType the site commission type
//     * @return the boolean
//     */
//    private Boolean checkSite(SiteTypeEnum siteType, SiteCommissionTypeEnum siteCommissionType) {
//        //检查佣金规则类型与站点是否匹配
//        Boolean result = false;
//        switch (siteType) {
//            case CP:
//                if (SiteCommissionTypeEnum.CP.equals(siteCommissionType)) {
//                    result = true;
//                }
//            case SCP:
//                if (SiteCommissionTypeEnum.SCP.equals(siteCommissionType)
//                        || SiteCommissionTypeEnum.PCP_SCP.equals(siteCommissionType)) {
//                    result = true;
//                }
//                break;
//            case PCP:
//                if (SiteCommissionTypeEnum.PCP.equals(siteCommissionType)) {
//                    result = true;
//                }
//            default:
//        }
//        return result;
//    }
//}
//
///**
// * The type Chain demo.
// */
//public class ChainDemo {
//
//    /**
//     * The entry point of application.
//     *
//     * @param args the input arguments
//     */
//    public static void main(String[] args) {
//        new Demo().execute(new RulesChainDTO());
//    }
//}
//
///**
// * The type Demo.
// */
//@Service
//class Demo extends ChainRuleTemplate {
//}
//
///**
// * The type Abstract chain.
// */
//
//@Slf4j
//abstract class AbstractChain {
//    @Autowired
//    private CoreConfigSpecifyCodeService coreConfigSpecifyCodeService;
//    @Autowired
//    private CoreConfigSpecifyTypeService coreConfigSpecifyTypeService;
//    @Autowired
//    private CoreConfigTransportTypeService configTransportTypeService;
//    @Autowired
//    private CoreConfigCustomerCodeService coreConfigCustomerCodeService;
//    /**
//     * The Specify type.
//     */
//    protected ObjectTypeEnum specifyType;
//    /**
//     * The Next chain.
//     * 责任链中的下一个元素
//     */
//    protected AbstractChain nextChain;
//
//    /**
//     * Sets next chain.
//     *
//     * @param nextChain the next chain
//     */
//    public void setNextChain(AbstractChain nextChain) {
//        this.nextChain = nextChain;
//    }
//
//    /**
//     * Gets rules.
//     * 调用此方法前需要
//     *
//     * @param bo the bo
//     * @param vo the vo
//     * @return the rules
//     */
//    public RulesChainVO getRules(@NotNull RulesChainBO bo, @NotNull RulesChainVO vo) {
//        ObjectTypeEnum specifyType = ObjectTypeEnum.NATION;
//        //station -> area -> nation
//        //根据站点类型 筛选对应的规则
//        if (this.specifyType.getCode() >= (specifyType.getCode())) {
//            log.info("{}Rule execute ruleChain start --> {}", bo.getWayBillCode(), this.specifyType.getDesc());
//            rule(bo, vo);
////            if (SiteTypeEnum.SCP.equals(bo.getSiteType()) && EmptyUtil.isNotEmpty(vo.getIRules()) && EmptyUtil.isNotEmpty(vo.getUpRules())) {
////                log.info("{}Rule execute ruleChain end --> {}", bo.getWayBillCode(), this.specifyType.getDesc());
////                return vo;
////            } else if ((SiteTypeEnum.CP.equals(bo.getSiteType()) || SiteTypeEnum.PCP.equals(bo.getSiteType())) && EmptyUtil.isNotEmpty(vo.getIRules())) {
////                log.info("{}Rule execute ruleChain end --> {}", bo.getWayBillCode(), bo.getSiteType().getDesc());
////                return vo;
////            } else {
////                nextChain.getRules(bo, vo);
////            }
//            if (EmptyUtil.isAnyEmpty(vo, vo.getIRules())) {
//                nextChain.getRules(bo, vo);
//            }
//        }
//        return vo;
//    }
//
//    /**
//     * Rule commission rules.
//     *
//     * @param bo the bo
//     * @param vo the vo
//     */
//    protected void rule(RulesChainBO bo, RulesChainVO vo) {
//        log.info("{}Rule execute ruleChain rule --> {}", bo.getWayBillCode(), this.specifyType.getDesc());
//        List<CommissionRulesChainVO> rulesList = bo.getSpecifyType().get(this.specifyType);
//        if (EmptyUtil.isEmpty(rulesList)) {
//            log.info("{}Rule execute ruleChain rule --> {} rulesList Empty", bo.getWayBillCode(), this.specifyType.getDesc());
//            return;
//        }
//        Map<SiteCommissionTypeEnum, List<CommissionRulesChainVO>> map = rulesList.stream().collect(Collectors.groupingBy(CommissionRulesChainVO::getSiteCommissionType, Collectors.toList()));
//        map.forEach((k, v) -> filterRules(bo, k, v, vo));
//    }
//
//    /**
//     * 时间符合 佣金类型符合 条件的阶段
//     * 如果是站点需要找到对应的类型
//     * 找到对应的规则
//     *
//     * @param bo   the bo
//     * @param type the type
//     * @param v
//     * @param vo   the vo
//     */
//    protected void filterRules(RulesChainBO bo, SiteCommissionTypeEnum type, List<CommissionRulesChainVO> v, RulesChainVO vo) {
//        log.info("{}Rule execute ruleChain filterRules --> {} - {}", bo.getWayBillCode(), type.getDesc(), JSONObject.toJSONString(v));
//        //排序 阶段 -> 永久 优先级 大 -> 小
//        List<CommissionRulesChainVO> rulesList = v.stream().sorted(Comparator.comparing(CommissionRulesChainVO::getCustomType)
//                .thenComparing(CommissionRulesChainVO::getPriority).reversed()).collect(Collectors.toList());
//        log.info("{}Rule execute ruleChain filterRules --> {} - {}", bo.getWayBillCode(), type.getDesc(), JSONObject.toJSONString(rulesList));
//        for (CommissionRulesChainVO r : rulesList) {
//            CommissionRulesChainVO rule = ruleMatch(bo, r);
//            //匹配规则 匹配到就结束
//            if (EmptyUtil.isNotEmpty(rule)) {
//                //站点类型与佣金类型相同
//                if (bo.getSiteType().getCode() == type.getCode() && EmptyUtil.isEmpty(vo.getIRules())) {
//                    vo.setIRules(rule);
//                }
//                //如果是SCP会多一个佣金规则 PCP_SCP
//                if (SiteCommissionTypeEnum.PCP_SCP.equals(type) && EmptyUtil.isEmpty(vo.getUpRules())) {
//                    vo.setUpRules(rule);
//                }
//                return;
//            }
//        }
//    }
//
//    /**
//     * 根据优先级顺序匹配当前运单中的参数类型 信息是否匹配 当前规则
//     */
//    private CommissionRulesChainVO ruleMatch(RulesChainBO bo, CommissionRulesChainVO rule) {
//        log.info("{}Rule execute ruleChain ruleMatch --> {}", bo.getWayBillCode(), JSONObject.toJSONString(rule));
//        boolean result = false;
//        boolean contains;
//        switch (rule.getBusinessType()) {
//            case PRODUCT_TYPE:
//                if (EmptyUtil.isEmpty(bo.getExpressType()) || EmptyUtil.isEmpty(bo.getProductType())) {
//                    break;
//                }
//                contains = rule.getConfigItem().equals(bo.getExpressType().name() + "/" + bo.getProductType());
//                if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
//                        || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
//                    result = true;
//                }
//                break;
//            case EXPRESS_TYPE:
//                contains = bo.getWayBillCode().startsWith(BaseConstant.STR_SIX_SIX);
//                switch (rule.getConfigItem()) {
//                    case BaseConstant.FOREIGN:
//                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
//                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
//                            result = true;
//                        }
//                        break;
//                    case BaseConstant.DOMESTIC:
//                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && !contains)
//                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && contains)) {
//                            result = true;
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            case CUSTOMER_TYPE:
//                if (EmptyUtil.isEmpty(bo.getVipCustomerCode())) {
//                    break;
//                }
//                switch (rule.getConfigItem()) {
//                    case BaseConstant.VIP_CUSTOMER:
//                        if (EmptyUtil.isEmpty(bo.getCustomerCodes())) {
//                            //查询大客户内容
//                            LambdaQueryWrapper<ConfigCustomerCode> customerWrapper = Wrappers.lambdaQuery(ConfigCustomerCode.class).eq(ConfigCustomerCode::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE);
//                            if (CommCalculateTypeEnum.AGAIN.equals(bo.getType())) {
//                                customerWrapper.le(ConfigCustomerCode::getUpdateTime, bo.getReceiveTime());
//                            } else {
//                                customerWrapper.eq(ConfigCustomerCode::getEffectiveType, EffectiveTypeEnum.EFFECTIVE);
//                            }
//                            ConfigCustomerCode customerCode = coreConfigCustomerCodeService.getOne(customerWrapper
//                                    .select(ConfigCustomerCode::getCustomerCode)
//                                    .eq(ConfigCustomerCode::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE), false);
//                            if (EmptyUtil.isEmpty(customerCode)) {
//                                log.info("{}Rule execute ruleChain ruleMatch end -->  ConfigCustomerCode Empty", bo.getWayBillCode());
//                                throw new BusinessException(BusinessErrorEnum.ERROR_CONFIG);
//                            }
//                            bo.setCustomerCodes(customerCode.getCustomerCode());
//                        }
//                        contains = bo.getCustomerCodes().contains(bo.getVipCustomerCode());
//                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
//                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
//                            result = true;
//                        }
//                        break;
//                    case BaseConstant.NATION_CUSTOMER:
//                        contains = bo.getVipCustomerCode().startsWith(BaseConstant.NATION_CUSTOMER_STR);
//                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
//                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
//                            result = true;
//                        }
//                        break;
//                    case BaseConstant.OTHER_CUSTOMER:
//                        boolean vip = bo.getCustomerCodes().contains(bo.getVipCustomerCode());
//                        boolean nation = bo.getVipCustomerCode().startsWith(BaseConstant.NATION_CUSTOMER_STR);
//                        if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && !nation && !vip)
//                                || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && (nation || vip))) {
//                            result = true;
//                        }
//                    default:
//                        break;
//                }
//                break;
//            case SPECIFY_TYPE:
//                //配置类型直接查询 如果查询为空那就是没有
//                if (EmptyUtil.isAnyEmpty(bo.getDropPointDestination(), bo.getPickUpDP())) {
//                    break;
//                }
//                if (EmptyUtil.isAnyEmpty(bo.getSpecifyCodes(), bo.getSpecifyTypes())) {
//                    LambdaQueryWrapper<ConfigSpecifyCode> codeWrapper = Wrappers.lambdaQuery(ConfigSpecifyCode.class).eq(ConfigSpecifyCode::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE);
//                    LambdaQueryWrapper<ConfigSpecifyType> typeWrapper = Wrappers.lambdaQuery(ConfigSpecifyType.class).eq(ConfigSpecifyType::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE);
//                    //如果通过收件时间去计算的话 可以处理
//                    if (CommCalculateTypeEnum.AGAIN.equals(bo.getType())) {
//                        codeWrapper.le(ConfigSpecifyCode::getUpdateTime, bo.getReceiveTime());
//                        typeWrapper.le(ConfigSpecifyType::getUpdateTime, bo.getReceiveTime());
//                    } else {
//                        codeWrapper.eq(ConfigSpecifyCode::getEffectiveType, EffectiveTypeEnum.EFFECTIVE);
//                        typeWrapper.eq(ConfigSpecifyType::getEffectiveType, EffectiveTypeEnum.EFFECTIVE);
//                    }
//                    //查询对应的网点编号
//                    ConfigSpecifyCode specifyCode = coreConfigSpecifyCodeService.getOne(codeWrapper, false);
//                    if (EmptyUtil.isEmpty(specifyCode)) {
//                        log.info("{}Rule execute ruleChain ruleMatch end --> ConfigSpecifyCode Empty", bo.getWayBillCode());
//                        throw new BusinessException(BusinessErrorEnum.ERROR_CONFIG);
//                    }
//                    List<ConfigSpecifyCode> specifyCodes = coreConfigSpecifyCodeService.list(Wrappers.lambdaQuery(ConfigSpecifyCode.class)
//                            .eq(ConfigSpecifyCode::getSnapshotVersion, specifyCode.getSnapshotVersion())
//                            .eq(ConfigSpecifyCode::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE));
//                    if (EmptyUtil.isEmpty(specifyCodes)) {
//                        log.info("{}Rule execute ruleChain ruleMatch end -->  List<ConfigSpecifyCode> Empty", bo.getWayBillCode());
//                        throw new BusinessException(BusinessErrorEnum.ERROR_CONFIG);
//
//                    }
//                    //查询网点编号对应的运输类型
//                    ConfigSpecifyType specifyType = coreConfigSpecifyTypeService.getOne(typeWrapper, false);
//                    if (EmptyUtil.isEmpty(specifyType)) {
//                        log.info("{}Rule execute ruleChain ruleMatch end --> ConfigSpecifyType Empty", bo.getWayBillCode());
//                        throw new BusinessException(BusinessErrorEnum.ERROR_CONFIG);
//                    }
//                    List<ConfigSpecifyType> specifyTypes = coreConfigSpecifyTypeService.list(Wrappers.lambdaQuery(ConfigSpecifyType.class)
//                            .eq(ConfigSpecifyType::getSnapshotVersion, specifyType.getSnapshotVersion())
//                            .eq(ConfigSpecifyType::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE));
//                    if (EmptyUtil.isEmpty(specifyTypes)) {
//                        log.info("{}Rule execute ruleChain ruleMatch end --> List<ConfigSpecifyType> Empty", bo.getWayBillCode());
//                        throw new BusinessException(BusinessErrorEnum.ERROR_CONFIG);
//                    }
//                    bo.setSpecifyTypes(specifyTypes.stream().collect(Collectors.toMap(k -> k.getSpecifyCode(), k -> k.getTransportTypeId())))
//                            .setSpecifyCodes(specifyCodes.stream().collect(Collectors.toMap(k -> k.getTransceiverCode(), k -> k.getSpecifyCode())));
//                }
//                String drop = bo.getSpecifyCodes().get(obtainDestination(bo.getDropPointDestination()));
//                String pick = bo.getSpecifyCodes().get(obtainDestination(bo.getPickUpDP()));
//                if (EmptyUtil.isAnyEmpty(drop, pick)) {
//                    break;
//                }
//                Long type = bo.getSpecifyTypes().get(pick + drop);
//                if (EmptyUtil.isNotEmpty(type)) {
//                    rule.setConfigTransportTypeId(type);
//                    result = true;
//                }
//                break;
//            case GOODS_TYPE:
//                contains = rule.getConfigItem().equals(bo.getKind().name());
//                if ((JudgeEnum.EQUAL.equals(rule.getJudge()) && contains)
//                        || (JudgeEnum.NOT_EQUAL_TO.equals(rule.getJudge()) && !contains)) {
//                    result = true;
//                }
//                break;
//            default:
//                if (YesAndNoEnum.YES.equals(rule.getGuaranteed())) {
//                    result = true;
//                }
//                break;
//        }
//        if (result) {
//            if (EmptyUtil.isEmpty(bo.getTransportTypeMap())) {
//                log.info("{}Rule execute ruleChain ruleMatch --> {} - {} ", bo.getWayBillCode(), result, JSONObject.toJSONString(rule));
//                LambdaQueryWrapper<ConfigTransportType> wrapper = Wrappers.lambdaQuery(ConfigTransportType.class)
//                        .eq(ConfigTransportType::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE);
//                //查询运输类型map
//                if (CommCalculateTypeEnum.AGAIN.equals(bo.getType())) {
//                    wrapper.ge(ConfigTransportType::getUpdateTime, bo.getReceiveTime());
//                } else {
//                    wrapper.eq(ConfigTransportType::getEffectiveType, EffectiveTypeEnum.EFFECTIVE);
//                }
//                ConfigTransportType one = configTransportTypeService.getOne(wrapper, false);
//                if (EmptyUtil.isEmpty(one)) {
//                    log.info("{}Rule execute calculateCommission end --> ConfigTransportType Empty", bo.getWayBillCode());
//                    throw new BusinessException(BusinessErrorEnum.ERROR_CALCULATE_TRANSPORT);
//                }
//                List<ConfigTransportType> list = configTransportTypeService.list(Wrappers.lambdaQuery(ConfigTransportType.class)
//                        .eq(ConfigTransportType::getIsDeleted, IsDeletedTypeEnum.NOT_DELETE)
//                        .eq(ConfigTransportType::getSnapshotVersion, one.getSnapshotVersion()));
//                if (EmptyUtil.isEmpty(list)) {
//                    log.info("{}Rule execute calculateCommission end --> ConfigTransportType list Empty", bo.getWayBillCode());
//                    throw new BusinessException(BusinessErrorEnum.ERROR_CALCULATE_TRANSPORT);
//                }
//                Map<String, ConfigTransportType> map = list.stream().collect(Collectors.toMap(k -> String.valueOf(k.getId()), k -> k));
//                if (CommCalculateTypeEnum.AGAIN.equals(bo.getType())) {
//                    map = list.stream().collect(Collectors.toMap(k -> k.getTransportType(), k -> k));
//                }
//                bo.setTransportTypeMap(map);
//            }
//            ConfigTransportType transportType = bo.getTransportTypeMap().get(String.valueOf(rule.getConfigTransportTypeId()));
//            if (EmptyUtil.isNotEmpty(transportType)) {
//                rule.setTransportType(transportType.getTransportType()).setTransportName(transportType.getTransportName());
//            }
//            return rule;
//        }
//        return null;
//    }
//
//    /**
//     * 获取收发地
//     *
//     * @param destination
//     * @return
//     */
//    private String obtainDestination(String destination) {
//        if (destination == null) {
//            return null;
//        }
//        if (destination.startsWith(BaseConstant.STR_SIX)) {
//            return destination;
//        }
//        return destination.substring(BaseConstant.ZERO, BaseConstant.THREE);
//    }
//}
//
///**
// * The type Station.
// */
//@Slf4j
//@Service
//class Station extends AbstractChain {
//    /**
//     * Instantiates a new Station.
//     */
//    public Station() {
//        this.specifyType = ObjectTypeEnum.STATION;
//    }
//
//    @Override
//    protected void rule(RulesChainBO bo, RulesChainVO vo) {
//        super.rule(bo, vo);
//    }
//}
//
///**
// * The type Area.
// */
//@Slf4j
//@Service
//class Area extends AbstractChain {
//    /**
//     * Instantiates a new Area.
//     */
//    public Area() {
//        this.specifyType = ObjectTypeEnum.AREA;
//    }
//
//    @Override
//    protected void rule(RulesChainBO bo, RulesChainVO vo) {
//        super.rule(bo, vo);
//    }
//}
//
///**
// * The type Nation.
// */
//@Slf4j
//@Service
//class Nation extends AbstractChain {
//    /**
//     * Instantiates a new Nation.
//     */
//    public Nation() {
//        this.specifyType = ObjectTypeEnum.NATION;
//    }
//
//    @Override
//    protected void rule(RulesChainBO bo, RulesChainVO vo) {
//        super.rule(bo, vo);
//    }
//}
//
///**
// * The type Rules chain bo.
// */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Accessors(chain = true)
//class RulesChainDTO {
//    private String wayBillCode;
//    private CommCalculateTypeEnum type;
//    private LocalDateTime time;
//}
//
///**
// * The type Rules chain bo.
// */
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Accessors(chain = true)
//class RulesChainBO {
//    private SiteTypeEnum siteType;
//    private LocalDateTime receiveTime;
//    private Map<ObjectTypeEnum, List<CommissionRulesChainVO>> specifyType;
//    private Map<String, ConfigTransportType> transportTypeMap;
//    private Map<String, Long> specifyTypes;
//    private Map<String, String> specifyCodes;
//    private String customerCodes;
//    private CommCalculateTypeEnum type;
//    /**
//     * 参数类型
//     */
//    private String wayBillCode;
//    private String vipCustomerCode;
//    private KindEnum kind;
//    private ExpressTypeEnum expressType;
//    private String productType;
//
//    private String dropPointDestination;
//    private String pickUpDP;
//}
//
//
///**
// * The type Rules chain vo.
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Accessors(chain = true)
//class RulesChainVO {
//    private CommissionRulesChainVO iRules;
//    private CommissionRulesChainVO upRules;
//
//}
