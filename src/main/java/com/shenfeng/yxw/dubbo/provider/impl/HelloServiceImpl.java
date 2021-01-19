package com.shenfeng.yxw.dubbo.provider.impl;

import com.shenfeng.yxw.dubbo.provider.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        // System.out.println("Hello: "+name);
        return "Hello: "+name;
    }
}
