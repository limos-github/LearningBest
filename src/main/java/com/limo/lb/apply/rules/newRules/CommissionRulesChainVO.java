package com.limo.lb.apply.rules.newRules;

import com.limo.lb.apply.rules.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class CommissionRulesChainVO implements Serializable {

    private static final long serialVersionUID = 7437225941564995547L;
    private Long id;
    private Long snapshotVersion;
    private String ruleName;
    private ObjectTypeEnum specifyType;
    private String appointCode;
    private String customName;
    private CustomTypeEnum customType;
    private YesAndNoEnum guaranteed;
    private LocalDateTime effectiveTime;
    private LocalDateTime expiredTime;
    private SiteCommissionTypeEnum siteCommissionType;
    private Long priority;
    private BusinessTypeEnum businessType;
    private JudgeEnum judge;
    private String configItem;
    private Long configTransportTypeId;
    private EffectiveTypeEnum effectiveType;
    private CommissionTypeEnum commissionType;
    private String commissionTypeDetails;
    private IsDeletedTypeEnum isDeleted;


    private String specifyCode;
    private String transportType;
    private String transportName;

}