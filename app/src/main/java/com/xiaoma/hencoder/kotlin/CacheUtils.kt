package com.xiaoma.hencoder.kotlin

import android.content.Context
import com.xiaoma.studyretrofit.R


object CacheUtils {

    val context = BaseApplication.currentApplication()

   private val SP = context.getSharedPreferences(context.getString(R.string.sp), Context.MODE_PRIVATE)

    fun save(key: String?, value: String?) {
        SP.edit().putString(key, value).apply()
    }

    fun get(key: String?): String? {
        return SP.getString(key, "default")
    }
}