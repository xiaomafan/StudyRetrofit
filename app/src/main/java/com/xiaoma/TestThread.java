package com.xiaoma;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThread implements Runnable {

    private int num;

    public TestThread(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"/num="+num);
    }
}
