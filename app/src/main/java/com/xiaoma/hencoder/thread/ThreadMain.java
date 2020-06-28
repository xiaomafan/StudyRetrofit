package com.xiaoma.hencoder.thread;

import com.xiaoma.hencoder.view.CustomViewActivity;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadMain {

    public static void main(String[] args) {
        runType1();
        typeRun typeRun = new typeRun();
        Thread thread = new Thread(typeRun);
        thread.start();
        runType3();
        runType4();
        runSynchronized1Demo();
        threadInteraction();
        waitInteraction();
/*
        ClassLoader classLoader = ThreadMain.class.getClassLoader();
        while (classLoader!=null){
            System.out.println("ClassLoader名称是="+classLoader);
            classLoader=classLoader.getParent();
        }*/

    }


    private static void waitInteraction() {
        new WaitDemo().runTest();
    }


    private static void threadInteraction() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 100000; i++) {
//                    System.out.println("打印newFixedThreadPool数据" + isInterrupted());
//                    if (Thread.interrupted()) {
                    if (isInterrupted()) {
                        System.out.println("interrupt==" + isInterrupted());
                        return;
                    }
                    System.out.println("打印newFixedThreadPool数据" + i + "///" + isInterrupted());
//                    }
                }
            }
        };
        thread.start();

        try {
            Thread.sleep(20);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
//        thread.stop();
        thread.interrupt();

    }


    static void runSynchronized1Demo() {
        new SyncronizedDemo().runTest();
    }


    private static void runType4() {
        CallThread callThread = new CallThread("小马哥");
        try {
            FutureTask<String> future = new FutureTask<>(callThread);
            new Thread(future).start();
            System.out.println("执行结果是" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static class CallThread implements Callable<String> {

        private String name;

        public CallThread(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            int nextInt = new Random().nextInt(10);
//           System.out.println("sleep"+nextInt+"second.");
            TimeUnit.SECONDS.sleep(nextInt);
            return name + nextInt;
        }
    }

    private static void runType3() {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 6; i++) {
                    System.out.println("打印newFixedThreadPool数据" + i);
                }
            }
        });
    }

    private static void runType1() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 5; i++) {
                    System.out.println("打印Thread数据" + i);
                }
            }
        };
        thread.start();
    }

    static class typeRun implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 6; i++) {
                System.out.println("打印Runnable数据" + i);
            }
        }
    }

}
