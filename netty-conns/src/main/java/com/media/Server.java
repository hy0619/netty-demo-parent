package com.media;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 服务端
 * @Author Hero
 * @Date 2021/5/14
 * @Version 1.0.0
 */
@Slf4j
public class Server {

    public void start(int port) {
        log.info("server starting....");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childHandler(new ConnectionCountHandler());
        bootstrap.bind(port).addListener((ChannelFutureListener) future -> {
            log.info("bind success in port: " + port);
        });
        log.info("server started!");
    }



}
