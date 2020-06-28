package com.xiaoma.hencoder.thread;

public class Singleton {

    private Singleton() {
    }

    private volatile static Singleton singleton;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    /**
                     * 下面的赋值语句不是一个原子操作，可以抽象为下面几条Jvm指令
                     * 1.分配对象的内存空间
                     * 2.初始化对象
                     * 3.设置instance指向刚分配的内存地址
                     *
                     * 需要注意的是因为Jvm优化原因进行重排序，可以步骤2、3更换顺序
                     * 导致的后果就是instance已经指向内存空间；如果另外一个线程调用
                     * getInstance方法，因为singleton==null判为false，
                     * 返回了一个没有初始化的singleton，解决了问题的方法就是
                     * 将singleton生命为volatile变量
                     */
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }

}
