package com.limo.lb.apply.rules;

import com.limo.lb.apply.rules.entity.ScanRecord;
import com.limo.lb.apply.rules.enums.CommCalculateTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author limo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RulesChainDTO implements Serializable {
    private static final long serialVersionUID = 1749710162671064846L;
    private String wayBillCode;
    private CommCalculateTypeEnum type;
    private LocalDateTime time;
    private ScanRecord scanRecord;
}