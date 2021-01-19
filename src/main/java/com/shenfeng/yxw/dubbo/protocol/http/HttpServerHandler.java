package com.shenfeng.yxw.dubbo.protocol.http;

import com.shenfeng.yxw.dubbo.framework.Invocation;
import com.shenfeng.yxw.dubbo.provider.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        // 处理业务逻辑
        try {
            ServletInputStream inputStream = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) ois.readObject();


            Class implClass = LocalRegister.get(invocation.getInterfaceName());

            Method classMethod = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());

            String result = (String) classMethod.invoke(implClass.newInstance(), invocation.getParams());

            IOUtils.write(result,resp.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
