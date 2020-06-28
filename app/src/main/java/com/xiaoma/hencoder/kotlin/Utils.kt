package com.xiaoma.hencoder.kotlin

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


private val displayMetrics = Resources.getSystem().displayMetrics

fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

object Utils {   //里面的属性和方法都是静态的
    fun toast(string: String?) {
        toast(string, Toast.LENGTH_SHORT)
    }

    private fun toast(string: String?, duration: Int) {
        Toast.makeText(BaseApplication.currentApplication(), string, duration).show()
    }

    fun drawBadge(activity: AppCompatActivity) {
        val decorView: ViewGroup = activity.window.decorView as ViewGroup
        val view = View(activity)
        view.setBackgroundColor(Color.RED)
        decorView.addView(view, 200, 100)
    }
}