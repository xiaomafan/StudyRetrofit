package com.xiaoma.hencoder.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class OneHundredView extends View {
    public OneHundredView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(200,200);
    }



}
