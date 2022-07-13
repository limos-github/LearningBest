package com.limo.lb.learn.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The type Local date utils.
 *
 * @description: 负责LocalDateTime 与  LocalDate相关的时间运算与时间获取
 * @author: Limo
 * @time: 2022 /6/26 17:13
 */
public class LocalDateUtils {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println(getNowTime(false));
        System.out.println(getMonthDay(getNowTime(false), null));
        System.out.println(getWeekDay(getNowTime(false), null));
        System.out.println(dateToTime(LocalDate.now()));
        System.out.println(getNowTime(false).minusDays(2));
        System.out.println(getNowTime(true));
        System.out.println();
        System.out.println(getDailyDifference(getNowTime(false).minusDays(2), getNowTime(true)));
    }

    /**
     * <P/> 获取当前时间
     * <P/> if true return 2022-06-26T00:00
     * <P/> if false return 2022-06-26T17:19:24.808
     */
    public static LocalDateTime getNowTime(boolean isZero) {
        LocalDateTime now = LocalDateTime.now();
        if (isZero) return now.withHour(0).withMinute(0).withSecond(0).withNano(0);
        return now;
    }

    /**
     * <P/> 获取月时间的某日 默认获取月首日
     * <P/> if (null == time) return null
     * <P/> if (null == day) return 2022-06-01T17:32:09.815
     * <P/> if day == 5 return 2022-06-05T17:32:09.815
     */
    public static LocalDateTime getMonthDay(LocalDateTime time, Integer day) {
        if (null == time) return null;
        if (null == day) day = 1;
        return time.withDayOfMonth(day);
    }

    /**
     * <P/> 获取时间的周几 默认获取时间的周一
     * <P/> if (null == time) return null
     * <P/> if (null == day) return 2022-06-27T09:23:29.242
     * <P/> if day == DayOfWeek.MONDAY return 2022-06-27T09:23:29.242
     */
    public static LocalDateTime getWeekDay(LocalDateTime time, DayOfWeek day) {
        if (null == time) return null;
        if (null == day) day = DayOfWeek.MONDAY;
        return time.with(day);
    }

    /**
     * <P/> LocalDate -> LocalDateTime
     * <P/> if (null == time) return null
     * <P/> if date == 2022-06-26 return 2022-06-26T00:00
     */
    public static LocalDateTime dateToTime(LocalDate date) {
        if (null == date) return null;
        return date.atTime(0, 0, 0, 0);
    }

    /**
     * <P/> 获取两个时间的天数差
     * <P/> if (null == time) return null
     * <P/> 2022-06-24T18:30:12.888 - 2022-06-26T00:00 -> 2
     */
    public static Integer getDailyDifference(LocalDateTime time1, LocalDateTime time2) {
        if (null == time1 || null == time2) return null;
        if (time1.isBefore(time2)) return time2.compareTo(time1);
        if (time1.isAfter(time2)) return time1.compareTo(time2);
        return time1.compareTo(time2);
    }
}