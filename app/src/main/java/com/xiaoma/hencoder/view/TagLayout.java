package com.xiaoma.hencoder.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {

    private List<Rect> childrenBounds = new ArrayList<Rect>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthUsed = 0;
        int heightUsed = 0;
        int lineHeight = 0;
        int lineWidthUsed=0;

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0,
                    heightMeasureSpec, heightUsed);
            if(widthMode!=MeasureSpec.UNSPECIFIED && lineWidthUsed+child.getMeasuredWidth()>widthSize){
                lineWidthUsed=0;
                heightUsed+=lineHeight;
            }
            measureChildWithMargins(child, widthMeasureSpec, 0,
                    heightMeasureSpec, heightUsed);
            Rect rect;
            if (childrenBounds.size() <= i) {
                rect = new Rect();
                childrenBounds.add(rect);
            } else {
                rect = childrenBounds.get(i);
            }
            rect.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(),
                    heightUsed + child.getMeasuredHeight());
            lineWidthUsed += child.getMeasuredWidth();
            widthUsed=Math.max(lineWidthUsed,widthUsed);
            lineHeight = Math.max(lineHeight, child.getMeasuredHeight());
        }

        int measureWidth = widthUsed;
        heightUsed += lineHeight;
        int measureHeight = heightUsed;

        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childBounds = childrenBounds.get(i);
            child.layout(childBounds.left, childBounds.top,
                    childBounds.right, childBounds.bottom);
//            child.layout(0, 0, (r - l) / 2, (b - t) / 2);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
