package com.theuran.corenetwork;

import com.theuran.corenetwork.server.NettyServer;
import com.theuran.corenetwork.utils.Side;

public class Entry {
    public static void main(String[] args) {
        System.out.println("Starting up the server on port 1488.....");

        new NettyServer(new Dispatcher(Side.SERVER), "mimi").startup(1488);
    }
}
