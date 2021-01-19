package com.shenfeng.yxw.dubbo.protocol.dubbo;

import com.alibaba.fastjson.JSONObject;

import com.shenfeng.yxw.dubbo.framework.Invocation;
import com.shenfeng.yxw.dubbo.provider.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接成功!" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端断开连接!" + ctx.channel().remoteAddress());
        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation rpcRequest = JSONObject.parseObject(msg.toString(), Invocation.class);
        if ("heartBeat".equals(rpcRequest.getMethodName())) {
            System.out.println("客户端心跳信息..." + ctx.channel().remoteAddress());
        } else {
            String interfaceName = rpcRequest.getInterfaceName();
            System.out.println("RPC客户端请求接口:" + interfaceName + "   方法名:" + rpcRequest.getMethodName());
            try {
                Class clas = LocalRegister.get(interfaceName);
                Method method = clas.getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
                String result = (String) method.invoke(clas.newInstance(), rpcRequest.getParams());
                ctx.writeAndFlush(result);
            } catch (NoSuchMethodException | IllegalAccessException
                    | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}