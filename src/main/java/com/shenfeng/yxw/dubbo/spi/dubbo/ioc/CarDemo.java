package com.shenfeng.yxw.dubbo.spi.dubbo.ioc;

import com.shenfeng.yxw.dubbo.spi.dubbo.Robot2;
import org.apache.dubbo.common.extension.ExtensionLoader;

public class CarDemo {
    public static void main(String[] args) {
        ExtensionLoader<Car> extensionLoader =
                ExtensionLoader.getExtensionLoader(Car.class);
        Car car = extensionLoader.getExtension("black");
        car.getColor(null);

    }
}
