package com.xiaoma.proxy;

public class Bird implements IFly {

    @Override
    public void fly(String name) {
        System.out.println(name+"==小鸟在飞");
    }
}
