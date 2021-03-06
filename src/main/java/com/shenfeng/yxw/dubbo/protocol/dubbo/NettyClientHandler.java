package com.shenfeng.yxw.dubbo.protocol.dubbo;

import com.alibaba.fastjson.JSONObject;

import com.shenfeng.yxw.dubbo.framework.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private Object response;

    private ChannelHandlerContext context;//上下文

    private Invocation param;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("已经连上服务端");
        this.context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到服务端的数据：" + msg);
        this.response = msg;
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 设置参数
     */
    void setParam(Invocation param) {
        this.param = param;
    }
    /**
     * 被代理对象调用，发送数据给服务器 -> wait -> 等待被唤醒(channelRead) -> 返回结果
     */
    @Override
    public synchronized Object call() throws Exception {
        String rpcParam = JSONObject.toJSONString(param);
        System.out.println("客服端发送数据：" + rpcParam);
        context.writeAndFlush(rpcParam);
        // 进行 wait,等待channelRead获取到服务器的结果
        wait();
        // 返回结果
        return response;
    }
}