package com.theuran.corenetwork.server;

import com.theuran.corenetwork.Dispatcher;
import com.theuran.corenetwork.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Packet> {
    private final Dispatcher dispatcher;

    public ServerHandler(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Packet packet) {
        System.out.println("Received packet: " + packet.getClass().getSimpleName());
        packet.handle();
    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        System.out.println("Client connected: " + context.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        System.out.println("Client disconnected");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
