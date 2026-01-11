package com.theuran.corenetwork.packet;

import com.theuran.corenetwork.client.ClientPacketHandler;
import com.theuran.corenetwork.server.ServerPacketHandler;

public abstract class CommonPacket extends Packet implements ClientPacketHandler, ServerPacketHandler {
}
