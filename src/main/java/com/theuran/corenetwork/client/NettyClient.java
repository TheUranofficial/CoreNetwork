package com.theuran.corenetwork.client;

import com.theuran.corenetwork.Dispatcher;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    private final Dispatcher dispatcher;
    private final String encryptionKey;
    private MultiThreadIoEventLoopGroup workerGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());

    public NettyClient(Dispatcher dispatcher) {
        this(dispatcher, null);
    }

    public NettyClient(Dispatcher dispatcher, String encryptionKey) {
        this.dispatcher = dispatcher;
        this.encryptionKey = encryptionKey;

        if (encryptionKey != null && !encryptionKey.isEmpty()) {
            System.out.println("Encryption enabled");
        }
    }

    public void connect(int port) {
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(this.workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ClientInitializer(this.dispatcher, this.encryptionKey));

        bootstrap.connect("localhost", port).syncUninterruptibly();
        System.out.println("Connected to localhost:" + port);
    }
}
