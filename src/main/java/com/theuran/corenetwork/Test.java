package com.theuran.corenetwork;

import com.theuran.corenetwork.packet.Packet;
import com.theuran.corenetwork.utils.ByteSerialize;
import io.netty.buffer.ByteBuf;

public class Test implements Packet {
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
    public void handle() {
        System.out.println(this.message);
    }
}
