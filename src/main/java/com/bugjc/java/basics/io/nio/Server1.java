package com.bugjc.java.basics.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * socket服务端
 * 等待连接时和等待数据时不阻塞
 *
 * @author aoki
 * @date 2020/1/13
 **/
@Slf4j
public class Server1 {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            //Java为非阻塞设置的类
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    //表示没人连接
                    log.info("正在等待客户端请求连接...");
                    Thread.sleep(5000);
                } else {
                    log.info("当前接收到客户端请求连接...");
                }
                if (socketChannel != null) {
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    byteBuffer.flip();//切换模式  写-->读
                    int effective = socketChannel.read(byteBuffer);
                    if (effective != 0) {
                        String content = StandardCharsets.UTF_8.decode(byteBuffer).toString();
                        log.info(content);
                    } else {
                        log.info("当前未收到客户端消息");
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            log.error("异常信息：{}", e.getMessage());
        }
    }
}
