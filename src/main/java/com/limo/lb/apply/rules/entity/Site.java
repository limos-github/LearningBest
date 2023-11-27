package com.limo.lb.apply.rules.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.limo.lb.apply.rules.enums.IsDeletedTypeEnum;
import com.limo.lb.apply.rules.enums.SiteTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author shaokui.chen
 * @since 2021-09-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("SITE")
@KeySequence("SITE_SEQ")
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableField("ID")
    private Long id;

    /**
     * 站点编码(唯一)
     */
    @TableField("STATION_CODE")
    private String stationCode;

    /**
     * 站点名称
     */
    @TableField("STATION_NAME")
    private String stationName;

    /**
     * 负责人
     */
    @TableField("PRINCIPAL")
    private String principal;

    /**
     * 联系电话
     */
    @TableField("PRINCIPAL_MOBILE")
    private String principalMobile;

    /**
     * 邮编
     */
    @TableField("POST_CODE")
    private String postCode;

    /**
     * 城市
     */
    @TableField("LOCATION")
    private String location;

    /**
     * 省/州
     */
    @TableField("PROVINCE_ID")
    private String provinceId;

    /**
     * 县
     */
    @TableField("CITY_ID")
    private String cityId;

    /**
     * 区
     */
    @TableField("TOWN_ID")
    private String townId;

    /**
     * 省/州
     */
    @TableField("PROVINCE")
    private String province;

    /**
     * 县
     */
    @TableField("CITY")
    private String city;

    /**
     * 区
     */
    @TableField("TOWN")
    private String town;

    /**
     * 详细地址
     */
    @TableField("DETAIL_ADDR")
    private String detailAddr;

    /**
     * 经度
     */
    @TableField("LONGITUDE")
    private String longitude;

    /**
     * 纬度
     */
    @TableField("LATITUDE")
    private String latitude;
    /**
     * 营业时间
     */
    @TableField("BUSINESS_TIME")
    private String businessTime;

    /**
     * 负责人证件照
     */
    @TableField("PRINCIPAL_PHOTO_PATH")
    private String principalPhotoPath;

    /**
     * 营业执照
     */
    @TableField("BUSINESS_LICENSE")
    private String businessLicense;

    /**
     * 站点外景
     */
    @TableField("SITE_PHOTO_PATH")
    private String sitePhotoPath;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

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
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除 0-未删除，1-已删除
     */
    @TableField("IS_DELETED")
    private IsDeletedTypeEnum isDeleted;

    /**
     * 站长ID
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 站点类型 CP/PCP 0-CP,1-PCP，2-SCP
     */
    @TableField("SITE_TYPE")
    private SiteTypeEnum siteType;

    /**
     * 我的邀请码
     */
    @TableField("MY_INVITE_CODE")
    private String myInviteCode;

    /**
     * 我填写的邀请码
     */
    @TableField("INPUT_INVITE")
    private String inputInvite;

    /**
     * 审核提交时间
     */
    @TableField("AUDIT_SUBMIT_TIME")
    private LocalDateTime auditSubmitTime;

    /**
     * 审核时间
     */
    @TableField("AUDIT_TIME")
    private LocalDateTime auditTime;


    /**
     * 所属区域CODE
     */
    @TableField("AREA_CODE")
    private String areaCode;

    /**
     * 所属区域名称
     */
    @TableField("AREA_NAME")
    private String areaName;

    /**
     * 营业时间段
     */
    @TableField("BUSINESS_PERIOD_TIME")
    private String businessPeriodTime;

    /**
     * 寄件网点
     */
    @TableField("MAILING_POINT")
    private String mailingPoint;


    /**
     * 生效中的预扣税比例
     */
    @TableField("EFFECTIVE_WITHHOLDING_TAX")
    private Long effectiveWithholdingTax;


    /**
     * 未生效的预扣税比例
     */
    @TableField("INVALID_WITHHOLDING_TAX")
    private Long invalidWithholdingTax;


    /**
     * 账单计算时的预扣税比例
     */
    @TableField("BILL_WITHHOLDING_TAX")
    private Long billWithholdingTax;

    /**
     * 网点编号
     */
    @TableField("NET_CODE")
    private String netCode;


    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;
}
