package com.theuran.messenger.network.packets;

import com.theuran.corenetwork.packet.CommonPacket;
import com.theuran.corenetwork.utils.ByteSerialize;
import com.theuran.corenetwork.utils.ConnectionChannel;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;
import com.theuran.messenger.MessengerServer;
import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeKey;

public class JoinToServerPacket extends CommonPacket {
    public String username;

    public JoinToServerPacket() {}

    public JoinToServerPacket(String username) {
        this.username = username;
    }

    @Override
    @SideOnly(Side.SERVER)
    public void handle(ConnectionChannel channel) {
        System.out.println("Connected client: " + channel.getChannel().remoteAddress() + " with username: " + this.username);

        channel.getChannel().attr(AttributeKey.<String>valueOf("username")).set(this.username);
        MessengerServer.getInstance().clients.put(this.username, channel);

        for (ConnectionChannel connection : MessengerServer.getInstance().clients.values()) {
            connection.send(new JoinToServerPacket(this.username));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClient(ConnectionChannel channel) {
        System.out.println(this.username + " joined to the messaging");
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.username = ByteSerialize.readString(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteSerialize.writeString(buf, this.username);
    }
}
