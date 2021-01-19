package com.shenfeng.yxw.dubbo.consumer;

import com.shenfeng.yxw.dubbo.framework.Invocation;
import com.shenfeng.yxw.dubbo.framework.ProxyFactory;
import com.shenfeng.yxw.dubbo.protocol.http.HttpClient;
import com.shenfeng.yxw.dubbo.provider.HelloService;

public class Consumer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        System.out.println("res: " + helloService.sayHello("123"));

    }
}
