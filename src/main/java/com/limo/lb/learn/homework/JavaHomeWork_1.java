package com.limo.lb.learn.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaHomeWork_1 {
    public static void main(String[] args) {
        //1.编写一个打印出“The Java World”的Java Application程序，并编译运行。
        doPrint();
        //2.设n为自然数:n!=1×2×3×…×n,称为n的阶乘，并且规定О!=1，试编程计算2!、4!、6!和10!，并将结果输出到屏幕。
        doFactorial();
        //3.使用java.lang.Math类，生成100个0-99之间的随机整数，找出它们之中的最大者和最小者，并统计大于50的整数个数。
        doRandom();
        //4.输入一个数组，对该数组从小到大排序。如输入”1,65,7,3,8,9,4,3,9,5,84”
        doSorted();
        //5.接收从键盘上输入的两个整数，求两个数的最大公约数和最小公倍数，并输出。
        doEuclidean();
    }

    private static void doEuclidean() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入第一个数：");
        int a = in.nextInt();
        System.out.println("请输入第二个数：");
        int b = in.nextInt();
        int t, r, n;
        if (a < b) {
            t = b;
            b = a;
            a = t;
        }
        r = a % b;
        n = a * b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        System.out.println("最大公约数：" + b + ";最小公倍数:" + n / b);
    }

    private static void doSorted() {
        System.out.println("排序后为： " + Stream.of(1, 65, 7, 3, 8, 9, 4, 3, 9, 5, 84)
                .sorted(Integer::compareTo).collect(Collectors.toList()).toString());
    }

    private static void doRandom() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) list.add((int) (Math.random() * 100));
        System.out.println("Max数为： " + list.stream().max(Integer::compareTo).get());
        System.out.println("Min数为： " + list.stream().min(Integer::compareTo).get());
        System.out.println("大于50的整数个数为： " + list.stream().filter(x -> x > 50).count());
    }

    private static void doFactorial() {
        int[] factorial = {0, 2, 3, 6, 10};
        for (int num : factorial) {
            int count = 1;
            for (int i = 1; i <= num; i++) {
                count = count * i;
            }
            System.out.println(num + "! = " + count);
        }
    }

    private static void doPrint() {
        System.out.println("The Java world");
    }

}
