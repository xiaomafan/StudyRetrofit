package com.xiaoma.hencoder.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.GestureDetectorCompat;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.OverScroller;

import com.xiaoma.core.PxUtils;
import com.xiaoma.studyretrofit.R;

public class NewScaleableImageView extends AppCompatImageView {

    private final static float IMAGE_WIDTH = PxUtils.dp2px(300);
    private final static float OVER_SCALE_FACTION = 1.5f;
    Bitmap bitmap;
    float originOffsetX;
    float offsetX;
    float origiOffsetY;
    float offsetY;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float bigScale;
    float smallScale;
    boolean isBigScale;
    //    float scaleFraction;
    float currentFraction;
    ObjectAnimator scaleAnmator;
    GestureDetectorCompat detecotr;
    OverScroller overScroller; //跟scroller的区别是多久滑动那个地方最好
    XiaoGestureListener xiaoGestureListener = new XiaoGestureListener();
    XiaoFlingRunner xiaoFlingRunner = new XiaoFlingRunner();
    XiaoScaleListener scaleListener = new XiaoScaleListener();

    ScaleGestureDetector scaleDetector;

    public NewScaleableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = getAvatar((int) IMAGE_WIDTH);
        //外挂的侦测器
        detecotr = new GestureDetectorCompat(context, xiaoGestureListener);
        //上面的代码已调用下面的代码
//        detecotr.setOnDoubleTapListener(this);
        overScroller = new OverScroller(context);
        scaleDetector = new ScaleGestureDetector(context, scaleListener);
    }

    //处理的接管发送事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = scaleDetector.onTouchEvent(event);
        if (!scaleDetector.isInProgress()) {
            result = detecotr.onTouchEvent(event);
        }
        return result;
//        return scaleDetector.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originOffsetX = (getWidth() - bitmap.getWidth()) / 2f;
        origiOffsetY = (getHeight() - bitmap.getHeight()) / 2f;

        //获取图片放大后的比例，防止放大后超出屏幕,注意是float否则出现缩小的情况
        if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTION;
            smallScale = (float) getWidth() / bitmap.getWidth();
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTION;
        }
        currentFraction = smallScale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动图片时，画布移动
//        canvas.translate(offsetX, offsetY);
//        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        float scaleFraction = (currentFraction - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        //直接改变结果
//        float scale = isBigScale ? bigScale : smallScale;
        //动画效果获取的缩放比例
//        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        float scale = currentFraction;
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
        //图片移动
        canvas.drawBitmap(bitmap, originOffsetX, origiOffsetY, paint);
    }

    //属性动画内容

    public float getCurrentFraction() {
        return currentFraction;
    }

    public void setCurrentFraction(float currentFraction) {
        this.currentFraction = currentFraction;
        invalidate();
    }

   /* public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }*/

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnmator == null) {
//            scaleAnmator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
            scaleAnmator = ObjectAnimator.ofFloat(this, "currentFraction", 0);
        }
        scaleAnmator.setFloatValues(smallScale, bigScale);

        return scaleAnmator;
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


    private void fixOffsetX() {
        offsetX = Math.min(offsetX, (bitmap.getWidth() * bigScale - getWidth()) / 2);
        offsetX = Math.max(offsetX, -(bitmap.getWidth() * bigScale - getWidth()) / 2);
    }


    private void fixOffsetY() {
        offsetY = Math.min(offsetY, (bitmap.getHeight() * bigScale - getHeight()) / 2);
        offsetY = Math.max(offsetY, -(bitmap.getHeight() * bigScale - getHeight()) / 2);
    }


    //滑动动画应该有系统控制
    private void refresh() {
        overScroller.computeScrollOffset();
        offsetY = overScroller.getCurrY();
        offsetX = overScroller.getCurrX();
        invalidate();
    }


    private class XiaoGestureListener implements GestureDetector.OnGestureListener
            , GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //distanceX 是旧坐标减去新坐标的距离
            if (isBigScale) {
                offsetX = offsetX - distanceX;
                fixOffsetX();
                offsetY = offsetY - distanceY;
                fixOffsetY();
                invalidate();//注意不能划出边界，需要确定边界
            }

            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (isBigScale) {
                overScroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY
                        , (int) -(bitmap.getWidth() * bigScale - getWidth()) / 2,
                        (int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        (int) -(bitmap.getHeight() * bigScale - getHeight()) / 2,
                        (int) (bitmap.getHeight() * bigScale - getHeight()) / 2, 100, 100);

                //下一帧刷新
                postOnAnimation(xiaoFlingRunner);//下一帧去执行，post立即去执行
       /*     for (int i=10;i<=100;i+=10){
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      refresh();
                    }
                },i);
            }*/
            }
            return false;
        }


        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            isBigScale = !isBigScale;
            if (isBigScale) {
                offsetX = (e.getX() - getWidth() / 2f) * (1 - bigScale / smallScale);
                offsetY = (e.getY() - getHeight() / 2f) * (1 - bigScale / smallScale);
                fixOffsetX();
                fixOffsetY();

                //变完之后是大的
                getScaleAnimator().start();
            } else {
                getScaleAnimator().reverse();
            }
//        invalidate();
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    }

    private class XiaoFlingRunner implements Runnable {
        @Override
        public void run() {
            if (overScroller.computeScrollOffset()) {
                offsetY = overScroller.getCurrY();
                offsetX = overScroller.getCurrX();
                invalidate();
                postOnAnimation(this);//下一帧做
            }
        }
    }

    private class XiaoScaleListener implements ScaleGestureDetector.OnScaleGestureListener {
        float initialScale;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            currentFraction = initialScale * detector.getScaleFactor();//手指放缩系数
            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialScale = currentFraction;
            //返回true才调用两个其它方法
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}
