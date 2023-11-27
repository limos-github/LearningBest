package com.limo.lb.apply.rules;

import com.limo.lb.apply.rules.newRules.CommissionRulesChainVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type Rules chain vo.
 *
 * @author limo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RulesChainVO implements Serializable {
    private static final long serialVersionUID = 5674719001724022710L;
    private CommissionRulesChainVO iRules;
    private CommissionRulesChainVO upRules;

}