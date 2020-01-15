package com.bugjc.java.basics.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferExample {
    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = new RandomAccessFile(new File("D:\\data\\db.data"), "rw").getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());

        // 写
        byte[] data = new byte[4];
        int position = 8;
        // 从当前 mmap 指针的位置写入 4b 的数据
        mappedByteBuffer.put(data);
        // 指定 position 写入 4b 的数据
        ByteBuffer subBuffer = mappedByteBuffer.slice();
        subBuffer.position(position);
        subBuffer.put(data);

        // 读
        data = new byte[4];
        position = 8;
        // 从当前 mmap 指针的位置读取 4b 的数据
        mappedByteBuffer.get(data);
        // 指定 position 读取 4b 的数据
        subBuffer = mappedByteBuffer.slice();
        subBuffer.position(position);
        subBuffer.get(data);
    }
}
