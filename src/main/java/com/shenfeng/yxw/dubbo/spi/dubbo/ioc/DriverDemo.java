package com.shenfeng.yxw.dubbo.spi.dubbo.ioc;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.HashMap;

public class DriverDemo {

    public static void main(String[] args) {


        ExtensionLoader<Driver> extensionLoader =
                ExtensionLoader.getExtensionLoader(Driver.class);
        Driver driver = extensionLoader.getExtension("trucker");

        HashMap<String, String> map = new HashMap<>();
        map.put("carType", "red");
        URL url = new URL("", "", 0, map);
        driver.driverCar(url);

    }
}
