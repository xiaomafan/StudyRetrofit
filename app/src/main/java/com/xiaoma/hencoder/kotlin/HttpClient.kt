package com.xiaoma.hencoder.kotlin

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R.string
import retrofit2.adapter.rxjava2.Result.response
import okhttp3.ResponseBody

//单例
object HttpClient : OkHttpClient() {

    private val gson = Gson()

    private fun <T> convert(json: String?, type: Type): T {
        return gson.fromJson(json, type)
    }

    fun <T> get(path: String?, type: Type, callback: EntityCallback<T>) {
        val request: Request = Request.Builder()
//                .url("https://api.hencoder.com/$path")
                .url("https://api.hencoder.com/${path}")
                .build()
        val call: Call =newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                when (response.code()) {
                    in 200..299 -> callback.onSuccess(convert(response.body()!!.string(), type) as T)
                    in 400..499 -> callback.onFailure("客户端错误")
                    in 501..599 -> callback.onFailure("服务器错误")
                    else -> callback.onFailure("未知错误")
                }
            }

        })
    }
}