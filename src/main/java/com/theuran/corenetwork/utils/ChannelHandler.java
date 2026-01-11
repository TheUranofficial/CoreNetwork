package com.theuran.corenetwork.utils;

import com.theuran.corenetwork.AbstractDispatcher;
import com.theuran.corenetwork.Dispatcher;
import com.theuran.corenetwork.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class ChannelHandler extends SimpleChannelInboundHandler<Packet> {
    protected AbstractDispatcher dispatcher;

    public ChannelHandler(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
