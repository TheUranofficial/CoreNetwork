package com.theuran.corenetwork.packet;

import io.netty.buffer.ByteBuf;

public interface Packet {
    void fromBytes(ByteBuf buf);

    void toBytes(ByteBuf buf);

    void handle();
}
