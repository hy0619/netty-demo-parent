package com.media;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Description TODO
 * @Author Hero
 * @Date 2021/5/14
 * @Version 1.0.0
 */
public class Client {
    private static final String SERVER_HOST = "127.0.0.1";


    public void start(final int beginPort, int nPort) {
        System.out.println("client starting....");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
            }
        });
        int index = 0;
        int port;
        while (!Thread.interrupted()) {
            port = beginPort + index;
            try {
                ChannelFuture channelFuture = bootstrap.connect(SERVER_HOST, port);
                channelFuture.addListener((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        System.out.println("连接失败, 退出!");
                        System.exit(0);
                    }
                });
                channelFuture.get();
            } catch (Exception e) {
            }
            if (++index == nPort) {
                index = 0;
            }
        }
    }





}
