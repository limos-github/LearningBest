package com.limo.lb.apply.rules;

import com.limo.lb.apply.rules.enums.ObjectTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Area extends AbstractChain {
    /**
     * Instantiates a new Area.
     */
    public Area() {
        this.specifyType = ObjectTypeEnum.AREA;
    }

    @Override
    protected void rule(RulesChainBO bo, RulesChainVO vo) {
        super.rule(bo, vo);
    }
}