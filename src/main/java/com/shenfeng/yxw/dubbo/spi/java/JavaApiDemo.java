package com.shenfeng.yxw.dubbo.spi.java;

import java.util.ServiceLoader;

public class JavaApiDemo {
    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}
