package com.xiaoma.studyretrofit.proxy;

public class ProxyBuy implements ICustomer {

    private ICustomer customer;

    public ProxyBuy(ICustomer customer) {
        this.customer = customer;
    }

    @Override
    public void buyMac() {
        System.out.println("ICustomer:=代理购买");
        customer.buyMac();
    }
}
