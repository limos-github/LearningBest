package com.limo.lb.apply.rules;

import com.limo.lb.apply.rules.entity.ConfigTransportType;
import com.limo.lb.apply.rules.enums.*;
import com.limo.lb.apply.rules.newRules.CommissionRulesChainVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author limo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RulesChainBO implements Serializable {
    private static final long serialVersionUID = 4280499139279708902L;
    private SiteTypeEnum siteType;
    private LocalDateTime receiveTime;
    private Map<ObjectTypeEnum, List<CommissionRulesChainVO>> specifyType;
    private Map<String, ConfigTransportType> transportTypeMap;
    private Map<String, String> specifyCodes;
    private String customerCodes;
    private CommCalculateTypeEnum type;
    /**
     * 参数类型
     */
    private String wayBillCode;
    private String vipCustomerCode;
    private KindEnum kind;
    private ExpressTypeEnum expressType;
    private String productType;

    private String dropPointDestination;
    private String pickUpDP;
}