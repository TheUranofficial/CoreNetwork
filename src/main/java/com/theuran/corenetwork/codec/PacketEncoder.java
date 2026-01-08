package com.theuran.corenetwork.codec;

import com.theuran.corenetwork.AbstractDispatcher;
import com.theuran.corenetwork.utils.ByteSerialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<AbstractDispatcher.PacketType> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, AbstractDispatcher.PacketType packet, ByteBuf byteBuf) throws Exception {
        ByteSerialize.writeVarInt(byteBuf, packet.getIndex());
        packet.getPacket().toBytes(byteBuf);
    }
}
