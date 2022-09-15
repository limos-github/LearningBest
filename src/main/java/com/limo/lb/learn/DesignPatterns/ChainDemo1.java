package com.limo.lb.learn.DesignPatterns;


import com.limo.lb.learn.enums.ObjectTypeEnum;

/**
 * The type Chain demo.
 *
 * @description:
 * @author: Limo
 * @time: 2022 /8/17 10:50
 */
public class ChainDemo1 {
    public static AbstractChain getSpecifyType() {
        AbstractChain station = new Station(ObjectTypeEnum.STATION);
        AbstractChain area = new Area(ObjectTypeEnum.AREA);
        AbstractChain nation = new Nation(ObjectTypeEnum.NATION);
        station.setNextChain(area);
        area.setNextChain(nation);
        return station;
    }

    public static void main(String[] args) {
        AbstractChain type = getSpecifyType();
//        type.logMessage(ObjectTypeEnum.STATION,"站点处理");
//        type.logMessage(ObjectTypeEnum.AREA,"区域处理");
        type.getRules(ObjectTypeEnum.NATION, "全国");
    }
}

/**
 * The type Abstract chain.
 */
abstract class AbstractChain {
    protected ObjectTypeEnum specifyType;
    //责任链中的下一个元素
    protected AbstractChain nextChain;

    public void setNextChain(AbstractChain nextChain) {
        this.nextChain = nextChain;
    }

    public CommissionRules getRules(ObjectTypeEnum specifyType, String message) {
        //station -> area -> nation
        if (this.specifyType.getCode() >= (specifyType.getCode())) {
            CommissionRules rule = rule(specifyType, message);
            if (null!= rule) {
                return rule;
            } else {
                nextChain.getRules(specifyType, message);
            }
        }
        return null;
    }

    /**
     * Rule.
     *
     * @param message the message
     */
    abstract protected CommissionRules rule(ObjectTypeEnum specifyType, String message);
}

/**
 * The type Site.
 */
class Station extends AbstractChain {
    public Station(ObjectTypeEnum specifyType) {
        this.specifyType = specifyType;
    }

    /**
     * Rule.
     *
     * @param message the message
     */
    @Override
    protected CommissionRules rule(ObjectTypeEnum specifyType, String message) {
        if (this.specifyType.equals(specifyType)) {
            System.out.println("Station" + message);
            return null;
        } else {
            System.out.println(this.specifyType.name() + " 处理不来");
            return null;
        }
    }
}

/**
 * The type Area.
 */
class Area extends AbstractChain {
    public Area(ObjectTypeEnum specifyType) {
        this.specifyType = specifyType;
    }

    /**
     * Rule.
     *
     * @param message the message
     */
    @Override
    protected CommissionRules rule(ObjectTypeEnum specifyType, String message) {
        if (this.specifyType.equals(specifyType)) {
            System.out.println("Area" + message);
            return null;
        } else {
            System.out.println(this.specifyType.name() + " 处理不来");
            return null;
        }
    }
}

/**
 * The type Nation.
 */
class Nation extends AbstractChain {
    public Nation(ObjectTypeEnum specifyType) {
        this.specifyType = specifyType;
    }

    /**
     * Rule.
     *
     * @param message the message
     */
    @Override
    protected CommissionRules rule(ObjectTypeEnum specifyType, String message) {
        if (this.specifyType.equals(specifyType)) {
            System.out.println("Nation" + message);
            return null;
        } else {
            System.out.println(this.specifyType.name() + " 处理不来");
            return null;
        }
    }
}
class CommissionRules {

}