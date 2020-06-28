package com.xiaoma.studykotlin.entity

import com.xiaoma.studykotlin.lesson.entity.Lesson

//kotlin默认不是继承Object,默认是Any
//class User:Any {
//data 关键字是自动处理hashcode equaul  toString
data class User constructor(var username: String?,var password: String?,var code: String?) {
//    @JvmField
//    var userName: String? = null
    //    set(value) {}
//    var password: String? = null
//    var code: String? = null

//    lateinit var age:Lesson

    constructor() : this(null, null, null)

    /*  constructor(username:String?,password:String?,code:String?) : this() {
  //        Lesson=Lesson("123","234",Lesson.State.LIVE)
          this.userName=username
          this.password=password
          this.code=code


      }*/
}
