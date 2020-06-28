package com.xiaoma.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaoma.core.PxUtils;

public class PieChat extends View {

    private static final float RADIUS = PxUtils.dp2px(120);
    private static final float PULL_DISTANCE = PxUtils.dp2px(10);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF bounds = new RectF();
    int[] COLORS = {Color.parseColor("#6CF770"),
            Color.parseColor("#F76046"),
            Color.parseColor("#5B20F7"),
            Color.parseColor("#39F7CC")};
    int[] ANGLES = {60, 100, 120, 80};

    private static final int PULLED_INDEX = 1;

    public PieChat(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS
                , getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngle = 0;
        for (int i = 0; i < COLORS.length; i++) {
            paint.setColor(COLORS[i]);
            if (i == PULLED_INDEX) {
                canvas.save();
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + ANGLES[i] / 2)) * PULL_DISTANCE
                        , (float) Math.sin(Math.toRadians(currentAngle + ANGLES[i] / 2)) * PULL_DISTANCE);
            }
            canvas.drawArc(bounds, currentAngle, ANGLES[i], true, paint);
            if (i == PULLED_INDEX) {
                canvas.restore();
            }
            currentAngle += ANGLES[i];
        }
    }
}
