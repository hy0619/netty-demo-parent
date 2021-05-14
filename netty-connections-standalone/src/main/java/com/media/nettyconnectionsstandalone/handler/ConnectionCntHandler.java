package com.media.nettyconnectionsstandalone.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

/**
 * @Description 连接次数处理器
 * @Author Hero
 * @Date 2021/5/14
 * @Version 1.0.0
 */
public class ConnectionCntHandler  extends ChannelInboundHandlerAdapter {


    public ConnectionCntHandler(){

    }


    /**
     * @description: 建立连接 +1
     * @param ctx
     * @return: void
     * @author: Hero
     * @date: 2021/5/14
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress socket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = socket.getAddress().getHostAddress();
        String clientPort = String.valueOf(socket.getPort());
        System.out.println(clientIP + "::" + clientPort);
        ConnRecord.getInstance().increase();
    }

    /**
     * 连接断开的时候减一
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ConnRecord.getInstance().decrease();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }



}
