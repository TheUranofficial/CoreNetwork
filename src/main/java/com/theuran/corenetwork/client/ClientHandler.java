package com.theuran.corenetwork.client;

import com.theuran.corenetwork.Dispatcher;
import com.theuran.corenetwork.Test;
import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.packet.PacketContext;
import com.theuran.corenetwork.utils.ChannelHandler;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;
import io.netty.channel.ChannelHandlerContext;

@SideOnly(Side.CLIENT)
public class ClientHandler extends ChannelHandler {
    public ClientHandler(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        System.out.println("Connected to server");

        this.dispatcher.send(new Test("Test from client"));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Packet packet) {
        System.out.println("Received packet: " + packet.getClass().getSimpleName());

        this.dispatcher.handlePacket(packet, new PacketContext(this.dispatcher));
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        System.out.println("Disconnected from server");
    }
}
