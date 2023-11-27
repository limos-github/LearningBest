package com.limo.lb.apply.rules.newRules;

import com.limo.lb.apply.rules.enums.TierTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 返佣规则 对象范围关联表
 * </p>
 *
 * @author shaokui.chen
 * @since 2021-11-30
 */
@Data
public class WeightRuleJson implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 等级1-T1,2-T2,3-T3,4-T4,5-T5
     */
    private TierTypeEnum tier;
    /**
     * 重量起始段
     */
    private String weightRangeStart;
    /**
     * 重量结束段
     */
    private String weightRangeEnd;
    /**
     * 佣金RM(固定收益/重量段展示最小单位san，百分比展示比重 )
     */
    private String commission;

}
