package com.xiaoma.studyretrofit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private ICustomer customer;

    public DynamicProxy(ICustomer customer) {
        this.customer = customer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理执行的操作="+method.getName());
        Object result = method.invoke(customer, args);
        return result;
    }
}
