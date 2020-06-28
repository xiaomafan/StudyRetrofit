package com.xiaoma;

import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;

public class MainDemo {

    public static void main(String[] args) {

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 50; i++) {
            singleThreadExecutor.submit(new TestThread(i + 1));
        }


        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            cachedThreadPool.execute(new TestThread(i + 1));
        }
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 80; i++) {
            fixedThreadPool.execute(new TestThread(i + 1));
        }
        ScheduledExecutorService scheduleService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 80; i++) {
            scheduleService.schedule(new TestThread(i + 1), 3000, TimeUnit.MILLISECONDS);
        }


//        HashMap<String, String> map = new HashMap<>();
//        map.put("abc","bcd");

//        testSingle();
//        testComplete();
//        testMaybe();
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.requested();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribe(new FlowableSubscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private static void testMaybe() {
        Maybe.create(new MaybeOnSubscribe<String>() {

            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                /**
                 * Maybe只能发送一次success,如果多次发射，只有第一条成功，其他忽略掉
                 * Maybe只能发送一次onComplete,如果多次发送，只有第一条成功，其他忽略掉
                 * Maybe只能发送一次onError,如果多次发送，则报错io.reactivex.exceptions.UndeliverableException
                 *  onComplete和onError只能调用一个，如果先调用onComplete，则报错，
                 *       如果先调用onError，则onComplete失效
                 * 方法onSuccess必须在方法onComplete或onError之前调用，否则会导致方法onSuccess调用无效。
                 */
//                emitter.onSuccess("Maybe发射成功数据1");
//                emitter.onSuccess("Maybe发射成功数据2");
                emitter.onError(new Exception("错误通知"));
                emitter.onComplete();

            }
        }).subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                System.out.println("接收到的success==" + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("接收到的error==" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("接收到的complete==");
            }
        });
    }

    private static void testComplete() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                /**
                 * 发射器只能发射一条错误通知或者一条完成通知；不能发送数据(没有对应的onNext())
                 * onError()和onComplete()只能发送一条；若先调用onError()，则onComplete无效，
                 *   若先调用onComplete()，则发生错误io.reactivex.exceptions.UndeliverableException
                 */
                emitter.onComplete();
                emitter.onError(new Exception("发射错误"));

            }
        })
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("接收完成了啊");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("接收=" + e.getMessage());
                    }
                });
    }

    private static void testSingle() {
        Single.create(new SingleOnSubscribe<String>() {

            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                /**
                 * 发射器只能发射单一的数据，且只能调用一次；若有第二次，则不发射
                 * 发射器也只能发射一条错误的通知
                 * 发送数据或者发射错误只能发射一次；若先调用onError()，则会导致onSuccess()无效
                 * 若先调用onSuccess(),调用onError()报错io.reactivex.exceptions.UndeliverableException
                 */
                emitter.onSuccess("小马1号");
                emitter.onError(new Exception("错误"));
                emitter.onSuccess("小马2号");
            }
        })
                .subscribe(new SingleObserver<String>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String o) {
                        System.out.println("收到的数据==" + o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("收到的错误==" + e);
                    }
                });
    }


    static class TestLock {
        public static void test() throws Exception {

            final Lock lock = new ReentrantLock();
            lock.lock();


            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                  /*  try {
                        lock.lockInterruptibly();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                }
            }, "child thread -1");

            t1.start();
            Thread.sleep(1000);

            t1.interrupt();

            Thread.sleep(10000);
//            lock.unlock();

        }
    }
}
