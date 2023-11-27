package com.limo.lb.apply.rules.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.limo.lb.apply.rules.enums.CustomerTypeEnum;
import com.limo.lb.apply.rules.enums.EffectiveTypeEnum;
import com.limo.lb.apply.rules.enums.IsDeletedTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 配置-指定大客户编号
 * </p>
 *
 * @author zyy
 * @since 2022-08-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("CONFIG_CUSTOMER_CODE")
@KeySequence("CONFIG_CUSTOMER_CODE_SEQ")
public class ConfigCustomerCode implements Serializable {

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
     * effective expired pending 0- 生效 1- 失效 2- 待生效
     */
    @TableField("EFFECTIVE_TYPE")
    private EffectiveTypeEnum effectiveType;

    /**
     * 客户类型 默认0-指定大客户
     */
    @TableField("CUSTOMER_TYPE")
    private CustomerTypeEnum customerType;

    /**
     * 客户编号
     */
    @TableField("CUSTOMER_CODE")
    private String customerCode;

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
