package com.xiaoma.adapter;

public class ServerMethod<R, T> {

    private CallAdapter<R, T> callAdapter;

    public ServerMethod(Builder builder) {
        callAdapter = builder.callAdapter;
    }

    T adapt(Call<R> call) {
        return callAdapter.adapt(call);
    }

    static final class Builder<R, T> {
        private Retrofit retrofit;
        private CallAdapter<R, T> callAdapter;

        public Builder(Retrofit retrofit) {
            this.retrofit = retrofit;
        }


        public ServerMethod build() {
            callAdapter = createCallAdapter();
            return new ServerMethod(this);
        }

        private CallAdapter<R, T> createCallAdapter() {
            return (CallAdapter<R, T>) retrofit.getCallAdapter();
        }


    }
}
