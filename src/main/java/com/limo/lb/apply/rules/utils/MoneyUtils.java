package com.limo.lb.apply.rules.utils;

import com.limo.lb.apply.rules.content.BaseConstant;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author wangxiangbing
 * @description 货币转换工具类 令吉(RM):仙(SEN) 1：100
 * @date 2021/7/1
 */
public class MoneyUtils {


    /**
     * 令吉(RM)转仙(SEN)
     *
     * @param rmAmount
     * @return java.math.BigDecimal
     * @author yangxy
     * @date 2021/11/25
     */
    public static long rmToSen(BigDecimal rmAmount) {
        rmAmount = null == rmAmount ? BigDecimal.ZERO : rmAmount;
        return rmAmount.multiply(new BigDecimal(BaseConstant.ONE_HUNDRED)).longValue();
    }

    /**
     * 仙(SEN)转令吉(RM)
     *
     * @param senAmount
     * @return java.math.BigDecimal
     * @author yangxy
     * @date 2021/11/25
     */
    public static BigDecimal senToRm(BigDecimal senAmount) {
        senAmount = null == senAmount ? BigDecimal.ZERO : senAmount;
        return senAmount.divide(new BigDecimal(BaseConstant.ONE_HUNDRED), BaseConstant.TWO, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 令吉(RM)转仙(SEN)
     *
     * @param rmAmount
     * @return java.math.BigDecimal
     * @author yangxy
     * @date 2021/11/25
     */
    public static long rmToSen(Long rmAmount) {
        return rmToSen(BigDecimal.valueOf(rmAmount));
    }

    /**
     * 仙(SEN)转令吉(RM)保留两位,四舍五入
     *
     * @param senAmount
     * @Author yangxy
     * @Return java.math.BigDecimal
     **/
    public static BigDecimal senToRm(Long senAmount) {
        return null == senAmount ? new BigDecimal(0) : senToRm(BigDecimal.valueOf(senAmount));
    }

    /**
     * 仙(SEN)转令吉(RM)保留两位，超过两位舍掉
     *
     * @param senAmount
     * @Author xuyangyang
     * @Return java.math.BigDecimal
     **/
    public static BigDecimal senToRm2(Long senAmount) {
        return null == senAmount ? new BigDecimal(0) : BigDecimal.valueOf(senAmount).divide(new BigDecimal(BaseConstant.ONE_HUNDRED), BaseConstant.TWO, BigDecimal.ROUND_DOWN);
    }

    /**
     * 令吉(RM)转仙(SEN)
     *
     * @param rmAmount
     * @Author yangxy
     * @Return java.math.BigDecimal
     **/
    public static Long rmToSen(String rmAmount) {
        return EmptyUtil.isEmpty(rmAmount) ? null : rmToSen(new BigDecimal(rmAmount));
    }

    /**
     * 仙(SEN)转令吉(RM)
     *
     * @param senAmount
     * @return java.math.BigDecimal
     * @author wangxiangbing
     * @date 2021/7/1
     */
    public static BigDecimal senToRm(String senAmount) {
        return senToRm(new BigDecimal(senAmount));
    }

    /**
     * 比较金额大小 大于等于返回true 小于返回false
     */
    public static boolean geAndEqual(BigDecimal val1, BigDecimal val2) {
        val1 = null == val1 ? BigDecimal.ZERO : val1;
        val2 = null == val1 ? BigDecimal.ZERO : val2;
        // 提现金额 > 可用余额 - 管控金额
        return val1.compareTo(val2) > 0;
    }

    /**
     * 百分比获取金额
     *
     * @param minAmountUnit 金额最小单位
     * @param minPercent    百分比 * 10000  百分比12%  数据库
     * @return
     */
    public static Long againAmountByPercent(Long minAmountUnit, Long minPercent) {
        if (EmptyUtil.isEmpty(minAmountUnit) || EmptyUtil.isEmpty(minPercent)) {
            return Long.valueOf(BaseConstant.ZERO);
        }
        BigDecimal minTotal = new BigDecimal(minAmountUnit).multiply(new BigDecimal(minPercent));
        //单位转换 除一万
        BigDecimal percentConvert = new BigDecimal(BaseConstant.ONE_HUNDRED).multiply(new BigDecimal(BaseConstant.ONE_HUNDRED));
        // 获取运费信息
        BigDecimal bigDecimal = minTotal.divide(percentConvert).setScale(BaseConstant.ZERO, RoundingMode.HALF_UP);
        // 提现金额 > 可用余额 - 管控金额
        return bigDecimal.longValue();
    }

}
