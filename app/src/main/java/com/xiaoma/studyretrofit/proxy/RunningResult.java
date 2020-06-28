package com.xiaoma.studyretrofit.proxy;

import com.xiaoma.studyretrofit.GitHubService;

public class RunningResult {

    public static void main(String args[]) {
      /*  ZhangSanBuy zhangSanBuy = new ZhangSanBuy();
        ProxyBuy proxyBuy = new ProxyBuy(zhangSanBuy);
        proxyBuy.buyMac();

        System.out.println("---------------");

        DynamicProxy dynamicProxy = new DynamicProxy(zhangSanBuy);
        ICustomer proxyInstance = (ICustomer)Proxy.newProxyInstance(XiaoMingBuy.class.getClassLoader(),
                new Class<?>[]{ICustomer.class}, dynamicProxy);
        proxyInstance.buyMac();
        System.out.println("----------------");*/

        GitHubService service = ProxyUtil.create(GitHubService.class);
        service.serviceApi("xiaoma","123456");

    }
}
