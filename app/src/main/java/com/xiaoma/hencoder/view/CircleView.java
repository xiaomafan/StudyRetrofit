package com.xiaoma.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoma.core.PxUtils;

public class CircleView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float RADIU = (int) PxUtils.dp2px(200);
    private float PADDING = (int) PxUtils.dp2px(8);

    //自定义View需要处理wrap_content及padding

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = (int) ((PADDING + RADIU) / 2);
        int measureWidth = resolveSize(size, widthMeasureSpec);
        int measureHeight = resolveSize(size, heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);

//        resolveSize(widthMeasureSpec)

        //原理的内容，需要处理子控件超过父控件的情况
     /*   int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension((int) PxUtils.dp2px(200), (int) PxUtils.dp2px(200));
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension((int) PxUtils.dp2px(200), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, (int) PxUtils.dp2px(200));
        }*/
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingButtom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingButtom;
        int RADIU = Math.min(width, height) / 2;
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, RADIU, paint);
    }
}
