package com.theuran.corenetwork.server;

import com.theuran.corenetwork.AbstractDispatcher;
import com.theuran.corenetwork.codec.PacketDecoder;
import com.theuran.corenetwork.codec.PacketEncoder;
import com.theuran.corenetwork.utils.ChannelHandler;
import com.theuran.corenetwork.utils.Encryption;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;

@SideOnly(Side.SERVER)
public class ServerChannel extends ChannelInitializer<SocketChannel> {
    private AbstractDispatcher dispatcher;
    private String encryptionKey;
    private Class<? extends ChannelHandler> handler;

    public ServerChannel(AbstractDispatcher dispatcher, Class<? extends ChannelHandler> handler, String encryptionKey) {
        this.dispatcher = dispatcher;
        this.encryptionKey = encryptionKey;
        this.handler = handler;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(
            1048576,
            0,
            4,
            0,
            4
        ));

        if (this.encryptionKey != null && !this.encryptionKey.isEmpty()) {
            pipeline.addLast("encryption", new Encryption(this.encryptionKey));
        }

        pipeline.addLast("decoder", new PacketDecoder(this.dispatcher));
        pipeline.addLast("encoder", new PacketEncoder());
        pipeline.addLast("handler", this.handler.getConstructor(AbstractDispatcher.class).newInstance(this.dispatcher));
    }
}
