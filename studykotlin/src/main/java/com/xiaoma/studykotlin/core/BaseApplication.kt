package com.xiaoma.studykotlin.core

import android.app.Application
import android.content.Context

class BaseApplication :Application(){


    companion object{
        @get:JvmName("currentApplication")
        @JvmStatic
         lateinit var currentApplication:Context
            private set  //设置该参数只能调用，不能设置
       /* @JvmStatic  //便于java 通过类名.的方式调用，即静态函数
        fun currentApplication():Context{
            return currentApplication
        }*/
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication=this
    }
}