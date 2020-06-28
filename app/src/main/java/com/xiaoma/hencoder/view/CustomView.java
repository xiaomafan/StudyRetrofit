package com.xiaoma.hencoder.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class CustomView extends ViewGroup {

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }
}
