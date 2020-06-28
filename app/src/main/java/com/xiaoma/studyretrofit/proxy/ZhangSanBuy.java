package com.xiaoma.studyretrofit.proxy;

public class ZhangSanBuy implements ICustomer {

    @Override
    public void buyMac() {
        System.out.println("ICustomer:=张三买东西");
    }
}
