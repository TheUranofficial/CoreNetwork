package com.theuran.corenetwork.server;

import com.theuran.corenetwork.Dispatcher;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    private MultiThreadIoEventLoopGroup bossGroup;
    private MultiThreadIoEventLoopGroup workerGroup;
    private Dispatcher dispatcher;

    public NettyServer(Dispatcher dispatcher) {
        this.bossGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
        this.workerGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
        this.dispatcher = dispatcher;
    }

    public void startup(int port) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap
            .group(this.bossGroup, this.workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 1024)
            .option(ChannelOption.AUTO_CLOSE, true)
            .option(ChannelOption.SO_REUSEADDR, true)
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            .childOption(ChannelOption.TCP_NODELAY, true);

        bootstrap.childHandler(new ServerInitializer(this.dispatcher));
        bootstrap.bind(port).syncUninterruptibly();
        System.out.println("Server started on " + port);
    }
}
