package com.shenfeng.yxw.dubbo.provider;

import com.shenfeng.yxw.dubbo.framework.Protocol;
import com.shenfeng.yxw.dubbo.framework.ProxyFactory;
import com.shenfeng.yxw.dubbo.framework.RpcProxyFactory;
import com.shenfeng.yxw.dubbo.framework.URL;
import com.shenfeng.yxw.dubbo.protocol.http.HttpServer;
import com.shenfeng.yxw.dubbo.provider.impl.HelloServiceImpl;
import com.shenfeng.yxw.dubbo.registers.RemoteMapRegister;


public class Provider {
    public static void main(String[] args) {
        protocol();
    }


    /**
     * 使用协议实现
     */
    public static void protocol() {
        URL url = URL.builder().hostname("localhost").port(8080).build();
        // 利用文件代替注册中心
        RemoteMapRegister.register(HelloService.class.getName(), url);
        // 注册服务
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        Protocol protocol = RpcProxyFactory.getProtocol();
        protocol.start(url);
    }

}
