package com.xiaoma.hencoder.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncronizedDemo implements TestDemo {

    //    private volatile int x = 0;
    private int x = 0;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private void selfIncreame() {
        atomicInteger.getAndIncrement();
    }


    private void lockTest(){
        ReentrantLock lock = new ReentrantLock(true);
       /* lock.lock();
        try {
            //处理任务
        }catch (Exception ex){

        }finally {
            lock.unlock();
        }*/
     /*   try {
            if(lock.tryLock(1000,TimeUnit.SECONDS)){
                try {
                    //处理任务
                }catch (Exception ex){

                }finally {
                    lock.unlock();
                }

            }else {
                //如果不能获取锁，则直接做其它事情
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        try {
            lock.lockInterruptibly();
            //处理业务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }



    }


    private void count() {
        Lock lock = new ReentrantLock();
//        lock.tryLock(200, TimeUnit.SECONDS);
////        synchronized (this) {
//        lock.lockInterruptibly();

        try {
            x++;
        } finally {
            lock.unlock();
        }

//        }

    }

    @Override
    public void runTest() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    count();
                }
//                System.out.println("final x from 1: " + atomicInteger.get());
                System.out.println("final x from 1: " + x);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    count();
                }
//                System.out.println("final x from 2: " + atomicInteger.get());
                System.out.println("final x from 2: " + x);
            }
        }.start();
    }


}

