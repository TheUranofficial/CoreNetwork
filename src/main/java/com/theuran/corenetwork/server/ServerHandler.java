package com.theuran.corenetwork.server;

import com.theuran.corenetwork.Dispatcher;
import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.packet.PacketContext;
import com.theuran.corenetwork.utils.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandler {
    public ServerHandler(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Packet packet) {
        System.out.println("Received packet: " + packet.getClass().getSimpleName());

        this.dispatcher.handlePacket(packet, new PacketContext(this.dispatcher));
    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        System.out.println("Client connected: " + context.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        System.out.println("Client disconnected");
    }
}