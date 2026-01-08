package com.theuran.corenetwork;

import com.theuran.corenetwork.server.NettyServer;

public class Entry {
    public static void main(String[] args) {
        System.out.println("Starting up the server on port 1488.....");

        new NettyServer(new Dispatcher()).startup(1488);

        System.out.println("TCP server started on port 1488");
    }
}
