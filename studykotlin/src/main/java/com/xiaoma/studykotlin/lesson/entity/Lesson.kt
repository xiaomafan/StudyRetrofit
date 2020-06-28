package com.xiaoma.studykotlin.lesson.entity

import java.lang.StringBuilder

//主构造器
class Lesson constructor(var date: String,var content: String,var state: State) {
  /*  var date: String? = null

    var content: String? = null

    var state: State? = null

    //构造器初始化，参数可以直接调用主构造器参数
    init {
        this.date = date
        this.content = content
        this.state = state
    }*/

    override fun toString(): String {
        val builder = StringBuilder("date=")
        builder.append(date).append("/content=").append(content).append("/state=").append(state)
        return builder.toString()
    }

    //枚举关键字    注解关键字跟java不一致
    enum class State {
        PLAYBACK {
            override fun stateName(): String {
                return "有回放"
            }
        },

        LIVE {
            override fun stateName(): String {
                return "正在直播"
            }
        },

        WAIT {
            override fun stateName(): String {
                return "等待中"
            }
        };

        abstract fun stateName(): String
    }


}