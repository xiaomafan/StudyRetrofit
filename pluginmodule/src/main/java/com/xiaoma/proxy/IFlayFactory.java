package com.xiaoma.proxy;

import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IFlayFactory {

    public static void getFlyProxy(IFly fly) {
        Proxy.newProxyInstance(fly.getClass().getClassLoader(), fly.getClass().getInterfaces()
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("方法执行前");
                        Object invoke = method.invoke(proxy, args);
                        System.out.println("方法执行后");
                        return invoke;
                    }
                });
    }
}


