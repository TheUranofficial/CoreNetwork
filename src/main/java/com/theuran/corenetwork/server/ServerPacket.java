package com.theuran.corenetwork.server;

import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;

@SideOnly(Side.SERVER)
public abstract class ServerPacket extends Packet implements ServerPacketHandler {
}
