package com.xiaoma;

import com.xiaoma.adapter.Call;
import com.xiaoma.adapter.CallAdapter;

import io.reactivex.Observable;

public class RxjavaCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get() {
        return new CallAdapter<Object, Observable<?>>() {
            @Override
            public Observable<?> adapt(Call<Object> call) {
                System.out.println("Rxjava  Adapter");
                Observable<String> just = Observable.just("1");

                return just;
            }
        };
    }
}
