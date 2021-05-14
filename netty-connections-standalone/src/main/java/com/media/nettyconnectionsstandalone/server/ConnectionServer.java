package com.media.nettyconnectionsstandalone.server;

import com.media.nettyconnectionsstandalone.handler.ConnectionCntHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @Description netty demo 连接服务器
 * @Author Hero
 * @Date 2021/5/14
 * @Version 1.0.0
 */
public class ConnectionServer {
    private int port;

    public ConnectionServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // (1)
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // (2)
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    // (3)
                    .channel(NioServerSocketChannel.class)
                    // (4)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ConnectionCntHandler());
                        }
                    })
                    // (5)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // (6)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            // (7)
            ChannelFuture f = b.bind(port).addListener((ChannelFutureListener) future -> {
                System.out.println("bind success in port: " + port);
            }).sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new ConnectionServer(port).run();
    }
}
