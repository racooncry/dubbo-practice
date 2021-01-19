package com.shenfeng.yxw.dubbo.spi.dubbo;


import org.apache.dubbo.common.extension.ExtensionLoader;

public class DubboSpiDemo {
    public static void main(String[] args) {
        ExtensionLoader<Robot2> extensionLoader =
                ExtensionLoader.getExtensionLoader(Robot2.class);
        Robot2 optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot2 bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
