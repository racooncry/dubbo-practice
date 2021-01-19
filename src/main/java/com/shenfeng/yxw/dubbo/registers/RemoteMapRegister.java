package com.shenfeng.yxw.dubbo.registers;

import com.alibaba.fastjson.JSON;
import com.shenfeng.yxw.dubbo.framework.URL;

import java.io.*;
import java.util.*;

public class RemoteMapRegister {
    private static Map<String, List<URL>> REGISTER = new HashMap();
    private static final String registerFile = "D:\\temp.txt";

    public static void register(String interfaceName, URL url) {
        List<URL> list = REGISTER.get(interfaceName);
        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }

        list.add(url);
        REGISTER.put(interfaceName, list);
        saveToFile();
    }


    public static List<URL> get(String interfaceName) {
        REGISTER = getFile();
        List<URL> list = REGISTER.get(interfaceName);
        return list;
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream inputStream = new FileInputStream(registerFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Map<String, List<URL>> map = (Map<String, List<URL>>) objectInputStream.readObject();
            System.out.println(JSON.toJSONString(map));
            return map;
        } catch (IOException | ClassNotFoundException e) {
            e.fillInStackTrace();
        }
        return null;
    }

    private static void saveToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(registerFile);
            ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
            objectInputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
