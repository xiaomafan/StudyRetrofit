package com.xiaoma.studykotlin

import android.util.Log
import com.xiaoma.studykotlin.bail.DelegateClass
import com.xiaoma.studykotlin.bail.Human
import com.xiaoma.studykotlin.bail.Pig
import com.xiaoma.studykotlin.entity.User
import com.xiaoma.studykotlin.java.Java
import kotlinx.coroutines.*


var user=User("123","1212","211")
var userCopy=user.copy()

/**
 * 内联函数的作用是把调用的位置替换为方法内容
 * 减少一次调用栈
 *
 *
 * 函数类型
 */
inline fun log(text:String){
    Log.e("TAG",text)
}

private suspend fun getStr():String= withContext(Dispatchers.IO){
    println(Thread.currentThread().name)
    Thread.sleep(1000)
   return@withContext "来了老弟"
}

fun main(){


    /**
     * 类委托
     */

    val delegateClass=DelegateClass()
    val human=Human(delegateClass)
    human.run()
    println(human.type)

    val pig= Pig()
    pig.run()
    println(pig.type)


//    log("aaaa")

    runBlocking {
        var str=getStr()
        println(Thread.currentThread().name+"/////")
        println(str)
    }

  /*  GlobalScope.launch{
        var str=getStr()
        println(Thread.currentThread().name+"/////")
        println(str)
    }*/



//    println("hello world."+ getSum(5))
//    name="Android"   只能被赋值一次
//    age=20

//    val (code,message,body)=execute()

    println(user)
    println(userCopy)
    println(user== userCopy)//true
    println(user=== userCopy)//false

    /**
     * repeat函数如果最后一个是lamda函数，可以放到括号外面
     *
     * inline  内联函数
     */
 /*   repeat(100){
        println(it)
    }*/

   /* for(i in 0..99){
        println(i)
    }*/

    var arrayOf=intArrayOf(1,23,212,1212,21)
//    for (i in 0 until arrayOf.size-1){
    for (i in 0.until(arrayOf.size-1)){
        println(arrayOf[i])
    }

}



fun getSum(x:Int):Int{
    return x*2
}

//可读可写
var age:Int=18

var grade=20  //类型推断为Int

//只读变量，类似final
val name:String="kotlin"

val jav:Java= Java()