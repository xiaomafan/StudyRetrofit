package com.xiaoma.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaoma.core.PxUtils;

public class DashBoard extends View {


    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path dash = new Path();
    private static final float RADIUS = PxUtils.dp2px(120);
    private static final float LENGTH = PxUtils.dp2px(100);
    private static final float ANGLE = 120;
    PathMeasure pathMeasure;
    private PathDashPathEffect pathDashPathEffect;
    Path path;
    private float pathLenth;
    //图形绘制  测量


    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(PxUtils.dp2px(2));
        //横向是宽度，竖向是长度
        dash.addRect(0, 0, PxUtils.dp2px(2)
                , PxUtils.dp2px(10), Path.Direction.CCW);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS
                , getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS
                , 90 + ANGLE / 2, 360 - ANGLE);
        pathMeasure = new PathMeasure(path, false);
        pathLenth = pathMeasure.getLength() - PxUtils.dp2px(2);
        pathDashPathEffect = new PathDashPathEffect(dash, pathLenth / 20
                , 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画原图形
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS
                , getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS
                , 90 + ANGLE / 2, 360 - ANGLE, false, paint);
        //画刻度
        paint.setPathEffect(pathDashPathEffect);

        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS
                , getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS
                , 90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);

        //画指针
        canvas.drawCircle(getWidth()/2,getHeight()/2,4,paint);
        canvas.drawLine(getWidth() / 2, getHeight() / 2
                , getWidth() / 2 + (float) Math.cos(Math.toRadians(getAngleForMark(8))) * LENGTH
                , getHeight() / 2 + (float) Math.sin(Math.toRadians(getAngleForMark(8))) * LENGTH, paint);
    }

    float getAngleForMark(int mark) {
        return 90 + ANGLE / 2 + (360 - ANGLE) / 20 * mark;
    }
}
