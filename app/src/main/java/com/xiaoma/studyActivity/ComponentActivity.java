package com.xiaoma.studyActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaoma.mqtt.MQTTService;
import com.xiaoma.studyretrofit.DemoAdapter;
import com.xiaoma.studyretrofit.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComponentActivity extends AppCompatActivity {

    @BindView(R.id.btn_get)
    Button mBtnGet;

    private static final String TAG = "ComponentActivity";
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.btn_stop)
    Button mBtnStop;
    @BindView(R.id.btn_bind)
    Button mBtnBind;
    @BindView(R.id.btn_unbind)
    Button mBtnUnbind;
    @BindView(R.id.btn_jump)
    Button mBtnJump;
    private Intent intent;
    private MQTTService.MyBinder myBinder;

    private static Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e(TAG, "onCreate: ");
        mHandler.sendEmptyMessage(0);
        ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();
        threadLocal.set(true);
        threadLocal.get();
//        mHandler.obtainMessage()

    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvList.setLayoutManager(layoutManager);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("测试数据" + i);
        }
        DemoAdapter adapter = new DemoAdapter(this, data);
        mRvList.setAdapter(adapter);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MQTTService.MyBinder) service;
            Log.e(TAG, "onServiceConnected: " + myBinder.getserver(1, 2));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: ");
        }
    };

    @OnClick({R.id.btn_get, R.id.btn_start, R.id.btn_stop, R.id.btn_bind, R.id.btn_unbind, R.id.btn_jump})
    public void onClick(View v) {
        intent = new Intent(this, MQTTService.class);
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_get:
//                Log.e(TAG, "onClick: "+myBinder.getserver(2,3));
//                getData();
                sendData();
                break;
            case R.id.btn_start:
                startService(intent);
                break;
            case R.id.btn_stop:
                if (intent != null) {
                    stopService(intent);
                }
                break;
            case R.id.btn_bind:
                bindService(intent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(connection);
                break;
            case R.id.btn_jump:
                jump2B();
                break;
        }
    }

    private void jump2B() {
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("haha",1);
        Bundle bundle = new Bundle();
        bundle.putInt("aaa",2);
        intent.putExtra("aa",bundle);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    private void sendData() {
        MQTTService.publish("CSDN 一口仨馍");
    }


}
