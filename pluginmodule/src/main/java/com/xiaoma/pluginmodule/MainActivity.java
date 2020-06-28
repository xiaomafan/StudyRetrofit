package com.xiaoma.pluginmodule;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.xiaoma.proxy.Bird;
import com.xiaoma.proxy.IFlayFactory;

public class MainActivity extends AppCompatActivity {

    TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvContent=findViewById(R.id.tv_content);
//        Utils util = new Utils();
//        util.shout();
        Bird bird = new Bird();
        IFlayFactory.getFlyProxy(bird);
        bird.fly("小马养的");
    }
}
