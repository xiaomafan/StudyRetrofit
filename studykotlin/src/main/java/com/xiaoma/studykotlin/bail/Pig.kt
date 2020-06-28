package com.xiaoma.studykotlin.bail

class Pig : Creature by DelegateClass() {

    override fun run() {
        println("自己执行实现的run方法")
    }

    override val type: String
        get() = "自己实现的type属性"
}