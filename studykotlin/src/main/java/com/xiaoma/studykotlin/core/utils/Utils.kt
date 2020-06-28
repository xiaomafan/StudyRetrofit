@file:JvmName("DpAUtils")
package com.xiaoma.studykotlin.core.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.xiaoma.studykotlin.core.BaseApplication

/**
 * 静态函数声明 static
 * 1、在文件声明的就是static
 * 2、object
 * 3、companion object
 */



//顶层函数 包级函数
private val displayMetrics= Resources.getSystem().getDisplayMetrics()

//fun dp2px(dp:Float):Float{
fun  Float.dp2px():Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
}

//扩展函数
fun Activity.log(text:String){
    Log.e("Activity",text)
}

fun Context.log(text:String){
    Log.e("Context",text)
}

//扩展属性
val ViewGroup.firstChild:View
   get() = getChildAt(0)

object Utils {
   /* @JvmStatic  //只在object中有用
    fun toast(string: String?){
        toast(string, Toast.LENGTH_SHORT)
    }*/

    /**
     * 对于第二个参数如果有默认值，则可以直接使用一个参数调用
     * 但是对于java调用，要通过注解 @JvmOverloads  可以保证一个参数的也可以使用
     */
    @JvmOverloads
    @JvmStatic
    fun toast(string: String?,duration:Int=Toast.LENGTH_SHORT){
        Toast.makeText(BaseApplication.currentApplication, string, duration).show()
    }


}