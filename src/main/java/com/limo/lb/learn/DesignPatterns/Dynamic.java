package com.limo.lb.learn.DesignPatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Dynamic {
    /**
     *动态代理
     * Proxy.newProxyInstance生成代理对象
     *
     **/
    public static void main(String[] args) {
        //创建使用的接口实现
        UsbShell proxy = new SD();
        //生成代理对象
        proxy = (UsbShell) Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
                proxy.getClass().getInterfaces(),
                new MyProxy(proxy));
        //调用不同的方法接口实现方法
        System.out.println(proxy.shell(2));
        System.out.println(proxy.back(2));

    }
}

interface UsbShell {

    int shell(int a);

    int back(int a);

}

class JSD implements UsbShell {

    @Override
    public int shell(int a) {
        return 85 * a;
    }

    @Override
    public int back(int a) {
        return 65 * a;
    }

}

class SD implements UsbShell {

    @Override
    public int shell(int a) {
        return 100 * a;
    }

    @Override
    public int back(int a) {
        return 65 * a;
    }
}

class MyProxy implements InvocationHandler {

    private Object target;

    public MyProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //根据不同的方法名 来使用不同的增强逻辑
        if ("back".equals(method.getName())) {
            //调用目标方法
            int res = (int) method.invoke(target, args);
            //功能增强
            if (res != 0) {
                res += 15;
            }
            System.out.println(method.getName() + "回收增强价格");
            //返回调用结果
            return res;
        } else if ("shell".equals(method.getName())) {
            //调用目标方法
            int res = (int) method.invoke(target, args);
            //功能增强
            if (res != 0) {
                res += 25;
            }
            System.out.println(method.getName() + "买入增强价格");
            //返回调用结果
            return res;
        }
        return null;
    }
}