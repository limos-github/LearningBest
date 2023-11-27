package com.limo.lb.apply.rules.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.limo.lb.apply.rules.enums.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 扫描记录表
 * </p>
 *
 * @author yangxy
 * @since 2021-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SCAN_RECORD")
@KeySequence("SCAN_RECORD_SEQ")
@Accessors(chain = true)
public class ScanRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("ID")
    private Long id;

    /**
     * 运单号
     */
    @TableField("WAY_BILL_CODE")
    private String wayBillCode;

    /**
     * 扫描类型 0-发件件 1-收件
     */
    @TableField("WAY_BILL_TYPE")
    private ScanTypeEnum wayBillType;

    /**
     * 状态
     */
    @TableField("STATUS")
    private StatusEnum status;

    /**
     * 上传时间
     */
    @TableField("UPLOAD_TIME")
    private LocalDateTime uploadTime;

    /**
     * 扫描时间
     */
    @TableField("SCAN_TIME")
    private LocalDateTime scanTime;

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
    @TableField("CREATE_TIME")
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
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 是否删除 0-未删除，1-已删除
     */
    @TableField("IS_DELETED")
    private IsDeletedTypeEnum isDeleted;
    /**
     * 站点编码
     */
    @TableField("STATION_CODE")
    private String stationCode;

    /**
     * 站点名称
     */
    @TableField("STATION_NAME")
    private String stationName;

    /**
     * 员工 唯一识别码(手机号)
     */
    @TableField("USER_MOBILE")
    private String userMobile;

    /**
     * 是否已经结算佣金0-是，1-否 默认1
     */
    @TableField("IS_COMMISSION")
    private YesAndNoEnum isCommission;

    /**
     * 下一站
     */
    @TableField("NEXT_STATION")
    private String nextStation;

    /**
     * 长
     */
    @TableField("LENGTH")
    private Long length;

    /**
     * 宽
     */
    @TableField("WIDTH")
    private Long width;

    /**
     * 高
     */
    @TableField("HEIGHT")
    private Long height;

    /**
     * 体积重量(G)
     */
    @TableField("VOLUME_WEIGHT")
    private Long volumeWeight;

    /**
     * 运单重量(G)(实际重量)
     */
    @TableField("WAYBILL_WEIGHT")
    private Long waybillWeight;

    /**
     * 结算重量(G)
     */
    @TableField("SETTLEMENT_WEIGHT")
    private Long settlementWeight;

    /**
     * 运单价格(san)
     */
    @TableField("WAYBILL_PRICE")
    private Long waybillPrice;

    /**
     * 运费
     */
    @TableField("FREIGHT_CHARGE")
    private Long freightCharge;

    /**
     * 税费
     */
    @TableField("TAXATION")
    private Long taxation;

    /**
     * 是否是子母单
     */
    @TableField("IS_MPS")
    private YesAndNoEnum isMps;

    /**
     * 舍入金额
     */
    @TableField("CHANGES")
    private Long changes;

    /**
     * 预估金额
     */
    @TableField("ORDER_AMOUNT")
    private Long orderAmount;

    /**
     * NE
     * MS
     * 大件...
     */
    @TableField("PRODUCT_TYPE")
    private String productType;

    /**
     * 寄件人
     */
    @TableField("SENDER")
    private String sender;

    /**
     * 寄件人手机
     */
    @TableField("SENDER_MOBILE")
    private String senderMobile;


    /**
     * 寄件人详细地址
     */
    @TableField("SENDER_DETAIL_ADDR")
    private String senderDetailAddr;
    /**
     * 收件人
     */
    @TableField("RECIPIENT")
    private String recipient;

    /**
     * 收件人手机
     */
    @TableField("RECIPIENT_MOBILE")
    private String recipientMobile;

    /**
     * 收件人详细地址
     */
    @TableField("RECIPIENT_DETAIL_ADDR")
    private String recipientDetailAddr;

    /**
     * 发票编号
     */
    @TableField("INVOICE_NO")
    private String invoiceNo;
    /**
     * 发票日期
     */
    @TableField("INVOICE_TIME")
    private LocalDateTime invoiceTime;

    /**
     * 结算类型 0-到付，1-月结，2-寄付
     */
    @TableField("SETTLEMENT_TYPE")
    private SettlementTypeEnum settlementType;

    /**
     * 快递类型 0 EZ（普通件）、1 EX（次日达）、2 SF（海运件）、3 JS（高端商务件）、4 INT（国际件）
     */
    @TableField("EXPRESS_TYPE")
    private ExpressTypeEnum expressType;

    /**
     * 大客户编号
     */
    @TableField("VIP_CUSTOMER_CODE")
    private String vipCustomerCode;

    /**
     * 种类
     */
    @TableField("KIND")
    private KindEnum kind;

    /**
     * 收件时间
     */
    @TableField("RECEIVE_TIME")
    private LocalDateTime receiveTime;

    /**
     * 目的地编号
     */
    @TableField("DESTINATION_NUMBER")
    private String destinationNumber;

    /**
     * 是否是退件签收
     */
    @TableField("IS_RETURN")
    private YesAndNoEnum isReturn;

}
