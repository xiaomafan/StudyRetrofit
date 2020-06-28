package com.xiaoma.adapter;

public class Retrofit {

    private CallAdapter.Factory factory;

    private Retrofit(CallAdapter.Factory factory) {
        this.factory = factory;
    }

    public CallAdapter<?, ?> getCallAdapter() {
        return factory.get();
    }

    public <T> T create() {
        //适配器
        ServerMethod serverMethod = new ServerMethod.Builder<>(this).build();
        OkhttpCall<Object> okhttpCall = new OkhttpCall<>();
        return (T)serverMethod.adapt(okhttpCall);
    }

    public static final class Builder {
        CallAdapter.Factory factory;

        public Builder addCallAdapterFactroy(CallAdapter.Factory factory) {
            this.factory = factory;
            return this;
        }

        public Retrofit build() {
            if (factory == null) {
                factory = new ExecutorCallAdapterFactory();
            }
            return new Retrofit(factory);
        }
    }
}
