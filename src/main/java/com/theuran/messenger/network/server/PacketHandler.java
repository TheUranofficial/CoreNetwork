package com.theuran.messenger.network.server;

import com.theuran.corenetwork.AbstractDispatcher;
import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.utils.ChannelHandler;
import com.theuran.corenetwork.utils.ConnectionChannel;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;
import com.theuran.messenger.MessengerServer;
import com.theuran.messenger.network.packets.ChatMessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.io.IOException;

@SideOnly(Side.SERVER)
public class PacketHandler extends ChannelHandler {
    public PacketHandler(AbstractDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Packet packet) {
        this.dispatcher.handlePacket(packet, new ConnectionChannel(context.channel()), Side.SERVER);
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) throws Exception {
        String username = context.channel().attr(AttributeKey.<String>valueOf("username")).get();

        if (username != null) {
            MessengerServer.getInstance().clients.remove(username);
            
            for (ConnectionChannel connection : MessengerServer.getInstance().clients.values()) {
                connection.send(new ChatMessagePacket("%username% disconnected from messaging", username));
            }
        }

        System.out.println(context.channel().remoteAddress() + " disconnected from server");

        super.channelInactive(context);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        if (!(cause instanceof IOException)) {
            cause.printStackTrace();
        }
        context.close();
    }
}
