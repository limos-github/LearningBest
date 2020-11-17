package com.limo.lb.learn.homework;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class JavaHomeWork_3 {
    public static void main(String[] args) {
        /**
         * 1. 统计从键盘输入字符的个数，当输入字符“#”时中止输入。
         * */
//        doScanner();
        /**
         * 2. 声明一个类 People，成员变量有姓名、出生日期、性别、身高、体重等；生成 10 个 People
         * （可使用 for 循环），并放在一维数组中，编写方法按身高进行排序。
         * */
        doSorted();
        /**
         * 3. 设计一个圆类，能计算圆的面积。Circle 类满足以下要求:
         * (1) 属性有：
         * cPoint_x , cPoint_y 代表圆心坐标
         * cRadius: double 型,代表圆的半径。
         * cPI: double 型,代表圆的圆周率。
         * cArea: double 型,代表圆的面积。
         * (2) 方法有：
         * Circle ( ) : 构造函数，将圆的圆心置坐标原点,半径设为 1。
         * Circle ( int x , int y, double r) : 构造函数，
         * double cAreaCount() : 计算当前圆类对象的面积并赋值给 cArea
         * String toString( ) : 把当前圆类对象的圆心坐标、半径以及面积组合成“圆心为（x, y）半径为 r 的圆
         * 的面积为 cA”字符串形式
         * */
        doCalculate();
    }

    private static void doCalculate() {
        System.out.println("计算圆的参数开始");
        System.out.println("请输入坐标轴x...");
        int x = new Scanner(System.in).nextInt();
        System.out.println("请输入坐标轴y...");
        int y = new Scanner(System.in).nextInt();
        System.out.println("请输入圆的半径...");
        double r = new Scanner(System.in).nextDouble();
        Circle c = new Circle(x, y, r);
        System.out.println(c);
        System.out.println("计算圆的参数结束");
    }

    private static void doSorted() {
        System.out.println("根据身高排序开始");
        People[] list = new People[10];
        for (int i = 0; i < 10; i++) {
            list[i] = new People(new Random().nextInt(10) + "号",
                    new Birth(new Random().nextInt(11) + 1, new Random().nextInt(30) + 1, 1990 + new Random().nextInt(20)),
                    new Random().nextBoolean() ? Sex.MAN : Sex.WOMAN,
                    new Random().nextFloat() * 200,
                    new Random().nextFloat() * 200);
        }
        Arrays.stream(list).sorted(Comparator.comparing(People::getHeight)).forEach(l -> System.out.println(l.toString()));
        System.out.println("根据身高排序结束");
    }

    private static void doScanner() {
        System.out.println("程序执行开始");
        String next = "";
        while (!"#".equals(next)) {
            next = new Scanner(System.in).next();
            System.out.println("当前输入的字符为：" + next);
        }
        System.out.println("程序执行结束");
    }
}

class Birth {
    int year, month, day;

    Birth(int m, int d, int y) {
        year = y;
        month = m;
        day = d;
    }
}

enum Sex {MAN, WOMAN;}

class People {
    String name;
    Birth birth;
    Sex s;
    float height;
    float weight;

    People(String name, Birth birth, Sex s, float h, float w) {
        this.name = name;
        this.birth = birth;
        this.s = s;
        this.height = h;
        this.weight = w;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    public Sex getS() {
        return s;
    }

    public void setS(Sex s) {
        this.s = s;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", birth=" + birth.year + "-" + birth.month + "-" + birth.day +
                ", s=" + s +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}

class Circle {
    final double cPI = 3.14;
    int cPoint_x, cPoint_y;
    double cRadius, cArea;

    public Circle() {
        cPoint_x = 0;
        cPoint_y = 0;
        cRadius = 1;
    }

    public Circle(int x, int y, double r) {
        cRadius = r;
        cPoint_x = x;
        cPoint_y = y;
    }

    public double cAreaCount() {
        cArea = cPI * cRadius * cRadius;
        return cArea;
    }

    public String toString() {
        return "圆心为(" + cPoint_x + "," + cPoint_y + ")半径 为 " + cRadius + "的圆的面积为" + cAreaCount();
    }
}

