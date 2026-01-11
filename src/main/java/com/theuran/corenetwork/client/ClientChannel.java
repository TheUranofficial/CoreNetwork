package com.theuran.corenetwork.client;

import com.theuran.corenetwork.Dispatcher;
import com.theuran.corenetwork.codec.PacketDecoder;
import com.theuran.corenetwork.codec.PacketEncoder;
import com.theuran.corenetwork.utils.Encryption;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;

@SideOnly(Side.CLIENT)
public class ClientChannel extends ChannelInitializer<SocketChannel> {
    private final Dispatcher dispatcher;
    private final String encryptionKey;

    public ClientChannel(Dispatcher dispatcher, String encryptionKey) {
        this.dispatcher = dispatcher;
        this.encryptionKey = encryptionKey;
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new LengthFieldPrepender(4));

        if (this.encryptionKey != null && !this.encryptionKey.isEmpty()) {
            pipeline.addLast("encryption", new Encryption(this.encryptionKey));
        }

        pipeline.addLast(new PacketDecoder(this.dispatcher));
        pipeline.addLast(new PacketEncoder());
        pipeline.addLast(new ClientHandler(this.dispatcher));

        this.dispatcher.setChannel(channel);
    }
}
