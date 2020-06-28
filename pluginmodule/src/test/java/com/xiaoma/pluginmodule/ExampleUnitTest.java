package com.xiaoma.pluginmodule;

import com.xiaoma.RxjavaCallAdapterFactory;
import com.xiaoma.adapter.Retrofit;
import com.xiaoma.proxy.Bird;
import com.xiaoma.proxy.IFlayFactory;
import com.xiaoma.proxy.IFly;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//     Retrofit retrofit=  new Retrofit.Builder()
//             .addCallAdapterFactroy(new RxjavaCallAdapterFactory())
//             .build();
//       retrofit.create();

        Bird bird = new Bird();
        IFlayFactory.getFlyProxy(bird);
        bird.fly("小马养的");
    }
}