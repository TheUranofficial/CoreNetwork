package com.theuran.corenetwork.packet;

import java.util.ArrayList;
import java.util.List;

public class PacketRegistry {
    private final List<Class<? extends Packet>> packets = new ArrayList<>();

    public <T extends Packet> void register(Class<T> packet) {
        this.packets.add(packet);
    }

    public int getIndex(Packet packet) {
        return this.packets.indexOf(packet.getClass());
    }

    public Packet create(int index) {
        try {
            return this.packets.get(index).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}