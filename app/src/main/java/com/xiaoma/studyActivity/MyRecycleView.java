package com.xiaoma.studyActivity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecycleView extends RecyclerView {

    public MyRecycleView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        onLayoutListener.beforeLayout();
        super.onLayout(changed, l, t, r, b);
    }
}
