package com.theuran.corenetwork.codec;

import com.theuran.corenetwork.AbstractDispatcher;
import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.utils.ByteSerialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    private final AbstractDispatcher dispatcher;

    public PacketDecoder(AbstractDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf byteBuf, List<Object> out) {
        System.out.println(byteBuf);
        if (!byteBuf.isReadable()) {
            return;
        }
        System.out.println(byteBuf);

        int packetId = ByteSerialize.readVarInt(byteBuf);
        Packet packet = this.dispatcher.decode(packetId);

        packet.fromBytes(byteBuf);
        out.add(packet);
    }
}
