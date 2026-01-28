package com.theuran.messenger.network.packets;

import com.theuran.corenetwork.packet.CommonPacket;
import com.theuran.corenetwork.utils.ByteSerialize;
import com.theuran.corenetwork.utils.ConnectionChannel;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;
import com.theuran.messenger.MessengerServer;
import io.netty.buffer.ByteBuf;

public class ChatMessagePacket extends CommonPacket {
    public String message;
    public String username;

    public ChatMessagePacket() {}

    public ChatMessagePacket(String message, String username) {
        this.message = message;
        this.username = username;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClient(ConnectionChannel context) {
        System.out.println(this.message.replace("%username%", this.username));
    }

    @Override
    @SideOnly(Side.SERVER)
    public void handle(ConnectionChannel channel) {
        System.out.println("Received chat message from " + this.username + " - " + this.message);

        for (ConnectionChannel connection : MessengerServer.getInstance().clients.values()) {
            connection.send(new ChatMessagePacket("<%username%> " + this.message, username));
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.message = ByteSerialize.readString(buf);
        this.username = ByteSerialize.readString(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteSerialize.writeString(buf, this.message);
        ByteSerialize.writeString(buf, this.username);
    }
}
