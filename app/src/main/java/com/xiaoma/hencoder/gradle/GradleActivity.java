package com.xiaoma.hencoder.gradle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.service.voice.VoiceInteractionService;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xiaoma.studyretrofit.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GradleActivity extends AppCompatActivity {

    @BindView(R.id.btn_demo)
    Button mBtnDemo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_demo)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_demo:
                testVoice();
                break;
        }
    }

    private void testVoice() {
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentServices(new Intent(VoiceInteractionService.SERVICE_INTERFACE)
                , getPackageManager().MATCH_ALL);
        for (ResolveInfo resolveInfo:resolveInfos){
            Log.e("xiaoma",resolveInfo.serviceInfo.packageName+"//"+resolveInfo.serviceInfo.name);
        }

        Intent intent = new Intent("android.service.voice.VoiceInteractionService");
        intent.setPackage(getPackageName());

//        ComponentName mComponentName = new ComponentName("com.hugo.researchdemo",
//                "com.hugo.researchdemo.voice.MainInteractionService");
//        intent.setComponent(mComponentName);
        startService(intent);
    }
}
