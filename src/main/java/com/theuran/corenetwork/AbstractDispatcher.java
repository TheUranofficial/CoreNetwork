package com.theuran.corenetwork;

import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.packet.PacketRegistry;
import io.netty.channel.Channel;

public abstract class AbstractDispatcher {
    private final PacketRegistry registry = new PacketRegistry();
    private Channel channel;

    public AbstractDispatcher() {
        this.setup();
    }

    protected abstract void setup();

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public <T extends Packet> void register(Class<T> packet) {
        this.registry.register(packet);
    }

    public void send(Packet packet) {
        int index = this.registry.getIndex(packet);

        this.channel.writeAndFlush(new PacketType(index, packet));
    }

    public Packet decode(int index) {
        return this.registry.create(index);
    }

    public static class PacketType {
        private final int index;
        private final Packet packet;

        public PacketType(int index, Packet packet) {
            this.index = index;
            this.packet = packet;
        }

        public int getIndex() {
            return this.index;
        }

        public Packet getPacket() {
            return this.packet;
        }
    }
}
