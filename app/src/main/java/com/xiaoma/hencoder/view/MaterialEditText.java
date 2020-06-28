package com.xiaoma.hencoder.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.appcompat.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.xiaoma.core.PxUtils;
import com.xiaoma.studyretrofit.R;

public class MaterialEditText extends AppCompatEditText {

    private String hint;
    private static final float TEXT_SIZE = PxUtils.dp2px(12);
    private static final float TEXT_MARGIN = PxUtils.dp2px(8);
    private static final float VERTICAL_OFFSET = PxUtils.dp2px(38);
    private static final float HORIZONTAL_OFFSET = PxUtils.dp2px(5);
    private static final float VERTICAL_OFFSET_EXTRA = PxUtils.dp2px(16);
    private Paint paint;
    Rect backgroundPaddings = new Rect();
    private boolean floatingLabelShown;
    private boolean userFloatingLabel = true;
    private float floatingLabelFraction;
    private ObjectAnimator animator;

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        userFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_userFloatingLabel, true);
        typedArray.recycle();

        hint = getHint().toString();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(TEXT_SIZE);

        getBackground().getPadding(backgroundPaddings);
        if (userFloatingLabel) {
            floatingLabelFraction = 0f;
            setPadding(backgroundPaddings.left, (int) (backgroundPaddings.top + TEXT_SIZE + TEXT_MARGIN),
                    backgroundPaddings.right, backgroundPaddings.bottom);
        } else {
            floatingLabelFraction = 1;
            setPadding(backgroundPaddings.left, backgroundPaddings.top
                    , backgroundPaddings.right, backgroundPaddings.bottom);
        }
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (userFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(s)) {
                        floatingLabelShown = !floatingLabelShown;
                        getAnimator().reverse();
                    } else if (!TextUtils.isEmpty(s) && !floatingLabelShown) {
                        floatingLabelShown = !floatingLabelShown;
                        getAnimator().start();
                    }
                }
            }
        });


    }


    private ObjectAnimator getAnimator() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(MaterialEditText.this, "floatingLabelFraction",
                    0, 1);
        }
        return animator;
    }


    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    public void setUseFloatingLabel(boolean useFloatingLabel) {
        if (this.userFloatingLabel != useFloatingLabel) {
            this.userFloatingLabel = useFloatingLabel;
            getBackground().getPadding(backgroundPaddings);
            if (useFloatingLabel) {
                setPadding(backgroundPaddings.left, (int) (backgroundPaddings.top + TEXT_SIZE + TEXT_MARGIN), backgroundPaddings.right, backgroundPaddings.bottom);
            } else {
                setPadding(backgroundPaddings.left, backgroundPaddings.top, backgroundPaddings.right, backgroundPaddings.bottom);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAlpha((int) (floatingLabelFraction * 0xff));
        canvas.drawText(hint, HORIZONTAL_OFFSET, VERTICAL_OFFSET - floatingLabelFraction * VERTICAL_OFFSET_EXTRA, paint);

    }
}
