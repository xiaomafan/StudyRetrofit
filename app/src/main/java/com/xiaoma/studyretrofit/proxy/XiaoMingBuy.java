package com.xiaoma.studyretrofit.proxy;

public class XiaoMingBuy implements ICustomer {


    @Override
    public void buyMac() {
        System.out.println("ICustomer:=小明买东西");
    }
}
