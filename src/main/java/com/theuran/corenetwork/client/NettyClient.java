package com.theuran.corenetwork.client;

import com.theuran.corenetwork.Dispatcher;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

@SideOnly(Side.CLIENT)
public class NettyClient {
    private Dispatcher dispatcher;
    private String encryptionKey;
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

    public void connect(String host, int port) {
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(this.workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ClientChannel(this.dispatcher, this.encryptionKey));

        bootstrap.connect(host, port).syncUninterruptibly();
        System.out.println("Connected to " + host + ":" + port);
    }
}
