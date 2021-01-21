package com.shenfeng.yxw.dubbo.spi.dubbo.ioc;


import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Driver {
    public void driverCar(URL url);
}
