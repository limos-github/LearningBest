package com.limo.lb.learn.jdk.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 举例： (o1, o2) -> o1.compareTo(o2);
 * Lambda 本质是作为（函数式）接口的实例
 * 函数式接口：如果一个接口中只声明了一个抽象方法，则此接口就称为函数式接口
 * 注：@FunctionalInterface 带有这个注解的是显式的声明此接口是函数式接口
 * ->：表示lambda的操作符 或者 箭头操作符
 * ->：左边lambda的形参列表（其实就是接口中抽象方法的形参列表）
 * ->：右边lambda体（也就是重写抽象方法的方法体）
 * 在java。util.function包下有java8丰富的函数式接口
 */
public class LambdaDemo {
    //语法一：无参无返回值
    public void demo1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("");
            }
        };
        Runnable runnable1 = () -> System.out.println("Lambda1");
    }

    //语法二：有参无返回值
    public void demo2() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("谎言与誓言的区别是什么？");
        Consumer<String> consumer1 = (String s) -> System.out.println(s);
        consumer1.accept("Lambda2:" + "听的人当真了和说的人当真了");
    }

    //语法三：数据类型可以省略
    public void demo3() {
        //基于二
        Consumer<String> consumer1 = (s) -> System.out.println(s);
        consumer1.accept("Lambda3:" + "听的人当真了和说的人当真了");
    }

    //语法四：如果参数只有一个 可以不用小括号
    public void demo4() {
        //基于三
        Consumer<String> consumer1 = s -> System.out.println(s);
        consumer1.accept("Lambda4:" + "听的人当真了和说的人当真了");
    }

    //语法五：需要两个或者以上的参数，多条执行语句，并且可以有返回值
    public void demo5() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(comparator.compare(1, 2));
        Comparator<Integer> comparatorLambda = (o1, o2) -> {
            System.out.println("Lambda5:" + o1);
            System.out.println("Lambda5:" + o2);
            return o1.compareTo(o2);
        };
        System.out.println("Lambda5:" + comparatorLambda.compare(2, 1));
    }

    //语法六：如果只有一条执行语句 大括号也可以省略
    public void demo6() {
        //基于五
        Comparator<Integer> comparatorLambda = (o1, o2) -> o1.compareTo(o2);
        System.out.println("Lambda6:" + comparatorLambda.compare(2, 1));
    }

    public static void main(String[] args) {
        /**函数型
         * Function<T，R> R apply(T t)
         * 入参是T类型，返回参数是R类型
         * */
        Function<Double,Long> function = Math::round;
        System.out.println(function.apply(12.3));
        Function<Double,Long> function1 = d -> Math.round(d);
        System.out.println(function1.apply(12.6));

        /**
         * 供给型 Supplier<T> T get()
         * 没有入参，返回一个T类型的数据
         * */

        /**
         * 消费型 Consumer<T> void accept(T t)
         * 入参是一个T类型，没有返回参数
         * */

        /**
         * 断言型 Predicate<T> boolean test(T t)
         * 入参是T类型，返回参数是布尔型
         * */
        System.out.println(filterString(Arrays.asList("天津","北京"),s -> s.contains("京")));
    }

    public static List<String> filterString(List<String> list, Predicate<String> predicate){
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s: list) {
            if (predicate.test(s)){
                arrayList.add(s);
            }
        }
        return arrayList;
    }
}
