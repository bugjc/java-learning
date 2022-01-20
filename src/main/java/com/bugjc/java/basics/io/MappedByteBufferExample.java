package com.bugjc.java.basics.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MappedByteBufferExample {
    public static void main(String[] args) throws IOException {
        // 以读写的方式打开文件通道
        FileChannel fileChannel = new RandomAccessFile(new File("D:\\tmp\\222.txt"), "rw").getChannel();
        // 将整个文件映射到内存
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());

        // 写
        String data = "测试数据 1";
        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将 data 放入 byteBuffer
        byteBuffer.put(data.getBytes());
        //对 byteBuffer 进行反转
        byteBuffer.flip();
        int position = 8;
        // 从当前 mmap 指针的位置写入 4b 的数据
        mappedByteBuffer.put(byteBuffer);
//        // 指定 position 写入 4b 的数据
//        ByteBuffer subBuffer = mappedByteBuffer.slice();
//        subBuffer.position(position);
//        subBuffer.put(byteBuffer);

        // 读
//        position = 8;
//        // 从当前 mmap 指针的位置读取 4b 的数据
//        mappedByteBuffer.get(byteBuffer.get());
//        // 指定 position 读取 4b 的数据
////        subBuffer = mappedByteBuffer.slice();
////        subBuffer.position(position);
////        subBuffer.get(byteBuffer.get());
//        System.out.println(new String(subBuffer.array()));

//        if(buff.hasRemaining()) {
//            byte[] data = new byte[buff.remaining()];
//            buff.get(data);
//            assertEquals("world", new String(data, StandardCharsets.UTF_8));
//        }
    }
}
