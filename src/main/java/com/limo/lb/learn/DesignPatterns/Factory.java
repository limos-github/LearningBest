package com.limo.lb.learn.DesignPatterns;

public class Factory {
    /**
     * 工厂模式
     *
     * 工厂负责生产子类对象
     * 父类通过工厂 拿到子类对象
     * */
    public static void main(String[] args) {
        BothFactory bothFactory = new BothFactory();
        Children children = bothFactory.both("Boy");
        children.both();
        children = bothFactory.both("Girl");
        children.both();
    }
}

interface Children {
     void both();
}
class Boy implements Children {
    @Override
    public void both(){
        System.out.println("生了个男的");
    }
}

class Girl implements Children{
    @Override
    public void both(){
        System.out.println("生了个女的");
    }
}

class BothFactory{
    public Children both(String sex){
        if (sex.equalsIgnoreCase("Boy")){
            return new Boy();
        }else if (sex.equalsIgnoreCase("Girl")){
            return new Girl();
        }
        return null;
    }
}