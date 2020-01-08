package com.bugjc.java.basics.io;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            String message = "1111\n222";
            socket.getOutputStream().write(message.getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
