package com.xiaoma;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Choreographer;

import androidx.annotation.Nullable;

import com.xiaoma.studyretrofit.R;

public class DemoActivity extends Activity {

    private static final String TAG = "DemoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        int MODE_SHIFT = 30;
        int MODE_MASK = 0x3 << MODE_SHIFT;
        int UNSPECIFIED = 0 << MODE_SHIFT;
        int EXACTLY = 1 << MODE_SHIFT;
        int AT_MOST = 2 << MODE_SHIFT;

        Log.e(TAG, "MODE_MASK:=" + Int2FullBinaryString(MODE_MASK));
        Log.e(TAG, "UNSPECIFIED:=" + Int2FullBinaryString(UNSPECIFIED));
        Log.e(TAG, "EXACTLY:=" + Int2FullBinaryString(EXACTLY));
        Log.e(TAG, "AT_MOST:=" + Int2FullBinaryString(AT_MOST));

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {

            }
        });

    }

    public static String Int2FullBinaryString(int num) {
        char[] chs = new char[Integer.SIZE];
        for (int i = 0; i < Integer.SIZE; i++) {
            chs[Integer.SIZE - 1 - i] = (char) (((num >> i) & 1) + '0');
        }
        return new String(chs);
    }
}
