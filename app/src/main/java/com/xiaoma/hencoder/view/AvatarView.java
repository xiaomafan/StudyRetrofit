package com.xiaoma.hencoder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaoma.core.PxUtils;
import com.xiaoma.studyretrofit.R;

public class AvatarView extends View {

    Bitmap avatar;
    private static final float RADIUS = PxUtils.dp2px(200);
    private static final float PADDING = PxUtils.dp2px(40);
    private static final float BOARD_PADDING = PxUtils.dp2px(5);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    RectF cut = new RectF();
    RectF board = new RectF();

    {
        avatar = getAvatar((int) RADIUS);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cut.set(PADDING, PADDING, PADDING + RADIUS, PADDING + RADIUS);
        board.set(PADDING - BOARD_PADDING, PADDING - BOARD_PADDING
                , PADDING + BOARD_PADDING + RADIUS, PADDING + BOARD_PADDING + RADIUS);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(board, paint);
        int saved = canvas.saveLayer(cut, paint);
        canvas.drawOval(PADDING, PADDING, PADDING + RADIUS, PADDING + RADIUS, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(avatar, PADDING, PADDING, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved);
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
