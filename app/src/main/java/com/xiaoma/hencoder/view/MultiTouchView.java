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

public class MultiTouchView extends View {

    private static final float WIDTH_BITMAP = PxUtils.dp2px(200);
    private final Paint paint;
    private float downX;
    private float downY;
    private float offsetX;//x值偏移
    private float offsetY;
    private float imageOffsetX;  //图片初始偏移
    private float imageOffsetY;

    int trackingPointerID;


    public MultiTouchView(Context context, AttributeSet attrs) {
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
        switch (event.getActionMasked()) {  //index  会一直变，遍历手指头的序列号  id是不变的，用来跟踪手指
            case MotionEvent.ACTION_DOWN:   //pointer   x,y,index,id
                trackingPointerID = event.getPointerId(0);
                downX = event.getX(); //获取的是index=0的值
                downY = event.getY();
                imageOffsetX = offsetX;
                imageOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                int index = event.findPointerIndex(trackingPointerID);
                offsetX = imageOffsetX + event.getX(index) - downX;
                offsetY = imageOffsetY + event.getY(index) - downY;
                invalidate();

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                trackingPointerID = event.getPointerId(actionIndex);

                downX = event.getX(actionIndex); //获取的是index=0的值
                downY = event.getY(actionIndex);
                imageOffsetX = offsetX;
                imageOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                if (pointerId == trackingPointerID) {
                    int newIndex;  //下一刻最大的手指，UP时包括要抬起的手指
                    if (actionIndex == event.getPointerCount() - 1) {
                        newIndex = event.getPointerCount() - 2;
                    } else {
                        newIndex = event.getPointerCount() - 1;
                    }
                    trackingPointerID = event.getPointerId(newIndex);

                    downX = event.getX(newIndex); //获取的是index=0的值
                    downY = event.getY(newIndex);
                    imageOffsetX = offsetX;
                    imageOffsetY = offsetY;
                }
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
