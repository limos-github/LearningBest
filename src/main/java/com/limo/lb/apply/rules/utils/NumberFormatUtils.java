package com.limo.lb.apply.rules.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @author wangxiangbing
 * @version 1.0
 * @description:
 * @date 2021/8/16 15:43
 */
public class NumberFormatUtils {

    public static final Integer ONE_HUNDRED = 100;

    public static final Integer TWO = 2;

    public static final Long ZERO = 0L;

    public static final String PERCENT = "%";

    /**
     * 货币最小单位转换成其他单位  默认保留两位
     *
     * @param molecular   分子
     * @param denominator 分母
     * @param precision   保留精度
     * @return
     */
    public static BigDecimal numberDivideFormat(Long molecular, Integer denominator, Integer precision) {
        molecular = (null == molecular ? ZERO : molecular);
        denominator = (null == denominator || denominator.equals(0)) ? ONE_HUNDRED : denominator;
        precision = (null == precision || precision.equals(0)) ? TWO : precision;
        return BigDecimal.valueOf(molecular).divide(new BigDecimal(denominator), precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 大单位转换成小单位 按照比列转换
     *
     * @param bigUnit    乘数
     * @param proportion 倍数
     * @return
     */
    public static Long numberMultiplyFormat(BigDecimal bigUnit, Integer proportion) {
        if (null == bigUnit) {
            return ZERO;
        }
        proportion = (null == proportion) ? ONE_HUNDRED : proportion;
        BigDecimal multiply = bigUnit.multiply(new BigDecimal(proportion));
        return multiply.longValue();
    }

    /**
     * 计算百分比 保留留n位小数
     *
     * @param divisor
     * @param dividend
     * @param bit
     * @return
     */
    public static String proportionDouble(Float divisor, Float dividend, Integer bit) {
        if (dividend == null || divisor == null) {
            return null;
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        bit = null == bit ? TWO : bit;
        numberFormat.setMaximumFractionDigits(bit);
        String result = numberFormat.format(divisor / dividend * 100);
        return result + PERCENT;
    }


    public static void main(String[] args) {
        System.out.println(numberDivideFormat(100L, 3, 2));
    }
}
