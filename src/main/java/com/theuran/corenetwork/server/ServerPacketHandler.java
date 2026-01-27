package com.theuran.corenetwork.server;

import com.theuran.corenetwork.packet.PacketContext;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;

public interface ServerPacketHandler {
    @SideOnly(Side.SERVER)
    void handle(PacketContext context);
}
