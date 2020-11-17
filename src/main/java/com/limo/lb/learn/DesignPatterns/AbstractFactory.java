package com.limo.lb.learn.DesignPatterns;

public class AbstractFactory {
    /**
     * 抽象工程
     *
     * 将一系列的东西抽象
     * 再通过工程实现
     * */
    public static void main(String[] args) {
        EquipFactory ad = new APFactory();
        Weapon weapon = ad.creatWeapon();
        Shose shoes = ad.creatShose();
        Defens defens = ad.creatDefens();
        weapon.buy();
        defens.buy();
        shoes.buy();
    }
}

abstract class EquipFactory {
    abstract Weapon creatWeapon();

    abstract Shose creatShose();

    abstract Defens creatDefens();
}

interface Weapon {
    void buy();
}

interface Shose {
    void buy();
}

interface Defens {
    void buy();
}

class IronSword implements Weapon {
    @Override
    public void buy() {
        System.out.println("战士买铁剑");
    }
}
class NebuchadnezzarShoes implements Shose {
    @Override
    public void buy() {
        System.out.println("战士买布甲鞋");
    }
}
class ClothArmour implements Defens {
    @Override
    public void buy() {
        System.out.println("战士买布甲");
    }
}

class Wand implements Weapon {
    @Override
    public void buy() {
        System.out.println("法师买法杖");
    }
}
class MagicBoots implements Shose {
    @Override
    public void buy() {
        System.out.println("法师买法靴");
    }
}
class Cape implements Defens {
    @Override
    public void buy() {
        System.out.println("法师买法袍");
    }
}


class ADFactory extends EquipFactory {

    @Override
    Weapon creatWeapon() {
        return new IronSword();
    }

    @Override
    Shose creatShose() {
        return new NebuchadnezzarShoes();
    }

    @Override
    Defens creatDefens() {
        return new ClothArmour();
    }
}

class APFactory extends EquipFactory {

    @Override
    Weapon creatWeapon() {
        return new Wand();
    }

    @Override
    Shose creatShose() {
        return new MagicBoots();
    }

    @Override
    Defens creatDefens() {
        return new Cape();
    }
}

