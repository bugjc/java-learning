package com.bugjc.java.basics.io.nio;

import cn.hutool.core.date.DateUtil;

public class NIOPowerTest {
    private static final int CLIENT_SIZE = 100;

    public static void main(String[] args) {
        NIOClient[] client = new NIOClient[CLIENT_SIZE];
        for(int i = 0; i < client.length; i++) {
            client[i] = new NIOClient("Client" + i, "127.0.0.1", 9000);
            client[i].startup();
            System.out.println(DateUtil.now() + " CLIENT" + i + " startup");
        }
    }
}
