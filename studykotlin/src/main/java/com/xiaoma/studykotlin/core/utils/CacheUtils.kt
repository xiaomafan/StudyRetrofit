package com.xiaoma.studykotlin.core.utils

import android.annotation.SuppressLint
import android.content.Context
import com.xiaoma.studykotlin.R
import com.xiaoma.studykotlin.core.BaseApplication

@SuppressLint("StaticFieldLeak")
object CacheUtils {
    val context = BaseApplication.currentApplication

    val SP = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun save(key: String?, value: String?)=SP.edit().putString(key, value).apply()
   /* fun save(key: String?, value: String?) {
        SP.edit().putString(key, value).apply()
    }*/

    fun get(key: String?)= SP.getString(key, "")
   /* fun get(key: String?): String {
        return SP.getString(key, "")
    }*/
}