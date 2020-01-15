package com.bugjc.java.basics.io.bio;

import com.bugjc.java.basics.ThreadPoolExecutorUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

/**
 * socket服务端
 *
 * @author aoki
 * @date 2020/1/13
 **/
@Slf4j
public class Server {
    public static void main(String[] args) {

        try {
            //创建 socket 服务并绑定 8080端口
            ServerSocket serverSocket = new ServerSocket(8080);
            //如果超过这个时间没有新的数据，则不再继续等待数据
            serverSocket.setSoTimeout(10000);
            log.info("服务器已启动并监听8080端口");
            while (true) {
                log.info("(阻塞1)服务器正在等待连接...");
                Socket socket = serverSocket.accept();
                //使用线程池执行连接请求
                ThreadPoolExecutorUtil.execute(() -> {
                    log.info("服务器已接收到连接请求...");
                    log.info("(阻塞2)服务器正在等待数据...");
                    //创建读取数据的Reader,里面指定了需要的编码类型。
                    BufferedReader rd = null;
                    StringBuilder content = new StringBuilder();
                    try {
                        rd = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                        //读取每一行的数据.
                        String str;
                        while ((str = rd.readLine()) != null) {
                            content.append(str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //服务器已经接收到数据
                    log.info("接收到的数据:" + content.toString());
                });
            }
        } catch (SocketTimeoutException e) {
            log.error("设定超时时间内没有接收到连接！异常信息：{}", e.getMessage());
            ThreadPoolExecutorUtil.shutdown();
        } catch (IOException ignore) {
        }
    }
}
