package com.xiaoma.studykotlin.bail

class DelegateClass :Creature{
    override fun run() {
        println("委托类run方法")
    }

    override val type: String
        get() = "委托类属性"
}