package com.theuran.corenetwork.server;

import com.theuran.corenetwork.packet.PacketContext;

public interface ServerPacketHandler {
    void handle(PacketContext context);
}
