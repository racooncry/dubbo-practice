package com.shenfeng.yxw.dubbo.framework;

import com.power.common.util.UUIDUtil;
import com.shenfeng.yxw.dubbo.protocol.dubbo.DubboProtocol;
import com.shenfeng.yxw.dubbo.protocol.http.HttpClient;
import com.shenfeng.yxw.dubbo.protocol.http.HttpProtocol;
import com.shenfeng.yxw.dubbo.registers.RemoteMapRegister;


import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * @author yu 2020/11/8.
 */
public class RpcProxyFactory<T> {

    public static Protocol getProtocol() {
        // java spi
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();


        // 工厂模式
//        String protocol = System.getProperty("protocolName");
//        System.out.println("Used protocol is:" + protocol);
//        if (Objects.isNull(protocol)) {
//            protocol = "http";
//        }
//        switch (protocol) {
//            case "dubbo":
//                return new DubboProtocol();
//            default:
//                return new HttpProtocol();
//        }
    }
}
