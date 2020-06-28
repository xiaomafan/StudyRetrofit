package com.xiaoma.core;

import android.content.res.Resources;
import android.util.TypedValue;

public class PxUtils {

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                , dp, Resources.getSystem().getDisplayMetrics());
    }

}
