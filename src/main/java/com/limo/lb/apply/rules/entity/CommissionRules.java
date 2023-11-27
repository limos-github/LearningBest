package com.limo.lb.apply.rules.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.limo.lb.apply.rules.enums.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 佣金规则
 * </p>
 *
 * @author zyy
 * @since 2022-08-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("COMMISSION_RULES")
@KeySequence("COMMISSION_RULES_SEQ")
public class CommissionRules implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
    private Long id;

    /**
     * 快照
     */
    @TableField("SNAPSHOT_VERSION")
    private Long snapshotVersion;

    /**
     * 规则名称
     */
    @TableField("RULE_NAME")
    private String ruleName;

    /**
     * 区域类型 0- 全国 1- 区域 2- 站点
     */
    @TableField("SPECIFY_TYPE")
    private ObjectTypeEnum specifyType;

    /**
     * 指定编码  区域为区域编码 站点为站点编码
     */
    @TableField("APPOINT_CODE")
    private String appointCode;

    /**
     * 自定义名称
     */
    @TableField("CUSTOM_NAME")
    private String customName;

    /**
     * 自定义阶段类型  0-永久 1-阶段
     */
    @TableField("CUSTOM_TYPE")
    private CustomTypeEnum customType;

    /**
     * 保底 0- 是 1- 否
     */
    @TableField("GUARANTEED")
    private YesAndNoEnum guaranteed;

    /**
     * 生效时间
     */
    @TableField("EFFECTIVE_TIME")
    private LocalDateTime effectiveTime;

    /**
     * 过期时间
     */
    @TableField("EXPIRED_TIME")
    private LocalDateTime expiredTime;

    /**
     * 站点佣金类型 0-CP,1-PCP,2-SCP,3-PCP-PCP,4-PCP-SCP
     */
    @TableField("SITE_COMMISSION_TYPE")
    private SiteCommissionTypeEnum siteCommissionType;

    /**
     * 优先级 数字越小优先级越高 0是保底
     */
    @TableField("PRIORITY")
    private Long priority;

    /**
     * 业务类型
     */
    @TableField("BUSINESS_TYPE")
    private BusinessTypeEnum businessType;

    /**
     * 判断方式
     */
    @TableField("JUDGE")
    private JudgeEnum judge;

    /**
     * 配置项
     */
    @TableField("CONFIG_ITEM")
    private String configItem;

    /**
     * 配置运输类型ID
     */
    @TableField("CONFIG_TRANSPORT_TYPE_ID")
    private Long configTransportTypeId;

    /**
     * 0- 生效 1- 失效 2- 待生效
     */
    @TableField("EFFECTIVE_TYPE")
    private EffectiveTypeEnum effectiveType;

    /**
     * 返佣类型0-百分比，1-固定收益，2-重量段
     */
    @TableField("COMMISSION_TYPE")
    private CommissionTypeEnum commissionType;

    /**
     * 返佣类型规则明细 百分比 固定收益 重量段JSON
     */
    @TableField("COMMISSION_TYPE_DETAILS")
    private String commissionTypeDetails;

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
    @TableField("CREATE_TIME")
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
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 是否删除 0-未删除，1-已删除
     */
    @TableField("IS_DELETED")
    private IsDeletedTypeEnum isDeleted;


}
