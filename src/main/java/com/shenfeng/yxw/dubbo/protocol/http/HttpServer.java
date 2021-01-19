package com.shenfeng.yxw.dubbo.protocol.http;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {
    public void starTomcat(String hostName, Integer port) {
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        // 构建Connector对象,此对象负责与客户端的连接.
        Connector connector = new Connector("HTTP/1.1");
        // 设置服务端的监听端口
        connector.setPort(port);

        StandardEngine engine = new StandardEngine();
        engine.setDefaultHost(hostName);

        StandardHost standardHost = new StandardHost();
        standardHost.setName(hostName);

        String contextPath = "";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        standardHost.addChild(context);

        engine.addChild(standardHost);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(
                contextPath,
                "dispatcher",
                new DispatcherServlet());

        context.addServletMappingDecoded("/*", "dispatcher");


        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

        tomcat.getServer().await();
    }

    public static void main(String[] args) throws LifecycleException {
        // starTomcat("localhost", 8080);
    }
}
