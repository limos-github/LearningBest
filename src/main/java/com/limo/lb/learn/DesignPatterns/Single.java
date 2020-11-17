package com.limo.lb.learn.DesignPatterns;

public class Single {

}

class Singleton {
    /**
     * 饿汉式（静态变量）
     * 初始化就加载
     */
    private Singleton() {
    }

    private final static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}

class Singleton2 {
    /**
     * 饿汉式（静态代码块）
     * 初始化就加载
     */
    private Singleton2() {
    }

    private static Singleton2 instance;

    //静态代码块创建实例对象
    static {
        instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return instance;
    }
}

class Singleton3 {
    /**
     * 懒汉式（线程不安全）
     * 使用时创建
     */
    private static Singleton3 instance;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}

class Singleton4 {
    /**
     * 懒汉式（线程安全 synchronized）
     * 使用时创建
     * 效率低
     */
    private static Singleton4 instance;

    private Singleton4() {
    }

    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}

class Singleton5 {
    /**
     * 懒汉式（双重检查 线程安全 synchronized ）
     * 使用时创建
     * 效率低
     */
    private static volatile Singleton5 instance;

    private Singleton5() {}

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}

class Singleton6 {
    /**
     * 懒汉式（静态内部类 线程安全 synchronized ）
     * 使用时创建
     */
    private Singleton6() {}

    private static class Singleton6_1 {
        private final static Singleton6 instance = new Singleton6();
    }

    public static Singleton6 getInstance() { return Singleton6_1.instance; }
}

enum Singleton7 {
    INSTANCE;

    public void m() {
    }
}