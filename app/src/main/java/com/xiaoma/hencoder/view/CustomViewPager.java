package com.xiaoma.hencoder.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

public class CustomViewPager extends ViewGroup {


    OverScroller overScroller;
    ViewConfiguration viewConfiguration;
    int maxVelocity;
    int minVelocity;
    VelocityTracker velocityTracker;
    float downX;
    float downY;
    private int downScrollX;
    boolean scrolling;


    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        overScroller = new OverScroller(context);
        viewConfiguration = ViewConfiguration.get(context);
        maxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        minVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        velocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int chihldLeft = 0;
        int childRight = getWidth();
        int childTop = 0;
        int childButtom = getHeight();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(chihldLeft, childTop, childRight, childButtom);
            chihldLeft += getWidth();
            childRight += getWidth();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear();
        }
        velocityTracker.addMovement(ev);
        boolean result = false;
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                scrolling = false;
                downX = ev.getX();
                downY = ev.getY();
                downScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!scrolling) {
                    float distanceX = ev.getX() - downX;
                    if (Math.abs(distanceX) > viewConfiguration.getScaledPagingTouchSlop()) {
                        scrolling = true;
                        result = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear();
        }
        velocityTracker.addMovement(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                downScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx=downScrollX+downX-event.getX();
                if(dx>getWidth()){
                    dx=getWidth();
                }else if(dx<0){
                    dx=0;
                }
                scrollTo((int) dx,0);
                break;
            case MotionEvent.ACTION_UP:
                velocityTracker.computeCurrentVelocity(1000,maxVelocity);
                float xVelocity = velocityTracker.getXVelocity();
                int scrollX=getScrollX();
                int targetPage;
                if(Math.abs(xVelocity)<minVelocity){
                    targetPage=scrollX>getWidth()/2?1:0;
                }else {
                    targetPage=xVelocity<0?1:0;
                }
                int scrollDistance=targetPage==1?(getWidth()-scrollX):-scrollX;
                overScroller.startScroll(getScrollX(),0,scrollDistance,0);
                postInvalidateOnAnimation();
                break;
        }

        return true;
    }

    @Override
    public void computeScroll() {
       if(overScroller.computeScrollOffset()){
           scrollTo(overScroller.getCurrX(),overScroller.getCurrY());
           postInvalidateOnAnimation();
       }
    }
}
