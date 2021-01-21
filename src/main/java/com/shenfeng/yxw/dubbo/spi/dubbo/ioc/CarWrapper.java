package com.shenfeng.yxw.dubbo.spi.dubbo.ioc;

import com.shenfeng.yxw.dubbo.spi.dubbo.Robot2;
import org.apache.dubbo.common.URL;

public class CarWrapper implements Car {
    private Car car;

    public CarWrapper(Car car) {
        this.car = car;
    }

    @Override
    public void getColor(URL url) {
        System.out.println("begin");
        car.getColor(url);
        System.out.println("end");
    }
}