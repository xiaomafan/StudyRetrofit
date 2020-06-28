package com.xiaoma.adapter;

public class ExecutorCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get() {
        return new CallAdapter<Object, Call<?>>() {
            @Override
            public Call<?> adapt(Call<Object> call) {
                System.out.println("Default >>>>");
                return new ExcutorCallBackCall<>();
            }
        };
    }

    final static class ExcutorCallBackCall<T> implements Call<T> {

        @Override
        public void enqueque() {
            //不关心请求，只管适配器模式
        }
    }
}
