package com.shenfeng.yxw.dubbo.spi.dubbo;


public class Bumblebee implements Robot2 {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}