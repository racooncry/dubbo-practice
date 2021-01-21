package com.shenfeng.yxw.dubbo.spi.dubbo.ioc.impl;

import org.apache.dubbo.common.URL;

import com.shenfeng.yxw.dubbo.spi.dubbo.ioc.Car;
import com.shenfeng.yxw.dubbo.spi.dubbo.ioc.Driver;

public class Trucker implements Driver {
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public void driverCar(URL url) {
        car.getColor(url);
    }
}
