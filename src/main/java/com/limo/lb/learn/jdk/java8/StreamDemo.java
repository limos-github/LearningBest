package com.limo.lb.learn.jdk.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一、Stream 的三个操作步骤
 * <p>
 * 1、创建流
 * <p>
 * 2、中间操作（加工）
 * <p>
 * 3、终止操作（终端操作）
 */
public class StreamDemo {

    //创建Stream
    public static void create() {
        //1 可以通过collection 系列结合提供的stream() 或者prallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        //2、通过Arrays中的静态方法stream()获取数组流
        String[] strings = new String[10];
        Stream<String> stream1 = Arrays.stream(strings);
        //3、通过Stream类中的静态方法of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");
        //4、创建无线流
        //①迭代
        Stream.iterate(0, (x) -> x + 2).limit(10).forEach(System.out::println);
        //②生成
        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);
    }


    /**
     * 中间操作(没有任何结果)
     * 惰性求值；中间操作不会产生任何结果，除非调用终止操作时一起处理
     * <p>
     * 筛选与切片
     * filter-接收Lambda ，从流中排除某些元素
     * limit-截断流，使其元素不超过给定数量
     * skip-跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit（n）互补
     * distinct-筛选，通过流所生成元素的hashcode()和equals()去除重复元素
     */
    public static void middle() {
        Stream.of(1, 2, 3, 4, 5, 6)//创建流
                .filter((x) -> x > 3)//中间操作 取大于3的数
                .forEach(System.out::println);//终止操作 遍历结果

        Stream.of(1, 2, 3, 4, 5, 6)//创建流
                .limit(3)//中间操作 留前三个
                .forEach(System.out::println);//终止操作 遍历结果

        Stream.of(1, 2, 3, 4, 5, 6)//创建流
                .skip(2)//中间操作
                .forEach(System.out::println);//终止操作 遍历结果
    }

    /**
     * 中间操作(没有任何结果)
     * 惰性求值；中间操作不会产生任何结果，除非调用终止操作时一起处理
     * <p>
     * 映射Map
     * Map-接收Lambda,将元素转换为其他形式或提取信息。接收一个函数作为参数，
     * 该函数会被应用到每一个元素上，并将其映射成一个新的元素。
     * flat-map -接收一个函数作为参数，将流中的每一个值都换成另外一个流，然后将流连成一个新的流
     */
    public static void middleMap() {
        Stream.of(1, 2, 3, 4, 5, 6)//创建流
                .map(x -> x * x)//中间操作 返回当前数字的平方
                .forEach(System.out::println);//终止操作 遍历结果
        //流中套流
        Stream.of("a", "bb", "ccc", "ddd")//创建流
                .flatMap(x -> {
                    List list = new ArrayList();
                    for (Character c : x.toCharArray()) {

                        list.add(c);
                    }
                    //{a} {b,b} {c,c,c} {d,d,d,d}
                    return list.stream();
                })//中间操作 此时flat-map 内容结构为：{{a} {b,b} {c,c,c} {d,d,d,d}}
                .forEach(System.out::println);//终止操作 遍历结果
    }

    /**
     * 中间操作(没有任何结果)
     * 惰性求值；中间操作不会产生任何结果，除非调用终止操作时一起处理
     * <p>
     * 排序
     * sorted-自然排序(Comparable)
     * sorted(Comparator com) -定制排序（Comparator）
     */
    public static void middleSorted() {
        Stream.of("aaa", "ccc", "ddd", "bbb")//创建流
                .sorted()
                .forEach(System.out::println);//终止操作 遍历结果
    }

    /**
     * 终止操作
     * <p>
     * 查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是否至少匹配一个元素
     * noneMatch-检查是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回流任意元素
     * count-返回流中元素的总个数
     * max-返回流中最大值
     * min-返回流中最小值
     */
    public static void end() {
        System.out.println("allMatch " + Stream.of(1, 2, 3, 4, 5, 6)//创建流
                .allMatch(x -> x.equals(2)));//终止操作
        System.out.println("anyMatch " + Stream.of(1, 2, 3, 4, 5, 6)//创建流
                .anyMatch(x -> x.equals(3)));//终止操作
        System.out.println("noneMatch " + Stream.of(1, 2, 3, 4, 5, 6)//创建流
                .noneMatch(x -> x.equals(5)));//终止操作
        System.out.println("findFirst " + Stream.of(7, 5, 4, 2, 6, 3)//创建流
                .sorted(Integer::compareTo)//中间操作
                .findFirst().get());//终止操作 且get打印Optional
        System.out.println("findAny " + Stream.of(1, 5, 4, 2, 6, 3)//创建流
                .filter(x -> x > 2)//中间操作
                .findAny().get());//终止操作 且get打印Optional
        System.out.println("count " + Stream.of(7, 5, 4, 2, 6, 3)//创建流
                .count());//终止操作
        System.out.println("max " + Stream.of(7, 5, 4, 9, 6, 3)//创建流
                .max(Integer::compareTo).get());//终止操作 且get打印Optional
        System.out.println("min " + Stream.of(7, 5, 4, 9, 6, 3)//创建流
                .min(Integer::compareTo).get());//终止操作 取最小值 且get打印Optional
    }

    /**
     * 终止操作
     * <p>
     * 归约
     * reduce(T identity,BinaryOperator) / reduce(BinaryOperator)-可以将流中元素反复结合起来，得到一个值
     */
    public static void end1() {
        //带有初始值 一定不为空 所以不返回Optional
        System.out.println("归约 " + Stream.of(7, 5, 4, 9, 6, 3)//创建流
                .reduce(0, Integer::sum));//终止操作 从零开始将值相加

        System.out.println("归约 " + Stream.of(7, 5, 4, 9, 6, 3)//创建流
                .reduce(Integer::sum).get());//终止操作 将值相加默认从零开始 且get打印Optional

    }

    /**
     * 终止操作
     * <p>
     * 收集
     * collect- 将流转换为其他形式，接收一个Collector接口的实现，应用于给Stream中元素做汇总的方法
     * collect可用参数Collectors.*
     */
    public static void end2() {
        Stream.of("abc", "abd", "cbd", "dbc", "bad")
                .filter(x -> x.contains("a"))//中间操作 筛选出字符串中包含a的字符串
                .collect(Collectors.toList())//终止操作 转化成list
                .forEach(System.out::println);//终止操作 遍历list

        Stream.of("abc", "abd", "cbd", "dbc", "bad")
                .filter(x -> x.contains("a"))//中间操作 筛选出字符串中包含a的字符串
                .collect(Collectors.toCollection(HashSet::new))//终止操作 转化为集合接口中的子类实现
                //.collect(Collectors.toCollection(LinkedHashMap::new))//终止操作 转化为集合接口中的子类实现
                .forEach(System.out::println);//终止操作 遍历list
    }

    public static void main(String[] args) {
        end2();
    }
}
