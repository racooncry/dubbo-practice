package com.shenfeng.yxw.dubbo.spi.dubbo.ioc;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI
//@SPI(value = "black")
public interface Car {

    @Adaptive(value = "carType")
    public void getColor(URL url);
}
