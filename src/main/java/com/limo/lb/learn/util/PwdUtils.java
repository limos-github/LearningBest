package com.limo.lb.learn.util;

import org.apache.tomcat.util.security.MD5Encoder;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: Limo
 * @time: 2023-09-23 17:41
 */
public class PwdUtils {
    public static void main(String[] args) {
        System.out.println(getPwd("944701330", 6));
    }

    public static Integer getPwd(String account, Integer length) {
        byte[] bytes = account.getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(bytes));
        int i = MD5Encoder.encode(bytes).hashCode();
        System.out.println(i);
        return Integer.valueOf(i + length.toString().substring(length));
    }
}