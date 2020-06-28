package com.xiaoma.hencoder.plugin;

import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoma.hencoder.utils.Title;
import com.xiaoma.studyretrofit.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dalvik.system.PathClassLoader;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class PluginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.hot_fix)
    Button mHotFix;
    @BindView(R.id.btn_operate)
    Button mBtnOperate;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.remove_fix)
    Button mRemoveFix;
    @BindView(R.id.kill_self)
    Button mKillSelf;
    private File apk;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);
        initView();
        apk = new File(getCacheDir() + "/hotfix_mine.dex");
    }

    private void initView() {
        mBtnOperate = (Button) findViewById(R.id.btn_operate);
        mBtnOperate.setOnClickListener(this);
        mTvContent = (TextView) findViewById(R.id.tv_content);
    }


    @OnClick({R.id.hot_fix, R.id.btn_operate, R.id.remove_fix, R.id.kill_self})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.hot_fix:
                showPluginData();
                break;
            case R.id.btn_operate:
                hotFixData();
                break;
            case R.id.remove_fix:
                if (apk.exists()) {
                    apk.delete();
                }
                break;
            case R.id.kill_self:
                Process.killProcess(Process.myPid());
                break;
        }
    }


    private void hotFixData() {
        String name = getClassLoader().getClass().getName();
//        mTvContent.setText(name + "//" + new Util().showMsg());
//        new Title().getTitle();
        Title title = new Title();
        mTvContent.setText(title.getTitle());
//        PathClassLoader

       /* try {
            Class clazz = Class.forName("com.xiaoma.hencoder.utils.Util");
            Constructor constructor = clazz.getDeclaredConstructors()[0];
            Object util = constructor.newInstance();
            Method showMsg = clazz.getMethod("showMsg");
            showMsg.invoke(util);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }*/
    }

    private void showPluginData() {

        try (Source source = Okio.source(getAssets().open("apk/hotfix_mine.dex"));
             BufferedSink sink = Okio.buffer(Okio.sink(apk))) {
            sink.writeAll(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





    /*    apk = new File(getCacheDir() + "/pluginmodule-debug.apk");
        try (Source source = Okio.source(getAssets().open("apk/pluginmodule-debug.apk"));
             BufferedSink sink = Okio.buffer(Okio.sink(apk))) {
            sink.writeAll(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    /*    DexClassLoader classLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, null);
        try {
            Class pluginUtilsClass = classLoader.loadClass("com.xiaoma.pluginmodule.Utils");
            Constructor utilsConstructor = pluginUtilsClass.getDeclaredConstructors()[0];
            Object utils = utilsConstructor.newInstance();
            Method shoutMethod = pluginUtilsClass.getDeclaredMethod("shout");
            shoutMethod.invoke(utils);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
    }

}
