package com.xiaoma.studyretrofit.proxy;

import androidx.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.http.Field;
import retrofit2.http.POST;

public class ProxyUtil {

    private static final Map<Method,String> cacheMethod=new ConcurrentHashMap<>();

    public static <T> T create(final Class<T> service) {

        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, @Nullable Object[] args)
                            throws Throwable {
                        String str = cacheMethod.get(method);
                        if(str!=null){
                            System.out.println("缓存了对象的");
                           return null;
                        }
                        synchronized (cacheMethod){
                            str= cacheMethod.get(method);
                            if(str==null){
                                System.out.println("缓存了对象的");
                                cacheMethod.put(method,"你好啊");
                            }
                        }
                        getRequestType(method);
                        getParameters(method, args);
                        return null;
                    }
                });
    }

    //获取注解方法上的注解内容
    private static void getRequestType(Method method) {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation instanceof POST){
                String value = ((POST) annotation).value();
                System.out.println("动态代理方法注解内容="+value);
            }
        }
    }

    //获取参数
    private static void getParameters(Method method, @Nullable Object[] args) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            int length = parameterAnnotations[i].length;
            if (length == 0) {
                System.out.println("没有添加Annotation参数");
            } else {
                Annotation[] annotation = parameterAnnotations[i];
                for (Annotation anno : annotation) {
                    if (anno instanceof Field) {
                        String key = ((Field) anno).value();
                        System.out.println("动态代理参数key==" + key+"&value="+args[i]);
                    }
                }
            }

        }
    }
}
