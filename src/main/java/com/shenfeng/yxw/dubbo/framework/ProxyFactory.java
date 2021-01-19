package com.shenfeng.yxw.dubbo.framework;

import com.shenfeng.yxw.dubbo.protocol.http.HttpClient;
import com.shenfeng.yxw.dubbo.provider.HelloService;
import com.shenfeng.yxw.dubbo.registers.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Protocol protocol = RpcProxyFactory.getProtocol();
                        Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(),
                                method.getParameterTypes(), args);

                        // 从注册中心获取服务地址
                        List<URL> urlList = RemoteMapRegister.get(interfaceClass.getName());
                        URL url = LoadBalance.random(urlList);
                        String result = protocol.send(url, invocation);
                        return result;
                    }
                });


    }

}
