package com.bugjc.java.basics.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


/**
 * 文件写入
 * @author aoki
 * @date 2022/1/14
 * **/
public class FileIOWriteExample2 {

    public static void main(String[] args) throws IOException {
        String fileName = "D:\\tmp\\111.txt";

        //使用 FileOutputStream 写入单个字节
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            int b;
            while ((b = process()) != -1) {
                out.write(b);
            }
        }

        //使用 FileOutputStream 写入字节数组
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            byte[] bytes;
            while ((bytes = process1()) != null) {
                out.write(bytes);
            }
        }

        //使用 NIO.2 OutputStream 写入二进制数据
        try (OutputStream out = Files.newOutputStream(Path.of(fileName))) {
            int b;
            while ((b = process2()) != -1) {
                out.write(b);
            }
        }

        //使用 BufferedOutputStream 更快地写入
        try (FileOutputStream out = new FileOutputStream(fileName);
             BufferedOutputStream bout = new BufferedOutputStream(out)) {
            int b;
            while ((b = process3()) != -1) {
                bout.write(b);
            }
        }

        //使用 BufferedOutputStream 写入字节数组
        try (FileOutputStream out = new FileOutputStream(fileName);
             BufferedOutputStream bout = new BufferedOutputStream(out)) {
            byte[] bytes;
            while ((bytes = process4()) != null) {
                bout.write(bytes);
            }
        }

        //使用 FileWriter 编写文本文件
        try (FileOutputStream out = new FileOutputStream(fileName);
             OutputStreamWriter writer = new OutputStreamWriter(out)) {
            int c;
            while ((c = process5()) != -1) {
                writer.write(c);
            }
        }

//        try (FileWriter writer = new FileWriter(fileName)) {
//            int c;
//            while ((c = process5()) != -1) {
//                writer.write(c);
//            }
//        }

        //使用 BufferedWriter 更快地写入文本文件
        try (FileWriter writer = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            int c;
            while ((c = process6()) != -1) {
                bufferedWriter.write(c);
            }
        }

        //使用 NIO.2 BufferedWriter 更快地写入文本文件
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(fileName))) {
            int c;
            while ((c = process7()) != -1) {
                bufferedWriter.write(c);
            }
        }

    }

    private static final AtomicInteger COUNT = new AtomicInteger(10){};
    private static final AtomicInteger COUNT1 = new AtomicInteger(10){};
    private static final AtomicInteger COUNT2 = new AtomicInteger(10){};
    private static final AtomicInteger COUNT3 = new AtomicInteger(10){};
    private static final AtomicInteger COUNT4 = new AtomicInteger(10){};
    private static final AtomicInteger COUNT5 = new AtomicInteger(10){};
    private static final AtomicInteger COUNT6 = new AtomicInteger(10){};
    private static final AtomicInteger COUNT7 = new AtomicInteger(10){};

    private static int process() {
        return COUNT.getAndDecrement();
    }

    private static byte[] process1() {
        if (COUNT1.getAndDecrement() == -1){
            return null;
        }
        return ("测试数据 " + COUNT1.get() + "\n").getBytes();
    }

    private static int process2() {
        return COUNT2.getAndDecrement();
    }

    private static int process3() {
        return COUNT3.getAndDecrement();
    }

    private static byte[] process4() {
        if (COUNT4.getAndDecrement() == -1){
            return null;
        }
        return ("测试数据 " + COUNT4.get() + "\n").getBytes();
    }

    private static int process5() {
        return COUNT5.getAndDecrement();
    }

    private static int process6() {
        return COUNT6.getAndDecrement();
    }

    private static int process7() {
        return COUNT7.getAndDecrement();
    }
}
