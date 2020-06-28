package com.xiaoma.hencoder.thread;

public class WaitDemo implements TestDemo {

    private static String content;

    private synchronized void initContent() {
        content = "初始化了啊";
        notifyAll();
    }

    private synchronized String getContent() {
        while (content == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    @Override
    public void runTest() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initContent();
//                System.out.println("获取到的内容"+getContent());

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("获取到的内容" + getContent());
            }
        }.start();
    }
}
