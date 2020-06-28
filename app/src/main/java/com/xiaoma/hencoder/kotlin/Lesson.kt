package com.xiaoma.hencoder.kotlin

class Lesson constructor(date:String?,content:String?,state:State?){
     var date:String?=null
     var content:String?=null
     var state:State?=null

    init{
        this.date=date
        this.content=content
        this.state=state
    }


    enum class State{
        PLAYBACK{
            override fun stateName(): String {
                return "有回放"
            }
        },
        LIVE{
            override fun stateName(): String {
                return "正在直播"
            }
        },
        WAIT{
            override fun stateName(): String {
                return "等待中"
            }
        };


         abstract fun stateName():String
    }
}