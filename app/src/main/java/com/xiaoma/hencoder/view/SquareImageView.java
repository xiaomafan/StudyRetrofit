package com.xiaoma.hencoder.view;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

public class SquareImageView extends AppCompatImageView {


    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //getMeasureWidth获取的是测量的宽高，getWidth获取的是layout内r-l值
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int size=Math.max(measuredHeight,measuredWidth);
        setMeasuredDimension(size,size);//保存结果
    }

    //如果只是Layout里面处理宽和高，那么父控件无法获取到真是的宽高，导致后面的控件盖着前面的控件，一般不处理

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
//        super.layout(l, t, r + 150, b + 100);
    }

    //子View的layout方法
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
