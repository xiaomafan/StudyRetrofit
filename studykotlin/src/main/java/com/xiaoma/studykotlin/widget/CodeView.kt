package com.xiaoma.studykotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.xiaoma.studykotlin.R
import com.xiaoma.studykotlin.core.utils.dp2px
import kotlin.random.Random

//主构造器形式
class CodeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : AppCompatTextView(context, attrs) {


    //初始化参数   apply更合适
    private val paint = Paint().apply {
       isAntiAlias = true
        style = Paint.Style.STROKE
        color = getContext().getColor(R.color.colorAccent)
//        paint.strokeWidth = dp2px(6f)
        strokeWidth = 6f.dp2px()
    }
    //初始化参数
    private val paint1 = Paint().let {
        it.isAntiAlias = true
        it.style = Paint.Style.STROKE
        it.color = getContext().getColor(R.color.colorAccent)
//        paint.strokeWidth = dp2px(6f)
        it.strokeWidth = 6f.dp2px()
        return@let it
    }

    //定义数组通过arrayOf
    private val codeList = arrayOf(
            "kotlin",
            "android",
            "java",
            "http",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip"
    )

    //this调用其他构造函数
//    constructor(context: Context) : this(context, null)

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getContext().getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)

        updateCode()
        //基本数据类型通过intXXX定义数组，防止自动包装，有额外的消耗
//        var arrayof: IntArray = intArrayOf(1, 2, 3)
    }


    //非初始函数不能调用主控构造器上的变量
    fun updateCode() {
        val random: Int = Random.nextInt(codeList.size)
        val code: String = codeList[random]
        text = code
    }

    override fun onDraw(canvas: Canvas) {

        //Int  不可为null，对应的是基本类型    Int? 对应的是包装类型
//        canvas.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
        canvas.drawLine(0f, getHeight().toFloat(), width.toFloat(), 0f, paint)
        super.onDraw(canvas)
    }
}