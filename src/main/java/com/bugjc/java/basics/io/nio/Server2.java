package com.bugjc.java.basics.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * socket服务端
 * 轮询数据是否准备好
 *
 * @author aoki
 * @date 2020/1/13
 **/
@Slf4j
public class Server2 {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        List<SocketChannel> socketList = new ArrayList<>();
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
                    System.out.println("正在等待客户端请求连接...");
                    Thread.sleep(5000);
                } else {
                    System.out.println("当前接收到客户端请求连接...");
                    socketList.add(socketChannel);
                }
                for (SocketChannel socket : socketList) {
                    socket.configureBlocking(false);
                    int effective = socket.read(byteBuffer);
                    if (effective != 0) {
                        byteBuffer.flip();//切换模式  写-->读
                        String content = StandardCharsets.UTF_8.decode(byteBuffer).toString();
                        System.out.println("接收到消息:" + content);
                        byteBuffer.clear();
                    } else {
                        System.out.println("当前未收到客户端消息");
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
