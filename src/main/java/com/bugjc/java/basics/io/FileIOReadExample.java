package com.bugjc.java.basics.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * 文件读取
 * @author aoki
 * @date 2022/1/14
 * **/
public class FileIOReadExample {

    public static void main(String[] args) throws IOException {
        String fileName = "D:\\tmp\\111.txt";

        //将二进制文件读入字节数组
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        System.out.println(new String(bytes));

        //将文本文件读入字符串
        String text = Files.readString(Path.of(fileName));
        System.out.println(text);

        //逐行将文本文件读入字符串列表
        List<String> texts = Files.readAllLines(Paths.get(fileName));
        for (String s : texts) {
            System.out.println(s);
        }

        //逐行将文本文件读入字符串流
        Files.lines(Paths.get(fileName))
                .filter(line -> line.contains("测试"))
                .forEach(System.out::println);

    }
}
