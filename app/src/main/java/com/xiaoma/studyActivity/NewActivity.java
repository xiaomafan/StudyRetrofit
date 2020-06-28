package com.xiaoma.studyActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.core.app.ActivityManagerCompat;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.xiaoma.studyretrofit.R;

import java.lang.ref.WeakReference;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NewActivity";
    @BindView(R.id.btn_show)
    Button mBtnShow;
    @BindView(R.id.btn_notification)
    Button mBtnNotification;

    /**
     * 通知栏
     */

    private static class MyHandler extends Handler {
        private final WeakReference<NewActivity> mAct;

        public MyHandler(NewActivity activity,Callback callback) {
            super(callback);
            mAct = new WeakReference<NewActivity>(activity);
//            ViewGroup
//            LinearLayout
//            View
//            FrameLayout


        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.bind(this);
        Log.e(TAG, "onCreate: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channeID = "chat";
            String channelName = "聊天信息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channeID, channelName, importance);

            channeID = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channeID, channelName, importance);
        }



        MyHandler handler = new MyHandler(this, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });

        Message message = handler.obtainMessage();
        handler.sendMessage(message);
        handler.sendEmptyMessage(0);
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        mBtnShow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        Stack<Integer> stack = new Stack<>();
        RecyclerView recyclerView=new RecyclerView(this);
//        RecyclerView.RecycledViewPool
//        recyclerView.setRecycledViewPool();
         recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//       recyclerView.setLayoutManager();
        ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

        new Runnable(){
            @Override
            public void run() {
                      threadLocal.set(true);

            }
        };

        try{

        }finally {

        }

    }

    @OnClick({R.id.btn_show, R.id.btn_notification})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_show:
//                studyThreadLocal();
//                sendSubscribeMsg();
                startActivity(new Intent(this,NotProfiler.class));
                break;
            case R.id.btn_notification:
//                sendChatMsg();
                startActivity(new Intent(this,Profiler.class));
//                startService()
//                ActivityManager

                break;
        }
    }



    public void sendChatMsg() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);
    }


    public void sendSubscribeMsg() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setAutoCancel(true)
                .setNumber(3)
                .build();
        manager.notify(2, notification);
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e(TAG, "onWindowFocusChanged: ");
    }


    private void studyThreadLocal() {
        final ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();
        threadLocal.set(true);
        Log.e(TAG, Thread.currentThread() + "=" + threadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, Thread.currentThread() + "=" + threadLocal.get());
            }
        }).start();
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


}
