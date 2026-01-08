package com.theuran.corenetwork.client;

import com.theuran.corenetwork.Dispatcher;
import com.theuran.corenetwork.Test;
import com.theuran.corenetwork.packet.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.channels.SocketChannel;

public class ClientHandler extends SimpleChannelInboundHandler<Packet> {
    private final Dispatcher dispatcher;

    public ClientHandler(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        System.out.println("Connected to server");

        this.dispatcher.send(new Test("Test from client"));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Packet packet) {
        System.out.println("Received packet: " + packet.getClass().getSimpleName());
        packet.handle();
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        System.out.println("Disconnected from server");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
