package com.theuran.corenetwork;

import com.theuran.corenetwork.client.ClientPacketHandler;
import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.packet.PacketRegistry;
import com.theuran.corenetwork.server.ServerPacketHandler;
import com.theuran.corenetwork.utils.ConnectionChannel;
import com.theuran.corenetwork.utils.Side;

public abstract class AbstractDispatcher {
    private PacketRegistry registry = new PacketRegistry();

    public AbstractDispatcher() {
        this.setup();
    }

    protected abstract void setup();

    public <T extends Packet> void register(Class<T> packet) {
        this.registry.register(packet);
    }

    public void handlePacket(Packet packet, ConnectionChannel channel, Side side) {
        if (packet instanceof ClientPacketHandler && side.isClient()) {
            ((ClientPacketHandler) packet).handleClient(channel);
        }

        if (packet instanceof ServerPacketHandler && side.isServer()) {
            ((ServerPacketHandler) packet).handle(channel);
        }
    }

    public Packet create(String id) {
        return this.registry.create(id);
    }
}
