package com.shenfeng.yxw.dubbo.protocol.dubbo;

import com.shenfeng.yxw.dubbo.framework.Invocation;
import com.shenfeng.yxw.dubbo.framework.Protocol;
import com.shenfeng.yxw.dubbo.framework.URL;

/**
 * @author yu 2020/11/8.
 */
public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public <T> T send(URL url, Invocation rpcRequest) {
        NettyClient nettyClient = new NettyClient(url);
        return nettyClient.send(rpcRequest);
    }
}
