package com.xiaoma.hencoder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoma.core.PxUtils;
import com.xiaoma.studyretrofit.R;

public class MultiToucheViewTwo extends View {
    //多点触控
    private static final float WIDTH_BITMAP = PxUtils.dp2px(200);
    private final Paint paint;
    private float downX;
    private float downY;
    private float offsetX;//x值偏移
    private float offsetY;
    private float imageOffsetX;  //图片初始偏移
    private float imageOffsetY;

    int trackingPointerID;


    public MultiToucheViewTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(getAvatar((int) WIDTH_BITMAP), offsetX, offsetY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0;
        float sumY = 0;
        int pointerCount = event.getPointerCount();
        boolean isPointUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        for (int i = 0; i < pointerCount; i++) {
            if (!(isPointUp && event.getActionIndex() == i)) {  //手指抬起事件需要去掉相关坐标数据
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }
        if (isPointUp) {  //去掉对应的数据
            pointerCount--;
        }
        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;


        switch (event.getActionMasked()) {  //index  会一直变，遍历手指头的序列号  id是不变的，用来跟踪手指
            case MotionEvent.ACTION_DOWN://pointer   x,y,index,id
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                downX = focusX; //获取的是index=0的值
                downY = focusY;
                imageOffsetX = offsetX;
                imageOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = imageOffsetX + focusX - downX;
                offsetY = imageOffsetY + focusY - downY;
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.avatar_img, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.avatar_img, options);
    }
}
