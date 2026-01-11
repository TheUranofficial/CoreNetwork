package com.theuran.corenetwork.client;

import com.theuran.corenetwork.packet.PacketContext;

public interface ClientPacketHandler {
    void handleClient(PacketContext context);
}