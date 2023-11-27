package com.limo.lb.apply.rules.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.limo.lb.apply.rules.enums.SettlementTypeEnum;
import com.yl.tmp.my.nuang.common.enums.*;
import com.yl.tmp.my.nuang.common.enums.AddressTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 寄件下单记录表
 * </p>
 *
 * @author shaokui.chen
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SENDER_ORDER")
@KeySequence("SENDER_ORDER_SEQ")
@Accessors(chain = true)
public class SenderOrder extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableField("ID")
    private Long id;

    /**
     * 订单号
     */
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * JTS订单号
     */
    @TableField("JTS_ORDER_NO")
    private String jtsOrderNo;

    /**
     * 主账号(柜机 马来手机号/驿站 站点编码)
     */
    @TableField("BELONGER")
    private String belonger;

    /**
     * 运单号
     */
    @TableField("WAY_BILL_CODE")
    private String wayBillCode;

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
     * 目的地邮编
     */
    @TableField("RECEIVER_POSTCODE")
    private String receiverPostcode;
    /**
     * 寄件地邮编
     */
    @TableField("SENDER_POSTCODE")
    private String senderPostcode;
    /**
     * 寄件柜名称
     */
    @TableField("BOX_NAME")
    private String boxName;

    /**
     * 寄件柜设备号
     */
    @TableField("BOX_CODE")
    private String boxCode;

    /**
     * 保价金额 单位(san)
     */
    @TableField("DECLARATION_VALUE")
    private Long declarationValue;

    @TableField("OFFER_FEE_FLAG")
    @ApiModelProperty(value = "保价标识 1：保价")
    private OfferFeeFlagEnum offerFeeFlag;

    /**
     * 预估金额 单位(san)
     */
    @TableField("ORDER_AMOUNT")
    private Long orderAmount;

    /**
     * 订单状态 0-待投递 1-已完成 2-已取消(取消订单) 3-待打印
     * H5:  0-待投递 1-已完成 2-已取消(取消订单)
     * APP: 3-待打印 1-已完成 2-已取消(取消订单)
     */
    @TableField("ORDER_STATUS")
    private OrderStatusEnum orderStatus;

    /**
     * 支付状态 0-处理中 1-成功 2-失败
     */
    @TableField("PAYMENT_STATUS")
    private PaymentStatusEnum paymentStatus;

    /**
     * 寄件码
     */
    @TableField("SENDER_CODE")
    private String senderCode;

    /**
     * 结算类型 0-寄付 1-到付 2-月结
     */
    @TableField("SETTLEMENT_TYPE")
    private SettlementTypeEnum settlementType;

    /**
     * 计费重量(G)
     */
    @TableField("SETTLEMENT_WEIGHT")
    private Long settlementWeight;

    /**
     * 用户输入重量(G)
     */
    @TableField("USER_WEIGHT")
    private Long userWeight;

    /**
     * 结算金额 单位(san)
     */
    @TableField("SETTLEMENT_AMOUNT")
    private Long settlementAmount;

    /**
     * 物品类型(0.生活用品 1.文件 2.数码产品 3.食品 4.服饰 5.其他)
     */
    @TableField("GOODS_TYPE")
    private GoodsTypeEnum goodsType;

    /**
     * 物品宽（cm）
     */
    @TableField("GOODS_WIDTH")
    private Long goodsWidth;

    /**
     * 物品长（cm）
     */
    @TableField("GOODS_LENGTH")
    private Long goodsLength;

    /**
     * 物品高（cm）
     */
    @TableField("GOODS_HEIGHT")
    private Long goodsHeight;

    /**
     * 体积重量(KG)
     */
    @TableField("VOLUME_WEIGHT")
    private Long volumeWeight;

    /**
     * 称重重量(KG)
     */
    @TableField("WEIGHT")
    private Long weight;

    /**
     * 快递公司类型（0、J&T）
     */
    @TableField("COMPANY_TYPE")
    private Integer companyType;

    /**
     * 图片地址
     */
    @TableField("PHOTO")
    private String photo;

    /**
     * 打印面单的人id
     */
    @TableField("PRINT_BY")
    private String printBy;

    /**
     * 打印次数
     */
    @TableField("PRINT_NUMBER")
    private Integer printNumber;

    /**
     * 打印时间
     */
    @TableField("PRINT_TIME")
    private LocalDateTime printTime;

    /**
     * 面单打印状态0-已打印，1未打印
     */
    @TableField("PRINT_STATUS")
    private PrintStatusEnum printStatus;

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
     * 寄件人真实姓名
     */
    @TableField("SENDER_REAL_NAME")
    private String senderRealName;

    /**
     * 寄件人证件号
     */
    @TableField("SENDER_ID_CARD_NO")
    private String senderIdCardNo;

    /**
     * 寄件人寄件所在省/州的id
     */
    @TableField("SENDER_PROVINCE_ID")
    private String senderProvinceId;

    /**
     * 寄件人寄件所在区县的id
     */
    @TableField("SENDER_CITY_ID")
    private String senderCityId;

    /**
     * 寄件人收件所在镇/区id
     */
    @TableField("SENDER_TOWN_ID")
    private String senderTownId;

    /**
     * 寄件人寄件所在省/州
     */
    @TableField("SENDER_PROVINCE")
    private String senderProvince;

    /**
     * 寄件人寄件所在区县
     */
    @TableField("SENDER_CITY")
    private String senderCity;

    /**
     * 寄件人收件所在镇/区
     */
    @TableField("SENDER_TOWN")
    private String senderTown;

    /**
     * 寄件人所属区域
     */
    @TableField("SENDER_REGION")
    private String senderRegion;

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
     * 收件人收件所在省/州的id
     */
    @TableField("RECIPIENT_PROVINCE_ID")
    private String recipientProvinceId;

    /**
     * 收件人收件所在区县的id
     */
    @TableField("RECIPIENT_CITY_ID")
    private String recipientCityId;

    /**
     * 收件人收件所在镇/区id
     */
    @TableField("RECIPIENT_TOWN_ID")
    private String recipientTownId;

    /**
     * 收件人收件所在省/州
     */
    @TableField("RECIPIENT_PROVINCE")
    private String recipientProvince;

    /**
     * 收件人收件所在区县
     */
    @TableField("RECIPIENT_CITY")
    private String recipientCity;

    /**
     * 收件人收件所在镇/区
     */
    @TableField("RECIPIENT_TOWN")
    private String recipientTown;

    /**
     * 收件人所属区域
     */
    @TableField("RECIPIENT_REGION")
    private String recipientRegion;

    /**
     * 收件人详细地址
     */
    @TableField("RECIPIENT_DETAIL_ADDR")
    private String recipientDetailAddr;

    /**
     * 创建人id
     */
    @TableField("CREATE_BY_ID")
    private String createById;

    /**
     * 创建人name
     */
    @TableField("CREATE_BY_NAME")
    private String createByName;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    @TableField("UPDATE_BY_ID")
    private String updateById;

    /**
     * 更新人姓名
     */
    @TableField("UPDATE_BY_NAME")
    private String updateByName;

    /**
     * 更新时间
     * 不要自动添加时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    /**
     * 称重时间
     */
    @TableField("WEIGHT_TIME")
    private LocalDateTime weightTime;


    /**
     * 投柜时间
     */
    @TableField("CAST_TIME")
    private LocalDateTime castTime;


    /**
     * 取消时间
     */
    @TableField("CANCEL_TIME")
    private LocalDateTime cancelTime;

    /**
     * 种类
     */
    @TableField("KIND")
    private KindEnum kind;

    /**
     * 订单来源 0-H5，1-APP，2-JTC,3-VIP
     */
    @TableField("ORDER_SOURCE")
    private OrderSourceEnum source;

    /**
     * 是否JTS创建订单
     */
    @TableField("IS_JTS")
    private YesAndNoEnum isJts;

    /**
     * 物品价值
     */
    @TableField("GOODS_VALUE")
    private Long goodsValue;

    /**
     * 税金
     */
    @TableField("TAXATION")
    private Long taxation;

    /**
     * 快递类型 0 EZ（普通件）、1 EX（次日达）、2 SF（海运件）、3 JS（高端商务件）、4 INT（国际件）
     */
    @TableField("EXPRESS_TYPE")
    private ExpressTypeEnum expressType;

    /**
     * 订单完成时间
     */
    @TableField("COMPLETE_TIME")
    private LocalDateTime completeTime;

    /**
     * 六子码
     */
    @TableField("SEGMENT_CODE")
    private String segmentCode;

    /**
     * 物品数量
     */
    @TableField("QTY")
    private Integer qty;

    /**
     * 大客户编号
     */
    @TableField("VIP_CUSTOMER_CODE")
    private String vipCustomerCode;

    /**
     * 四字码（002）
     */
    @TableField("AREA_CODE")
    private String areaCode;

    /**
     * 地址类型 0-HOME,1-SCHOOL,2-OFFICE
     */
    @TableField("ADDRESS_TYPE")
    private AddressTypeEnum addressType;

    /**
     * 运费
     */
    @TableField("FREIGHT_CHARGE")
    private Long freightCharge;


    /**
     * 自寄类型 0-BOX,1-STATION
     */
    @TableField("SELF_SEND_TYPE")
    private SelfSendEnum selfSendType;

    /**
     * 是否收款 0-是，1-否
     */
    @TableField("IS_RECEIPT")
    private IsReceiptEnum isReceipt;

    /**
     * 预计到达时间
     */
    @TableField("EXPRESS_ETA")
    private String expressEta;

    /**
     * 国家编号60 66 86等
     */
    @TableField("COUNTRY_CODE")
    private CountryEnum countryCode;

    /**
     * 当前是否是预付款扣钱 0-是，1-否
     */
    @TableField("IS_WALLET")
    private YesAndNoEnum isWallet;

    /**
     * 是否经过柜机 0-是
     */
    @TableField("IS_BOX")
    private YesAndNoEnum isBox;

    /**
     * 是否经过站点 0-是
     */
    @TableField("IS_STATION")
    private YesAndNoEnum isStation;

    /**
     * 是否删除 0-未删除，1-已删除
     */
    @TableField("IS_DELETED")
    private IsDeletedTypeEnum isDeleted;

    /**
     * 运输类型A-B-C-D-E-F-G
     */
    @TableField("TRANSPORT_TYPE")
    @Deprecated
    private String transportType;

    /**
     * 佣金百分比
     */
    @TableField("COMMISSION_RATE")
    @Deprecated
    private String commissionRate;

    /**
     * 佣金金额（san）
     */
    @TableField("COMMISSION")
    @Deprecated
    private Long commission;

    /**
     * 关联海关表ID
     */
    @TableField("CUSTOMS_ID")
    private Long customsId;

    /**
     * 寄付状态下 付款方式 0-现金，1-JTC钱包
     */
    @TableField("PAYMENT_TYPE")
    private PaymentTypeEnum paymentType;

    /**
     * 发件扫描时间（回推）
     */
    @TableField("SEND_SCAN_TIME")
    private LocalDateTime sendScanTime;

    /**
     * 收件扫描时间
     */
    @TableField("RECEIVE_SCAN_TIME")
    private LocalDateTime receiveScanTime;

    /**
     * 当物品类型为其他时，物品描述
     */
    @TableField("GOOD_DESCRIPTION")
    private String goodDescription;

    /**
     * 运输目的 0-个人，1-企业
     */
    @TableField("TRANSPORT_PURPOSE")
    private TransportPurposeEnum transportPurpose;

    /**
     * 收件人邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 发货地编号
     */
    @TableField("SHIPMENT_NO")
    private String shipmentNo;

    /**
     * 目的地编号
     */
    @TableField("DESTINATION_NUMBER")
    private String destinationNumber;


    /**
     * 预付款扣款状态0-扣款成功，1-扣款失败
     */
    @TableField("DEDUCTION_STATUS")
    private SuccessAndFailEnum deductionStatus;

    /**
     * 是否签收 0-是 1-否
     */
    @TableField("IS_SIGN")
    private YesAndNoEnum isSign;

    /**
     * 收件人身份证/护照
     */
    @TableField("IDENTITY_CARD")
    private String identityCard;

    /**
     * 收款时间
     */
    @TableField("PAYMENT_TIME")
    private LocalDateTime paymentTime;

    /**
     * 推送jts收款状态  0，成功，1失败，2.未推送
     */
    @TableField("PUSH_PAYMENT_STATUS")
    private PushPaymentStatusEnum pushPaymentStatus;

    /**
     * 结算重量(g)
     */
    @TableField("RECEIVE_WEIGHT")
    private Long receiveWeight;

    /**
     * 运输条款  0-DDU 1-DDP
     */
    @TableField("TRANSIT_CLAUSE")
    private TransitClauseEnum transitClause;

    /**
     * 业务员(JT快递员code)
     */
    @TableField("SALESMAN_CODE")
    private String salesmanCode;

    /**
     * 签收人
     */
    @TableField("SIGN_PERSON")
    private String signPerson;
    /**
     * 签收时间
     */
    @TableField("SIGN_TIME")
    private LocalDateTime signTime;
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
     * 零头
     */
    @TableField("CHANGES")
    private Long changes;

    /**
     * 机场分拣码（国际件）
     */
    @TableField("SUB_SORTING_CODE")
    private String subSortingCode;

    /**
     * 当前是否收件扫描 0-是，1-否
     */
    @TableField("IS_RECEIVE_SCAN")
    private YesAndNoEnum isReceiveScan;

    /**
     * H5 是否收件，展示在列表 0-是 1-否
     */
    @TableField("IS_RECEIVE")
    private YesAndNoEnum isReceive;

    /**
     * 订单状态名称
     */
    @TableField("ORDER_STATUS_NAME")
    private String orderStatusName;

    /**
     * 修改订单时间
     */
    @TableField("MODIFY_ORDER_TIME")
    private LocalDateTime modifyOrderTime;

    /**
     * v1.1.0订单来源修改
     */
    @TableField("SOURCE_ORDER")
    private String sourceOrder;

    @TableField("VOID_BILL_CODE")
    private ScanVoidEnum voidBillCode;

    @TableField("COD_MONEY")
    private Long codMoney;

    @TableField("COD_MONEY_UNIT")
    private String codMoneyUnit;

    @TableField("COUPON_MONEY")
    private Long couponMoney;

    @TableField("APP_CUSTOMER_CODE")
    private String appCustomerCode;
}
