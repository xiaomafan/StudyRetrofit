package com.xiaoma.adapter;

public interface CallAdapter<R,T> {

    T adapt(Call<R> call);


    abstract class Factory{

        public abstract CallAdapter<?,?> get();
    }

}
