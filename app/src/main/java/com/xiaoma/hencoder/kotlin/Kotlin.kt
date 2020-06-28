package com.xiaoma.hencoder.kotlin

fun main(){
   println("Hello World!")
   println(doublex(5))
   println(name+"///"+ age)
   name="Android"
   age=20
   println(name+"///"+ age)
//   println("---"+ javaObj.name)
}

fun doublex(x:Int):Int{
   return x*2
}

var age=18
var ageV:Int=18
//var代表可读可写，val代表可读
var name="Kotlin"
//var javaObj= Java()