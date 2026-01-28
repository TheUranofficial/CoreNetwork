package com.theuran.messenger.network;

import com.theuran.corenetwork.AbstractDispatcher;
import com.theuran.messenger.network.packets.ChatMessagePacket;
import com.theuran.messenger.network.packets.JoinToServerPacket;

public class Dispatcher extends AbstractDispatcher {
    @Override
    protected void setup() {
        this.register(JoinToServerPacket.class);
        this.register(ChatMessagePacket.class);
    }
}