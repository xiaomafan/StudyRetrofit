package com.xiaoma.studykotlin.core.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

object HttpClient : OkHttpClient() {

    private val gson = Gson()

    private fun <T> convert(json: String, type: Type): T {
        return gson.fromJson(json, type)
    }

    //非静态函数
    fun <T> get(path: String, type: Type, entityCallback: EntityCallback<T>) {
//        val request = Request.Builder().url("https://api.hencoder.com/" + path)
        val request = Request.Builder().url("https://api.hencoder.com/$path")
                .build()

        val call: Call = this.newCall(request)

        //匿名内部类通过object：来定义
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                entityCallback.onFailure("网络异常")
            }

            override fun onResponse(call: Call, response: Response) {

//                val (code,message,body)=response
//                val (code,body) = response


                //对应switch高级版   枚举字符串基本类型
                when (response.code()) {
                    //as  强转
                    in 200..299 -> entityCallback.onSuccess(convert(response.body()!!.string(), type) as T)
                    in 400..499 -> entityCallback.onFailure("客户端错误")
                    in 500..600 -> entityCallback.onFailure("服务器错误")
                    else -> entityCallback.onFailure("未知错误")
                }
            }
        })
    }

}