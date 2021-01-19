package com.shenfeng.yxw.dubbo.protocol.http;


import com.shenfeng.yxw.dubbo.framework.Invocation;
import com.shenfeng.yxw.dubbo.framework.Protocol;
import com.shenfeng.yxw.dubbo.framework.URL;

/**
 * @author yu 2020/11/8.
 */
public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.starTomcat(url.getHostname(),url.getPort());
    }

    @Override
    public String send(URL url, Invocation rpcRequest) {
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url, rpcRequest);
    }
}
