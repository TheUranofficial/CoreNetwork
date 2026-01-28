package com.theuran.messenger;

import com.theuran.corenetwork.client.NettyClient;
import com.theuran.corenetwork.utils.ConnectionChannel;
import com.theuran.corenetwork.utils.Side;
import com.theuran.corenetwork.utils.SideOnly;
import com.theuran.messenger.network.Dispatcher;
import com.theuran.messenger.network.client.PacketHandler;
import com.theuran.messenger.network.packets.ChatMessagePacket;
import io.netty.channel.Channel;

import java.util.Scanner;

@SideOnly(Side.CLIENT)
public class MessengerClient implements Runnable {
    public String username;
    public NettyClient nettyClient;
    public String[] fullAddress;
    public ConnectionChannel channel;

    private static MessengerClient instance;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите никнейм: ");
        this.username = scanner.nextLine();

        System.out.print("Введите полный адрес: ");
        this.fullAddress = scanner.nextLine().split(":");

        this.nettyClient = new NettyClient(new Dispatcher(), PacketHandler.class, "polmateri");
        this.channel = new ConnectionChannel(this.nettyClient.connect(this.fullAddress[0], Integer.parseInt(this.fullAddress[1])).channel());

        while (true) {
            String input = scanner.nextLine();

            this.channel.send(new ChatMessagePacket(input, username));
        }
    }

    public static MessengerClient getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        instance = new MessengerClient();
        instance.run();
    }
}
