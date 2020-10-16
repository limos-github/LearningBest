package com.limo.lb.learn.jdk.java8;

public class OptionalDemo {
    /**
     * Optionalro 容器类的常用方法
     *          .of：创建一个Optional实例
     *          .empty：创建一个空的Optional实例
     *          .ofNullable(T t)：若t不为null，创建Optional实例，否则创建空实例
     *          .isPresent：判断是否包含值
     *          .orElse(T t)：如果调用对象包含值，返回该值，否则返回t
     *          .orElseGet(Supplier s) ：如果调用对象包含值，则返回该值，否则返回s获取的值
     *          .map(Function f) ：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     *          .flatMap(Function mapper)：与mapper相似，要求返回值必须是Optional
     * */
    public static void op(){

    }
}
