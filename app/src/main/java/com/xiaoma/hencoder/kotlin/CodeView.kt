package com.xiaoma.hencoder.kotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import com.xiaoma.studyretrofit.R
import kotlin.random.Random

class CodeView constructor(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private var paint = Paint()
    private var codeList = arrayOf(   //定义数组,基本数组类型是intArrayOf
            "kotlin",
            "android",
            "java"
    )

    constructor(context: Context) : this(context, null)


    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.toFloat())
        setBackgroundColor(getContext().getColor(R.color.colorPrimary))
        gravity = Gravity.CENTER
        setTextColor(Color.WHITE)
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = getContext().getColor(R.color.colorAccent)
        paint.strokeWidth = dp2px(4.toFloat())
        updateCode()
    }

    fun updateCode() {
        val random = Random.nextInt(codeList.size)  //数组大小用size，不是length
        val code = codeList[random]
        text = code
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(), 0.toFloat(), paint)
        super.onDraw(canvas)
    }


}
