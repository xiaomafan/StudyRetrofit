package com.xiaoma.core;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.multidex.MultiDex;

import com.squareup.leakcanary.LeakCanary;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;


public class MyApp extends Application {

    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        mContext=this;
        getApplicationContext();
//        BaseDexClassLoader
//        View
//        DexClassLoader
    }

    public static Context getContext() {
        return mContext;
    }
}
