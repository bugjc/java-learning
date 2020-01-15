package com.bugjc.java.basics.io;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

/**
 * socket客户端
 *
 * @author aoki
 * @date 2020/1/13
 **/
@Slf4j
public class Client {

    public static void newClient(){
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            String message = RandomUtil.randomNumbers(4).concat("\n").concat(RandomUtil.randomString(2));
            Thread.sleep(RandomUtil.randomInt(200));
            socket.getOutputStream().write(message.getBytes());
            log.info("客户端发送的消息：{}", message);
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)  {
        for (int i = 0; i < 2; i++) {
            Client.newClient();
        }
    }
}
