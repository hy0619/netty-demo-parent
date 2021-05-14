package com.media.nettyconnectionsstandalone.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Description netty连接客户端
 * @Author Hero
 * @Date 2021/5/14
 * @Version 1.0.0
 */
public class NettyConnectionClient {

    private static String nettyServerHost = "127.0.0.1" ;
    private static Integer nettyServerPort = 8080;

    public static void main(String[] args) throws Exception {
            start();
    }

    public static void start() throws Exception{
        System.out.println("client starting....");

            while(!Thread.interrupted()){
                EventLoopGroup workerGroup = new NioEventLoopGroup();
                //(1)
                Bootstrap b = new Bootstrap();
                // (2)
                b.group(workerGroup);
                // (3)
                b.channel(NioSocketChannel.class);
                // (4)
                b.option(ChannelOption.SO_KEEPALIVE, true);
                b.handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("initChannel...");
                    }
                });
                ChannelFuture f = b.connect(nettyServerHost, nettyServerPort).sync();
                f.addListener((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        System.out.println("连接失败, 退出!");
                        System.exit(0);
                    }
                });
                //f.get();
            }




    }
}
