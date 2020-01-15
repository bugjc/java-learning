package com.bugjc.java.basics.io.nio.handler;

import cn.hutool.core.date.DateUtil;
import com.bugjc.java.basics.io.nio.DateUtils;
import com.bugjc.java.basics.io.nio.NIOServer;
import com.bugjc.java.basics.io.nio.model.MsgPacket;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.SocketChannel;

/**
 * socket 服务端处理程序
 * @author aoki
 * @date 2020/1/14
 * **/
@Slf4j
public class ServerReadHandler extends AbstractReadHandler {
    private NIOServer nioServer;

    public ServerReadHandler(SocketChannel socketChannel, NIOServer nioServer) {
        super(socketChannel);
        this.nioServer = nioServer;
    }

    @Override
    protected synchronized void response(MsgPacket msgPacket) {
        final String content = new String(msgPacket.getData());
        log.info("{} receive content -> {}", DateUtils.now(), content);

        // 模拟耗时操作
        try {
            Thread.sleep(100);
            // 模拟一下回复客户端
            nioServer.response(socketChannel, DateUtil.now() + " Server reply -> " + content);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
