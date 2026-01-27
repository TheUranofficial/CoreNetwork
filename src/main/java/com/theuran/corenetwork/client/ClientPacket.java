package com.theuran.corenetwork.client;

import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientPacket extends Packet implements ClientPacketHandler {
}
