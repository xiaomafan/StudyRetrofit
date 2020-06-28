package com.xiaoma.hencoder.kotlin

class User constructor() {
    //可null
    var userName: String? = null
    var passWorld: String? = null
    @JvmField    //便于类名.变量访问，否则必须getCode访问
    var code: String? = null


    constructor(username: String?, password: String?,code:String?):this() {
        this.userName = username
        this.passWorld = password
        this.code=code
    }

}