package com.xiaoma.studykotlin

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

interface API{
    @GET("lessons")
    fun lessons():Call<Any>
}

val RETROFIT=Retrofit.Builder()
        .baseUrl("https://api.hencoder.com/")
        .build()

/*fun <T> create(clazz:Class<T>):T{
    return RETROFIT.create(clazz)
}*/
//inline fun <reified T> create(clazz:Class<T>):T{

//不用每次都调用 T::class.java
inline fun <reified T> create():T{
    return RETROFIT.create(T::class.java)
}

fun main() {
//    val api = create(API::class.java)
    val api = create<API>()
    println(api)
}