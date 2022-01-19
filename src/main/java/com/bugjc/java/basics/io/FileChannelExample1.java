package com.bugjc.java.basics.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * FileChannel
 * @author aoki
 * @date 2022/1/14
 * **/
public class FileChannelExample1 {
    public static void main(String[] args) throws IOException {
        //以读和写的方式打开与文件 111.txt 的连接的通道。
        FileChannel fileChannel = FileChannel.open(Paths.get("D:\\tmp\\111.txt"), StandardOpenOption.READ,
                StandardOpenOption.WRITE);

        // 写
        String data = "测试数据 2 \n";
        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将 data 放入 byteBuffer
        byteBuffer.put(data.getBytes());
        //对 byteBuffer 进行反转
        byteBuffer.flip();
        //将 byteBuffer 中数据写入 FileChannel
        fileChannel.write(byteBuffer);

        // 读
        fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));

    }
}
