package com.bugjc.java.basics.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            //如果超过这个时间没有新的数据，则不再继续等待数据
            serverSocket.setSoTimeout(10000);
            System.out.println("服务器已启动并监听8080端口");
            while (true) {
                //服务器正在等待连接...
                Socket socket = serverSocket.accept();
                //服务器已接收到连接请求...
                //服务器正在等待数据...
                //创建读取数据的Reader,里面指定了需要的编码类型。
                BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                //读取每一行的数据.注意大部分端口操作都需要交互数据。
                StringBuilder content = new StringBuilder();
                String str;
                while ((str = rd.readLine()) != null) {
                    System.out.println("按行读取数据:" +str);
                    content.append(str);
                }

                //服务器已经接收到数据
                System.out.println("接收到的数据:" + content.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
