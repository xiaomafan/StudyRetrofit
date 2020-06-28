package com.xiaoma;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class BuildTypeUtils {

    public static void drawBadge(AppCompatActivity activity){
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        View view = new View(activity);
        view.setBackgroundColor(Color.GREEN);
        decorView.addView(view,200,100);
    }
}
