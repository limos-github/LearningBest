package com.limo.lb.learn.homework;

import java.util.Scanner;

/**
 * @author lib
 */
public class JavaHomeWork_2 {
    public static void main(String[] args) {
        /**
         * 1. 声明病人 Patient 类，此类对象包括 name(String)、sex(char)、age(int)、weight(float)、allergies(boolean)。
         * 声明 setName 存取及修改方法。在一个单独的类中，声明测试方法，并生成两个 Patient 对象，设置其状态并将其信息显示在屏幕上。
         * 声明并测试 toString()方法，显示一个病人 age、sex、name 及 allergies属性
         * */
        Patient arnoldSchwarzenegger = new Patient().setName("阿诺德·施瓦辛格").setSex('男').setAge(73).setWeight(210f).setAllergies(false);
        Patient sylvesterStallone = new Patient().setName("西尔维斯特·史泰龙").setSex('男').setAge(74).setWeight(160f).setAllergies(false);
        System.out.println("arnoldSchwarzenegger.toString() = " + arnoldSchwarzenegger.toString());
        System.out.println("sylvesterStallone.toString() = " + sylvesterStallone.toString());
        /**
         * 2. 编写一个矩形类 Rect，包含两个 protected 属性：宽 width；高 height。 一个带有两个参数的构造器方
         * 法，用于将 width 和 height 属性初化；一个不带参数的构造器，将矩形初始化为宽和高都为 10。 两个
         * 方法： （1） 求矩形面积的方法 area() （2 ）求矩形周长的方法 perimeter()
         * */
        Rect rect = new Rect();
        Rect rect1 = new Rect(20, 15);
        System.out.println(rect.toString());
        System.out.println(rect1.toString());
        /**
         * 3. 接收从键盘上输入的两个整数，求两个数的最大公约数和最小公倍数，并输出。
         * */
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
}

class Patient {

    private String name;
    private char sex;
    private int age;
    private float weight;
    private boolean allergies;

//    public Patient(String name, char sex, int age, float weight, boolean allergies) {
//        this.name = name;
//        this.sex = sex;
//        this.age = age;
//        this.weight = weight;
//        this.allergies = allergies;
//    }


    public String getName() {
        return name;
    }

    public Patient setName(String name) {
        this.name = name;
        return this;
    }

    public char getSex() {
        return sex;
    }

    public Patient setSex(char sex) {
        this.sex = sex;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Patient setAge(int age) {
        this.age = age;
        return this;
    }

    public float getWeight() {
        return weight;
    }

    public Patient setWeight(float weight) {
        this.weight = weight;
        return this;
    }

    public boolean isAllergies() {
        return allergies;
    }

    public Patient setAllergies(boolean allergies) {
        this.allergies = allergies;
        return this;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", weight=" + weight +
                ", allergies=" + allergies +
                '}';
    }
}

class Rect {
    protected int width, height;

    Rect(int w, int h) {
        width = w;
        height = h;
    }

    Rect() {
        width = 10;
        height = 10;
    }

    public int areaRect() {
        return width * height;
    }

    public int permeterRect() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        String s = "The Rectangle is:";
        s += width + "," + height + "\nThe area is " + areaRect() + ";";
        s += "\nThe perimeter is " + permeterRect() + ";";
        return s;
    }
}