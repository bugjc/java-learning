package com.bugjc.java.basics.io.nio.handler;

import com.bugjc.java.basics.io.nio.DateUtils;
import com.bugjc.java.basics.io.nio.NIOClient;
import com.bugjc.java.basics.io.nio.model.MsgPacket;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.SocketChannel;

@Slf4j
public class ClientReadHandler extends AbstractReadHandler {
    private NIOClient nioClient;

    public ClientReadHandler(NIOClient nioClient, SocketChannel socketChannel) {
        super(socketChannel);
        this.nioClient = nioClient;
    }

    @Override
    protected void response(MsgPacket msgPacket) {
        String content = new String(msgPacket.getData());
        log.info("{} {} receive content -> {}", DateUtils.now(), nioClient.getName(), content);
    }
}
