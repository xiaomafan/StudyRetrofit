package com.xiaoma.studyActivity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xiaoma.studyretrofit.R;

public class Profiler extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testprofiler);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //内存泄露
            }
        },10000000);
    }
}
