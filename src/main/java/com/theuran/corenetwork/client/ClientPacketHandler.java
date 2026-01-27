package com.theuran.corenetwork.client;

import com.theuran.corenetwork.packet.PacketContext;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;

public interface ClientPacketHandler {
    @SideOnly(Side.CLIENT)
    void handleClient(PacketContext context);
}