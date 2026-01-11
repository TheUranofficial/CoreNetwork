package com.theuran.corenetwork;

import com.theuran.corenetwork.client.NettyClient;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientMain {
    public static void main(String[] args) {
        new NettyClient(new Dispatcher(Side.CLIENT), "mimi").connect("localhost", 1488);
    }
}
