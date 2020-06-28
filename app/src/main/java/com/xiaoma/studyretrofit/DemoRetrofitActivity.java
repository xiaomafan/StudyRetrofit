package com.xiaoma.studyretrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.xiaoma.studyActivity.NewActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DemoRetrofitActivity extends AppCompatActivity implements LifecycleOwner {

    private static final String TAG = "DemoRetrofitActivity";
    @BindView(R.id.btn_retrofit)
    Button mBtnRetrofit;
    @BindView(R.id.btn_okhttp)
    Button mBtnOkhttp;
    @BindView(R.id.btn_single)
    Button mBtnSingle;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    private Disposable mDisposable;

    Disposable disposable;

   Handler handler= new Handler();

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn);
        LifecycleRegistry registry = new LifecycleRegistry(this);
//        registry.addObserver(new LocationLife());
        ButterKnife.bind(this);
        Looper.prepare();
        Looper.loop();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });;
//        ReentrantLock
        Handler handler = new Handler();
        Looper.prepare();
//        ActivityThread
//
//        Context
        HashMap<Integer, String> hashMap = new HashMap<>(16);
        HashMap<Integer, String> hashMap1 = new HashMap<>();
        hashMap.put(1,"123");
        hashMap.get(1);
        ConcurrentHashMap<Integer,String> concurrentHashMap=new ConcurrentHashMap<>();
        concurrentHashMap.put(2,"12");

    }

    @OnClick({R.id.btn_retrofit, R.id.btn_okhttp, R.id.btn_single})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_retrofit:
                getData();
                break;
            case R.id.btn_okhttp:
                getOkData();
                break;
            case R.id.btn_single:
                getSingleDatas();
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void getSingleDatas() {
//        Observable.create()
//        Flowable.concatDelayError()
        Observable.just(1, 2)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer + 1;
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(String.valueOf(integer + 2));
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.equals("3");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: =" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: =");
                    }
                });


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("xiaoma");
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return null;
            }
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return false;
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return Observable.just(s);
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
//        Observable.create()
//        Observable.interval(1,1,TimeUnit.SECONDS)
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Single.just(2).
                delay(1, TimeUnit.SECONDS).
                map(
                        new Function<Integer, String>() {
                            @Override
                            public String apply(Integer integer) throws Exception {
                                return String.valueOf(integer);
                            }
                        })
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        mTvContent.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
      /*  Single<String> single = Single.just("1");
        single=single.subscribeOn(Schedulers.io());
        single.subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        mTvContent.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });*/


   /*     Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/") //必须以"/"结尾，不然将抛出异常
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GitHubService netApi = retrofit.create(GitHubService.class);
        netApi.singleRepos("octocat")
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<Repo> repos) {
                        System.out.println("返回值" + repos.get(0) + ",线程" + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }
                });*/


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor()
//        executorService.
//        ThreadPoolExecutor
    }



    private void getOkData() {
        String url = "https://api.github.com/users/rengwuxian/repos";
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(20, TimeUnit.SECONDS);
//        builder.proxySelector()
//        builder.hostnameVerifier()
//        builder.cache(new Cache());
        OkHttpClient okHttpClient = builder.build();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println("OkHTTP===" + response.code());
                    }
                });
    }

    private void getData() {

        startActivity(new Intent(this, NewActivity.class));
        Single<String> single = Single.just("1");
        single.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/") //必须以"/"结尾，不然将抛出异常
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GitHubService netApi = retrofit.create(GitHubService.class);
        Observable<List<Repo>> responseCall = netApi.listRepos("octocat");
        retrofit2.Call<NetResponse> serviceApi = netApi.serviceApi("octocat", "");
       /* try {
            retrofit2.Response<NetResponse> execute = serviceApi.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        serviceApi.enqueue(new retrofit2.Callback<NetResponse>() {
            @Override
            public void onResponse(retrofit2.Call<NetResponse> call
                    , retrofit2.Response<NetResponse> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<NetResponse> call, Throwable t) {

            }
        });


        responseCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        Log.e(TAG, "onResponse: =" + repos.size());
                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mDisposable.dispose();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
     /*    enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.e(TAG, "onResponse: =" + response.code());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e(TAG, "onFailure: =" + t.toString());
            }
        });*/
    }


    private void studyRxjava() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        });

    }

}
