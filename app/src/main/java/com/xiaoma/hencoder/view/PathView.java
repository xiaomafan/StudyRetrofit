package com.xiaoma.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaoma.core.PxUtils;

public class PathView extends View {


    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    private static final float RADIUS = PxUtils.dp2px(60);
    PathMeasure pathMeasure;

    //图形绘制  测量


    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStrokeWidth(PxUtils.dp2px(3));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.addCircle(getWidth() / 2, getHeight() / 2
                , RADIUS, Path.Direction.CCW);

        path.addRect(getWidth() / 2 - RADIUS, getHeight() / 2
                , getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS * 2, Path.Direction.CCW);
        path.addCircle(getWidth() / 2, getHeight() / 2, RADIUS * 2, Path.Direction.CCW);
        path.setFillType(Path.FillType.EVEN_ODD);
        pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(100, 100, 400, 400, paint);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2
//                , PxUtils.dp2px(60), paint);
//        dash.setFillType(Path.FillType.WINDING);//默认值
//          dash.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);
    }
}
