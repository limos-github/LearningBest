package com.limo.lb.apply.rules.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author yangxinyang
 * @version 1.0
 * @description: 判空工具类
 * @date 2021/8/16 15:04
 */
public class EmptyUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断集合对象是否为NULL
     *
     * @param collection 需要验证的集合对象
     * @return true|false 是或否
     */
    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 判断map是否为空
     **/
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    /**
     * 判断对象是否为NULL
     * string
     * List
     * Map
     * 其它直接判空是否为null
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return isEmpty((String) obj);
        } else if (obj instanceof Collection) {
            return isEmpty((Collection) obj);
        } else if (obj instanceof Map) {
            return isEmpty((Map) obj);
        }
        return false;

    }

    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length < 1;
    }


    /**
     * 判断字符串是否不为空
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 判断对象是否不为NULL
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map是否不为空
     **/
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断对象是否不为NULL
     **/
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Object[] obj) {
        return !isEmpty(obj);
    }

    /**
     * 字符串数据判空  存在任何一个为空则返回true
     * 比如 EmptyUtil.isAmyEmpty("1",2,null); 则返回true
     * 比如 EmptyUtil.isAmyEmpty("1",2,3L); 则返回false
     **/
    public static boolean isAnyEmpty(Object... strArr) {
        if (null != strArr) {
            for (Object str : strArr) {
                if (isEmpty(str)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * 字符串数据判空  全部都为空则返回true
     * 比如 EmptyUtil.isAllEmpty("1",2,null); 则返回false
     * 比如 EmptyUtil.isAllEmpty("",null); 则返回true
     **/
    public static boolean isAllEmpty(Object... strArr) {
        if (null != strArr) {
            for (Object str : strArr) {
                if (isEmpty(str)) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }


}
