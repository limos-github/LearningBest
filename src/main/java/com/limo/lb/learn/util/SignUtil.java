package com.limo.lb.learn.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.*;

/**
 * @description:
 * @author: Limo
 * @time: 2023-07-21 14:33
 */
@Slf4j
public class SignUtil {

    public static void main(String[] args) {
        String param = "{\"auditState\":\"AUDIT\",\"auditTime\":\"2023-07-20T15:59:00\",\"checkInTime\":\"2023-07-21T14:40:05\",\"createTime\":\"2023-07-20T15:59:00\",\"id\":751,\"isDeleted\":\"NOT_DELETE\",\"scanVoidType\":\"WAYBILL_VOID\",\"stationCode\":\"MY080011\",\"wayBillCode\":\"650001430097\"}";
        String key = "2356534444";
        String ts = String.valueOf(System.currentTimeMillis());
        System.out.println(sign(key, ts, param));
    }

    public static String sign(String key, String ts, String param) {
        log.info("1.sign 入参 -> 秘钥: {} | 时间戳: {}  -> {}", key, ts, param);
        String json;
        if (null != param && "" != param) {
            //json排序
            Map<String, Object> map = jsonToMap(param);
            json = JSONObject.toJSONString(map);
        } else {
            json = "{}";
        }
        log.info("2.sign 排序 -> 秘钥: {} | 时间戳: {}  -> {}", key, ts, json);
        StringBuilder sb = new StringBuilder(json + "&");
        //拼接 ts和 //替换所有的双引号
        sb.append("ts=").append(ts).append("&signKey=").append(key);
        log.info("3.sign 拼接 -> 秘钥: {} | 时间戳: {}  -> {}", key, ts, sb);
        //替换所有的双引号
        String md5_str = sb.toString().replaceAll("\\\"", "");
        log.info("4.sign 替换 -> 秘钥: {} | 时间戳: {}  -> {}", key, ts, md5_str);
        String server_sign = MD5NOSALT(md5_str).toUpperCase();
        log.info("5.sign 加签 -> 秘钥: {} | 时间戳: {}  -> {}", key, ts, server_sign);
        return server_sign;
    }

    public static Map<String, Object> jsonToMap(String jsonStr) {
        Map<String, Object> treeMap = new TreeMap();
        //
        JSONObject json = JSONObject.parseObject(jsonStr, Feature.OrderedField);
        Iterator<String> keys = json.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = json.get(key);
            //判断传入kay-value中是否含有&#34;&#34; 或null
            if (json.get(key) == null || value == null) {
                //当JSON字符串存在null时,不将该kay-value放入Map中,即显示的结果不包括该kay-value
                continue;
            }
            // 判断是否为JSONArray(json数组)
            if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                List<Object> arrayList = new ArrayList<>();
                for (Object object : jsonArray) {
                    // 判断是否为JSONObject&#xff0c;如果是 转化成TreeMap
                    if (object instanceof JSONObject) {
                        object = jsonToMap(object.toString());
                    }
                    arrayList.add(object);
                }
                treeMap.put(key, arrayList);
            } else {
                //判断该JSON中是否嵌套JSON
                boolean flag = isJSONValid(value.toString());
                if (flag) {
                    //若嵌套json了,通过递归再对嵌套的json(即子json)进行排序
                    value = jsonToMap(value.toString());
                }
                // 其他基础类型直接放入treeMap
                // JSONObject可进行再次解析转换
                treeMap.put(key, value);
            }
        }
        return treeMap;
    }

    /**
     * 校验是否是json字符串
     *
     * @param json
     * @return boolean
     * @author wangxiangbing
     * @date 2021/9/1
     */
    public final static boolean isJSONValid(String json) {
        try {
            if (null == JSONObject.parseObject(json)) {
                return false;
            }
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    public static String MD5NOSALT(String s) {
        if (null == s || "" == s) {
            return "";
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = (s).getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}