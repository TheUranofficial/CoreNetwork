package com.theuran.messenger.network.client;

import com.theuran.corenetwork.AbstractDispatcher;
import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.utils.ChannelHandler;
import com.theuran.corenetwork.utils.ConnectionChannel;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;
import com.theuran.messenger.MessengerClient;
import com.theuran.messenger.network.packets.JoinToServerPacket;
import io.netty.channel.ChannelHandlerContext;

@SideOnly(Side.CLIENT)
public class PacketHandler extends ChannelHandler {
    public PacketHandler(AbstractDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Packet packet) {
        this.dispatcher.handlePacket(packet, new ConnectionChannel(context.channel()), Side.CLIENT);
    }

    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception {
        ConnectionChannel.send(context, new JoinToServerPacket(MessengerClient.getInstance().username));

        super.channelActive(context);
    }
}