package com.theuran.corenetwork;

import com.theuran.corenetwork.client.NettyClient;

public class ClientMain {
    public static void main(String[] args) {
        new NettyClient(new Dispatcher()).connect(1488);
    }
}
