package com.xiaoma.studykotlin.java;

import com.xiaoma.studykotlin.View;
import com.xiaoma.studykotlin.core.BaseApplication;
import com.xiaoma.studykotlin.core.utils.DpAUtils;
import com.xiaoma.studykotlin.core.utils.Utils;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
//import com.xiaoma.studykotlin.core.utils.UtilsKt;

public class Java {

    void fun(){


        //函数类型，调用kotlin 函数类型
        View view = new View();
        view.setOnClickListener(new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view) {
                return null;
            }
        });

        //原生java调用
//        BaseApplication.Companion.currentApplication();
//        BaseApplication.getCurrentApplication();
        BaseApplication.currentApplication();

//        Utils.INSTANCE.toast("aa");
        Utils.toast("bbb");//通过增加注解@JvmStatic


        //普通函数调用Kotlin
//        UtilsKt.dp2px(10f);
        //如果是文件形式调用，则通过定义@file:JvmName("DpUtil"),可以通过类名方法调用
        DpAUtils.dp2px(10f);



        //从java调用半生对象函数的情况
//        BaseApplication.Companion.currentApplication();
//        BaseApplication.getCurrentApplication();

        //在对应的方法上定义注解JvmStatic，就可以以静态函数调用
//        BaseApplication.currentApplication();
    }
}
