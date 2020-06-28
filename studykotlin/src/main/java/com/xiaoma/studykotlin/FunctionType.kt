package com.xiaoma.studykotlin

class View{

    interface OnClickListener{
        fun onClick(view:View)
    }

//    fun setOnClickListener(listener:View.OnClickListener){}
    fun setOnClickListener(listener:(View)->Unit){}

}

//inline 推荐是函数类型，减少调用栈和减少一次对象实例
inline fun measureTime(action:()->Unit){
    println(">>>>>>")
    val start:Long=System.currentTimeMillis()
    action()
    val end:Long=System.currentTimeMillis()
    println("<<<<<<<[${end-start}]")
}



/**
 * 函数类型
 *
 * kotlin可以传递函数,    函数类型声明
 */

fun main(){

    measureTime {
        println("Hello kotlin")
    }

    val view=View()
    //传递函数
//    view.setOnClickListener(::onClick)
    //匿名函数    一个lamda代表一个闭包
    view.setOnClickListener{
        println("被点击了啊")
    }
 /*   view.setOnClickListener(object :View.OnClickListener{
        override fun onClick(view: View) {

        }
    })*/
}

fun onClick(view:View){
    println("被点击了啊")
}