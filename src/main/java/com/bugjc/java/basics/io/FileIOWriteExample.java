package com.bugjc.java.basics.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * 文件写入
 * @author aoki
 * @date 2022/1/14
 * **/
public class FileIOWriteExample {

    public static void main(String[] args) throws IOException {
        String fileName = "D:\\tmp\\111.txt";

        //将二进制文件读入字节数组
        byte[] bytes = "测试数据 1".getBytes();
        Files.write(Paths.get(fileName), bytes);

        //将字符串列表写入文本文件
        String text = "测试数据 2";
        Files.writeString(Paths.get(fileName), text);

        //将字符串列表写入文本文件
        List<String> lines = new ArrayList<>();
        lines.add("测试数据 1");
        lines.add("测试数据 2");
        lines.add("测试数据 3");
        Files.write(Path.of(fileName), lines);

        //将字符串流写入文本文件
        Stream<String> streamLines = Stream.of("测试数据 1", "测试数据 2", "测试数据 3", "测试数据 4");
        Files.write(Path.of(fileName), (Iterable<String>) streamLines::iterator);


    }
}
