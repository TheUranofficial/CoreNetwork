package com.theuran.corenetwork;

import com.theuran.corenetwork.packet.CommonPacket;
import com.theuran.corenetwork.packet.PacketContext;
import com.theuran.corenetwork.utils.ByteSerialize;
import io.netty.buffer.ByteBuf;

public class Test extends CommonPacket {
    public String message;

    public Test() {}

    public Test(String message) {
        this.message = message;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.message = ByteSerialize.readString(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteSerialize.writeString(buf, this.message);
    }

    @Override
    public void handle(PacketContext context) {
        System.out.println("Client says: " + this.message);

        context.send(new Test("Zov online"));
    }

    @Override
    public void handleClient(PacketContext context) {
        System.out.println("Server says: " + this.message);
    }
}