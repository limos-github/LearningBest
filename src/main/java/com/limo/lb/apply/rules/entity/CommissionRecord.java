package com.limo.lb.apply.rules.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.limo.lb.apply.rules.enums.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 佣金记录表
 * </p>
 *
 * @author shaokui.chen
 * @since 2021-11-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("COMMISSION_RECORD")
@KeySequence("COMMISSION_RECORD_SEQ")
public class CommissionRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId("ID")
    private Long id;
    /**
     * 月账单id
     */
    @TableField("BILL_ID")
    private Long billId;

    /**
     * 站点
     */
    @TableField("STATION_CODE")
    private String stationCode;
    /**
     * 站点名称
     */
    @TableField("STATION_NAME")
    private String stationName;

    /**
     * 佣金流水编号
     */
    @TableField("COMMISSION_RECORD_SERIAL")
    @Deprecated
    private String commissionRecordSerial;

    /**
     * 和账单状态保持一直
     */
    @TableField("SETTLEMENT_STATUS")
    private SettlementStatusEnum settlementStatus;

    /**
     * 订单号
     */
    @TableField("ORDER_NO")
    @Deprecated
    private String orderNo;

    /**
     * 运单号
     */
    @TableField("WAY_BILL_CODE")
    private String wayBillCode;

    /**
     * 返佣金额
     */
    @TableField("AMOUNT")
    private Long amount;

    /**
     * 运输方式
     */
    @TableField("TRANSPORT_TYPE")
    private String transportType;

    /**
     * 运输方式全称
     */
    @TableField("TRANSPORT_NAME")
    private String transportName;

    /**
     * 返佣类型0-百分比，1-固定收益，2-重量段
     */
    @TableField("COMMISSION_TYPE")
    private CommissionTypeEnum commissionType;

    /**
     * 初始佣金 该字段仅记录调账前的返佣金额，百分比是计算后的佣金 固定收益就是金额，重量段存取的就是当时匹配到的重量段的金额
     */
    @TableField("COMMISSION")
    private Long commission;
    /**
     * 返佣比值
     */
    @TableField("COMMISSION_PERCENT")
    private Long commissionPercent;


    /**
     * 重量段信息范围开始，只有重量段的时候才有
     */
    @TableField("WEIGHT_RANGE_BEGIN")
    @Deprecated
    private Long weightRangeBegin;
    /**
     * 重量段信息范围结束，只有重量段的时候才有
     */
    @TableField("WEIGHT_RANGE_END")
    @Deprecated
    private Long weightRangeEnd;
    /**
     * 快递费用
     */
    @TableField("DELIVERY_COSTS")
    private Long deliveryCosts;
    /**
     * 月账单编号
     */
    @TableField("BILL_SERIAL_NO")
    private String billSerialNo;

    /**
     * 佣金计算日期
     */
    @TableField("COMMISSION_CALC_DATE")
    private LocalDateTime commissionCalcDate;

    /**
     * 佣金结算日期
     */
    @TableField("COMMISSION_DATE")
    private LocalDateTime commissionDate;

    /**
     * 目的地
     */
    @TableField("DESTINATION")
    private String destination;

    /**
     * 默认0 0-未删除，1删除
     */
    @TableField("IS_DELETED")
    private IsDeletedTypeEnum isDeleted;

    /**
     * 结算方式
     */
    @TableField("PAYMENT_METHOD")
    private SettlementTypeEnum paymentMethod;

    /**
     * 创建人id
     */
    @TableField("CREATE_BY_ID")
    private String createById;

    /**
     * 创建人名称
     */
    @TableField("CREATE_BY_NAME")
    private String createByName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人Id
     */
    @TableField("UPDATE_BY_ID")
    private String updateById;

    /**
     * 修改人姓名
     */
    @TableField("UPDATE_BY_NAME")
    private String updateByName;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 是否调账 0 - 是，1-否 默认1
     */
    @TableField("IS_ADJUST")
    @Deprecated
    private YesAndNoEnum isAdjust;

    /**
     * 计费重量
     */
    @TableField("SETTLEMENT_WEIGHT")
    private Long settlementWeight;

    /**
     * 下单时间
     */
    @TableField("ORDER_DATE")
    private LocalDateTime orderDate;

    /**
     * 签收时间
     */
    @TableField("SIGN_TIME")
    private LocalDateTime signTime;
    /**
     * 重量段
     */
    @TableField("TIER")
    private TierTypeEnum tier;
}
