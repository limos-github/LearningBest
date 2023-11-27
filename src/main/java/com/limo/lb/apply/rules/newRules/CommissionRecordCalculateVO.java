package com.limo.lb.apply.rules.newRules;

import com.yl.tmp.my.nuang.common.enums.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Rules chain vo.
 *
 * @author limo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommissionRecordCalculateVO implements Serializable {

    private static final long serialVersionUID = 7437225941564995547L;

    @ApiModelProperty("站点")
    private String stationCode;

    @ApiModelProperty("站点名称")
    private String stationName;

    @ApiModelProperty("佣金流水编号")
    @Deprecated
    private String commissionRecordSerial;

    @ApiModelProperty("账单状态保持一致")
    private SettlementStatusEnum settlementStatus;

    @ApiModelProperty("订单号")
    @Deprecated
    private String orderNo;

    @ApiModelProperty("运单号")
    private String wayBillCode;

    @ApiModelProperty("返佣金额")
    private String amount;

    @ApiModelProperty("运输方式")
    private String transportType;

    @ApiModelProperty("运输方式全称")
    private String transportName;

    @ApiModelProperty("返佣类型0-百分比，1-固定收益，2-重量段")
    private CommissionTypeEnum commissionType;

    @ApiModelProperty("初始佣金 该字段仅记录调账前的返佣金额，百分比是计算后的佣金 固定收益就是金额，重量段存取的就是当时匹配到的重量段的金额")
    private String commission;

    @ApiModelProperty("返佣比值")
    private String commissionPercent;

    @ApiModelProperty("重量段信息范围开始，只有重量段的时候才有")
    @Deprecated
    private String weightRangeBegin;

    @ApiModelProperty("重量段信息范围结束，只有重量段的时候才有")
    @Deprecated
    private String weightRangeEnd;

    @ApiModelProperty("快递费用")
    private Long deliveryCosts;

    @ApiModelProperty("佣金计算日期")
    private LocalDateTime commissionCalcDate;

    @ApiModelProperty("佣金结算日期")
    private LocalDateTime commissionDate;

    @ApiModelProperty("目的地")
    private String destination;

    @ApiModelProperty("默认0 0-未删除，1删除")
    private IsDeletedTypeEnum isDeleted;

    @ApiModelProperty("结算方式")
    private SettlementTypeEnum paymentMethod;

    @ApiModelProperty("是否调账 0 - 是，1-否 默认1")
    @Deprecated
    private YesAndNoEnum isAdjust;

    @ApiModelProperty("计费重量")
    private String settlementWeight;

    @ApiModelProperty("下单时间")
    private LocalDateTime orderDate;

    @ApiModelProperty("签收时间")
    private LocalDateTime signTime;

    @ApiModelProperty("重量段")
    private TierTypeEnum tier;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "区域类型 0- 全国 1- 区域 2- 站点")
    @NotNull
    private ObjectTypeEnum specifyType;

    @ApiModelProperty(value = "指定编码  区域为区域编码 站点为站点编码 全国填NATION")
    private String appointCode;

    @ApiModelProperty(value = "自定义名称 永久-阶段")
    private String customName;

    @ApiModelProperty(value = "自定义阶段类型  0-永久 1-阶段")
    private CustomTypeEnum customType;

    @ApiModelProperty(value = "参数类型")
    private BusinessTypeEnum businessType;

    @ApiModelProperty(value = "判断方式")
    private JudgeEnum judge;

    @ApiModelProperty(value = "配置类型")
    private String configItem;
}