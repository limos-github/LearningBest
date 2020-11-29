package com.limo.lb.learn.homework;

import java.util.Scanner;

public class JavaHomeWork_4 {
    public static void main(String[] args) {
        /**
         * 1. 编写一个 Java 源程序，在程序中建立一个含 10 个整型（int）元素的一维数组，对数组
         * 中的每个元素赋值，然后按下标的逆序输出。
         *
         * */
        doScanner();


        /**
         * 2. 有 1、2、3 个数字，能组成多少个互不相同且无重复数字的两位数。编写程序输出所有符
         * 合条件的两位数。
         *
         * */
        doExhaustion();

        /**
         * 3. 编写程序实现能：四位的整数进行加密，加密规则：每位数字都乘以 2,然后用乘积除以
         * 10 的余数代替该数字，再将第一位和第三位交换，第二位和第四位交换。
         *
         * */
        doEncry();
    }

    private static void doEncry() {
        Scanner s = new Scanner(System.in);
        int num = 0, temp;
        do {
            System.out.print("请输入一个4位正整数：");
            num = s.nextInt();
        } while (num < 1000 || num > 9999);
        int a[] = new int[4];
        a[0] = num / 1000;
        a[1] = (num / 100) % 10;
        a[2] = (num / 10) % 10;
        a[3] = num % 10;
        for (int j = 0; j < 4; j++) {
            a[j] *= 2;
            a[j] %= 10;
        }
        for (int j = 0; j <= 1; j++) {
            temp = a[j];
            a[j] = a[2 + j];
            a[2 + j] = temp;
        }
        System.out.print("加密后的数字为：");
        for (int j = 0; j < 4; j++) {
            System.out.print(a[j]);
        }
    }


    private static void doExhaustion() {
        int count = 0;
        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (x != y) {
                    count++;
                    System.out.println(x * 10 + y);
                }
            }
        }
        System.out.println("共有" + count + "个两位数");
    }

    private static void doScanner() {
        int[] x = new int[10];
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            x[i] = s.nextInt();
        }
        for (int i = x.length - 1; i >= 0; i--) {
            System.out.println(x[i]);
        }
    }
}
