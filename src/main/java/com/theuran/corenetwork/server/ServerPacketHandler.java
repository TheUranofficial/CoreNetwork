package com.theuran.corenetwork.server;

import com.theuran.corenetwork.utils.ConnectionChannel;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;

public interface ServerPacketHandler {
    @SideOnly(Side.SERVER)
    void handle(ConnectionChannel channel);
}
