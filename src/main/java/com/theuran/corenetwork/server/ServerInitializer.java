package com.theuran.corenetwork.server;

import com.theuran.corenetwork.Dispatcher;
import com.theuran.corenetwork.codec.PacketDecoder;
import com.theuran.corenetwork.codec.PacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    private final Dispatcher dispatcher;

    public ServerInitializer(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new LengthFieldBasedFrameDecoder(
            1048576,
            0,
            4,
            0,
            4
        ));

        pipeline.addLast(new PacketDecoder(this.dispatcher));
        pipeline.addLast(new PacketEncoder());
        pipeline.addLast(new ServerHandler(this.dispatcher));

        this.dispatcher.setChannel(channel);
    }
}
