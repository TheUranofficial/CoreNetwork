package com.theuran.messenger;

import com.theuran.corenetwork.server.NettyServer;
import com.theuran.corenetwork.utils.ConnectionChannel;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;
import com.theuran.messenger.network.Dispatcher;
import com.theuran.messenger.network.server.PacketHandler;

import java.util.HashMap;

@SideOnly(Side.SERVER)
public class MessengerServer implements Runnable {
    public HashMap<String, ConnectionChannel> clients;
    public NettyServer nettyServer;

    private static MessengerServer instance;

    @Override
    public void run() {
        this.nettyServer = new NettyServer(new Dispatcher(), PacketHandler.class, "polmateri");
        this.nettyServer.startup(1337);

        this.clients = new HashMap<>();
    }

    public static MessengerServer getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        instance = new MessengerServer();
        instance.run();
    }
}
