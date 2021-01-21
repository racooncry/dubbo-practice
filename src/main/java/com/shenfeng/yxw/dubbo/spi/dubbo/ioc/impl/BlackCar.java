package com.shenfeng.yxw.dubbo.spi.dubbo.ioc.impl;

import com.shenfeng.yxw.dubbo.spi.dubbo.ioc.Car;
import org.apache.dubbo.common.URL;

public class BlackCar implements Car {
    @Override
    public void getColor(URL url) {
        System.out.println("black");
    }
}
