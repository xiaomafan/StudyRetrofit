package com.xiaoma.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiaoma.studyActivity.ComponentActivity;
import com.xiaoma.studyretrofit.DemoRetrofitActivity;
import com.xiaoma.studyretrofit.R;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends Activity {

    @BindView(R.id.lv_demo)
    ListView mLvDemo;

    private static final int POSITON_RETROFIT = 0;
    private static final int POSITON_ACTIVITY = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mLvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jump2Next(position);
            }
        });

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        handler.sendEmptyMessage(0);



//        Executors.newCachedThreadPool()
    }

    private void jump2Next(int position) {
        switch (position) {
            case POSITON_RETROFIT:
                startActivity(new Intent(this, DemoRetrofitActivity.class));
                break;
            case POSITON_ACTIVITY:
                startActivity(new Intent(this, ComponentActivity.class));
                break;
        }
    }

}
